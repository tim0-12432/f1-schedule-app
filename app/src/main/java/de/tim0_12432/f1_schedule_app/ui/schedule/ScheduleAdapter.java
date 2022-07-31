package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.Utility;
import de.tim0_12432.f1_schedule_app.data.entity.Race;

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

        holder.raceName.setText(race.getName());
        holder.raceRound.setText(String.valueOf(race.getRound()));
        holder.raceDate.setText(Utility.getDatestamp(race.getDate()));
        holder.raceTime.setText(Utility.getTimestamp(race.getTime()));

        return row;
    }

    static class ViewHolder {

        TextView raceName;
        TextView raceRound;
        TextView raceDate;
        TextView raceTime;

        ViewHolder(View row) {
            this.raceName = row.findViewById(R.id.race_name);
            this.raceRound = row.findViewById(R.id.race_round);
            this.raceDate = row.findViewById(R.id.race_date);
            this.raceTime = row.findViewById(R.id.race_time);
        }
    }
}
