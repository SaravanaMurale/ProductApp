package com.valor.productapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.valor.productapp.utils.PreferencesUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.btnGetStarted)
    Button btnGetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        PreferencesUtil.setValueSInt(WelcomeActivity.this,PreferencesUtil.WELCOME_VIEW_STATUS,1);

        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }
}