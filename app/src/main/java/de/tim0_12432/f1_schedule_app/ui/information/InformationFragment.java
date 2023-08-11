package de.tim0_12432.f1_schedule_app.ui.information;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.databinding.FragmentInformationBinding;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class InformationFragment extends Fragment {

    private FragmentInformationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInformationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        try {
            setText(binding.informationVersion, R.string.version,
                    MainActivity.getAppContext().getPackageManager().getPackageInfo(
                            MainActivity.getAppContext().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            setText(binding.informationVersion, R.string.version, "unknown");
        }
        setText(binding.informationLicenseButton, R.string.app, "MIT");

        binding.informationGithubButton.setOnClickListener(v -> {
            Logger.log("Github opened");
            Uri url = Uri.parse("https://github.com/tim0-12432/f1-schedule-app");
            requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
        });

        binding.informationReleaseButton.setOnClickListener(v -> {
            Logger.log("Latest release opened");
            Uri url = Uri.parse("https://github.com/tim0-12432/f1-schedule-app/releases/latest");
            requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
        });

        binding.informationLicenseButton.setOnClickListener(v -> {
            Logger.log("App license opened");
            Uri url = Uri.parse("https://github.com/tim0-12432/f1-schedule-app/blob/main/LICENSE.md");
            requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
        });

        binding.informationF1apiButton.setOnClickListener(v -> {
            Logger.log("Ergast API opened");
            Uri url = Uri.parse("https://ergast.com/mrd/");
            requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
        });

        binding.informationOsmButton.setOnClickListener(v -> {
            Logger.log("OSM opened");
            Uri url = Uri.parse("https://www.openstreetmap.org/copyright");
            requireActivity().startActivity(new Intent(Intent.ACTION_VIEW, url));
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @SuppressLint("SetTextI18n")
    private void setText(TextView view, int prefix, String text) {
        if (view != null && text != null) {
            view.setText(getString(prefix) + ": " + text);
        }
    }
}
