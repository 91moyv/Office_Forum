package com.example.office_forum.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.office_forum.Activity.SearchActivity;
import com.example.office_forum.Activity.WritePostActivity;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private FloatingActionButton mWrite_post;
    TabLayout mPost_Navigation;
    ViewPager mPost_ViewPager;
    List<Fragment> mFragments;
    Button mBt_Search;

    String[]title={"推荐","求助","Word","PPT","Excel","PS"};
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        mWrite_post=(FloatingActionButton)view.findViewById(R.id.write_post);
        mPost_Navigation=view.findViewById(R.id.post_navigation);
        mPost_ViewPager=view.findViewById(R.id.post_viewpager);
        mBt_Search=view.findViewById(R.id.bt_start_send);
        mBt_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("if_from_home","1");
                startActivity(intent);
            }
        });
        mWrite_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), WritePostActivity.class);
                startActivity(intent);
            }
        });

        mFragments=new ArrayList<>();

        mFragments.add(new RecommendFragment());
        mFragments.add(new HelpFragment());
        mFragments.add(new WordFragment());
        mFragments.add(new PPTFragment());
        mFragments.add(new ExcelFragment());
        mFragments.add(new PSFragment());
        adapter myadapter=new adapter(getChildFragmentManager(),mFragments);
        mPost_ViewPager.setAdapter(myadapter);
        mPost_Navigation.setupWithViewPager(mPost_ViewPager);
        return view;
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
