package de.tim0_12432.f1_schedule_app.ui.world_cup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class WorldCupPagerAdapter extends FragmentStateAdapter {
    private Fragment[] pages = null;
    private String[] titles = null;

    public WorldCupPagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public WorldCupPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void addPages(Fragment[] fragments, String[] titles) {
        this.pages = fragments;
        this.titles = titles;
    }

    public String getTitle(int position) {
        return titles[position];
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return pages[position];
    }

    @Override
    public int getItemCount() {
        return pages.length;
    }
}
