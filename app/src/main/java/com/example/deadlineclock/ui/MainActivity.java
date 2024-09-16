package com.example.deadlineclock.ui;

import static java.nio.file.Files.find;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.User;
import com.example.deadlineclock.util.MyDataHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                MyDataHelper myDataHelper = new MyDataHelper(getApplicationContext());
                List<User> users = myDataHelper.getAll();

                if(!users.isEmpty()){
                    Intent intent2 = new Intent(MainActivity.this, MainMainActivity.class);
                    startActivities(new Intent[]{intent2});
                    finish();
                }else {
                    Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
                    startActivities(new Intent[]{intent1});
                    finish();
                }

            }
        },1000);
    }


}