package com.example.stmanage.data.remote;

import com.example.stmanage.data.Student;
import com.example.stmanage.data.StudentResponse;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApiService {
    @GET("/students/{id}")
    Single<Student> getStudentById(@Path("id") int studentId);


    @POST("/students")
    Call<StudentResponse> postStudentData(@Body StudentResponse student);


    @PUT("/students/{id}")
    Call<StudentResponse> updateStudentData(@Path("id") int studentId, @Body StudentResponse student);
}
