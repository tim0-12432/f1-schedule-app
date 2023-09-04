package de.tim0_12432.f1_schedule_app.ui.team;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;
import java.util.stream.Collectors;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Constructor;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentTeamDetailsBinding;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class TeamDetailsFragment extends Fragment {

    private FragmentTeamDetailsBinding binding;

    private Constructor team;

    private int points;

    private int wins;

    private List<DriverStanding> results;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            ConstructorStanding standing = (ConstructorStanding) getArguments().getSerializable("teamObj");
            team = standing.getConstructor();
            points = standing.getPoints();
            wins = standing.getWins();
        }
        DataManager manager = new DataManager(getContext());
        manager.getDataFrom(Resource.DRIVER_RANKING, new ILoadCallback<DriverStanding>() {
            @Override
            public void onLoaded(List<DriverStanding> list) {
                results = list;
            }
        });
    }

    private String getDriverText(DriverStanding standing) {
        Driver driver = standing.getDriver();
        return driver.getCode() + " " + driver.getNationality().getEmojiFlag()
                + "      \uD83C\uDF96" + standing.getPoints() + "  \uD83C\uDFC6" + standing.getWins();
    }

    @Override
    @SuppressLint({"NewApi", "SetTextI18n"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamDetailsBinding.inflate(inflater, container, false);

        ConstructorAttr attr = ConstructorAttr.getConstructorOfTeam(team);

        if (team != null) {
            setText(binding.teamScreenName, R.string.name, attr.getName());
            setText(binding.teamScreenNationality, R.string.nationality, team.getNationality().getTranslation() + " " + team.getNationality().getEmojiFlag());
            setText(binding.teamScreenEngine, R.string.engine, attr.getEngine().getName());

            if (attr.getTeamLead() != null) {
                setText(binding.teamScreenTeamlead, R.string.teamlead, attr.getTeamLead().getName() + " " + attr.getTeamLead().getFamilyName() + " " + attr.getTeamLead().getNationality().getEmojiFlag());
            } else {
                binding.teamScreenTeamlead.setVisibility(View.GONE);
            }
            if (attr.getTechnicalDirector() != null) {
                setText(binding.teamScreenDirector, R.string.tech_director, attr.getTechnicalDirector().getName() + " " + attr.getTechnicalDirector().getFamilyName() + " " + attr.getTechnicalDirector().getNationality().getEmojiFlag());
            } else {
                binding.teamScreenDirector.setVisibility(View.GONE);
            }

            setText(binding.teamScreenPoints, R.string.points, points + "\uD83C\uDF96");
            setText(binding.teamScreenWins, R.string.wins, wins + "\uD83C\uDFC6");

            if (results.size() > 0) {
                List<DriverStanding> list = results.stream().filter(x -> x.getConstructor().getName().equals(team.getName())).collect(Collectors.toList());
                setText(binding.teamScreenFirstDriver, R.string.first_driver, getDriverText(list.get(0)));
                setText(binding.teamScreenSecondDriver, R.string.second_driver, getDriverText(list.get(1)));
                if (list.size() > 2) {
                    if (list.subList(2, list.size()).size() == 1) {
                        setText(binding.teamScreenSubDriver, R.string.sub_driver, getDriverText(list.get(2)));
                    } else {
                        setText(binding.teamScreenSubDriver, R.string.sub_drivers, "\n" + list.subList(2, list.size())
                                .stream()
                                .map(this::getDriverText)
                                .collect(Collectors.joining("\n")));
                    }
                } else {
                    binding.teamScreenSubDriver.setVisibility(View.GONE);
                    LinearLayout.LayoutParams second = (LinearLayout.LayoutParams) binding.teamScreenSecondDriver.getLayoutParams();
                    second.bottomMargin = 0;
                    binding.teamScreenSecondDriver.setLayoutParams(second);
                }
            }

            binding.teamScreenWikiButton.setOnClickListener(v -> {
                Logger.log("Wikipedia opened for", team.getName());
                Uri url = Uri.parse(team.getUrl());
                requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
            });
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Race object was null!");
        }

        return binding.getRoot();
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