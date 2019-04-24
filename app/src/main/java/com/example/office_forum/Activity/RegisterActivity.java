package com.example.office_forum.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends BaseStatusBarActivity {
    private static final String TAG = "RegisterActivity";

    private final int SUCCESS = 0;
    private final int FAILURE = -1;
    Button mOK;
    Button mBt_Send;
    EditText mPhone;
    EditText mCode;
    EditText mPassword;
    EditText mRepassword;
    private boolean codeOK = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mOK=findViewById(R.id.bt_ok);
        mBt_Send=findViewById(R.id.bt_start_send);
        mPhone=findViewById(R.id.edt_phone);
        mCode=findViewById(R.id.edt_code);
        mPassword=findViewById(R.id.edt_password);
        mRepassword=findViewById(R.id.edt_repassword);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (checkpassword()) {
                    checkcode();
                    if (codeOK) {
                        Post_User user = new Post_User ();
                        user.setUsername(getInput(mPhone));

                        user.setPassword(getInput(mPassword));
                        user.setPost_username(getInput(mPhone));
                        user.signUp(new SaveListener<BmobUser>() {
                            @Override
                            public void done(BmobUser s, BmobException e) {
                                if(e==null){
                                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                }else{
                                    Toast.makeText(RegisterActivity.this, "注册失败"+"，未知错误"+e.toString(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });


                    }
                }
            }
        });

        mBt_Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = getInput(mPhone);

                if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, "请输入手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(checkPhone()) {
                    BmobSMS.requestSMSCode(phone, "善用OfficeS", new QueryListener<Integer>() {
                        @Override
                        public void done(Integer smsId, BmobException e) {
                            if (e == null) {
                                Toast.makeText(RegisterActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(RegisterActivity.this, "发送验证码失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }else {
                    Toast.makeText(RegisterActivity.this, "请检查手机号码格式", Toast.LENGTH_SHORT).show();
                }
                downTimer.start();
                mBt_Send.setEnabled(false);
                mCode.requestFocus();
            }
        });

    }


    private String getInput(EditText view) {
        return view.getText().toString().trim();
    }

    /**
     * 验证码重新获取
     * millisInFuture 毫秒数
     * countDownInterval 返回时间间隔
     */
    private CountDownTimer downTimer = new CountDownTimer(60 * 1000, 1000) {

        //定期返回剩余值
        @Override
        public void onTick(long l) {
            mBt_Send.setText("已发送(" + (l / 1000) + "s)");
        }

        //完成时进行的方法
        @Override
        public void onFinish() {
            mBt_Send.setText("重新获取");
            mBt_Send.setEnabled(true);
        }
    };

    private boolean checkPhone() {
        //正则表达式
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        String account = getInput(mPhone);
        Matcher matcher = Pattern
                .compile(regex)
                .matcher(account);
        //返回查找结果
        return matcher.find();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SUCCESS:
                    codeOK = true;

                    break;
                case FAILURE:
                    codeOK = false;
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };


    /**
     * bmob 检查验证码
     */
    private void checkcode() {
        final Message message = mHandler.obtainMessage();
        Log.e(TAG, "checkPhone: " + getInput(mPhone));
        BmobSMS.verifySmsCode(getInput(mPhone), getInput(mCode), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    message.what = SUCCESS;
                    mHandler.sendMessage(message);
                } else {
                    message.what = FAILURE;
                    mHandler.sendMessage(message);
                    Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "done: 验证码错误：" + e.getErrorCode() + "-" + e.getMessage());
                }
            }
        });
    }

    /**
     * bmob 检查验证码
     */
    private boolean checkpassword() {

        if(TextUtils.isEmpty(getInput(mPassword))){
            mRepassword.setError("请输入密码！");
            return false;
        }
        if(TextUtils.isEmpty(getInput(mRepassword))){
            mRepassword.setError("请再次输入密码！");
            return false;
        }
        if(!getInput(mPassword).equals(getInput(mRepassword))){
            mRepassword.setError("你输入的两次密码不一样！");
            return false;
        }

        return true;
    }

}
