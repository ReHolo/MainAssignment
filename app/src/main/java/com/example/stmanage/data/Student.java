package com.example.stmanage.data;

public class Student {
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String department;
    private String course;
    private String school;

    public Student(
            String firstName,
            String lastName,
            String email,
            String gender,
            String department,
            String course,
            String school
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.course = course;
        this.school = school;
    }

    //Getters methods

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public String getGender(){
        return gender;
    }
    public String getDepartment(){
        return department;
    }
    public String getCourse(){
        return course;
    }
    public String getSchool(){
        return school;
    }

}
