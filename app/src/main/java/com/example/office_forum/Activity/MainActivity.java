package com.example.office_forum.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Fragment.CurriculumFragment;
import com.example.office_forum.Fragment.HomeFragment;
import com.example.office_forum.Fragment.MineFragment;
import com.example.office_forum.Fragment.SkillFragment;
import com.example.office_forum.R;
import com.example.office_forum.Fragment.TempletFragment;

import cn.bmob.v3.Bmob;


public class MainActivity extends BaseStatusBarActivity {

    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            mCurrentIndex = item.getItemId();
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new HomeFragment())
                            .commit();
                    return true;
                case R.id.navigation_follow:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new CurriculumFragment())
                            .commit();
                    return true;

                case R.id.navigation_skill:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new SkillFragment())
                            .commit();
                    return true;

                case R.id.templet:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new TempletFragment())
                            .commit();
                    return true;
                case R.id.navigation_mine:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container,new MineFragment())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "e7081d7f0a0d11a77be191f3dc41796a");

        if (savedInstanceState == null){

                    getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container,new HomeFragment())
                    .commit();
        }


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected int getStatusBarColor() {
        return getResources().getColor(R.color.colorPrimaryDark);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        // 按下键盘上返回按钮
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            new AlertDialog.Builder(this)
                    .setMessage("确定退出系统吗？")
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    finish();
                                }
                            }).show();

            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }

    }
}
