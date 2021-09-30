package com.touhid.technicalassignment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.touhid.technicalassignment.models.ProductModel;
import com.touhid.technicalassignment.models.ProductRepository;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository productRepository;
    private LiveData<List<ProductModel>> allProducts;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        productRepository = new ProductRepository(application);
        allProducts = productRepository.getAllProduct();
    }

    public void insertProduct(ProductModel productModel){
        productRepository.insert(productModel);
    }

    public LiveData<List<ProductModel>> getAllProducts(){
        return allProducts;
    }
}
