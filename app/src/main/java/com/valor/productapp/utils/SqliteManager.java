package com.valor.productapp.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.valor.productapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class SqliteManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PRODUCTS";
    public static final int DATABASE_VERSION = 1;
    Context context;


    //Signup
    public static final String SIGNUP_TABLE = "signuptable";
    public static final String COLUMN_ID = "id";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_MOBILE = "user_mobile";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_CREATION_DATE = "user_creation_date";


    //Product
    public static final String PRODUCT_TABLE_NAME = "producttbl";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_NAME = "product_name";
    public static final String PRODUCT_CATEGORY = "product_category";
    public static final String PRODUCT_PRICE = "product_price";
    public static final String PRODUCT_TAX = "product_tax";
    public static final String DATE = "date";
    public static final String TOTAL_AMOUNT = "total_amount";


    public SqliteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String signUpTable = "CREATE TABLE IF NOT EXISTS " + SIGNUP_TABLE + "(\n" +
                "    " + COLUMN_ID + " INTEGER NOT NULL CONSTRAINT add_cart_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + USER_NAME + " varchar(200) NOT NULL,\n" +
                "    " + USER_EMAIL + " tinyint(4) NOT NULL,\n" +
                "    " + USER_MOBILE + " varchar(200) NOT NULL,\n" +
                "    " + USER_PASSWORD + " varchar(200) NOT NULL,\n" +
                "    " + USER_CREATION_DATE + " varchar(200) NOT NULL\n" +
                ");";

        String productTable = "CREATE TABLE IF NOT EXISTS " + PRODUCT_TABLE_NAME + "(\n" +
                "    " + PRODUCT_ID + " INTEGER NOT NULL CONSTRAINT add_product_pk PRIMARY KEY AUTOINCREMENT,\n" +
                "    " + PRODUCT_NAME + " varchar(200) NOT NULL,\n" +
                "    " + PRODUCT_CATEGORY + " varchar(200) NOT NULL,\n" +
                "    " + PRODUCT_PRICE + " varchar(200) NOT NULL,\n" +
                "    " + PRODUCT_TAX + " varchar(200) NOT NULL,\n" +
                "    " + DATE + " varchar(200) NOT NULL,\n" +
                "    " + TOTAL_AMOUNT + " varchar(200) NOT NULL\n" +
                ");";

        db.execSQL(signUpTable);
        db.execSQL(productTable);

    }


    public boolean addProduct(String productName, String productCategory, String productAmount, String productTax, String productTotalAmount) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(PRODUCT_NAME, productName);
        contentValues.put(PRODUCT_CATEGORY, productCategory);
        contentValues.put(PRODUCT_PRICE, productAmount);
        contentValues.put(PRODUCT_TAX, productTax);
        contentValues.put(TOTAL_AMOUNT, productTotalAmount);
        contentValues.put(DATE, MathUtil.dateAndTime());

        return sqLiteDatabase.insert(PRODUCT_TABLE_NAME, null, contentValues) != -1;

    }

    public List<Product> getProductListFromSqlite() {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase selectAllData = getReadableDatabase();

        Cursor cursor = selectAllData.rawQuery("select product_id,product_name,product_category,product_price,product_tax,total_amount from producttbl ", null);
        if (cursor.moveToFirst()) {

            do {

                Product product = new Product(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

                productList.add(product);

            }
            while (cursor.moveToNext());

        }
        return productList;

    }

    public boolean addUser(String userName, String userEmail, String mobileNumber, String userPassword) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(USER_NAME, userName);
        contentValues.put(USER_EMAIL, userEmail);
        contentValues.put(USER_MOBILE, mobileNumber);
        contentValues.put(USER_PASSWORD, userPassword);
        contentValues.put(USER_CREATION_DATE, MathUtil.dateAndTime());

        return sqLiteDatabase.insert(SIGNUP_TABLE, null, contentValues) != -1;

    }

    public int validateLoginUser(String userEmail, String password) {

        String username = "empty";
        int id = 0;

        SQLiteDatabase selectAllData = getReadableDatabase();

        Cursor userData = selectAllData.rawQuery("select id,user_name from signuptable where user_email=? and user_password=? ", new String[]{userEmail, password});

        if (userData.moveToFirst()) {

            do {

                id = userData.getInt(0);
                //System.out.println("ReceivedUserId" + id);
                username = userData.getString(1);

                PreferencesUtil.setValueSInt(context, PreferencesUtil.USER_ID, id);
                PreferencesUtil.setValueString(context, PreferencesUtil.USER_NAME, username);


            }
            while (userData.moveToNext());

        }
        return id;
    }

    public void deleteAllItemsInSqlite() {

        SQLiteDatabase db = getWritableDatabase();

        //db.execSQL("delete from " + SIGNUP_TABLE);
        db.execSQL("delete from " + PRODUCT_TABLE_NAME);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

}
