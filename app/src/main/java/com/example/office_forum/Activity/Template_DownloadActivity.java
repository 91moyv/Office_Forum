package com.example.office_forum.Activity;

import android.app.ProgressDialog;
import android.os.Environment;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.DownloadUtil;
import com.example.office_forum.R;

import java.io.File;

public class Template_DownloadActivity extends BaseStatusBarActivity {
    ProgressDialog progressDialog;
    Button mButton;
    private Button mReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_template__download);
        mButton=findViewById(R.id.download_ppt);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downFile("https://yeek.top/officeS/PPT/template/水墨竹子.pptx");
            }
        });
        mReturn=findViewById(R.id.template_download_return);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void downFile(String url) {
        String filename = "";
        //获取文件名字（链接最后的名字）
        filename = url.substring(url.lastIndexOf("/") + 1);

        progressDialog = new ProgressDialog(Template_DownloadActivity.this);
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
                    Toast.makeText(Template_DownloadActivity.this,"下载成功",Toast.LENGTH_LONG).show();
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
                Toast.makeText(Template_DownloadActivity.this,"下载错误"+e.toString(),Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
                Looper.loop();
            }
        });
    }

}
