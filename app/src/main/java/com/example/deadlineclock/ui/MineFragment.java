package com.example.deadlineclock.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.deadlineclock.R;
import com.google.android.material.navigation.NavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends Fragment {

    private View view;
    private DrawerLayout mDrawerLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        setHasOptionsMenu(true);

        //toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //drawerlayout
        mDrawerLayout = view.findViewById(R.id.fragment_mine);
        NavigationView navView = view.findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);

        //HomeAsUp
        ActionBar actionBar = ((AppCompatActivity)getActivity()).getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.person);
        }

        //更改头像
        CircleImageView icon_head = headerView.findViewById(R.id.icon_head);
        icon_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Glide.with(HomeActivity.this).load("https://www.goupuzi.com/newatt/Mon_1905/1_179959_835e436f4392dce.jpg").into(icon_head);
                startActivity(new Intent(getActivity(), HeaderActivity.class));
                getActivity().overridePendingTransition(R.anim.header_in, R.anim.static_animation);

            }
        });

        //左划抽屉
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                switch (itemId) {
                    case R.id.nav_friends:
                        // 处理"Call"菜单项的点击事件
                        // 例如，打开一个电话拨打界面或跳转到相关Activity
//                        startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                        break;
                }
                mDrawerLayout.closeDrawers();
                return false;
            }

        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_home,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout != null) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}