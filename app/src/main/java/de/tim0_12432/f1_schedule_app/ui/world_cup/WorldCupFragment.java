package de.tim0_12432.f1_schedule_app.ui.world_cup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import de.tim0_12432.f1_schedule_app.MainActivity;
import de.tim0_12432.f1_schedule_app.R;
import de.tim0_12432.f1_schedule_app.databinding.FragmentWorldCupBinding;

public class WorldCupFragment extends Fragment {

    private FragmentWorldCupBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentWorldCupBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TabLayout tabLayout = binding.worldCupTabLayout;
        ViewPager2 viewPager = binding.worldCupPager;

        WorldCupPagerAdapter adapter = new WorldCupPagerAdapter(requireActivity());

        adapter.addPages(new Fragment[] {
                new DriverRankingFragment(),
                new TeamRankingFragment()
        }, new String[] {
                getString(R.string.driver),
                getString(R.string.constructor)
        });
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(adapter.getTitle(position));
        }).attach();

        binding.worldCupProgress.setVisibility(View.GONE);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}