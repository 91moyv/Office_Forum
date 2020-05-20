package com.example.office_forum.Fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.office_forum.Activity.PPT_VideoActivity;
import com.example.office_forum.R;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurriculumFragment extends Fragment {
    private Banner mBanner_Class;
    private List<Integer> mimages = new ArrayList<>();
    private View mPPT_Video;
    private View mWord_Video;
    private View mPS_Video;
    private View mExcel_Video;
    public CurriculumFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View view= inflater.inflate(R.layout.fragment_curriculum, container, false);
        mBanner_Class=view.findViewById(R.id.banner_class);
        mPPT_Video=view.findViewById(R.id.ppt_video);
        mWord_Video=view.findViewById(R.id.word_video);
        mPS_Video=view.findViewById(R.id.ps_video);
        mExcel_Video=view.findViewById(R.id.excel_video);
        mimages.add(R.drawable.banner2);
        mimages.add(R.drawable.banner3);
        mimages.add(R.drawable.banner1);
        mBanner_Class.setBannerStyle(BannerConfig.NUM_INDICATOR);
        //设置图片加载器
     mBanner_Class.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner_Class.setImages(mimages);
        //设置轮播时间
        mBanner_Class.setDelayTime(2000);
        //banner设置方法全部调用完毕时最后调用
        mBanner_Class.start();
        mPPT_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PPT_VideoActivity.class));
            }
        });
        mWord_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"不更了，word视频在下一个app里见",Toast.LENGTH_SHORT).show();
            }
        });
        mExcel_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"不更了，Excel视频在下一个app里见",Toast.LENGTH_SHORT).show();
            }
        });
        mPS_Video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"不更了，PS视频在下一个app里见",Toast.LENGTH_SHORT).show();
            }
        });
      return view;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource((Integer) path);
        }

        //提供createImageView 方法，如果不用可以不重写这个方法，主要是方便自定义ImageView的创建
        @Override
        public ImageView createImageView(Context context) {
            //使用fresco，需要创建它提供的ImageView，当然你也可以用自己自定义的具有图片加载功能的ImageView
            return new ImageView(context);
        }
    }

}
