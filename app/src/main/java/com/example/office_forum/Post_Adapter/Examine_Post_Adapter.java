package com.example.office_forum.Post_Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.office_forum.Post_Date.Examine_Post_Data;
import com.example.office_forum.Post_Date.Post_Data;
import com.example.office_forum.R;

import java.util.List;

public class Examine_Post_Adapter extends BaseAdapter {
    private Context context ;
    private List<Examine_Post_Data> list;

    public Examine_Post_Adapter(Context context, List<Examine_Post_Data> list){
        this.context=context;
        this.list=list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.home_list_item, null);
        TextView post_type=convertView.findViewById(R.id.post_type);
        TextView post_title=convertView.findViewById(R.id.post_title);
        TextView post_content=convertView.findViewById(R.id.post_content);
        TextView post_username=convertView.findViewById(R.id.post_username);
        TextView post_date=convertView.findViewById(R.id.post_date);
        post_title.setText(list.get(position).getPost_title());
        post_content.setText(list.get(position).getPost_content());
        post_username.setText(list.get(position).getPost_username());
        post_type.setText(list.get(position).getPost_type());
        post_date.setText(list.get(position).getCreatedAt());
        return convertView;
    }
}