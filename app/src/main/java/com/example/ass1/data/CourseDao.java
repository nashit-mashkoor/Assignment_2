package com.example.ass1.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addCourse(Course ... course);

    // All courses
    @Query ("SELECT * FROM Course")
    List<Course> getAllCourse();

    // All  course of a single student
    @Query ("SELECT * FROM COURSE WHERE COURSE.FuserName = :FN")
    List<Course> getStudentCourse(String FN);

    // Single course with unique name and student name
    @Query  ("Select  * from Course  where (Course.name = :cn and Course.FuserName = :sn) ")
    Course getCourse(String cn, String sn);


    // Chekc based on combination of student name and course name
    @Query("Select Count (*) from Course  where (Course.name = :cn and Course.FuserName = :sn) ")
    Integer isCoursePresent (String cn, String sn);
    @Update
    void updateCourse(Course ... course);
    @Query  ("Delete from Course  where (Course.name = :cn and Course.FuserName = :sn) ")
    void deleteCourse(String cn, String sn);

    @Delete
    void deleteCourse(Course c);








}
