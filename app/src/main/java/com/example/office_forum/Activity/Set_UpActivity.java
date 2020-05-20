package com.example.office_forum.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.R;
import com.example.office_forum.WxShareUtils;

import cn.bmob.v3.BmobUser;

import static com.example.office_forum.Fragment.MineFragment.mLogin;

public class Set_UpActivity extends BaseStatusBarActivity {
    View mLogin_Out;
    private ImageView mReturn;
   private View mShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__up);
        mLogin_Out=findViewById(R.id.login_out);
        mReturn=findViewById(R.id.set_up_return);
        mShare=findViewById(R.id.share_view);
        if(!BmobUser.isLogin()){
            mLogin_Out.setVisibility(mLogin_Out.GONE);
        }

        mLogin_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut();
                mLogin.setText("登录/注册");
                finish();
            }
        });
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(Set_UpActivity.this).asBitmap().load(R.mipmap.ic_newicon01).into(new SimpleTarget<Bitmap>() {
                    /**
                     * 成功的回调
                     */
                    @Override
                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                        // 下面这句代码是一个过度dialog，因为是获取网络图片，需要等待时间

                        // 调用方法
                        WxShareUtils.shareWeb(Set_UpActivity.this, "wx216cb4f8b560590b",
                                "https://linchen.pro/officeS", "善用officeS", "一款学习office的APP",
                                bitmap);
                    }

                    /**
                     * 失败的回调
                     */
                    @Override
                    public void onLoadFailed(@Nullable Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);


                        WxShareUtils.shareWeb(Set_UpActivity.this, "wx216cb4f8b560590b",
                                "https://linchen.pro/officeS", "善用officeS", "一款学习office的APP"
                                ,
                                null);
                    }
                });

            }
        });
    }
}
