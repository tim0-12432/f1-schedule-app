package de.tim0_12432.f1_schedule_app.ui.driver;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.databinding.FragmentDriverDetailsBinding;
import de.tim0_12432.f1_schedule_app.utility.DateTime;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class DriverDetailsFragment extends Fragment {

    private FragmentDriverDetailsBinding binding;

    private Driver driver;

    private int points;

    private ConstructorAttr team;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            driver = (Driver) getArguments().getSerializable("driverObj");
            points = getArguments().getInt("driverPoints");
            team = (ConstructorAttr) getArguments().getSerializable("teamObj");
        }
    }

    @Override
    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDriverDetailsBinding.inflate(inflater, container, false);

        if (driver != null) {
            setText(binding.driverScreenName, R.string.first_name, driver.getName());
            setText(binding.driverScreenFamily, R.string.family_name, driver.getFamilyName());
            setText(binding.driverScreenBirthday, R.string.birthday, DateTime.getDatestamp(driver.getDateOfBirth())
                    + (DateTime.isBirthday(driver.getDateOfBirth()) ? " \uD83D\uDC51" : ""));
            setText(binding.driverScreenAge, R.string.age, DateTime.getAge(driver.getDateOfBirth()) + " " + getString(R.string.years));
            setText(binding.driverScreenNationality, R.string.nationality, driver.getNationality().getTranslation() + " " + driver.getNationality().getEmojiFlag());

            setText(binding.driverScreenCode, R.string.code, driver.getCode());
            setText(binding.driverScreenNumber, R.string.number, String.valueOf(driver.getNumber()));
            if (team != null) {
                setText(binding.driverScreenTeam, R.string.team, team.getName());
            } else {
                binding.driverScreenTeam.setVisibility(View.GONE);
            }
            setText(binding.driverScreenPoints, R.string.points, points + "\uD83C\uDF96");

            binding.driverScreenWikiButton.setOnClickListener(v -> {
                Logger.log("Wikipedia opened for", driver.getName(), driver.getFamilyName());
                Uri url = Uri.parse(driver.getUrl());
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