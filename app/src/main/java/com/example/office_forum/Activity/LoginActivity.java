package com.example.office_forum.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;



public class LoginActivity extends BaseStatusBarActivity {
    Button mRegister;
    Button mLogin_Main;
    private EditText mUserName;
    private EditText mPassWord;
    private Button mQQ_Login;

    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mRegister = findViewById(R.id.bt_register);
        mLogin_Main = findViewById(R.id.login_main);
        mUserName = findViewById(R.id.login_username);
        mPassWord = findViewById(R.id.login_password);
        mQQ_Login = findViewById(R.id.bt_qq_login);
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        mLogin_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Post_User user = new Post_User();
                user.setUsername(getInput(mUserName));
                user.setPassword(getInput(mPassWord));
                user.login(new SaveListener<Post_User>() {
                    @Override
                    public void done(Post_User bmobUser, BmobException e) {
                        if (e == null) {

                            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        } else {
                            if (TextUtils.isEmpty(getInput(mUserName))) {
                                mUserName.setError("请输入用户名");
                                return;
                            }
                            if (TextUtils.isEmpty(getInput(mPassWord))) {
                                mPassWord.setError("请输入密码");
                                return;
                            }
                            if ("errorCode:101,errorMsg:username or password incorrect.".equals(e.toString())) {
                                Toast.makeText(LoginActivity.this, "登录失败,账号或密码不正确", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(LoginActivity.this, "未知错误" + e, Toast.LENGTH_SHORT).show();
                            }

                        }
                    }


                });


            }
        });


        mQQ_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }


    private String getInput(EditText view) {
        return view.getText().toString().trim();
    }





}