package com.example.ass1.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Student implements Serializable {

    String username;
    String password;
    String name;
    String rollNum;
    String batch;
    Course course;
    ArrayList<Course> allCourse;
    public Course getCourse() {
        return course;
    }

    public Student(String username, String password, String name, String rollNum, String batch) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.rollNum = rollNum;
        this.batch = batch;

        this.course = null;
        this.allCourse = new ArrayList<>();

    }

    public ArrayList<Course> getAllCourse() {
        return allCourse;
    }

    public void setAllCourse(ArrayList<Course> allCourse) {
        this.allCourse = allCourse;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
