package com.touhid.technicalassignment.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "product_table")
public class ProductModel implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "product_name")
    private String product_name;

    @ColumnInfo(name = "brand_name")
    private String brand_name;

    @ColumnInfo(name = "price")
    private int price;

    public ProductModel(String product_name, String brand_name, int price) {
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public int getPrice() {
        return price;
    }
}
