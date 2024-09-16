package com.example.deadlineclock.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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


public class NewDeadlineActivity extends AppCompatActivity {

    private Button startdate;
    private Button starttime;
    private Button enddate;
    private Button endtime;
    private Button add;
    private EditText title;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_from_top, R.anim.slide_to_bottom);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        add = findViewById(R.id.add);
        title = findViewById(R.id.ddltitle);

        //设置布局
        setContentView(R.layout.activity_newdeadline);

        //获取SharedPreferences实例
        SharedPreferences sp1 = getSharedPreferences("date_start", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp1.edit();

        //获取当前日期
        Calendar calendar = Calendar.getInstance();

        //初始化
        Button startdate = findViewById(R.id.startdate);
        startdate.setText(getFormattedCurrentDate(calendar));
        Button starttime = findViewById(R.id.starttime);
        starttime.setText(getFormattedCurrentTime(calendar));
        Button enddate = findViewById(R.id.enddate);
        enddate.setText(getFormattedCurrentDate(calendar));
        Button endtime = findViewById(R.id.endtime);
        endtime.setText(getFormattedCurrentTime(calendar));


        //开始日期选择
        DatePickerDialog.OnDateSetListener listener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //
                editor.putInt("START_YEAR", year);
                editor.putInt("START_MONTH", month);
                editor.putInt("START_DAY", dayOfMonth);
                editor.apply();


                Button startdate = findViewById(R.id.startdate);
                startdate.setText(String.valueOf(year) + "年"
                        + String.valueOf(month + 1) + "月"
                        + String.valueOf(dayOfMonth) + "日");

            }
        };

        findViewById(R.id.startdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(NewDeadlineActivity.this, listener1, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
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

                Button starttime = findViewById(R.id.starttime);
                starttime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };

        findViewById(R.id.starttime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(NewDeadlineActivity.this, listener2, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
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

                Button enddate = findViewById(R.id.enddate);
                enddate.setText(String.valueOf(year) + "年"
                        + String.valueOf(month + 1) + "月"
                        + String.valueOf(dayOfMonth) + "日");
            }
        };

        findViewById(R.id.enddate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpd = new DatePickerDialog(NewDeadlineActivity.this, listener3, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
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

                Button endtime = findViewById(R.id.endtime);
                endtime.setText(String.format("%02d:%02d", hourOfDay, minute));
            }
        };

        findViewById(R.id.endtime).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog tpd = new TimePickerDialog(NewDeadlineActivity.this, listener4, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                tpd.show();
            }
        });



        //点击事件
        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText title = findViewById(R.id.ddltitle);

                //获取数据
                SharedPreferences sp1 = getSharedPreferences("date_start", MODE_PRIVATE);
                int year1 = sp1.getInt("START_YEAR", calendar.get(Calendar.YEAR));
                int month1 = sp1.getInt("START_MONTH", calendar.get(Calendar.MONTH));
                int dayOfMonth1 = sp1.getInt("START_DAY", calendar.get(Calendar.DAY_OF_MONTH));

                int year2 = sp1.getInt("END_YEAR", calendar.get(Calendar.YEAR));
                int month2 = sp1.getInt("END_MONTH", calendar.get(Calendar.MONTH));
                int dayOfMonth2 = sp1.getInt("END_DAY", calendar.get(Calendar.DAY_OF_MONTH));

                int hour1 = sp1.getInt("START_HOUR", calendar.get(Calendar.HOUR_OF_DAY));
                int min1 = sp1.getInt("START_MIN",  calendar.get(Calendar.MINUTE));

                int hour2 = sp1.getInt("END_HOUR", calendar.get(Calendar.HOUR_OF_DAY));
                int min2 = sp1.getInt("END_MIN",  calendar.get(Calendar.MINUTE));

                //
                String sd = String.format("%d年%d月%d日", year1, month1 + 1, dayOfMonth1);
                String st = String.format("%02d:%02d", hour1, min1);
                String ed = String.format("%d年%d月%d日", year2, month2 + 1, dayOfMonth2);
                String et = String.format("%02d:%02d", hour2, min2);

                DateBean dateBean = new DateBean(-1, title.getText().toString(),sd, st, ed, et);
                DateDadaHelper dateDadaHelper = new DateDadaHelper(NewDeadlineActivity.this);
                String s = dateDadaHelper.addone(dateBean);
                Toast.makeText(NewDeadlineActivity.this, "ADD:" + s, Toast.LENGTH_SHORT).show();

                //
                onBackPressed();


                finish();

            }
        });

        //


        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_newdeadline);
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
