package de.tim0_12432.f1_schedule_app.ui.world_cup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.ResourceNames;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorStanding;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.source.LoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentTeamRankingBinding;
import de.tim0_12432.f1_schedule_app.ui.ListView;

public class TeamRankingFragment extends Fragment implements ListView<ConstructorStanding> {

    private FragmentTeamRankingBinding binding;

    private DataManager manager;

    public TeamRankingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showLoading() {
        getActivity().findViewById(R.id.world_cup_progress).setVisibility(View.VISIBLE);
    }

    @Override
    public void showEntries(List<ConstructorStanding> entries) {
        this.getActivity().runOnUiThread(() -> {
            if (entries.size() > 0) {
                android.widget.ListView list = binding.teamList;
                list.setAdapter(new TeamRankingAdapter(getActivity(), R.layout.fragment_team, entries));
                binding.teamList.setSelection(0);
            } else {
                binding.teamRankingError.setVisibility(View.VISIBLE);
            }

            binding.teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    ConstructorStanding standing = entries.get(i);
                    NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    Bundle params = new Bundle();
                    params.putSerializable("teamObj", standing);
                    params.putString("teamName", standing.getConstructor().getName()
                            + " " + standing.getConstructor().getNationality().getEmojiFlag());
                    controller.navigate(R.id.action_navigation_world_cup_to_teamDetailsFragment, params);
                }
            });

            getActivity().findViewById(R.id.world_cup_progress).setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamRankingBinding.inflate(inflater, container, false);
        binding.teamRankingError.setVisibility(View.GONE);

        getActivity().findViewById(R.id.world_cup_progress).setVisibility(View.GONE);

        manager = new DataManager(getContext());

        View root = binding.getRoot();

        List<ConstructorStanding> constructorStandings = new ArrayList<>();
        showLoading();
        manager.getDataFrom(ResourceNames.TEAM_RANKING, new LoadCallback<ConstructorStanding>() {
            @Override
            public void onLoaded(List<ConstructorStanding> list) {
                constructorStandings.addAll(list);
                showEntries(constructorStandings);
            }
        });

        return root;
    }
}