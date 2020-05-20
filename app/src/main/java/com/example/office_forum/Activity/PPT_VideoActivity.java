package com.example.office_forum.Activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Exercises_PPWindow;
import com.example.office_forum.R;

import java.util.HashMap;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;

import static cn.jzvd.JZVideoPlayer.CLICK_QUIT_FULLSCREEN_TIME;
import static cn.jzvd.JZVideoPlayer.FULL_SCREEN_NORMAL_DELAY;

public class PPT_VideoActivity extends BaseStatusBarActivity {
    private JZVideoPlayerStandard mJZVideoPlayerStandard;
    private BubblePopupWindow mBubblePopupWindow;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    mBubblePopupWindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };
    private Button mDo_Exercises;
    ListView mVideo_ListView;
    String[]video_list= {"1 如何安装PPT2013","2 PPT如何保存成不同版本","3 如何插入文字、形状、图片","4 如何把照片设置成PPT背景"
    ,"5 PPT的宽屏和普屏是什么意思","6 PPT要怎么更改背景颜色","7 PPT最经常使用什么字体","8 怎么能使PPT不让人修改",
            "9 如何组合与取消组合PPT里的图片和文字","10 PPT取色器要怎么用"
    };

    String [] video_url={"https://yeek.top/officeS/PPT/videos/PPT01.mp4","https://yeek.top/officeS/PPT/videos/PPT02.mp4",
            "https://yeek.top/officeS/PPT/videos/PPT03.mp4", "https://yeek.top/officeS/PPT/videos/PPT04.mp4",
            "https://yeek.top/officeS/PPT/videos/PPT05.mp4","https://yeek.top/officeS/PPT/videos/PPT06.mp4",
            "https://yeek.top/officeS/PPT/videos/PPT07.mp4","https://yeek.top/officeS/PPT/videos/PPT08.mp4",
            "https://yeek.top/officeS/PPT/videos/PPT09.mp4", "https://yeek.top/officeS/PPT/videos/PPT10.mp4",};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt__video);
        mVideo_ListView=findViewById(R.id.video_list);
        mDo_Exercises=findViewById(R.id.bt_do_exercises);
        mJZVideoPlayerStandard=findViewById(R.id.tv);
        VideoBaseAdapter videoBaseAdapter = new VideoBaseAdapter();
        mVideo_ListView.setAdapter(videoBaseAdapter);
       Glide.with(getApplicationContext()).load("https://yeek.top/officeS/PPT/images/PPT01.jpg")
                .into(mJZVideoPlayerStandard.thumbImageView);
       /* mJZVideoPlayerStandard.widthRatio = 9;//播放比例,可以设置为16:9,4:3
        mJZVideoPlayerStandard.heightRatio = 16;*/
        //将缩略图的scaleType设置为FIT_XY（图片全屏）
        mJZVideoPlayerStandard.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        mJZVideoPlayerStandard.setUp("https://yeek.top/officeS/PPT/videos/PPT01.mp4", JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,"1 如何安装PPT2013");
        //设置容器内播放器高,解决黑边（视频全屏）
       /*JZVideoPlayer.setVideoImageDisplayType(JZVideoPlayer.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);*/
        mVideo_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mJZVideoPlayerStandard.setUp(video_url[position], JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL,video_list[position]);
            }
        });

        mDo_Exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              new Exercises_PPWindow(PPT_VideoActivity.this).showAtBottom(mVideo_ListView);
            }
        });



    }


    @Override
    public void onBackPressed() {
        if(JZVideoPlayer.backPress()){
            return;
        }

        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    class VideoBaseAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return video_list.length;
        }

        @Override
        public Object getItem(int position) {
            return video_list[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(PPT_VideoActivity.this,R.layout.video_list_item,null);
            TextView textView = view.findViewById(R.id.video_item);
            textView.setText(video_list[position]);



            return view;
        }
    }
    private void showppwindow() {
        mBubblePopupWindow.showArrowTo(mDo_Exercises, BubbleStyle.ArrowDirection.Up,10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg =new Message();
                mHandler.sendMessage(msg);
            }
        }).start();
    }
}