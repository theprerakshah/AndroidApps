package com.example.lma.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.lma.entity.Category;
import com.example.lma.entity.Course;
import com.example.lma.entity.CourseShopRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    // Repository
    private CourseShopRepository repository;

    //Live Data
    private LiveData<List<Category>> allCategoriesList;
    private LiveData<List<Course>> allCourseList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        repository = new CourseShopRepository(application);
    }

    public LiveData<List<Category>> getAllCategories() {
        return repository.getAllCategory();
    }

    public LiveData<List<Course>> getAllCourse(int categoryId) {
        return repository.getCourse(categoryId);
    }

    public void addNewCourse(Course course) {
        repository.insertCourse(course);
    }

//    public void addNewCategory(Category category) {
//        repository.insertCategory(category);
//    }

//    public void updateCategory(Category category) {
//        repository.updateCategory(category);
//    }

    public void updateCourse(Course course) {
        repository.updateCourse(course);
    }

    public void deleteCourse(Course course) {
        repository.deleteCourse(course);
    }
//
//    public void deletedCategory(Category category) {
//        repository.deleteCategory(category);
//    }

}
