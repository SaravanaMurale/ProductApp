package com.valor.productapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.valor.productapp.utils.PreferencesUtil;

public class SplashScreenActivity extends AppCompatActivity {

    int userId;
    int welcome_viewed_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new SplashDownCountDown(3000, 1000).start();
    }

    private class SplashDownCountDown extends CountDownTimer {

        SplashDownCountDown(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long milliSecond) {

        }

        @Override
        public void onFinish() {

            Intent intent=null;

            userId = PreferencesUtil.getValueInt(SplashScreenActivity.this, PreferencesUtil.USER_ID);
            welcome_viewed_status=PreferencesUtil.getValueInt(SplashScreenActivity.this,PreferencesUtil.WELCOME_VIEW_STATUS);

            if (userId > 0 && welcome_viewed_status>0) {
                intent = new Intent(SplashScreenActivity.this, MainActivity.class);

            }else if(userId>0 && welcome_viewed_status==0)  {
                intent = new Intent(SplashScreenActivity.this, WelcomeActivity.class);

            }else {
                intent = new Intent(SplashScreenActivity.this, SigninActivity.class);
            }

            startActivity(intent);
            finish();

        }
    }
}