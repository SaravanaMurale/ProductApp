package com.valor.productapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.valor.productapp.model.Product;
import com.valor.productapp.utils.PreferencesUtil;
import com.valor.productapp.utils.SqliteManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    RecyclerView recyclerview;
    ProgressBar progressBar;

    SqliteManager sqliteManager;
    ProductAdapter productAdapter;
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqliteManager = new SqliteManager(MainActivity.this);

        recyclerview = findViewById(R.id.productRecyclerView);
        fab = findViewById(R.id.fab);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        userName=(TextView)findViewById(R.id.userName);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);

        userName.setText("Hi, "+PreferencesUtil.getValueString(MainActivity.this,PreferencesUtil.USER_NAME));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,AddProductActivity.class);
                //startActivityForResult(intent, FormAddUpdateActivity.REQUEST_ADD);
                startActivity(intent);

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        
        getProductList();
        
    }

    private void getProductList() {

        List<Product> productList=sqliteManager.getProductListFromSqlite();

        if(productList.size()>0){

            productAdapter=new ProductAdapter(this,productList);
            recyclerview.setAdapter(productAdapter);

        }else {
            System.out.println("YouHaveNotAnyProduct");
        }



    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqliteManager != null){
            sqliteManager.close();
        }

        finish();


    }
}