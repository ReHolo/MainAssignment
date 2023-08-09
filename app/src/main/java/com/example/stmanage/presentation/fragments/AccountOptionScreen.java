package com.example.stmanage.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stmanage.R;
import com.example.stmanage.databinding.FragmentAccountOptionScreenBinding;

public class AccountOptionScreen extends Fragment {

    private FragmentAccountOptionScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountOptionScreenBinding.inflate(inflater, container, false);

        View rootView = binding.getRoot();

        binding.btnRegisterAccount.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_accountOptionScreen_to_signUpScreen);
        });

        binding.btnLoginAccount.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_accountOptionScreen_to_loginScreen);
        });
        return rootView;
    }
}