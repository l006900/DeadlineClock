package com.example.deadlineclock.ui;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.deadlineclock.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class HeaderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            // 当用户按下屏幕时，调用finish()关闭当前Activity，返回上一个页面
            finish();
            // 可选：如果你想定义一个过渡动画，可以在这里设置
//                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        return super.dispatchTouchEvent(event);
    }

}
