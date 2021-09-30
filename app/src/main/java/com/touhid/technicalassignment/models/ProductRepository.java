package com.touhid.technicalassignment.models;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProductRepository {

    private ProductDao productDao;
    private LiveData<List<ProductModel>> allProducts;

    public ProductRepository(Application application) {
        Database database = Database.getInstance(application);
        productDao = database.productDao();
        allProducts = productDao.getAllProducts();
    }

    public LiveData<List<ProductModel>> getAllProduct(){
        return allProducts;
    }

    public void insert(ProductModel productModel){new ProductInsertAsyncTask(productDao).execute(productModel);}

    private class ProductInsertAsyncTask extends AsyncTask<ProductModel, Void, Void> {

        ProductDao productDao;
        public ProductInsertAsyncTask(ProductDao productDao) {
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(ProductModel... productModels) {
            productDao.insert(productModels[0]);
            return null;
        }
    }
}
