package com.example.stmanage.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stmanage.R;
import com.example.stmanage.databinding.FragmentLoginScreenBinding;

public class LoginScreen extends Fragment {

    private FragmentLoginScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentLoginScreenBinding.inflate(inflater, container, false);

        View rootView = binding.getRoot();

        binding.btnLogin.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_loginScreen_to_detailsScreen);
        });

        binding.tvRegister.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_loginScreen_to_signUpScreen);
        });
        return rootView;
    }
}