package com.valor.productapp.utils;

import android.content.Context;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MathUtil {

    public static String dateAndTime() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date d = new Date();
        String dateAndTime = sdf.format(d);

        return dateAndTime;
    }

    public static boolean validateName(String name) {

        if (name.length() < 3) {
            return false;
        }

        return true;

    }

    public static boolean validateEmail(String email) {

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(email.matches(emailPattern)){
            return true;
        }


        return false;
    }

    public static boolean validateMobile(String mobile) {

        if (mobile.length() < 10) {
            return false;
        }
        return true;
    }

    public static boolean validatePassword(String password) {
        if (password.length() < 6) {

            // ToastUtils.getInstance(SignUpActivity.this).showLongToast(R.string.short_password);
            return false;
        }
        return true;
    }

    public static void showToast(Context context, String msg){

        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();

    }
}
