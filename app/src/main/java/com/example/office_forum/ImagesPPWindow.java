package com.example.office_forum;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.office_forum.Activity.Template_DownloadActivity;

import java.io.File;

public class ImagesPPWindow extends PopupWindow {
   private ProgressDialog progressDialog;
    private Context context;
    private String ImagesUrl;
    private Activity mActivity;
    public ImagesPPWindow(Context context,String ImagesUrl,Activity activity){
        super(context);
        this.ImagesUrl=ImagesUrl;
        this.context = context;
        mActivity=activity;
        setBackgroundDrawable(null);
        Initialize_Layout();
        requestAllPower();
    }
    private void Initialize_Layout() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.images_ppwindow, null);
        ImageView imageView=view.findViewById(R.id.imageView_ppw);
        TextView textView=view.findViewById(R.id.images_ppw_return);
        Button button=view.findViewById(R.id.images_dow);
        Glide.with(context).load(ImagesUrl)
                .into(imageView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestAllPower();
                downFile(ImagesUrl);
            }
        });
        setContentView(view);
        initWindow();
    }

    private void initWindow() {
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        /* this.setWidth((int) (d.widthPixels * 0.6));*/
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();



    }
    public void showAtBottom(View view) {
        //弹窗位置设置
        showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 50);

        //showAtLocation(view, Gravity.TOP | Gravity.CENTER, 10, 1000);
        //showAtLocation(view, Gravity.TOP | Gravity.RIGHT, 10, 110);//有偏差
        //showAsDropDown(view, Math.abs((view.getWidth() - getWidth()) / 2), 0);

    }
    public void downFile(String url) {
        String filename = "";
        //获取文件名字（链接最后的名字）
        filename = url.substring(url.lastIndexOf("/") + 1);

        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("正在下载");
        progressDialog.setMessage("请稍后...");
        progressDialog.setProgress(0);
        progressDialog.setMax(100);
        progressDialog.show();
        progressDialog.setCancelable(false);
        DownloadUtil.get().download(url, Environment.getExternalStorageDirectory().getAbsolutePath(),filename, new DownloadUtil.OnDownloadListener() {
            @Override
            public void onDownloadSuccess(File file) {
                if (progressDialog != null && progressDialog.isShowing()) {
                    Looper.prepare();
                    Toast.makeText(context,"下载成功",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                    Looper.loop();
                }
                //下载完成进行相关逻辑操作

            }

            @Override
            public void onDownloading(int progress) {
                progressDialog.setProgress(progress);
            }

            @Override
            public void onDownloadFailed(Exception e) {
                //下载异常进行相关提示操作
                Looper.prepare();
                Toast.makeText(context,"下载错误"+e.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                Looper.loop();
            }
        });
    }
    public void requestAllPower() {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(mActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.INTERNET}, 1);
            }
        }
    }
}
