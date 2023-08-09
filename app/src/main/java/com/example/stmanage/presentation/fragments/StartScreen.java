package com.example.stmanage.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stmanage.R;
import com.example.stmanage.databinding.FragmentStartScreenBinding;

public class StartScreen extends Fragment {

    private FragmentStartScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStartScreenBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        binding.btnStart.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_startScreen_to_accountOptionScreen);
        });
        return rootView;
    }
}