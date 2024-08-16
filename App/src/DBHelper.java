package com.example.expence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME ="Expense_Tracker.db";
    public DBHelper(Context context) {
        super(context, "Expense_Tracker", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table login(username TEXT primary key,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("Drop Table if exists login");
    }

    public boolean insertdata(String username,String password){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("password",password);
        long result=DB.insert("login",null,cv);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean updatepassword(String username,String password){
        SQLiteDatabase DB= this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("password",password);
        long result=DB.update("login",cv,"username=?",new String[] {username});
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkusername(String username){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from login where username=?",new String[]{username});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkusernamepassword(String username,String password){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("Select *from login where username=? and password=?",new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }
}
