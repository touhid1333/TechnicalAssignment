package com.touhid.technicalassignment.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.touhid.technicalassignment.R;
import com.touhid.technicalassignment.models.ProductModel;
import com.touhid.technicalassignment.viewmodel.ProductViewModel;

public class AddProductActivity extends AppCompatActivity {

    private TextInputEditText productNameTIE, productBrandTIE, productPriceTIE;
    private MaterialButton saveProductBTN;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        //initialize views
        initilize();
    }

    private void initilize() {
        productNameTIE = findViewById(R.id.product_name_tie);
        productBrandTIE = findViewById(R.id.product_brand_tie);
        productPriceTIE = findViewById(R.id.product_price_tie);

        saveProductBTN = findViewById(R.id.save_product_btn);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        saveProductBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = String.valueOf(productNameTIE.getText());
                String productBrand = String.valueOf(productBrandTIE.getText());
                int productPrice = Integer.parseInt(String.valueOf(productPriceTIE.getText()));

                if (validate(productName, productBrand, productPrice)) {
                    ProductModel productModel = new ProductModel(productName, productBrand, productPrice);
                    productViewModel.insertProduct(productModel);
                    Toast.makeText(AddProductActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddProductActivity.this, MainActivity.class));
                }
            }
        });
    }

    private boolean validate(String productName, String productBrand, int productPrice) {
        boolean valid = true;

        if (productName.isEmpty()){
            productNameTIE.setError("ERROR!");
            valid = false;
        }

        if (productBrand.isEmpty()){
            productBrandTIE.setError("ERROR!");
            valid = false;
        }

        if (productPrice == 0){
            productPriceTIE.setError("ERROR!");
            valid = false;
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AddProductActivity.this, MainActivity.class));
    }
}