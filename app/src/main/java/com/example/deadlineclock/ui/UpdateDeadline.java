package com.example.deadlineclock.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.DateBean;
import com.example.deadlineclock.util.DateDadaHelper;

import java.util.Calendar;


public class UpdateDeadline extends AppCompatActivity {

    private Button update_startdate;
    private Button update_starttime;
    private Button update_enddate;
    private Button update_endtime;
    private Button update_add;
    private EditText update_title;
    private static final String TAG = "YourCustomTag";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        update_startdate = findViewById(R.id.update_startdate);
        update_starttime = findViewById(R.id.update_starttime);
        update_enddate = findViewById(R.id.update_enddate);
        update_endtime = findViewById(R.id.update_endtime);
        update_title = findViewById(R.id.update_ddltitle);

        //设置布局
        setContentView(R.layout.activity_updatedeadline);

        //获取SharedPreferences实例
        SharedPreferences sp1 = getSharedPreferences("update_start", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp1.edit();

        //获取当前日期
        Calendar calendar = Calendar.getInstance();

        //初始化
        Button update_startdate = findViewById(R.id.update_startdate);
        update_startdate.setText(getFormattedCurrentDate(calendar));
        Button update_starttime = findViewById(R.id.update_starttime);
        update_starttime.setText(getFormattedCurrentTime(calendar));
        Button update_enddate = findViewById(R.id.update_enddate);
        update_enddate.setText(getFormattedCurrentDate(calendar));
        Button update_endtime = findViewById(R.id.update_endtime);
        update_endtime.setText(getFormattedCurrentTime(calendar));

        //开始日期选择
        DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //
                editor.putInt("START_YEAR", year);
                editor.putInt("START_MONTH", month);
                editor.putInt("START_DAY", dayOfMonth);
                editor.apply();


                Button startdate = findViewById(R.id.update_startdate);
                startdate.setText(String.valueOf(year) + "年"
                        + String.valueOf(month + 1) + "月"
                        + String.valueOf(dayOfMonth) + "日");

            }
        };

        findViewById(R.id.update_startdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(UpdateDeadline.this, listener1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();

            }
        });

        //开始时间选择
        TimePickerDialog.OnTimeSetListener listener2 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //
                editor.putInt("START_HOUR", hourOfDay);
                editor.putInt("START_MIN", minute);
                editor.apply();

                Button starttime = findViewById(R.id.update_starttime);
                starttime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };

        findViewById(R.id.update_starttime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(UpdateDeadline.this, listener2, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });

        //结束日期选择
        DatePickerDialog.OnDateSetListener listener3 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //
                editor.putInt("END_YEAR", year);
                editor.putInt("END_MONTH", month);
                editor.putInt("END_DAY", dayOfMonth);
                editor.apply();



                Button enddate = findViewById(R.id.update_enddate);
                enddate.setText(String.valueOf(year) + "年"
                        + String.valueOf(month + 1) + "月"
                        + String.valueOf(dayOfMonth) + "日");
            }
        };

        findViewById(R.id.update_enddate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(UpdateDeadline.this, listener3, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

        //结束时间选择
        TimePickerDialog.OnTimeSetListener listener4 = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                //
                editor.putInt("END_HOUR", hourOfDay);
                editor.putInt("END_MIN", minute);
                editor.apply();

                Button endtime = findViewById(R.id.update_endtime);
                endtime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };

        findViewById(R.id.update_endtime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(UpdateDeadline.this, listener4, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });

        //获取数据库的值
        Intent intent2 = getIntent();
        int id = intent2.getIntExtra("id", 0);
        String title = intent2.getStringExtra("title");
        String startdate = intent2.getStringExtra("startdate");
        String starttime = intent2.getStringExtra("starttime");
        String enddate = intent2.getStringExtra("enddate");
        String endtime = intent2.getStringExtra("endtime");
        Log.d(TAG, "onClick: ");

        EditText update_ddltitle = findViewById(R.id.update_ddltitle);
        update_ddltitle.setText(title);

        //点击事件
        Button update_add = findViewById(R.id.update_add);
        update_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //获取数据
                SharedPreferences sp1 = getSharedPreferences("update_start", MODE_PRIVATE);
                int year1 = sp1.getInt("START_YEAR", 2024);
                int month1 = sp1.getInt("START_MONTH", 0);
                int dayOfMonth1 = sp1.getInt("START_DAY", 1);

                int year2 = sp1.getInt("END_YEAR", 2024);
                int month2 = sp1.getInt("END_MONTH", 0);
                int dayOfMonth2 = sp1.getInt("END_DAY", 1);

                int hour1 = sp1.getInt("START_HOUR", 12);
                int min1 = sp1.getInt("START_MIN", 00);

                int hour2 = sp1.getInt("END_HOUR", 12);
                int min2 = sp1.getInt("END_MIN", 00);

                //
                String sd = String.format("%d年%d月%d日", year1, month1 + 1, dayOfMonth1);
                String st = String.format("%02d:%02d", hour1, min1);
                String ed = String.format("%d年%d月%d日", year2, month2 + 1, dayOfMonth2);
                String et = String.format("%02d:%02d", hour2, min2);




                DateBean dateBean = new DateBean(id, update_ddltitle.getText().toString(), sd, st, ed, et);
                DateDadaHelper dateDadaHelper = new DateDadaHelper(UpdateDeadline.this);
                dateDadaHelper.updateOne(dateBean);

                finish();





            }
        });

        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_updatedeadline);
        setSupportActionBar(toolbar);

        //HomeAsUp
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }

    }

    //
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_newdeadline, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private String getFormattedCurrentDate(Calendar calendar) {
        return String.format("%d年%d月%d日", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
    }
    private String getFormattedCurrentTime(Calendar calendar) {
        return String.format("%02d:%02d",  calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
    }

}
