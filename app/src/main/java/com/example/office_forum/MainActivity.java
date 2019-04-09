package com.example.office_forum;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;



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
                            .replace(R.id.fragment_container,new FollowFragment())
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


}
