package com.example.office_forum.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.R;

public class WelcomeActivity extends BaseStatusBarActivity {
    private Button mtg;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //接收到消息后跳转
            if(ifonclick==false) {
                goMain();
                super.handleMessage(msg);
            }
        }
    };
    boolean ifonclick =false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //透明状态栏
        /*setNavigationBarStatusBarTranslucent(WelcomeActivity.this);*/


        //点击立即跳过
        mtg=findViewById(R.id.tg);
        mtg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goMain();
                ifonclick=true;
            }
        });
        //延迟发送消息

        handler.sendEmptyMessageDelayed(0,5000);
    }

    private void goMain() {
        //设定调动其他的Activity、Service
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);  //将控制权交给MainActivity
        finish();   //结束
    }

    public static void setNavigationBarStatusBarTranslucent(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

    }
}
