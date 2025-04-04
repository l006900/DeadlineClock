package com.example.deadlineclock.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.deadlineclock.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 *
 */
public class MainMainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    private HomeFragment mHomeFragment;
    private MoreFragment mMoreFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmain);

        // 设置沉浸式模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }


        //初始化控件
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavigation);

        selectedFragment(0);

        //bottom点击事件
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.bottom_home) {
                    selectedFragment(0);
                } else if (item.getItemId() == R.id.bottom_find) {
                    selectedFragment(1);
                } else {
                    selectedFragment(2);
                }

                return true;
            }
        });
    }

    private void selectedFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        if (position == 0) {
            if (mHomeFragment == null) {
                mHomeFragment = new HomeFragment();
                fragmentTransaction.add(R.id.bottomframelayout, mHomeFragment);
            } else {
                fragmentTransaction.show(mHomeFragment);
            }
        } else if (position == 1) {
            if (mMoreFragment == null) {
                mMoreFragment = new MoreFragment();
                fragmentTransaction.add(R.id.bottomframelayout, mMoreFragment);
            } else {
                fragmentTransaction.show(mMoreFragment);
            }
        } else {
            if (mMineFragment == null) {
                mMineFragment = new MineFragment();
                fragmentTransaction.add(R.id.bottomframelayout, mMineFragment);
            } else {
                fragmentTransaction.show(mMineFragment);
            }
        }

        //提交
        fragmentTransaction.commit();

    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mHomeFragment != null) {
            fragmentTransaction.hide(mHomeFragment);
        }

        if (mMoreFragment != null) {
            fragmentTransaction.hide(mMoreFragment);
        }

        if (mMineFragment != null) {
            fragmentTransaction.hide(mMineFragment);
        }
    }
}
