package com.example.lma.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "Course_table", foreignKeys = @ForeignKey(entity = Category.class, parentColumns = "id"
        , childColumns = "category_id"))
public class Course extends BaseObservable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "course_id")
    private int CourseId;
    @ColumnInfo(name = "course_name")
    private String CourseName;
    @ColumnInfo(name = "course_price")
    private String CoursePrice;
    @ColumnInfo(name = "category_id")
    private int CategoryId;


    public Course(int courseId, String courseName, String coursePrice, int categoryId) {
        CourseId = courseId;
        CourseName = courseName;
        CoursePrice = coursePrice;
        CategoryId = categoryId;
    }

    public Course() {
    }

    @Bindable
    public int getCourseId() {
        return CourseId;
    }

    public void setCourseId(int courseId) {
        CourseId = courseId;
        notifyPropertyChanged(BR.courseId);
    }

    @Bindable
    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
        notifyPropertyChanged(BR.courseName);
    }

    @Bindable
    public String getCoursePrice() {
        return CoursePrice;
    }


    public void setCoursePrice(String coursePrice) {
        CoursePrice = coursePrice;
        notifyPropertyChanged(BR.coursePrice);
    }

    @Bindable
    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int categoryId) {
        CategoryId = categoryId;
        notifyPropertyChanged(BR.categoryId);
    }


}
