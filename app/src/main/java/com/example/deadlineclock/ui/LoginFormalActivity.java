package com.example.deadlineclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.User;
import com.example.deadlineclock.util.DateDadaHelper;
import com.example.deadlineclock.util.MyDataHelper;

import android.database.Cursor;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;


public class LoginFormalActivity extends AppCompatActivity {

    EditText name1, password1;
    Button login;
    MyDataHelper myDataHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局
        setContentView(R.layout.activity_login_formal);

        //
        myDataHelper = new MyDataHelper(this);

        //登录查询账号
/*        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String name = intent.getStringExtra("name");
        String password = intent.getStringExtra("password");*/

        name1 = findViewById(R.id.name1);
        password1 = findViewById(R.id.password1);
        login = findViewById(R.id.login);

/*        name1.setText(name);
        password1.setText(password);*/


        //点击事件
        Button login = (Button) this.findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = name1.getText().toString();
                String s1 = password1.getText().toString();

                boolean login1 = myDataHelper.login(s, s1);
                if(login1){
                    Intent intent3 = new Intent();
                    intent3.setClass(LoginFormalActivity.this, MainMainActivity.class);
                    startActivities(new Intent[]{intent3});
                    finish();

                }else {
                    Toast.makeText(LoginFormalActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}
