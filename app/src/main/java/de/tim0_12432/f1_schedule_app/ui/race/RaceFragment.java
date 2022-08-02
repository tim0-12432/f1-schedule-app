package de.tim0_12432.f1_schedule_app.ui.race;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;
import de.tim0_12432.f1_schedule_app.databinding.FragmentRaceScreenBinding;
import de.tim0_12432.f1_schedule_app.utility.DateTime;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class RaceFragment extends Fragment {

    private FragmentRaceScreenBinding binding;

    private Race race;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            race = (Race) getArguments().getSerializable("raceObj");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRaceScreenBinding.inflate(inflater, container, false);

        if (race != null) {
            setText(binding.raceScreenCircuitName, R.string.circuit, race.getCircuit().getName());
            setText(binding.raceScreenDate, R.string.date, DateTime.getDatestamp(race.getDate()));
            setText(binding.raceScreenTime, R.string.time, DateTime.getTimestamp(race.getTime()));

            RaceResultList resultList = race.getResults();
            if (resultList != null) {
                List<RaceResult> results = resultList.getResults();
                setText(binding.raceScreenWinner1, "\uD83E\uDD47", ' ',
                        results.get(0).getDriver().getCode() + " "
                                + results.get(0).getDriver().getNationality().getEmojiFlag());
                setText(binding.raceScreenWinner2, "\uD83E\uDD48", ' ',
                        results.get(1).getDriver().getCode() + " "
                                + results.get(1).getDriver().getNationality().getEmojiFlag());
                setText(binding.raceScreenWinner3, "\uD83E\uDD49", ' ',
                        results.get(2).getDriver().getCode() + " "
                                + results.get(2).getDriver().getNationality().getEmojiFlag());

                RecyclerView list = binding.raceScreenDrivers;
                list.setLayoutManager(new LinearLayoutManager(getContext()));
                list.setOverScrollMode(View.OVER_SCROLL_NEVER);
                list.setAdapter(new RaceAdapter(results));
            } else {
                binding.raceScreenPodium.setVisibility(View.GONE);
                binding.raceScreenResults.setVisibility(View.GONE);
            }
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Race object was null!");
            binding.raceScreenPodium.setVisibility(View.GONE);
            binding.raceScreenResults.setVisibility(View.GONE);
        }

        View root = binding.getRoot();

        return root;
    }

    private void setText(TextView view, int prefixId, String text) {
        String prefix = getString(prefixId);
        setText(view, prefix, text);
    }

    private void setText(TextView view, String prefix, String text) {
        setText(view, prefix, ':', text);
    }

    @SuppressLint("SetTextI18n")
    private void setText(TextView view, String prefix, char concatenate, String text) {
        if (view != null && text != null) {
            view.setText(prefix + concatenate + ' ' + text);
        }
    }
}