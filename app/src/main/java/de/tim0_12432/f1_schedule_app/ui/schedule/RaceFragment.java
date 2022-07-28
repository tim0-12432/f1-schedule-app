package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.Race;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RaceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RaceFragment extends Fragment {

    private static final String ARG_RACE = "race";

    private Race mRace;

    public RaceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param race the race.
     * @return A new instance of fragment RaceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RaceFragment newInstance(String race) {
        RaceFragment fragment = new RaceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_RACE, race);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRace = (Race) getArguments().get(ARG_RACE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_race, container, false);
    }
}