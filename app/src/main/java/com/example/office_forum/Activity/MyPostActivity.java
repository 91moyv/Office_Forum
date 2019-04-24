package com.example.office_forum.Activity;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Fragment.ExamineFragment;
import com.example.office_forum.Fragment.ExcelFragment;
import com.example.office_forum.Fragment.HelpFragment;
import com.example.office_forum.Fragment.HomeFragment;
import com.example.office_forum.Fragment.MyPostFragment;
import com.example.office_forum.Fragment.PPTFragment;
import com.example.office_forum.Fragment.PSFragment;
import com.example.office_forum.Fragment.RecommendFragment;
import com.example.office_forum.Fragment.WordFragment;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;

public class MyPostActivity extends BaseStatusBarActivity {
   private ViewPager mViewPager;
   private TabLayout mTabLayout;
    List<Fragment> mFragments;
    private Button mReturn;
    String[]title={"未审核","我的文章"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        mTabLayout=findViewById(R.id.mypost_tablayout);
        mViewPager=findViewById(R.id.mypost_viewpager);
        mFragments=new ArrayList<>();
        mReturn=findViewById(R.id.my_post_return);
        mFragments.add(new ExamineFragment());
        mFragments.add(new MyPostFragment());

        adapter myadapter=new adapter(getSupportFragmentManager(),mFragments);
        mViewPager.setAdapter(myadapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private class adapter extends FragmentPagerAdapter {
        private List<Fragment>list;

        public adapter(FragmentManager RD, List<Fragment> mList) {
            super(RD);
            this.list=mList;
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
