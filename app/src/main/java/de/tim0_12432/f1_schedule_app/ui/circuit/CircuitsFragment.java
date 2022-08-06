package de.tim0_12432.f1_schedule_app.ui.circuit;

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
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.data.DataManager;
import de.tim0_12432.f1_schedule_app.data.Resource;
import de.tim0_12432.f1_schedule_app.data.entity.Circuit;
import de.tim0_12432.f1_schedule_app.data.entity.Nationality;
import de.tim0_12432.f1_schedule_app.data.source.ILoadCallback;
import de.tim0_12432.f1_schedule_app.databinding.FragmentCircuitsBinding;
import de.tim0_12432.f1_schedule_app.ui.IListView;

public class CircuitsFragment extends Fragment implements IListView<Circuit> {

    private FragmentCircuitsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCircuitsBinding.inflate(inflater, container, false);
        binding.circuitsProgress.setVisibility(View.INVISIBLE);
        binding.circuitsError.setVisibility(View.GONE);

        DataManager manager = new DataManager(getContext());

        View root = binding.getRoot();

        List<Circuit> circuits = new ArrayList<>();
        showLoading();
        manager.getDataFrom(Resource.CIRCUITS, new ILoadCallback<Circuit>() {
            @SuppressLint("NewApi")
            @Override
            public void onLoaded(List<Circuit> list) {
                circuits.addAll(list);
                circuits.sort(Comparator.comparing((Circuit circuit) -> circuit.getName().toLowerCase(Locale.ROOT)));
                showEntries(circuits);
            }
        });
        return root;
    }

    @Override
    @SuppressLint("NewApi")
    public void showEntries(List<Circuit> entries) {
        this.getActivity().runOnUiThread(() -> {
            if (entries.size() > 0) {
                android.widget.ListView list = binding.circuitsList;
                list.setAdapter(new CircuitsAdapter(getActivity(), R.layout.fragment_circuit, entries));
            } else {
                binding.circuitsError.setVisibility(View.VISIBLE);
            }
            binding.circuitsProgress.setVisibility(View.INVISIBLE);
            binding.circuitsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Circuit circuit = entries.get(i);
                    Nationality nation = Nationality.getNationalityOfCountry(circuit.getLocation().getCountry());
                    NavController controller = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
                    Bundle params = new Bundle();
                    params.putSerializable("circuitObj", circuit);
                    params.putString("circuitName", circuit.getName() + " " + nation.getEmojiFlag());
                    controller.navigate(R.id.action_navigation_driver_ranking_to_navigation_circuit_details_screen, params);
                }
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void showLoading() {
        binding.circuitsProgress.setVisibility(View.VISIBLE);
    }
}