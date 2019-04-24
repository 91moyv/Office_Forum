package com.example.office_forum.Post_Adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.office_forum.Post_Date.All_Post_Data;
import com.example.office_forum.Post_Date.Post_Data;
import com.example.office_forum.R;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_Post_Adapter extends BaseAdapter {
    private Context context ;
    private List<All_Post_Data> list;
    private String key;
    public Search_Post_Adapter(Context context, List<All_Post_Data> list,String key){
        this.context=context;
        this.list=list;
        this.key=key;
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

        TextView post_title=convertView.findViewById(R.id.post_title);
        TextView post_content=convertView.findViewById(R.id.post_content);
        TextView post_username=convertView.findViewById(R.id.post_username);
        TextView post_date=convertView.findViewById(R.id.post_date);
        post_title.setText(matcherSerch(Color.rgb(86,202,90),list.get(position).getPost_title(),key));
       /* matcherSerch(Color.rgb(86,202,90),list.get(position).getPost_content(),key);*/
        post_content.setText(matcherSerch(Color.rgb(86,202,90),list.get(position).getPost_content(),key));

        post_username.setText(list.get(position).getPost_username());
        post_date.setText(list.get(position).getCreatedAt());
        return convertView;
    }
    public static SpannableString matcherSerch(int color, String text, String keyword){
        String string= text.toLowerCase();
        String key=keyword.toLowerCase();
        Pattern pattern = Pattern.compile(key);
        Matcher matcher=pattern.matcher(string);
        SpannableString ss=new SpannableString(text);
        while (matcher.find()){
            int start=matcher.start();
            int end = matcher.end();
            ss.setSpan(new ForegroundColorSpan(color),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return ss;
    }
}
