package com.example.ass1.data;

import java.io.Serializable;

public class Course implements Serializable {
    String name;

    // Weightage
    Float wFinal;
    Float wProject;
    Float wAssignment;
    Float wMid_1;
    Float wMid_2;

    //Total
    Float tFinal;
    Float tProject;
    Float tAssignment;
    Float tMid_1;
    Float tMid_2;

    //Acquired
    Float aFinal;
    Float aProject;
    Float aAssignment;
    Float aMid_1;
    Float aMid_2;

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getwFinal() {
        return wFinal;
    }

    public void setwFinal(Float wFinal) {
        this.wFinal = wFinal;
    }

    public Float getwProject() {
        return wProject;
    }

    public void setwProject(Float wProject) {
        this.wProject = wProject;
    }

    public Float getwAssignment() {
        return wAssignment;
    }

    public void setwAssignment(Float wAssignment) {
        this.wAssignment = wAssignment;
    }

    public Float getwMid_1() {
        return wMid_1;
    }

    public void setwMid_1(Float wMid_1) {
        this.wMid_1 = wMid_1;
    }

    public Float getwMid_2() {
        return wMid_2;
    }

    public void setwMid_2(Float wMid_2) {
        this.wMid_2 = wMid_2;
    }

    public Float gettFinal() {
        return tFinal;
    }

    public void settFinal(Float tFinal) {
        this.tFinal = tFinal;
    }

    public Float gettProject() {
        return tProject;
    }

    public void settProject(Float tProject) {
        this.tProject = tProject;
    }

    public Float gettAssignment() {
        return tAssignment;
    }

    public void settAssignment(Float tAssignment) {
        this.tAssignment = tAssignment;
    }

    public Float gettMid_1() {
        return tMid_1;
    }

    public void settMid_1(Float tMid_1) {
        this.tMid_1 = tMid_1;
    }

    public Float gettMid_2() {
        return tMid_2;
    }

    public void settMid_2(Float tMid_2) {
        this.tMid_2 = tMid_2;
    }

    public Float getaFinal() {
        return aFinal;
    }

    public void setaFinal(Float aFinal) {
        this.aFinal = aFinal;
    }

    public Float getaProject() {
        return aProject;
    }

    public void setaProject(Float aProject) {
        this.aProject = aProject;
    }

    public Float getaAssignment() {
        return aAssignment;
    }

    public void setaAssignment(Float aAssignment) {
        this.aAssignment = aAssignment;
    }

    public Float getaMid_1() {
        return aMid_1;
    }

    public void setaMid_1(Float aMid_1) {
        this.aMid_1 = aMid_1;
    }

    public Float getaMid_2() {
        return aMid_2;
    }

    public void setaMid_2(Float aMid_2) {
        this.aMid_2 = aMid_2;
    }
}
