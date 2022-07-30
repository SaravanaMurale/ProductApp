package com.valor.productapp;

import static com.valor.productapp.utils.MathUtil.validateEmail;
import static com.valor.productapp.utils.MathUtil.validateMobile;
import static com.valor.productapp.utils.MathUtil.validateName;
import static com.valor.productapp.utils.MathUtil.validatePassword;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.valor.productapp.utils.MathUtil;
import com.valor.productapp.utils.SqliteManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.internal.Utils;

public class SignUpActivity extends AppCompatActivity {


    @BindView(R.id.signup_name)
    EditText signup_name;
    @BindView(R.id.signup_email)
    EditText signUpEmail;
    @BindView(R.id.signup_mobile)
    EditText signup_mobile;
    @BindView(R.id.signup_password)
    EditText signUpPassword;
    @BindView(R.id.btnSignup)
    Button btnSignup;

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        sqliteManager = new SqliteManager(SignUpActivity.this);

        signup_name.addTextChangedListener(new MyTextWatcher(signup_name));
        signUpEmail.addTextChangedListener(new MyTextWatcher(signUpEmail));
        signup_mobile.addTextChangedListener(new MyTextWatcher(signup_mobile));
        signUpPassword.addTextChangedListener(new MyTextWatcher(signUpPassword));

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean status = sqliteManager.addUser(signup_name.getText().toString(), signUpEmail.getText().toString(), signup_mobile.getText().toString(), signUpPassword.getText().toString());
                if (status) {

                    signup_name.getText().clear();
                    signUpEmail.getText().clear();
                    signup_mobile.getText().clear();
                    signUpPassword.getText().clear();


                    MathUtil.showToast(SignUpActivity.this, "User Added Successfully");

                    /*Intent intent = new Intent(SignUpActivity.this, SigninActivity.class);
                    startActivity(intent);*/
                    finish();

                } else {
                    MathUtil.showToast(SignUpActivity.this, "User Is Not Added Successfully");

                }
            }
        });

    }


    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String name = signup_name.getText().toString().trim();
            String email = signUpEmail.getText().toString().trim();
            String mobileNum = signup_mobile.getText().toString().trim();
            String password = signUpPassword.getText().toString().trim();

            btnSignup.setEnabled(validateName(name) && validateEmail(email) && validateMobile(mobileNum) && validatePassword(password) );

            if (btnSignup.isEnabled()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                    btnSignup.setBackground(getResources().getDrawable(R.drawable.card_view_border));

                }
            } else if (!btnSignup.isEnabled()) {
                btnSignup.setBackground(getResources().getDrawable(R.drawable.rounded_border));
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }


    }
}