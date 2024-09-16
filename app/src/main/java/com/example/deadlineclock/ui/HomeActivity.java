/*
package com.example.deadlineclock.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

//import com.example.deadlineclock.Manifest;
import com.bumptech.glide.Glide;
import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.DateBean;
import com.example.deadlineclock.util.DateDadaHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity {

    ListView view_all;
    DateBean dateBean;
    DateDadaHelper dateDadaHelper;
    CircleImageView icon_head;

    private DrawerLayout mDrawerLayout;
    private static final String TAG = "YourCustomTag";


    @Override
    protected void onRestart() {
        super.onRestart();
        view_and_update();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置布局
        setContentView(R.layout.activity_home);

        //点击事件
        FloatingActionButton fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(HomeActivity.this, NewDeadlineActivity.class);
                startActivity(intent1);
                overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
            }

        });

        //toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbar);

        //drawerlayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.home);
        NavigationView navView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navView.getHeaderView(0);

        //更改头像
        CircleImageView icon_head = headerView.findViewById(R.id.icon_head);
        icon_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Glide.with(HomeActivity.this).load("https://www.goupuzi.com/newatt/Mon_1905/1_179959_835e436f4392dce.jpg").into(icon_head);
                startActivity(new Intent(HomeActivity.this, HeaderActivity.class));
                overridePendingTransition(R.anim.header_in, R.anim.static_animation);

            }
        });

        //HomeAsUp
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.person);
        }

        //

        //左划抽屉
//        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int itemId = item.getItemId();

                switch (itemId) {
                    case R.id.nav_friends:
                        // 处理"Call"菜单项的点击事件
                        // 例如，打开一个电话拨打界面或跳转到相关Activity
                        startActivity(new Intent(HomeActivity.this, ReportActivity.class));
                        break;
                }
                mDrawerLayout.closeDrawers();
                return false;
            }

        });

        //
        view_all = findViewById(R.id.view_all);
        view_and_update();

        //修改+按钮
        //点按
*/
/*        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent4 = new Intent(HomeActivity.this, CompleteActivity.class);
                startActivity(intent4);
            }
        });*//*


        //长按
        view_all.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
//                DateBean dateBean = (DateBean) adapterView.getItemAtPosition(i);

                Map<String, String> map = (Map<String, String>) adapterView.getItemAtPosition(i);

                if (map != null) {
                    Integer id = Integer.valueOf(map.get("id"));
                    String title = map.get("title");
                    String startdate = map.get("startdate");
                    String starttime = map.get("starttime");
                    String enddate = map.get("enddate");
                    String endtime = map.get("endtime");


                    DateBean dateBean = new DateBean(id, title, startdate, starttime, enddate, endtime);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(HomeActivity.this);
                    dialog.setTitle("请选择操作");
                    dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String s = dateDadaHelper.deleteOne(dateBean);
                            Toast.makeText(HomeActivity.this, "DELETE:" + s, Toast.LENGTH_SHORT).show();
                            view_and_update();
                            Log.d(TAG, "onClick: ");

                        }
                    });
                    dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent2 = new Intent(HomeActivity.this, UpdateDeadline.class);
                            intent2.putExtra("id", dateBean.getId());
                            intent2.putExtra("title", dateBean.getTitle());
                            intent2.putExtra("startdate", dateBean.getStartdate());
                            intent2.putExtra("starttime", dateBean.getStarttime());
                            intent2.putExtra("enddate", dateBean.getEnddate());
                            intent2.putExtra("endtime", dateBean.getEndtime());

                            Log.d(TAG, "onClick: ");

                            startActivity(intent2);
                        }
                });
                    dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    dialog.create();
                    dialog.show();
                }
                return false;
            }
        });

    }

    //刷新显示
    private void view_and_update() {
        dateDadaHelper = new DateDadaHelper(HomeActivity.this);
        String[] from = {"title", "enddate", "endtime"};
        int[] to = {R.id.list_title, R.id.list_date, R.id.list_time};
        SimpleAdapter adapter1 = new SimpleAdapter(HomeActivity.this,
                dateDadaHelper.mapList(),
                R.layout.listview_item,
                from, to);
        view_all.setAdapter(adapter1);
    }

    @Override
    public void onResume() {
        super.onResume();
        //listview+刷新查找
        view_all = findViewById(R.id.view_all);

        dateDadaHelper = new DateDadaHelper(HomeActivity.this);
        view_and_update();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
        }
        return true;
    }

}
*/
