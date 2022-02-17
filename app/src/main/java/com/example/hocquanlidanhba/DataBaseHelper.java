package com.example.hocquanlidanhba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String ID_CUSTOMER = "ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String createTable= "CREATE TABLE " + CUSTOMER_TABLE + "(" + ID_CUSTOMER + " INTEGER PRIMARY KEY AUTOINCREMENT," + CUSTOMER_NAME + " TEXT," + CUSTOMER_AGE + " INT ," + ACTIVE_CUSTOMER + " BOOL)";
db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(Customer cus)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(CUSTOMER_NAME,cus.getName());
        cv.put(CUSTOMER_AGE,cus.getAge());
        cv.put(ACTIVE_CUSTOMER,cus.isActive());
        long b=db.insert(CUSTOMER_TABLE,null,cv);
        if(b==-1)
        {return false;}
        return true;
    }
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<>();
        String queryString="SELECT * FROM "+CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null,null);
        if(cursor.moveToFirst())
        {
            do {
                int cusID=cursor.getInt(0);
                String cusName=cursor.getString(1);
                int cusAge=cursor.getInt(2);
                boolean cusAct=(cursor.getInt(3)==1 ? true:false);
                Customer cus=new Customer(cusID,cusName,cusAge,cusAct);
                list.add(cus);
            }while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();
        return list;
    }
    public boolean deleteOne(Customer cus)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String queryString="DELETE FROM "+CUSTOMER_TABLE+" WHERE "+ID_CUSTOMER+" = "+cus.getId();
        Cursor cursor = db.rawQuery(queryString, null, null);
        if(cursor.moveToFirst())
        {
            return true;
        }
        return false;
    }

}
