package com.example.deadlineclock.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.deadlineclock.R;
import com.example.deadlineclock.bean.ListModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<ListModel> {

    private Context mcontext;
    private int mresource;

    public ListViewAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ListModel> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.mresource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        convertView = LayoutInflater.from(this.mcontext).inflate(this.mresource, parent, false);

        TextView title = convertView.findViewById(R.id.list_title);
        TextView time = convertView.findViewById(R.id.list_time);

        title.setText(getItem(position).getList_title());
        time.setText(getItem(position).getList_time());

        return convertView;
    }
}
