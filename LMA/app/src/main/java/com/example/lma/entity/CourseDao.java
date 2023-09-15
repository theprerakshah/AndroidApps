package com.example.lma.entity;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void addCourse(Course course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("Select * from course_table")
    LiveData<List<Course>> getAllCourse();

    @Query("Select * from course_table where category_id==:categoryId")
    LiveData<List<Course>> getCourse(int categoryId);

}
