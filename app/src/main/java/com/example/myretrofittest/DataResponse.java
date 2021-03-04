package com.example.myretrofittest;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("students")
    List<Student> students;

    public String getStatus() {
        return status;
    }

    public List<com.example.myretrofittest.Student> getStudents() {
        return students;
    }
}

class Student {
    @SerializedName("roll")
    int Roll;
    @SerializedName("name")
    String Name;
    @SerializedName("image")
    String Image;
    public Student(int roll, String name, String image) {
        Roll = roll;
        Name = name;
        Image = image;
    }

    public int getRoll() {
        return Roll;
    }

    public String getName() {
        return Name;
    }

    public String getImage() {
        return Image;
    }

    public void setRoll(int roll) {
        Roll = roll;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setImage(String image) {
        Image = image;
    }

}
