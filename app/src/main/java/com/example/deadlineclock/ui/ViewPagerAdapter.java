package com.example.deadlineclock.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.deadlineclock.R;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {

    private View view;
    private List<Banner> banners;
    private LayoutInflater mInflater;

    //监听接口
    private OnViewPagerClickListener onViewPagerClickListener;

    public void setOnViewPagerClickListener(OnViewPagerClickListener onViewPagerClickListener){
        this.onViewPagerClickListener = onViewPagerClickListener;
    }

    public ViewPagerAdapter(Context context, List<Banner> banners){
        this.mInflater = LayoutInflater.from(context);
        this.banners = banners;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = mInflater.inflate(R.layout.viewpager,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Banner banner = banners.get(position);
        holder.textView.setText(banner.getText());
        holder.imageView.setImageResource(banner.getImageId());


    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public ViewHolder(View itemText){
            super(itemText);
            textView = itemText.findViewById(R.id.vp_textview);
            imageView = itemText.findViewById(R.id.vp_imageview);

            //监听
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onViewPagerClickListener!=null){
                        onViewPagerClickListener.onItemClick(getBindingAdapterPosition(), banners);
                    }
                }
            });
        }
    }

    public static class Banner {
        private String text;
        private int imageId;
        private Class<?> targetActivity;

        public Banner(String text, int imageId, Class<?> targetActivity){
            this.imageId = imageId;
            this.text = text;
            this.targetActivity = targetActivity;
        }

        public String getText() {
            return text;
        }

        public int getImageId() {
            return imageId;
        }

        public Class<?> getTargetActivity() {
            return targetActivity;
        }
    }
}

//自定义接口
interface OnViewPagerClickListener{
    void onItemClick(int Position, List<ViewPagerAdapter.Banner> banners);
}