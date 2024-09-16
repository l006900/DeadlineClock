/*
package com.example.deadlineclock.ui;

import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.deadlineclock.R;

import java.util.ArrayList;
import java.util.List;

public class ReportActivity extends AppCompatActivity {


    //TODO 这个是我们需要传入的，目前现在里面默认搞个静态的
    private List<CardAdapter.Card> cards = new ArrayList<>();
    private CardAdapter cardAdapter;

    private RecyclerView recyclerView;

    //由于我们使用的是两行瀑布流
    //为此我们的绝对位置是一个两位的一维数组
    public int[] firstStaggeredGridPosition = {0, 0};
    public int[] lastStaggeredGridPosition = {0, 0};

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        //这里是添加图片素材的地方
        List<CardAdapter.Card> cards = new ArrayList<>();
        cards.add(new CardAdapter.Card("吃樱桃", R.drawable.f1, 643, 900));
        cards.add(new CardAdapter.Card("打游戏", R.drawable.f2, 1117, 1800));
        cards.add(new CardAdapter.Card("红发", R.drawable.f3, 1286, 1964));
        cards.add(new CardAdapter.Card("绿毛", R.drawable.f4, 1138, 1532));
        cards.add(new CardAdapter.Card("端午快乐", R.drawable.f5, 1280, 720));
        cards.add(new CardAdapter.Card("吃樱桃", R.drawable.f1, 643, 900));
        cards.add(new CardAdapter.Card("打游戏", R.drawable.f2, 1117, 1800));
        cards.add(new CardAdapter.Card("红发", R.drawable.f3, 1286, 1964));
        cards.add(new CardAdapter.Card("绿毛", R.drawable.f4, 1138, 1532));


        //找到RV
        recyclerView = findViewById(R.id.findRV);
        //getActivity是获取当前的Context对象
        cardAdapter = new CardAdapter(this, cards);
        //瀑布流布局属性设置
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);


        //滚动监听事件-------------------------------
        //设置recycleView的滚动监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //该if判断的是滚动的状态，其意义是放置不断的刷新if内的语句
                if (newState == SCROLL_STATE_IDLE || newState == SCROLL_STATE_DRAGGING) {
                    // DES: 找出当前可视Item位置
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    //我们使用的layoutManager是StaggeredGrid
                    if (layoutManager instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager linearManager = (StaggeredGridLayoutManager) layoutManager;
                        //获取绝对坐标
                        linearManager.findFirstVisibleItemPositions(firstStaggeredGridPosition);
                        linearManager.findLastVisibleItemPositions(lastStaggeredGridPosition);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        //设置cardAdapter的Item监听
        cardAdapter.setOnRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, List<CardAdapter.Card> cards) {
                //设置跳转至另一个activity的intent-----------------------
                Intent intent = new Intent(ReportActivity.this, CardInfoActivity.class);
                //传递相应的参数
                //我们需要把构成一个图片的信息传递过去
                Bundle bundle = new Bundle();
                bundle.putInt("cardImageId", cards.get(Position).getImageId());
                bundle.putString("cardTitle", cards.get(Position).getTitle());
                bundle.putInt("height", cards.get(Position).getHeight());
                bundle.putInt("width", cards.get(Position).getWidth());
                intent.putExtras(bundle);

                //以下是过渡动画代码区域-----------------------
                //启用共享组件的activity过渡
                //所选择的共享元件，这个元件是当前页面的元件
                //获取item的ViewHolder
                //因为我使用的是StaggeredGridLayoutManager
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //由于瀑布流是两列，这里是为了获得处在页面的最小值
                int realFirstPosition = Math.min(firstStaggeredGridPosition[0], firstStaggeredGridPosition[1]);
                CardAdapter.MyViewHolder viewHolder = (CardAdapter.MyViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(Position - realFirstPosition));

                ImageView card_info_image = viewHolder.card_image;
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ReportActivity.this,
                        Pair.create(card_info_image, "card_info_image"));

                //跳转至另一个activity-----------------------
                startActivity(intent, options.toBundle());
            }
        });

        recyclerView.setAdapter(cardAdapter);
    }
}*/
