package com.example.deadlineclock.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.deadlineclock.bean.User;

import java.util.ArrayList;
import java.util.List;

public class MyDataHelper extends SQLiteOpenHelper {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";

    public MyDataHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_PASSWORD + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  String addOne(User user){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long insert = sqLiteDatabase.insert(TABLE_NAME, COLUMN_NAME, cv);
        if (insert == -1){
            return "fail";
        }

        return "success";
    }

    public String deleteOne(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(user.getId())});

        if (delete == 0){
            return "fail";
        }
        return "success";
    }

    public  String updateOne(User user){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, user.getName());
        cv.put(COLUMN_PASSWORD, user.getPassword());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int update = sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(user.getId())});


        if (update == 0){
            return "fail";
        }

        return "success";
    }

    public List<User> getAll(){
        User user;
        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
        int passwordIndex = cursor.getColumnIndex(COLUMN_PASSWORD);

        while (cursor.moveToNext()){
            user = new User(cursor.getInt(idIndex), cursor.getString(nameIndex), cursor.getString(passwordIndex));
            list.add(user);
        }
        return list;
    }

    public boolean login(String name, String password){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        boolean result = false;

        Cursor users = sqLiteDatabase.query("user", null, "name like ?", new String[]{name}, null, null, null);
        if(users != null){
            while (users.moveToNext()){
                String password1 = users.getString(1);
                result = password1.equals(password);
                return result;

            }

        }return false;

    }

}
