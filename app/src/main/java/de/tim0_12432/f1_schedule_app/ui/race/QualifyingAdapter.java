package de.tim0_12432.f1_schedule_app.ui.race;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;

public class QualifyingAdapter extends RecyclerView.Adapter<QualifyingAdapter.ViewHolder> {
    private final List<QualifyingResult> resultList;

    public QualifyingAdapter(List<QualifyingResult> items) {
        resultList = items;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_qualifying_result, parent, false);

        return new ViewHolder(view);
    }

    @Override
    @SuppressLint("SetTextI18n")
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QualifyingResult result = resultList.get(position);

        holder.driverName.setText(getDriverText(result.getDriver()));
        ConstructorAttr team = ConstructorAttr.getConstructorOfTeam(result.getTeam());
        holder.driverTeam.setText(team.name().substring(0, 3));
        holder.driverTeam.setTextColor(team.getColor());
        holder.driverPosition.setText(String.valueOf(result.getPosition()));
        holder.driverNumber.setText(String.valueOf(result.getNumber()));
        holder.driverRound.setText("Q" + result.getQualifyingRound());
        holder.driverTime.setText(result.getFastestLapTime());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView driverName;
        private final TextView driverRound;
        private final TextView driverNumber;
        private final TextView driverPosition;
        private final TextView driverTime;
        private final TextView driverTeam;

        ViewHolder(View row) {
            super(row);
            this.driverName = row.findViewById(R.id.driver_qualifying_name);
            this.driverRound = row.findViewById(R.id.driver_qualifying_round);
            this.driverNumber = row.findViewById(R.id.driver_qualifying_number);
            this.driverPosition = row.findViewById(R.id.driver_qualifying_position);
            this.driverTime = row.findViewById(R.id.driver_qualifying_time);
            this.driverTeam = row.findViewById(R.id.driver_qualifying_team);
        }
    }
}
