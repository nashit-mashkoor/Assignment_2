package com.example.ass1.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

import static android.arch.persistence.room.ForeignKey.CASCADE;


@Entity(foreignKeys = @ForeignKey(entity = Student.class,
        parentColumns = "username",
        childColumns = "FuserName",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE), primaryKeys = {"name","FuserName"})


public class Course implements Serializable {

    @ColumnInfo(name = "name")
    @NonNull
    String name;

    @ColumnInfo(name = "FuserName")
    @NonNull
    String FuserName;

    // Weightage

    @ColumnInfo(name = "wFinal")
    Double wFinal =  0.0;

    @ColumnInfo(name = "wProject")
    Double wProject = 0.0;

    @ColumnInfo(name = "wAssignment")
    Double wAssignment = 0.0;

    @ColumnInfo(name = "wMid_1")
    Double wMid_1= 0.0;

    @ColumnInfo(name = "wMid_2")
    Double wMid_2 = 0.0;

    //Total
    @ColumnInfo(name = "tFinal")
    Double tFinal =-1.0;

    @ColumnInfo(name = "tProject")
    Double tProject=-1.0;

    @ColumnInfo(name = "tAssignment")
    Double tAssignment=-1.0;

    @ColumnInfo(name = "tMid_1")
    Double tMid_1=-1.0;

    @ColumnInfo(name = "tMid_2")
    Double tMid_2=-1.0;


    //Acquired
    @ColumnInfo(name = "aFinal")
    Double aFinal=-1.0;

    @ColumnInfo(name = "aProject")
    Double aProject=-1.0;

    @ColumnInfo(name = "aAssignment")
    Double aAssignment=-1.0;

    @ColumnInfo(name = "aMid_1")
    Double aMid_1=-1.0;

    @ColumnInfo(name = "aMid_2")
    Double aMid_2=-1.0;


    //Acquired
    @ColumnInfo(name = "absFinal")
    Double absFinal = -1.0;

    @ColumnInfo(name = "absProject")
    Double absProject = -1.0;

    @ColumnInfo(name = "absAssignment")
    Double absAssignment = -1.0;

    @ColumnInfo(name = "absMid_1")
    Double absMid_1 = -1.0;

    @ColumnInfo(name = "absMid_2")
    Double absMid_2 = -1.0;


    // Extra
    @ColumnInfo(name = "aAss1")
    Double aAss1 = -1.0;

    @ColumnInfo(name = "aAss2")
    Double aAss2 = -1.0;

    @ColumnInfo(name = "aAss3")
    Double aAss3 = -1.0;

    @ColumnInfo(name = "tAss1")
    Double tAss1 = -1.0;

    @ColumnInfo(name = "tAss2")
    Double tAss2 = -1.0;

    @ColumnInfo(name = "tAss3")
    Double tAss3 = -1.0;





    public Course ( Course c)   {
        this.name=c.name;

        this.FuserName = c.FuserName;

        // Weightage
        this.wFinal = c.wFinal;
        this.wProject = c.wProject;
        this.wAssignment = c.wAssignment;
        this.wMid_1= c.wMid_1;
        this.wMid_2 = c.wMid_2;

        //Total
        this.tFinal =c.tFinal;
        this.tProject=c.tProject;
        this.tAssignment=c.tAssignment;
        this.tMid_1=c.tMid_1;
        this.tMid_2=c.tMid_2;

        //Acquired
        this.aFinal=c.aFinal;
        this.aProject=c.aProject;
        this.aAssignment=c.aAssignment;
        this.aMid_1=c.aMid_1;
        this.aMid_2=c.aMid_2;

        //Acquired
        this.absFinal = c.absFinal;
        this.absProject = c.absProject;
        this.absAssignment = c.absAssignment;
        this.absMid_1 = c.absMid_1;
        this.absMid_2 = c.absMid_2;

        // Extra
        this.aAss1 = c.aAss1;
        this.aAss2 = c.aAss2;
        this.aAss3 = c.aAss3;
        this.tAss1 = c.tAss1;
        this.tAss2 = c.tAss2;
        this. tAss3 = c.tAss3;

    }
    public void updateAllAssign ()  {

        Double acquired = 0.0;
        Double total = 0.0;

        if(aAss1 == -1.0 && aAss2 == -1.0 && aAss3 == -1.0) {
            aAssignment = -1.0;
        }
        else    {
            if(aAss1 != -1.0)
                acquired+=aAss1;
            if(aAss2 != -1.0)
                acquired+=aAss2;
            if(aAss3 != -1.0)
                acquired+=aAss3;
            aAssignment = acquired;
        }

        if(tAss1 == -1.0 && tAss2 == -1.0 && tAss3 == -1.0) {
            tAssignment = -1.0;
        }
        else    {
            if(tAss1 != -1.0)
                total+=tAss1;
            if(tAss2 != -1.0)
                total+=tAss2;
            if(tAss3 != -1.0)
                total+=tAss3;
            tAssignment = total;
        }


    }
    public Double getProgress() {

        return wFinal + wProject +wAssignment +wMid_1 +wMid_2 ;
    }
    public Double getaAss1() {
        return aAss1;
    }

    public void setaAss1(Double aAss1) {
        this.aAss1 = aAss1;
    }

    public Double getaAss2() {
        return aAss2;
    }

    public void setaAss2(Double aAss2) {
        this.aAss2 = aAss2;
    }

    public Double getaAss3() {
        return aAss3;
    }

    public void setaAss3(Double aAss3) {
        this.aAss3 = aAss3;
    }

    public Double gettAss1() {
        return tAss1;
    }

    public void settAss1(Double tAss1) {
        this.tAss1 = tAss1;
    }

    public Double gettAss2() {
        return tAss2;
    }

    public void settAss2(Double tAss2) {
        this.tAss2 = tAss2;
    }

    public Double gettAss3() {
        return tAss3;
    }

    public void settAss3(Double tAss3) {
        this.tAss3 = tAss3;
    }



    public void calculateAbsolutes ()  {
        if(aFinal != -1.0 && tFinal != -1.0)  {
            absFinal = Double.valueOf(Math.round((aFinal/tFinal) * wFinal));
        }
        if(aProject != -1.0 && tProject != -1.0)  {
            absProject = Double.valueOf(Math.round((aProject/tProject) * wProject));
        }
        if(aAssignment != -1.0 && tAssignment != -1.0)  {
            absAssignment = Double.valueOf(Math.round((aAssignment/tAssignment) * wAssignment));
        }
        if(aMid_1 != -1.0 && tMid_1 != -1.0)  {
            absMid_1 = Double.valueOf(Math.round((aMid_1/tMid_1) * wMid_1));
        }
        if(aMid_2 != -1.0 && tMid_2 != -1.0)  {
            absMid_2 = Double.valueOf(Math.round((aMid_2/tMid_2) * wMid_2));
        }


    }
    public Course() {
    }

    public Double getAbsFinal() {
        return absFinal;
    }

    public void setAbsFinal(Double absFinal) {
        this.absFinal = absFinal;
    }

    public Double getAbsProject() {
        return absProject;
    }

    public void setAbsProject(Double absProject) {
        this.absProject = absProject;
    }

    public Double getAbsAssignment() {
        return absAssignment;
    }

    public void setAbsAssignment(Double absAssignment) {
        this.absAssignment = absAssignment;
    }

    public Double getAbsMid_1() {
        return absMid_1;
    }

    public void setAbsMid_1(Double absMid_1) {
        this.absMid_1 = absMid_1;
    }

    public Double getAbsMid_2() {
        return absMid_2;
    }

    public void setAbsMid_2(Double absMid_2) {
        this.absMid_2 = absMid_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getwFinal() {
        return wFinal;
    }

    public void setwFinal(Double wFinal) {
        this.wFinal = wFinal;
    }

    public Double getwProject() {
        return wProject;
    }

    public void setwProject(Double wProject) {
        this.wProject = wProject;
    }

    public Double getwAssignment() {
        return wAssignment;
    }

    public void setwAssignment(Double wAssignment) {
        this.wAssignment = wAssignment;
    }

    public Double getwMid_1() {
        return wMid_1;
    }

    public void setwMid_1(Double wMid_1) {
        this.wMid_1 = wMid_1;
    }

    public Double getwMid_2() {
        return wMid_2;
    }

    public void setwMid_2(Double wMid_2) {
        this.wMid_2 = wMid_2;
    }

    public Double gettFinal() {
        return tFinal;
    }

    public void settFinal(Double tFinal) {
        this.tFinal = tFinal;
    }

    public Double gettProject() {
        return tProject;
    }

    public void settProject(Double tProject) {
        this.tProject = tProject;
    }

    public Double gettAssignment() {
        return tAssignment;
    }

    public void settAssignment(Double tAssignment) {
        this.tAssignment = tAssignment;
    }

    public Double gettMid_1() {
        return tMid_1;
    }

    public void settMid_1(Double tMid_1) {
        this.tMid_1 = tMid_1;
    }

    public Double gettMid_2() {
        return tMid_2;
    }

    public void settMid_2(Double tMid_2) {
        this.tMid_2 = tMid_2;
    }

    public Double getaFinal() {
        return aFinal;
    }

    public void setaFinal(Double aFinal) {
        this.aFinal = aFinal;
    }

    public Double getaProject() {
        return aProject;
    }

    public void setaProject(Double aProject) {
        this.aProject = aProject;
    }

    public Double getaAssignment() {
        return aAssignment;
    }

    public void setaAssignment(Double aAssignment) {
        this.aAssignment = aAssignment;
    }

    public Double getaMid_1() {
        return aMid_1;
    }

    public void setaMid_1(Double aMid_1) {
        this.aMid_1 = aMid_1;
    }

    public Double getaMid_2() {
        return aMid_2;
    }

    public Course(String name, String FuserName) {
        this.name = name;
        this.FuserName = FuserName;
    }

    public void setaMid_2(Double aMid_2) {
        this.aMid_2 = aMid_2;
    }

    @NonNull
    public String getFuserName() {
        return FuserName;
    }

    public void setFuserName(@NonNull String fuserName) {
        FuserName = fuserName;
    }
}
