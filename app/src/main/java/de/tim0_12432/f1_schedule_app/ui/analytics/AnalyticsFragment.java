package de.tim0_12432.f1_schedule_app.ui.analytics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.analysis.AnalysisService;
import de.tim0_12432.f1_schedule_app.data.analysis.ChartService;
import de.tim0_12432.f1_schedule_app.databinding.FragmentAnalyticsBinding;
import de.tim0_12432.f1_schedule_app.databinding.FragmentInformationBinding;

public class AnalyticsFragment extends Fragment {

    private FragmentAnalyticsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAnalyticsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        AnalysisService analysis = new AnalysisService();
        ChartService charts = new ChartService(MainActivity.getAppContext(), analysis);

        charts.createTotalPointsChart(binding.analyticsPointsChart);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
