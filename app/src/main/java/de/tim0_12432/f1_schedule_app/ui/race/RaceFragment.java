package de.tim0_12432.f1_schedule_app.ui.race;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.databinding.FragmentRaceScreenBinding;

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
            binding.raceScreenName.setText(race.getUrl());
        }

        View root = binding.getRoot();

        return root;
    }
}