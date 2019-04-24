package com.example.office_forum.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ListView;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.PS_Template_Adapter;
import com.example.office_forum.PS_Template_Images;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;

public class PS_TemplateActivity extends BaseStatusBarActivity {
    private ListView mListView;
    private List<PS_Template_Images> mList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ps__template);
        mListView=findViewById(R.id.ps_template_list);
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/001.jpg","https://yeek.top/officeS/PS/images/002.jpeg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/017.jpg","https://yeek.top/officeS/PS/images/004.jpeg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/005.jpeg","https://yeek.top/officeS/PS/images/006.jpeg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/007.jpg","https://yeek.top/officeS/PS/images/008.jpg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/009.jpg","https://yeek.top/officeS/PS/images/010.jpg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/011.jpeg","https://yeek.top/officeS/PS/images/012.jpg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/013.jpg","https://yeek.top/officeS/PS/images/014.jpeg"));
        mList.add(new PS_Template_Images("https://yeek.top/officeS/PS/images/015.jpeg","https://yeek.top/officeS/PS/images/016.jpg"));
        mListView.setAdapter(new PS_Template_Adapter(PS_TemplateActivity.this,mList,PS_TemplateActivity.this));
    }
}
