package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.HttpService;
import de.tim0_12432.f1_schedule_app.data.ResourceNames;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.parser.RaceResultsParser;
import de.tim0_12432.f1_schedule_app.data.parser.ScheduleParser;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import de.tim0_12432.f1_schedule_app.data.source.remote.RemoteDataSource;
import de.tim0_12432.f1_schedule_app.databinding.FragmentScheduleBinding;
import de.tim0_12432.f1_schedule_app.ui.ListView;

public class ScheduleFragment extends Fragment implements ListView<Race> {

    private FragmentScheduleBinding binding;

    private DataManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        binding.scheduleProgress.setVisibility(View.INVISIBLE);
        binding.scheduleError.setVisibility(View.INVISIBLE);

        manager = new DataManager(getContext());

        View root = binding.getRoot();

        List<Race> races = new ArrayList<>();
        showLoading();
        manager.getDataFrom(ResourceNames.SCHEDULE, new LoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                races.addAll(list);
                for (Race race : races) {
                    fetchRaces(race);
                }
                showEntries(races);
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
    @SuppressLint("NewApi")
    public void showEntries(List<Race> entries) {
        this.getActivity().runOnUiThread(() -> {
            if (entries.size() > 0) {
                android.widget.ListView list = binding.scheduleList;
                list.setAdapter(new ScheduleAdapter(getActivity(), R.layout.fragment_race, entries));
            } else {
                binding.scheduleError.setVisibility(View.VISIBLE);
            }
            binding.scheduleProgress.setVisibility(View.INVISIBLE);
            binding.scheduleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Race race = entries.get(i);
                    String locEmoji = Nationality.getNationalityOfCountry(race.getCircuit().getLocation().getCountry()).getEmojiFlag();
                    NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    Bundle params = new Bundle();
                    params.putSerializable("raceObj", race);
                    params.putString("raceName", race.getName() + " " + locEmoji);
                    controller.navigate(R.id.action_navigation_schedule_to_raceFragment, params);
                }
            });
            int nextRacePosition = entries.indexOf(entries.stream().filter(r -> r.getResults() == null).findFirst().get());
            binding.scheduleList.setSelection(Math.max(nextRacePosition - 2, 0));
            // binding.scheduleList.getChildAt(nextRacePosition).setStroke(getResources().getColor(R.color.colorPrimaryVariant));
        });
    }

    private void fetchRaces(Race race) {
        String url = race.getSeason() + "/" + race.getRound() + "/results";
        manager.getDataFrom(ResourceNames.RACE_RESULTS, url, new LoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                if (list.size() > 0) {
                    race.addResults(list.get(0).getResults());
                }
            }
        });
    }
}