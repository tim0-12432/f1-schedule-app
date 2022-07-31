package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.HttpService;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.parser.ScheduleParser;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.databinding.FragmentScheduleBinding;

public class ScheduleFragment extends Fragment implements ScheduleListView {

    private FragmentScheduleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        binding.scheduleProgress.setVisibility(View.INVISIBLE);
        binding.scheduleError.setVisibility(View.INVISIBLE);
        View root = binding.getRoot();

        ScheduleParser parser = new ScheduleParser();
        RemoteDataSource<Race> source = HttpService.getDataSourceForUrl("current", parser);
        List<Race> races = new ArrayList<>();
        showLoading();
        source.getData(new LoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                races.addAll(list);
                showRaceEntries(races);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showLoading() {
        binding.scheduleProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void showRaceEntries(List<Race> entries) {
        this.getActivity().runOnUiThread(() -> {
            if (entries.size() > 0) {
                ListView list = binding.scheduleList;
                list.setAdapter(new ScheduleAdapter(getActivity(), R.layout.fragment_race, entries));
            } else {
                binding.scheduleError.setVisibility(View.VISIBLE);
            }
            binding.scheduleProgress.setVisibility(View.INVISIBLE);
        });
    }
}