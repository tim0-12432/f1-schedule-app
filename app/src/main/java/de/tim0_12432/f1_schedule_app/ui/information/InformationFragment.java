package de.tim0_12432.f1_schedule_app.ui.information;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import de.tim0_12432.f1_schedule_app.databinding.FragmentInformationBinding;

public class InformationFragment extends Fragment {

    private FragmentInformationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInformationBinding.inflate(inflater, container, false);

        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
