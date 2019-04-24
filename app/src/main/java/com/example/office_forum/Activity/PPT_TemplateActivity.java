package com.example.office_forum.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.R;

public class PPT_TemplateActivity extends BaseStatusBarActivity {
View mView;
private Button mReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ppt__template);
        mView=findViewById(R.id.ppt_template_04);
        mReturn=findViewById(R.id.ppt_template_return);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PPT_TemplateActivity.this,Template_DownloadActivity.class));
            }
        });


        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
