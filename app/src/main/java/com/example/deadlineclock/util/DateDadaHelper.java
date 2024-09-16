package com.example.deadlineclock.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.deadlineclock.bean.DateBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DateDadaHelper extends SQLiteOpenHelper {
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_STARTDATE = "statrdate";
    private static final String COLUMN_STARTTIME = "starttime";
    private static final String COLUMN_ENDDATE = "enddate";
    private static final String COLUMN_ENDTIME = "endtime";
    private static final String TABLE_NAME = "date";


    public DateDadaHelper(@Nullable Context context) {
        super(context, "dates.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql1 = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TITLE + " TEXT, " + COLUMN_STARTDATE + " TEXT, " + COLUMN_STARTTIME + " TEXT, " + COLUMN_ENDDATE + " TEXT, " + COLUMN_ENDTIME + " TEXT)";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public  String addone(DateBean dateBean){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, dateBean.getTitle());
        cv.put(COLUMN_STARTDATE, dateBean.getStartdate());
        cv.put(COLUMN_STARTTIME, dateBean.getStarttime());
        cv.put(COLUMN_ENDDATE, dateBean.getEnddate());
        cv.put(COLUMN_ENDTIME, dateBean.getEndtime());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long insert = sqLiteDatabase.insert(TABLE_NAME, COLUMN_TITLE, cv);
        if (insert == -1){
            return "fail";
        }

        return "success";
    }

    public String deleteOne(DateBean dateBean){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(dateBean.getId())});

        if (delete == 0){
            return "fail";
        }
        return "success";
    }

    public  String updateOne(DateBean dateBean){

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, dateBean.getTitle());
        cv.put(COLUMN_STARTDATE, dateBean.getStartdate());
        cv.put(COLUMN_STARTTIME, dateBean.getStarttime());
        cv.put(COLUMN_ENDDATE, dateBean.getEnddate());
        cv.put(COLUMN_ENDTIME, dateBean.getEndtime());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int update = sqLiteDatabase.update(TABLE_NAME, cv, COLUMN_ID + "=?", new String[]{String.valueOf(dateBean.getId())});


        if (update == 0){
            return "fail";
        }

        return "success";
    }

    public List<DateBean> getAll(){
        DateBean dateBean;
        List<DateBean> list = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        int idIndex = cursor.getColumnIndex(COLUMN_ID);
        int titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        int startdateIndex = cursor.getColumnIndex(COLUMN_STARTDATE);
        int starttimeIndex = cursor.getColumnIndex(COLUMN_STARTTIME);
        int enddateIndex = cursor.getColumnIndex(COLUMN_ENDDATE);
        int endtimeIndex = cursor.getColumnIndex(COLUMN_ENDTIME);

        while (cursor.moveToNext()){
            dateBean = new DateBean(cursor.getInt(idIndex), cursor.getString(titleIndex), cursor.getString(startdateIndex), cursor.getString(starttimeIndex), cursor.getString(enddateIndex),cursor.getString(endtimeIndex));
            list.add(dateBean);
        }
        sqLiteDatabase.close();

        return list;
    }

    //
    public List<Map<String,String>> mapList(){
        List<Map<String,String>> mapList = new ArrayList<>();

        String sql = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor1 = sqLiteDatabase.rawQuery(sql, null);


        int idIndex = cursor1.getColumnIndex(COLUMN_ID);
        int titleIndex = cursor1.getColumnIndex(COLUMN_TITLE);
        int startdateIndex = cursor1.getColumnIndex(COLUMN_STARTDATE);
        int starttimeIndex = cursor1.getColumnIndex(COLUMN_STARTTIME);
        int enddateIndex = cursor1.getColumnIndex(COLUMN_ENDDATE);
        int endtimeIndex = cursor1.getColumnIndex(COLUMN_ENDTIME);

        while (cursor1.moveToNext()){

            Map<String,String> map = new HashMap<>();
            map.put("id", cursor1.getString(idIndex));
            map.put("title", cursor1.getString(titleIndex));
            map.put("startdate", cursor1.getString(startdateIndex));
            map.put("starttime", cursor1.getString(starttimeIndex));
            map.put("enddate", cursor1.getString(enddateIndex));
            map.put("endtime", cursor1.getString(endtimeIndex));
            mapList.add(map);
        }
        sqLiteDatabase.close();

        return mapList;
    }


}
