package com.example.deadlineclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.deadlineclock.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局
        setContentView(R.layout.activity_login);

        //点击事件
        Button btlogin = (Button) this.findViewById(R.id.btlogin);
        Button btregister = (Button) this.findViewById(R.id.btregister);

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(LoginActivity.this, LoginFormalActivity.class);
                startActivities(new Intent[]{intent1});
            }
        });

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent();
                intent2.setClass(LoginActivity.this, RegisterActivity.class);
                startActivities(new Intent[]{intent2});
            }
        });

    }

}
