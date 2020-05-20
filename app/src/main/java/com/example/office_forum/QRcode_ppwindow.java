package com.example.office_forum;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.office_forum.Activity.PostContentActivity;

public class QRcode_ppwindow extends PopupWindow {
    private  Context context;
    private ImageView mImageView;
    private String ImagesUrl;
    public QRcode_ppwindow(Context context,String ImagesUr){
        super(context);
        this.ImagesUrl=ImagesUr;
        this.context = context;
        setBackgroundDrawable(null);

        Initialize_Layout();

    }
    private void Initialize_Layout() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.qrcode_ppwindow, null);
        mImageView=view.findViewById(R.id.ppw_qrcode);
        if(ImagesUrl!=null){
            StringBuilder stringBuilder = new StringBuilder(ImagesUrl);
            stringBuilder.insert(4, "s");
            ImagesUrl = stringBuilder.toString();
            Glide.with(context).load(ImagesUrl)
                    .into(mImageView);

        }
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        /* this.setWidth((int) (d.widthPixels * 0.6));*/
        this.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();



    }


    public void showAtBottom(View view) {
        //弹窗位置设置
        showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 500);
        //showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 1000);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
        //showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 0);

    }
}
