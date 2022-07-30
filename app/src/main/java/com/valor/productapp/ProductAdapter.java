package com.valor.productapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.valor.productapp.model.Product;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    Context context;
    List<Product> productList;


    public ProductAdapter(Context context, List<Product> productList) {
        this.context=context;
        this.productList=productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_adapter, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.tvProductName.setText(productList.get(position).getProductName());
        holder.tvProductCat.setText(productList.get(position).getProductCat());
        holder.tvProductPrice.setText(productList.get(position).getProductAmt());
        holder.tvTaxAmt.setText(productList.get(position).getTax());
        holder.tvTotalAmount.setText(productList.get(position).getTotalAmt());

    }

    @Override
    public int getItemCount() {
        return productList==null?0:productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvProductName)
        TextView tvProductName;
        @BindView(R.id.tvProductCat)
        TextView tvProductCat;
        @BindView(R.id.tvProductPrice)
        TextView tvProductPrice;
        @BindView(R.id.tvTaAmt)
        TextView tvTaxAmt;
        @BindView(R.id.tvTotalAmount)
        TextView tvTotalAmount;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
