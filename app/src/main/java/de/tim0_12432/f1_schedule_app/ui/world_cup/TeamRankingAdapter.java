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
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class TeamRankingAdapter extends ArrayAdapter<ConstructorStanding> {
    private final List<ConstructorStanding> constructorStandings;

    private final int layoutId;

    public TeamRankingAdapter(Context context, int layoutId, List<ConstructorStanding> items) {
        super(context, layoutId, items);
        constructorStandings = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return constructorStandings.size();
    }

    @Override
    public ConstructorStanding getItem(int index) {
        return constructorStandings.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
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

        TeamRankingAdapter.ViewHolder holder = (TeamRankingAdapter.ViewHolder) row.getTag();
        if (holder == null) {
            holder = new TeamRankingAdapter.ViewHolder(row);
            row.setTag(holder);
        }

        ConstructorStanding team = constructorStandings.get(position);
        Logger.log(Logger.LogLevel.DEBUG, team.toString());

        ConstructorAttr infos = ConstructorAttr.getConstructorOfTeam(team.getConstructor());

        holder.teamName.setText(infos.getName() + " " + infos.getNationality().getEmojiFlag());
        holder.teamName.setTextColor(infos.getColor());
        holder.engineName.setText("\uD83D\uDD29 " + infos.getEngine().name());
        holder.position.setText(getPositionText(team.getPosition()));
        holder.points.setText(String.valueOf(team.getPoints()));
        holder.wins.setText("\uD83C\uDFC6 " + team.getWins());

        return row;
    }

    static class ViewHolder {
        TextView teamName;
        TextView engineName;
        TextView points;
        TextView wins;
        TextView position;

        public ViewHolder(View row) {
            engineName = row.findViewById(R.id.team_engine);
            teamName = row.findViewById(R.id.team_name);
            points = row.findViewById(R.id.team_points);
            wins = row.findViewById(R.id.team_wins);
            position = row.findViewById(R.id.team_position);
        }
    }
}