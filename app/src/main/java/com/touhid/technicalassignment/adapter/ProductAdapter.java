package com.touhid.technicalassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.touhid.technicalassignment.R;
import com.touhid.technicalassignment.models.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<ProductModel> allProductList = new ArrayList<>();


    public void setAllProductList(List<ProductModel> allProductList) {
        this.allProductList = allProductList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_recycler_view_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.productNameTV.setText(allProductList.get(position).getProduct_name());
        holder.brandNameTV.setText(allProductList.get(position).getBrand_name());
        holder.productPriceTV.setText(String.valueOf(allProductList.get(position).getPrice()));

    }

    @Override
    public int getItemCount() {
        return allProductList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{

        TextView productNameTV, brandNameTV, productPriceTV;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productNameTV = itemView.findViewById(R.id.row_product_name);
            brandNameTV = itemView.findViewById(R.id.row_product_brand);
            productPriceTV = itemView.findViewById(R.id.row_product_price);
        }
    }
}
