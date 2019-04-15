package com.example.ass1.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert (onConflict = OnConflictStrategy.ABORT)
    void addStudent (Student ... student);

    @Query ("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Query("Select Count (*) from Student  where (username = :un and password = :pw) ")
    Integer isStudentPresent (String un, String pw);

    @Query("Select Count (*) from Student  where (username = :un ) ")
    Integer isStudentPresent (String un);

    @Query  ("Select * from Student where Student.username = :un")
    Student getStudent(String un);

    @Query("Delete from Student")
    void deleteStudentTable();


}
