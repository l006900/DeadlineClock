package com.example.deadlineclock.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.DateBean;
import com.example.deadlineclock.util.DateDadaHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    private View view;
    private DrawerLayout mDrawerLayout;
    ListView view_all;
    DateDadaHelper dateDadaHelper;

    private ViewPagerAdapter mAdapter;
    private List<ViewPagerAdapter.Banner> banners;
    private ViewPager2 mViewpager;

    private static final String TAG = "YourCustomTag";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        setHasOptionsMenu(true);

        //banner
        List<ViewPagerAdapter.Banner> banners = new ArrayList<>();
        banners.add(new ViewPagerAdapter.Banner("banner1", R.drawable.i1, LoginActivity.class));
        banners.add(new ViewPagerAdapter.Banner("22222", R.drawable.i2, RegisterActivity.class));

        mViewpager = view.findViewById(R.id.viewPager);
        mAdapter = new ViewPagerAdapter(getActivity(),banners);
        mViewpager.setAdapter(mAdapter);

        mAdapter.setOnViewPagerClickListener(new OnViewPagerClickListener() {
            @Override
            public void onItemClick(int Position, List<ViewPagerAdapter.Banner> banners) {
                Intent intent = new Intent(getActivity(), banners.get(Position).getTargetActivity());
                startActivity(intent);
            }
        });


        //点击事件
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(getActivity(), NewDeadlineActivity.class);
                startActivity(intent1);
                getActivity().overridePendingTransition(R.anim.zoom_in, R.anim.static_animation);
            }

        });

        //toolbar
        Toolbar toolbar = view.findViewById(R.id.toolbar_home);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        //drawerlayout
        mDrawerLayout = view.findViewById(R.id.fragment_home);
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

        //
        view_all = view.findViewById(R.id.view_all);
        view_and_update();

        //点按
/*        view_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent4 = new Intent(HomeActivity.this, CompleteActivity.class);
                startActivity(intent4);
            }
        });*/

        //长按
        view_all.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Map<String, String> map = (Map<String, String>) adapterView.getItemAtPosition(i);

                if (map != null) {
                    Integer id = Integer.valueOf(map.get("id"));
                    String title = map.get("title");
                    String startdate = map.get("startdate");
                    String starttime = map.get("starttime");
                    String enddate = map.get("enddate");
                    String endtime = map.get("endtime");


                    DateBean dateBean = new DateBean(id, title, startdate, starttime, enddate, endtime);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
                    dialog.setTitle("请选择操作");
                    dialog.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String s = dateDadaHelper.deleteOne(dateBean);
                            Toast.makeText(getActivity(), "DELETE:" + s, Toast.LENGTH_SHORT).show();
                            view_and_update();
                            Log.d(TAG, "onClick: ");

                        }
                    });
                    dialog.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent2 = new Intent(getActivity(), UpdateDeadline.class);
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

        return view;
    }

    //刷新显示
    private void view_and_update() {
        dateDadaHelper = new DateDadaHelper(getActivity());
        String[] from = {"title", "enddate", "endtime"};
        int[] to = {R.id.list_title, R.id.list_date, R.id.list_time};
        SimpleAdapter adapter1 = new SimpleAdapter(getActivity(),
                dateDadaHelper.mapList(),
                R.layout.listview_item,
                from, to);
        view_all.setAdapter(adapter1);
    }

    @Override
    public void onResume() {
        super.onResume();
        //listview+刷新查找
        view_all = view.findViewById(R.id.view_all);

        dateDadaHelper = new DateDadaHelper(getActivity());
        view_and_update();
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