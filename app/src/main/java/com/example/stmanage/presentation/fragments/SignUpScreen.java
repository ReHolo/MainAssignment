package com.example.stmanage.presentation.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stmanage.R;
import com.example.stmanage.databinding.FragmentSignUpScreenBinding;
import com.example.stmanage.data.remote.RetrofitClient;
import com.example.stmanage.data.remote.StudentApiService;
import com.example.stmanage.data.StudentResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpScreen extends Fragment {

    private FragmentSignUpScreenBinding binding;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText genderEditText;
    private EditText departmentEditText;
    private EditText courseEditText;
    private EditText schoolEditText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpScreenBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        binding.tvLogin.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.action_signUpScreen_to_loginScreen);
        });

        binding.btnSignUp.setOnClickListener(view -> {
            postStudent();
            Navigation.findNavController(view).navigate(R.id.action_signUpScreen_to_loginScreen);
        });

        firstNameEditText = binding.etUserRegistrationFirstName;
        lastNameEditText = binding.etUserRegistrationLastName;
        emailEditText = binding.etRegisterEmailAddress;
        genderEditText = binding.etGender;
        departmentEditText = binding.etDepartment;
        courseEditText = binding.etCourse;
        schoolEditText = binding.etSchool;

        return rootView;

    }

    private void postStudent(){
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String gender = genderEditText.getText().toString();
        String department = departmentEditText.getText().toString();
        String course = courseEditText.getText().toString();
        String school = schoolEditText.getText().toString();

        StudentResponse studentData = new StudentResponse();

        studentData.setFirstName(firstName);
        studentData.setLastName(lastName);
        studentData.setEmail(email);
        studentData.setGender(gender);
        studentData.setDepartment(department);
        studentData.setCourse(course);
        studentData.setSchool(school);

        StudentApiService apiService = RetrofitClient.getClient().create(StudentApiService.class);

        Call<StudentResponse> call = apiService.postStudentData(studentData);

        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                if (response.isSuccessful()){
                    StudentResponse student = response.body();

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(requireContext(), "Ann error occurred", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {

            }
        });
    }
}