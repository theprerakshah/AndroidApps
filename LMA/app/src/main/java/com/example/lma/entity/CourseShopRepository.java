package com.example.lma.entity;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseShopRepository {
    private CategoryDao categoryDao;
    private CourseDao courseDao;

    private LiveData<List<Category>> categoryList;
    private LiveData<List<Course>> courseList;

    public CourseShopRepository(Application application) {
        CourseDatabase courseDatabase = CourseDatabase.getInstance(application);
        categoryDao = courseDatabase.categoryDao();
        courseDao = courseDatabase.courseDao();
    }

    public LiveData<List<Course>> getCourse(int categoryId) {
        courseList = courseDao.getCourse(categoryId);
        return courseList;
    }


    public LiveData<List<Category>> getAllCategory() {
        categoryList = categoryDao.getAllCategories();
        return categoryList;
    }

    public void insertCategory(Category category) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.insert(category);
            }
        });

    }

    public void insertCourse(Course course) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.addCourse(course);
            }
        });

    }

    public void deleteCategory(Category category) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.delete(category);
            }
        });
    }

    public void deleteCourse(Course course) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.deleteCourse(course);
            }
        });
    }

    public void updateCategory(Category category) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                categoryDao.update(category);
            }
        });

    }

    public void updateCourse(Course course) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                courseDao.updateCourse(course);
            }
        });

    }


}
