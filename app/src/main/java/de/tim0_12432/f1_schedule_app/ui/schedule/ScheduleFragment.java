package de.tim0_12432.f1_schedule_app.ui.schedule;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentScheduleBinding;
import de.tim0_12432.f1_schedule_app.ui.IListView;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class ScheduleFragment extends Fragment implements IListView<Race> {

    private FragmentScheduleBinding binding;

    private DataManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        binding.scheduleProgress.setVisibility(View.INVISIBLE);
        binding.scheduleError.setVisibility(View.GONE);

        manager = new DataManager(getContext());

        View root = binding.getRoot();

        List<Race> races = new ArrayList<>();
        showLoading();
        manager.getDataFrom(Resource.SCHEDULE, new ILoadCallback<Race>() {
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
        requireActivity().runOnUiThread(() -> {
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

            if (entries.size() > 0) {
                int nextRacePosition = entries.indexOf(entries.stream().filter(r -> r.getResults() == null).findFirst().get());
                binding.scheduleList.setSelection(Math.max(nextRacePosition - 2, 0));
                View selectedItem = binding.scheduleList.getAdapter().getView(nextRacePosition, null, binding.scheduleList);
                MaterialCardView card = selectedItem == null ? null : selectedItem.findViewById(R.id.race_card);
                if (card != null) {
                    card.setStrokeWidth(getResources().getDimensionPixelOffset(R.dimen.card_border_active));
                    card.setStrokeColor(getResources().getColor(R.color.red_auburn));
                }
            }
        });
    }

    private void fetchRaces(Race race) {
        String url = race.getSeason() + "/" + race.getRound() + "/results";
        manager.getDataFrom(race.getDate(), Resource.RACE_RESULTS, url, new ILoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                if (list.size() > 0) {
                    race.addResults(list.get(0).getResults());
                }
            }
        });
    }

    @SuppressLint("NewApi")
    public static void fetchLatestInformation() {
        Logger.log("Refreshing race results...");
        DataManager manager = new DataManager(MainActivity.getAppContext());
        List<Race> races = new ArrayList<>();
        manager.getDataFrom(Resource.SCHEDULE, new ILoadCallback<Race>() {
            @Override
            public void onLoaded(List<Race> list) {
                for (Race race : list) {
                    String url = race.getSeason() + "/" + race.getRound() + "/results";
                    manager.getDataFrom(race.getDate(), Resource.RACE_RESULTS, url, new ILoadCallback<Race>() {
                        @Override
                        public void onLoaded(List<Race> list) {
                            if (list.size() > 0) {
                                race.addResults(list.get(0).getResults());
                            }
                        }
                    });
                }
                races.addAll(list.stream()
                        .filter((Race race) -> race.getResults() == null || race.getResults().getResults().isEmpty())
                        .limit(2)
                        .collect(Collectors.toList()));
                for (Race race : races) {
                    String urlRace = race.getSeason() + "/" + race.getRound() + "/results";
                    manager.forceGetDataFrom(Resource.RACE_RESULTS, urlRace, new ILoadCallback<Race>() {
                        @Override
                        public void onLoaded(List<Race> list) {
                            if (list.size() > 0) {
                                Logger.log("Refreshed race", race.getName());
                            }
                        }
                    });
                    String urlQuali = race.getSeason() + "/" + race.getRound() + "/qualifying";
                    manager.forceGetDataFrom(Resource.QUALIFYING_RESULTS, urlQuali, new ILoadCallback<Race>() {
                        @Override
                        public void onLoaded(List<Race> list) {
                            if (list.size() > 0) {
                                Logger.log("Refreshed qualifying", race.getName());
                            }
                        }
                    });
                    Toast.makeText(MainActivity.getAppContext(), R.string.refreshed, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}