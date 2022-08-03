package de.tim0_12432.f1_schedule_app.ui.circuit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class CircuitsAdapter extends ArrayAdapter<Circuit> {
    private final List<Circuit> circuitList;

    private final int layoutId;

    public CircuitsAdapter(Context context, int layoutId, List<Circuit> items) {
        super(context, layoutId, items);
        circuitList = items;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return circuitList.size();
    }

    @Override
    public Circuit getItem(int index) {
        return circuitList.get(index);
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            row = inflater.inflate(layoutId, parent, false);
        }

        CircuitsAdapter.ViewHolder holder = (CircuitsAdapter.ViewHolder) row.getTag();
        if (holder == null) {
            holder = new CircuitsAdapter.ViewHolder(row);
            row.setTag(holder);
        }

        Circuit circuit = circuitList.get(position);
        Logger.log(Logger.LogLevel.DEBUG, circuit.toString());

        holder.circuitName.setText(circuit.getName());
        holder.circuitLocation.setText("\uD83D\uDCCD " + circuit.getLocation().getLocality());
        holder.circuitCountry.setText(Nationality.getNationalityOfCountry(circuit.getLocation().getCountry()).getEmojiFlag());

        return row;
    }

    static class ViewHolder {

        TextView circuitName;
        TextView circuitLocation;
        TextView circuitCountry;

        ViewHolder(View row) {
            this.circuitName = row.findViewById(R.id.circuit_name);
            this.circuitLocation = row.findViewById(R.id.circuit_locality);
            this.circuitCountry = row.findViewById(R.id.circuit_country);
        }
    }
}
