package de.tim0_12432.f1_schedule_app.ui.race;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultStatus;
import de.tim0_12432.f1_schedule_app.utility.Logger;
import de.tim0_12432.f1_schedule_app.utility.Speed;

public class RaceAdapter extends RecyclerView.Adapter<RaceAdapter.ViewHolder> {
    private final List<RaceResult> resultList;
    private final FastestDriver fastestDriver;

    public RaceAdapter(List<RaceResult> items) {
        resultList = items;
        Comparator<RaceResult> comparator = new Comparator<RaceResult>() {
            @Override
            public int compare(RaceResult o1, RaceResult o2) {
                String time1 = o1.getFastestLapTime();
                String time2 = o2.getFastestLapTime();
                if (time1 == null) {
                    return 1;
                } else if (time2 == null) {
                    return -1;
                } else if (time1.equals(time2)) {
                    return 0;
                } else {
                    SimpleDateFormat parser = new SimpleDateFormat("mm:ss.SSS", Locale.ROOT);
                    try {
                        Date date1 = parser.parse(time1);
                        Date date2 = parser.parse(time2);
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        Logger.log(Logger.LogLevel.ERROR, "'" + time1 + "' or '" + time2 + "' could not be parsed!");
                        return 0;
                    }
                }
            }
        };
        RaceResult fastestResult = Collections.min(resultList, comparator);
        fastestDriver = new FastestDriver(fastestResult.getDriver().getCode(), fastestResult.getFastestLapTime(), fastestResult.getFastestLapSpeed());
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    private String getDriverText(Driver driver) {
        if (driver == null) {
            return "";
        } else if (driver.getNationality() == null && driver.getName() != null && driver.getFamilyName() != null) {
            return driver.getName() + " " + driver.getFamilyName() + " " + Nationality.DEFAULT.getEmojiFlag();
        } else if (driver.getNationality() != null && (driver.getName() == null || driver.getFamilyName() == null)) {
            return MainActivity.getAppResources().getString(R.string.nationality_default) + " " + driver.getNationality().getEmojiFlag();
        } else if (driver.getNationality() == null && (driver.getName() == null || driver.getFamilyName() == null)) {
            return MainActivity.getAppResources().getString(R.string.nationality_default) + " " + Nationality.DEFAULT.getEmojiFlag();
        }
        return driver.getName() + " " + driver.getFamilyName() + " " + driver.getNationality().getEmojiFlag();
    }

    private String getGainText(RaceResult result) {
        int grid = result.getGrid();
        int position = result.getPosition();

        int gain = position - grid;
        if (gain < 0) {
            return "\uD83D\uDD3A" + Math.abs(gain);
        } else if (gain == 0) {
            return "➖";
        } else {
            return "\uD83D\uDD3B" + gain;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_driver_result, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RaceResult result = resultList.get(position);

        holder.driverName.setText(getDriverText(result.getDriver()));
        ConstructorAttr team = ConstructorAttr.getConstructorOfTeam(result.getTeam());
        holder.driverTeam.setText(team.name().substring(0, 4));
        holder.driverTeam.setTextColor(team.getColor());
        holder.driverPosition.setText(String.valueOf(result.getPosition()));
        holder.driverNumber.setText(String.valueOf(result.getNumber()));
        holder.driverGain.setText(getGainText(result));

        if (result.getStatus() != null) {
            holder.driverStatus.setText(result.getStatus().getEmoji());
            holder.driverStatus.setTooltipText(result.getStatus().getText());
        } else {
            holder.driverStatus.setText(RaceResultStatus.DEFAULT.getEmoji());
            holder.driverStatus.setTooltipText(RaceResultStatus.DEFAULT.getText());
        }

        if (fastestDriver.getCode().equals(result.getDriver().getCode())) {
            holder.driverIcon.setColorFilter(MainActivity.getAppResources().getColor(R.color.purple_purpureus), android.graphics.PorterDuff.Mode.SRC_IN);
            holder.driverIcon.setTooltipText(fastestDriver.getTime() + " (" + Speed.convertMphToKmh(fastestDriver.getSpeed()) + "kmh)");
        } else {
            holder.driverIcon.setVisibility(View.GONE);
        }
    }

    static class FastestDriver {
        private final String code;
        private final String time;
        private final String speed;

        FastestDriver(String code, String time, String speed) {
            this.code = code;
            this.time = time;
            this.speed = speed;
        }

        String getCode() {
            return code;
        }

        String getTime() {
            return time;
        }

        String getSpeed() {
            return speed;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView driverName;
        private final TextView driverGain;
        private final TextView driverNumber;
        private final TextView driverPosition;
        private final TextView driverStatus;
        private final TextView driverTeam;
        private final ImageView driverIcon;

        ViewHolder(View row) {
            super(row);
            this.driverName = row.findViewById(R.id.driver_result_name);
            this.driverGain = row.findViewById(R.id.driver_result_gain);
            this.driverNumber = row.findViewById(R.id.driver_result_number);
            this.driverPosition = row.findViewById(R.id.driver_result_position);
            this.driverStatus = row.findViewById(R.id.driver_result_status);
            this.driverTeam = row.findViewById(R.id.driver_result_team);
            this.driverIcon = row.findViewById(R.id.driver_result_fastest_lap);
        }
    }
}
