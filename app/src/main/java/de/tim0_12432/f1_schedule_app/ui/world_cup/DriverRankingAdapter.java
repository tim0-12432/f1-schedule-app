package de.tim0_12432.f1_schedule_app.ui.world_cup;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class DriverRankingAdapter extends ArrayAdapter<DriverStanding> {
    private final List<DriverStanding> driverStandings;

    private final int layoutId;

    public DriverRankingAdapter(Context context, int layoutId, List<DriverStanding> items) {
        super(context, layoutId, items);
        driverStandings = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return driverStandings.size();
    }

    @Override
    public DriverStanding getItem(int index) {
        return driverStandings.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
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

    private String getPositionText(int position) {
        switch (position) {
            case 1:
                return "\uD83E\uDD47";
            case 2:
                return "\uD83E\uDD48";
            case 3:
                return "\uD83E\uDD49";
            default:
                return String.valueOf(position);
        }
    }

    @NonNull
    @Override
    @SuppressLint("SetTextI18n")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row = inflater.inflate(layoutId, parent, false);
        }

        DriverRankingAdapter.ViewHolder holder = (DriverRankingAdapter.ViewHolder) row.getTag();
        if (holder == null) {
            holder = new DriverRankingAdapter.ViewHolder(row);
            row.setTag(holder);
        }

        DriverStanding driver = driverStandings.get(position);
        Logger.log(Logger.LogLevel.DEBUG, driver.toString());

        holder.driverName.setText(getDriverText(driver.getDriver()));
        ConstructorAttr team = ConstructorAttr.getConstructorOfTeam(driver.getConstructor());
        holder.teamName.setText("\uD83D\uDC65 " + team.name().substring(0, 3));
        holder.teamName.setTextColor(team.getColor());
        holder.position.setText(getPositionText(driver.getPosition()));
        holder.points.setText(String.valueOf(driver.getPoints()));
        holder.wins.setText("\uD83C\uDFC6 " + driver.getWins());

        return row;
    }

    static class ViewHolder {
        TextView driverName;
        TextView teamName;
        TextView points;
        TextView wins;
        TextView position;

        public ViewHolder(View row) {
            driverName = row.findViewById(R.id.driver_name);
            teamName = row.findViewById(R.id.driver_team);
            points = row.findViewById(R.id.driver_points);
            wins = row.findViewById(R.id.driver_wins);
            position = row.findViewById(R.id.driver_position);
        }
    }
}