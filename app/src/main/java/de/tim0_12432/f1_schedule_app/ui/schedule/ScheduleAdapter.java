package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.SprintRace;
import de.tim0_12432.f1_schedule_app.utility.DateTime;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class ScheduleAdapter extends ArrayAdapter<Race> {
    private final List<Race> raceList;

    private final int layoutId;

    public ScheduleAdapter(Context context, int layoutId, List<Race> items) {
        super(context, layoutId, items);
        raceList = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return raceList.size();
    }

    @Override
    public Race getItem(int index) {
        return raceList.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    private String getWinnerText(Driver winner) {
        if (winner == null) {
            return "";
        } else if (winner.getNationality() == null && winner.getCode() != null) {
            return Nationality.DEFAULT.getEmojiFlag() + " " + winner.getCode();
        } else if (winner.getNationality() != null && winner.getCode() == null) {
            return winner.getNationality().getEmojiFlag() + " NaN";
        } else if (winner.getNationality() == null && winner.getCode() == null) {
            return Nationality.DEFAULT.getEmojiFlag() + " NaN";
        }
        return winner.getNationality().getEmojiFlag() + " " + winner.getCode();
    }

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row = inflater.inflate(layoutId, parent, false);
        }

        ViewHolder holder = (ViewHolder) row.getTag();
        if (holder == null) {
            holder = new ViewHolder(row);
            row.setTag(holder);
        }

        Race race = raceList.get(position);
        Logger.log(Logger.LogLevel.DEBUG, race.toString());

        String raceName = race.getName();
        if (Arrays.stream(SprintRace.getSprintsByYear(race.getSeason()).getNumbers()).anyMatch(r -> r == race.getRound())) {
            raceName += " \uD83C\uDFC3";
        }
        holder.raceName.setText(raceName);
        holder.raceRound.setText(String.valueOf(race.getRound()));
        holder.raceDate.setText("\uD83D\uDDD3 " + DateTime.getDatestamp(race.getDate()));
        holder.raceTime.setText("\uD83D\uDD51 " + DateTime.getTimestamp(race.getTime()));

        if (race.getResults() != null) {
            List<RaceResult> results = race.getResults().getResults();
            if (results.size() > 0 && results.get(0).getPosition() == 1) {
                Driver winner = results.get(0).getDriver();
                holder.raceWinner.setText(getWinnerText(winner));
            } else {
                Driver winner = results.stream().filter(r -> r.getPosition() == 1).map(r -> r.getDriver()).findFirst().orElse(null);
                holder.raceWinner.setText(getWinnerText(winner));
            }
        } else {
            holder.raceWinner.setText("");
        }

        return row;
    }

    static class ViewHolder {

        TextView raceName;
        TextView raceRound;
        TextView raceDate;
        TextView raceTime;
        TextView raceWinner;

        ViewHolder(View row) {
            this.raceName = row.findViewById(R.id.race_name);
            this.raceRound = row.findViewById(R.id.race_round);
            this.raceDate = row.findViewById(R.id.race_date);
            this.raceTime = row.findViewById(R.id.race_time);
            this.raceWinner = row.findViewById(R.id.race_winner);
        }
    }
}
