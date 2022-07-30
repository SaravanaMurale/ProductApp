package com.valor.productapp;

import static com.valor.productapp.utils.MathUtil.validateEmail;
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
import android.widget.TextView;

import com.valor.productapp.utils.MathUtil;
import com.valor.productapp.utils.PreferencesUtil;
import com.valor.productapp.utils.SqliteManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SigninActivity extends AppCompatActivity {


    @BindView(R.id.login_email)
    EditText loginUserName;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.loginBtn)
    Button btnLogin;
    @BindView(R.id.signUp)
    TextView signUp;

    SqliteManager sqliteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);

        sqliteManager = new SqliteManager(SigninActivity.this);

        loginUserName.addTextChangedListener(new MyTextWatcher(loginUserName));
        loginPassword.addTextChangedListener(new MyTextWatcher(loginPassword));

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUserName.getText().clear();
                loginPassword.getText().clear();
                Intent intent = new Intent(SigninActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validate user from SQLITE

                int userId = sqliteManager.validateLoginUser(loginUserName.getText().toString(), loginPassword.getText().toString());
                int welcome_viewed_status= PreferencesUtil.getValueInt(SigninActivity.this,PreferencesUtil.WELCOME_VIEW_STATUS);

                if (userId>0 ) {
                    //System.out.println("ReceivedUserName"+userId);

                    if(welcome_viewed_status>0){
                        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(welcome_viewed_status==0){
                        Intent intent = new Intent(SigninActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                        finish();
                    }


                } else {
                    MathUtil.showToast(SigninActivity.this,"Entered UserName or Password Incorrect");
                    //System.out.println("ReceivedNotUserName"+userId);
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

            String email = loginUserName.getText().toString().trim();
            String password = loginPassword.getText().toString().trim();

            btnLogin.setEnabled(validateEmail(email) && validatePassword(password));

            if (btnLogin.isEnabled()) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    btnLogin.setBackground(getDrawable(R.drawable.product_on));

                }
            } else if (!btnLogin.isEnabled()) {
                btnLogin.setEnabled(false);

            }


        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}