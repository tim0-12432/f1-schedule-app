package de.tim0_12432.f1_schedule_app.ui.world_cup;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.List;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.ConstructorAttr;
import de.tim0_12432.f1_schedule_app.data.entity.Driver;
import de.tim0_12432.f1_schedule_app.data.entity.DriverStanding;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentDriverRankingBinding;
import de.tim0_12432.f1_schedule_app.ui.IListView;

public class DriverRankingFragment extends Fragment implements IListView<DriverStanding> {

    private FragmentDriverRankingBinding binding;

    private DataManager manager;

    public DriverRankingFragment() {
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
    public void showEntries(List<DriverStanding> entries) {
        this.getActivity().runOnUiThread(() -> {
            if (entries.size() > 0) {
                android.widget.ListView list = binding.driverList;
                list.setAdapter(new DriverRankingAdapter(getActivity(), R.layout.fragment_driver, entries));
                binding.driverList.setSelection(0);
            } else {
                binding.driverRankingError.setVisibility(View.VISIBLE);
            }

            binding.driverList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    DriverStanding standing = entries.get(i);
                    Driver driver = standing.getDriver();
                    NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    Bundle params = new Bundle();
                    params.putSerializable("driverObj", driver);
                    params.putSerializable("teamObj", ConstructorAttr.getConstructorOfTeam(standing.getConstructor()));
                    params.putInt("driverPoints", standing.getPoints());
                    params.putString("driverName", driver.getName() + " " + driver.getFamilyName()
                            + " " + driver.getNationality().getEmojiFlag());
                    controller.navigate(R.id.action_navigation_world_cup_to_navigation_driver_details_screen, params);
                }
            });

            getActivity().findViewById(R.id.world_cup_progress).setVisibility(View.GONE);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDriverRankingBinding.inflate(inflater, container, false);
        binding.driverRankingError.setVisibility(View.GONE);

        getActivity().findViewById(R.id.world_cup_progress).setVisibility(View.GONE);

        manager = new DataManager(getContext());

        View root = binding.getRoot();

        List<DriverStanding> driverStandings = new ArrayList<>();
        showLoading();
        manager.getDataFrom(Resource.DRIVER_RANKING, new ILoadCallback<DriverStanding>() {
            @Override
            public void onLoaded(List<DriverStanding> list) {
                driverStandings.addAll(list);
                showEntries(driverStandings);
            }
        });

        return root;
    }
}