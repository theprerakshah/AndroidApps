package com.example.lma.entity;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Query("Select * from category_table")
    LiveData<List<Category>> getAllCategories();

}
