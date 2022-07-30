package com.valor.productapp;

import static com.valor.productapp.utils.MathUtil.validateName;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.valor.productapp.utils.MathUtil;
import com.valor.productapp.utils.PreferencesUtil;
import com.valor.productapp.utils.SqliteManager;

public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText etProductName,etProductAmt;
    TextView tvProductTax,tvProductTotalAmount;
    Button btn_submit,btnLogout;

    String[] productCategory={"Select Category","Dairy","Vegetables","Snacks","Beverages","Fruits"};
    String selectedProductCategory ="";

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etProductName = (EditText)findViewById(R.id.etProductName);
        etProductAmt = (EditText)findViewById(R.id.etProductAmt);
        tvProductTax = findViewById(R.id.tvProductTax);
        tvProductTotalAmount = findViewById(R.id.tvProductTotalAmount);
        btn_submit=findViewById(R.id.btn_submit);
        btnLogout=findViewById(R.id.btnLogout);

        sqliteManager=new SqliteManager(AddProductActivity.this);

        Spinner spin = findViewById(R.id.spinnerProductCategory);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,productCategory);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        etProductAmt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String productAmount=etProductAmt.getText().toString().trim();
                int productPrice=Integer.parseInt(productAmount);
                int productTotal=productPrice+10;
                tvProductTotalAmount.setText(""+productTotal);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName=etProductName.getText().toString().trim();
                String productAmount=etProductAmt.getText().toString().trim();
                String productTax=tvProductTax.getText().toString().trim();
                String productTotalAmount=tvProductTotalAmount.getText().toString().trim();

                if(productName.equals("") || productName.equals(null) || productName.isEmpty()){
                    MathUtil.showToast(AddProductActivity.this,"Enter Product Name");
                    return;
                }

                if(productAmount.equals("") || productAmount.equals(null) || productAmount.isEmpty()){
                    MathUtil.showToast(AddProductActivity.this,"Enter Product Price");
                    return;
                }

                if(selectedProductCategory.equals("Select Category")){
                    MathUtil.showToast(AddProductActivity.this,"Select Product Category");
                    return;
                }

                boolean productAddedStatus = sqliteManager.addProduct(productName,selectedProductCategory,productAmount,productTax,productTotalAmount);

                if(productAddedStatus){

                    MathUtil.showToast(AddProductActivity.this,"ProductAddedSuccessfully");
                    finish();
                    System.out.println("ProductAddedSuccessfully");
                }else {
                    MathUtil.showToast(AddProductActivity.this,"ProductIsNotAddedSuccessfully");
                    System.out.println("ProductIsNotAddedSuccessfully");
                }



            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logoutAndClearAllValues();


            }
        });

    }

    private void logoutAndClearAllValues() {


        PreferencesUtil.clearAll(AddProductActivity.this);
        sqliteManager.deleteAllItemsInSqlite();
        Intent i=new Intent(AddProductActivity.this,SigninActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("EXIT", true);
        startActivity(i);
        finish();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedProductCategory = productCategory[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}