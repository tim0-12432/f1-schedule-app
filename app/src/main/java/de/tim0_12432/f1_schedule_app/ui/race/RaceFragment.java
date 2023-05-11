package de.tim0_12432.f1_schedule_app.ui.race;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import java.util.Arrays;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.entity.Qualifying;
import de.tim0_12432.f1_schedule_app.data.entity.QualifyingResult;
import de.tim0_12432.f1_schedule_app.data.entity.Race;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResult;
import de.tim0_12432.f1_schedule_app.data.entity.RaceResultList;
import de.tim0_12432.f1_schedule_app.data.entity.SprintRace;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentRaceScreenBinding;
import de.tim0_12432.f1_schedule_app.ui.transitions.FadeInTransition;
import de.tim0_12432.f1_schedule_app.ui.transitions.FadeOutTransition;
import de.tim0_12432.f1_schedule_app.utility.DateTime;
import de.tim0_12432.f1_schedule_app.utility.Logger;

public class RaceFragment extends Fragment {

    private FragmentRaceScreenBinding binding;

    private DataManager dataManager;

    private Race race;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            race = (Race) getArguments().getSerializable("raceObj");
        }
    }

    @Override
    @SuppressLint("SetTextI18n")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRaceScreenBinding.inflate(inflater, container, false);
        dataManager = new DataManager(getContext());

        if (race != null) {
            setText(binding.raceScreenCircuitName, R.string.circuit, race.getCircuit().getName());
            setText(binding.raceScreenDate, R.string.date, DateTime.getDatestamp(race.getDate()));
            setText(binding.raceScreenTime, R.string.time, DateTime.getTimestamp(race.getTime()));
            if (Arrays.stream(SprintRace.getSprintsByYear(race.getSeason()).getNumbers()).anyMatch(r -> r == race.getRound())) {
                binding.raceScreenSprint.setText(getString(R.string.sprint_race) + " \uD83C\uDFC3");
            } else {
                binding.raceScreenSprint.setVisibility(View.GONE);
            }

            RaceResultList resultList = race.getResults();
            if (resultList != null) {
                binding.raceScreenCounterCard.setVisibility(View.GONE);

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

                list.setVisibility(View.GONE);
                binding.expandResults.setOnClickListener(v -> {
                    if (list.getVisibility() == View.GONE) {
                        TransitionManager.beginDelayedTransition(binding.raceScreenResults, new FadeInTransition());
                        list.setVisibility(View.VISIBLE);
                        binding.expandResults.setImageResource(R.drawable.ic_expand_less_24);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandResultsContainer.getLayoutParams();
                        params.bottomMargin = 15;
                        binding.expandResultsContainer.setLayoutParams(params);
                    } else {
                        TransitionManager.beginDelayedTransition(binding.raceScreenResults, new FadeOutTransition());
                        list.setVisibility(View.GONE);
                        binding.expandResults.setImageResource(R.drawable.ic_expand_more_24);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandResultsContainer.getLayoutParams();
                        params.bottomMargin = 0;
                        binding.expandResultsContainer.setLayoutParams(params);
                    }
                });
            } else {
                binding.raceScreenPodium.setVisibility(View.GONE);
                binding.raceScreenResults.setVisibility(View.GONE);

                if (DateTime.getDaysDifference(DateTime.getToday(), race.getDate()) > 0) {
                    binding.raceScreenCounter.setText(getString(R.string.more_info_counter) + " "
                            + DateTime.getDaysDifference(DateTime.getToday(), race.getDate()) + " "
                            + getString(R.string.days) + ".");
                } else if (DateTime.getDaysDifference(DateTime.getToday(), race.getDate()) == 0) {
                    binding.raceScreenCounter.setText(getString(R.string.more_info_today) + ".");
                } else {
                    binding.raceScreenCounter.setText(getString(R.string.more_info_soon) + " "
                            + DateTime.getDaysDifference(DateTime.getToday(), race.getDate()) + " "
                            + getString(R.string.days) + ".");
                }
            }

            LinearLayout lastPodium = binding.raceScreenLastPodiumContainer;
            lastPodium.setVisibility(View.GONE);
            binding.expandPodium.setOnClickListener(v -> {
                if (lastPodium.getVisibility() == View.GONE) {
                    String url = (race.getSeason() - 1) + "/" + race.getRound() + "/results";
                    dataManager.getDataFrom(DateTime.plusDays(race.getDate(), -365), Resource.RACE_RESULTS, url, new ILoadCallback<Race>() {
                        @Override
                        public void onLoaded(List<Race> list) {
                            if (list.size() <= 0 || list.get(0).getResults() == null) {
                                binding.raceScreenLastInfo.setText(getString(R.string.no_last_podium));
                                binding.raceScreenLastPodiumLayoutContainer.setVisibility(View.GONE);
                            } else {
                                Race race = list.get(0);
                                setText(binding.raceScreenLastInfo,
                                        Nationality.getNationalityOfCountry(race.getCircuit().getLocation().getCountry()).getEmojiFlag(),
                                        ' ',
                                        race.getName());
                                List<RaceResult> results = race.getResults().getResults();
                                if (results.size() > 0) {
                                    setText(binding.raceScreenLastWinner1, "\uD83E\uDD47", ' ',
                                            results.get(0).getDriver().getCode() + " "
                                                    + results.get(0).getDriver().getNationality().getEmojiFlag());
                                    setText(binding.raceScreenLastWinner2, "\uD83E\uDD48", ' ',
                                            results.get(1).getDriver().getCode() + " "
                                                    + results.get(1).getDriver().getNationality().getEmojiFlag());
                                    setText(binding.raceScreenLastWinner3, "\uD83E\uDD49", ' ',
                                            results.get(2).getDriver().getCode() + " "
                                                    + results.get(2).getDriver().getNationality().getEmojiFlag());
                                }
                            }
                        }
                    });

                    TransitionManager.beginDelayedTransition(binding.raceScreenLastPodium, new FadeInTransition());
                    lastPodium.setVisibility(View.VISIBLE);
                    binding.expandPodium.setImageResource(R.drawable.ic_expand_less_24);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandPodiumContainer.getLayoutParams();
                    params.bottomMargin = 15;
                    binding.expandPodiumContainer.setLayoutParams(params);
                } else {
                    TransitionManager.beginDelayedTransition(binding.raceScreenLastPodium, new FadeOutTransition());
                    lastPodium.setVisibility(View.GONE);
                    binding.expandPodium.setImageResource(R.drawable.ic_expand_more_24);
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandPodiumContainer.getLayoutParams();
                    params.bottomMargin = 0;
                    binding.expandPodiumContainer.setLayoutParams(params);
                }
            });

            String url = race.getSeason() + "/" + race.getRound() + "/qualifying";
            dataManager.getDataFrom(DateTime.plusDays(race.getDate(), -1), Resource.QUALIFYING_RESULTS, url, new ILoadCallback<Qualifying>() {
                @Override
                public void onLoaded(List<Qualifying> list) {
                    requireActivity().runOnUiThread(() -> {
                        if (!list.isEmpty()) {
                            Qualifying qualifying = list.get(0);
                            List<QualifyingResult> results = qualifying.getResults();
                            Logger.log(Logger.LogLevel.DEBUG, results.toString());

                            RecyclerView resultList = binding.raceScreenQualifyingResults;
                            resultList.setLayoutManager(new LinearLayoutManager(getContext()));
                            resultList.setOverScrollMode(View.OVER_SCROLL_NEVER);
                            resultList.setAdapter(new QualifyingAdapter(results));

                            resultList.setVisibility(View.GONE);
                            binding.raceScreenQualifying.setVisibility(View.VISIBLE);
                            binding.expandQualifying.setOnClickListener(v -> {
                                if (resultList.getVisibility() == View.GONE) {
                                    TransitionManager.beginDelayedTransition(binding.raceScreenQualifying, new FadeInTransition());
                                    resultList.setVisibility(View.VISIBLE);
                                    binding.expandQualifying.setImageResource(R.drawable.ic_expand_less_24);
                                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandQualifyingContainer.getLayoutParams();
                                    params.bottomMargin = 15;
                                    binding.expandQualifyingContainer.setLayoutParams(params);
                                } else {
                                    TransitionManager.beginDelayedTransition(binding.raceScreenQualifying, new FadeOutTransition());
                                    resultList.setVisibility(View.GONE);
                                    binding.expandQualifying.setImageResource(R.drawable.ic_expand_more_24);
                                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) binding.expandQualifyingContainer.getLayoutParams();
                                    params.bottomMargin = 0;
                                    binding.expandQualifyingContainer.setLayoutParams(params);
                                }
                            });
                        } else {
                            binding.raceScreenQualifying.setVisibility(View.GONE);
                        }
                    });
                }
            });
        } else {
            Logger.log(Logger.LogLevel.ERROR, "Race object was null!");
            binding.raceScreenPodium.setVisibility(View.GONE);
            binding.raceScreenResults.setVisibility(View.GONE);
            binding.raceScreenCounterCard.setVisibility(View.GONE);
            binding.raceScreenLastPodium.setVisibility(View.GONE);
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