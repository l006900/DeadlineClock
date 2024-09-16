package com.example.deadlineclock.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.User;
import com.example.deadlineclock.util.MyDataHelper;


public class RegisterActivity extends AppCompatActivity {

    EditText name, password;
    Button register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局
        setContentView(R.layout.activity_register);

        //添加账户
        name = findViewById(R.id.name);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        //点击事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(-1,password.getText().toString(), name.getText().toString());
                MyDataHelper myDataHelper = new MyDataHelper(RegisterActivity.this);
                String s = myDataHelper.addOne(user);
                Toast.makeText(RegisterActivity.this,"注册成功", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this,MainMainActivity.class);
                startActivities(new Intent[]{intent});
                finish();
            }
        });


    }

}
