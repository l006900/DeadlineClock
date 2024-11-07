package com.example.deadlineclock.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.deadlineclock.R;
import com.example.deadlineclock.util.ScreenUtil;

import java.util.List;

//Card匹配器
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {
    private View view;
    private Context context;
    private List<Card> cards;


    //定义监听的接口
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    //定义一个方法可以直接提供调用
    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener){
        this.onRecyclerItemClickListener=onRecyclerItemClickListener;
    }

    public CardAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Card card = cards.get(position);
        //放入照片
        Glide.with(context).load(card.getImageUrl()).into(holder.card_image);
//        holder.card_image.setImageResource(card.getImageId());
        ViewGroup.LayoutParams params = holder.card_image.getLayoutParams();
        //TODO 显然，这高度是由这个参数决定的，如果我们知道了宽的大小width，那么我们就能知道实际缩放比
        //获取屏幕的宽度
        int screenWidth = ScreenUtil.getScreenWidth(context);
        //调整放入图片的大小，保证宽一定是屏幕的一半，高度随着缩放而改变
        float scale = (float)card.getHeight() / (float)card.getWidth();
        params.height =  (int) (screenWidth/2 * scale);
        //设置图片的参数
        holder.card_image.setLayoutParams(params);
        //放入文字
        holder.card_title.setText(card.getTitle());

    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView card_image;
        TextView card_title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            card_image = (ImageView) itemView.findViewById(R.id.card_image);
            card_title = (TextView) itemView.findViewById(R.id.card_title);

            //实现对于itemView的监听
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerItemClickListener!=null){
                        //可以发现，点击后获取的是某个数据的Position，那么自然可以找到对应的cards信息
                        onRecyclerItemClickListener.onItemClick(getBindingAdapterPosition(),cards);
                    }
                }
            });
        }
    }

    //Item类
    public static class Card {
        private String title;
        private String imageUrl;
        private int imageId;
        private int width;
        private int height;

        public Card(String title, String imageUrl, int imageId, int width, int height) {
            this.title = title;
            this.imageUrl = imageUrl;
            this.imageId = imageId;
            this.width = width;
            this.height = height;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public String getTitle() {
            return title;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public int getImageId() {
            return imageId;
        }
    }
}

//自定义监听接口
interface OnRecyclerItemClickListener {
    //RecyclerView的点击事件，将信息回调给view
    void onItemClick(int Position, List<CardAdapter.Card> cards);
}
