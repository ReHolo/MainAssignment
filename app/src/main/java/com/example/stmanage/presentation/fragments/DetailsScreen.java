package com.example.stmanage.presentation.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.stmanage.R;
import com.example.stmanage.databinding.FragmentDetailsScreenBinding;
import com.example.stmanage.data.remote.RetrofitClient;
import com.example.stmanage.data.Student;
import com.example.stmanage.data.remote.StudentApiService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class DetailsScreen extends Fragment {
    private FragmentDetailsScreenBinding binding;
    private TextView firstNameText;
    private TextView lastNameText;
    private TextView emailText;
    private TextView genderText;
    private TextView departmentText;
    private TextView courseText;
    private TextView schoolText;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    Switch modes;
    boolean nightMode;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetailsScreenBinding.inflate(inflater, container, false);

        View rootView = binding.getRoot();

        binding.btnEditDetails.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_detailsScreen_to_addDetailsScreen);
        });

        modes = binding.swMode;

        // SharedPreferences saves the mode if you exit the app and back again.
        sharedPreferences = getActivity().getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean("night", false);  //Light mode is the default mode

        if (nightMode) {
            modes.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        modes.setOnClickListener(view -> {
            if (nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", false);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                editor = sharedPreferences.edit();
                editor.putBoolean("night", true);
            }
        });

        firstNameText = binding.tvFirstName;
        lastNameText = binding.tvLastName;
        emailText = binding.tvEmail;
        genderText = binding.tvGender;
        departmentText = binding.tvDepartment;
        courseText = binding.tvCourseName;
        schoolText = binding.tvSchoolName;

        // Create the retrofit service with RxJava support
        StudentApiService apiService = RetrofitClient.getClient().create(StudentApiService.class);

        //Replace the student_id with the actual student Id
        int studentId = 100;

        //Making APi call
        compositeDisposable.add(apiService.getStudentById(studentId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onStudentReceived, this::onError));

        return rootView;
    }

    private void onStudentReceived(Student student){
        // Displaying the student data
        firstNameText.setText(student.getFirstName());
        lastNameText.setText(student.getLastName());
        emailText.setText(student.getEmail());
        genderText.setText(student.getGender());
        departmentText.setText(student.getDepartment());
        courseText.setText(student.getCourse());
        schoolText.setText(student.getSchool());
    }

    private void onError(Throwable throwable){
        // Handle the error
        firstNameText.setText("");
        lastNameText.setText("");
        emailText.setText("");
        genderText.setText("");
        departmentText.setText("");
        courseText.setText("");
        schoolText.setText("");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        compositeDisposable.dispose();
    }

}