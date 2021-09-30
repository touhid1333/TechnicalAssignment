package com.touhid.technicalassignment.models;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(ProductModel productModel);

    @Query("SELECT * FROM product_table ORDER BY id ASC")
    LiveData<List<ProductModel>> getAllProducts();
}
