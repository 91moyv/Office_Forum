package com.example.office_forum;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.office_forum.Post_Date.Examine_Post_Data;

import java.util.ArrayList;
import java.util.List;

public class PS_Template_Adapter extends BaseAdapter {
    private Context context ;
    private List<PS_Template_Images> list;
    private Activity mActivity;

    public PS_Template_Adapter(Context context, List<PS_Template_Images> list,Activity mActivity) {
        this.context = context;
        this.list = list;
        this.mActivity=mActivity;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.ps_template_item, null);

        final ImageView imageView1=convertView.findViewById(R.id.ps_item_images1);
        final ImageView imageView2=convertView.findViewById(R.id.ps_item_images2);
        final Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case 0:

                        Glide.with(context).load(list.get(position).getImages1())

                                .into(imageView1);
                        Glide.with(context).load(list.get(position).getImages2())
                                .into(imageView2);
                        break;
                    default:
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
               /* Glide.with(context).load(list.get(position).getImages1())
                        .into(imageView1);
                Glide.with(context).load(list.get(position).getImages2())
                        .into(imageView2);*/
               Message msg = new Message();
                mHandler.sendMessage(msg);
            }
        }).start();
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagesPPWindow(context,list.get(position).getImages1(),mActivity).showAtBottom(imageView1);
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImagesPPWindow(context,list.get(position).getImages2(),mActivity).showAtBottom(imageView2);
            }
        });
        return convertView;
    }
}
