package com.example.office_forum.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Post_Adapter.Post_Adapter;
import com.example.office_forum.Post_Adapter.Search_Post_Adapter;
import com.example.office_forum.Post_Date.All_Post_Data;
import com.example.office_forum.Post_Date.Post_Data;
import com.example.office_forum.Post_Date.Word_Post_Data;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchActivity extends BaseStatusBarActivity {
    EditText mEt_Start_Search;
    TextView mTx_Search;
    ListView mList_Serach;
    private ImageView mReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mEt_Start_Search=findViewById(R.id.edt_start_search);
        mReturn=findViewById(R.id.search_return);
        mTx_Search=findViewById(R.id.tx_search);
        mList_Serach=findViewById(R.id.list_search);
        Intent intent = getIntent();
        if(intent.getStringExtra("if_from_home").equals("1")){
            showSoftInputFromWindow(SearchActivity.this,mEt_Start_Search);
        }
        mList_Serach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView post_title=view.findViewById(R.id.post_title);
                TextView post_content=view.findViewById(R.id.post_content);
                TextView post_username=view.findViewById(R.id.post_username);
                TextView post_date=view.findViewById(R.id.post_date);

                Intent intent = new Intent(SearchActivity.this, PostContentActivity.class);
                intent.putExtra("post_title", post_title.getText().toString());
                intent.putExtra("post_content", post_content.getText().toString());
                intent.putExtra("post_username", post_username.getText().toString());
                intent.putExtra("post_date", post_date.getText().toString());
                startActivity(intent);
            }
        });
        mEt_Start_Search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
        mTx_Search.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(mEt_Start_Search.getText().toString().length()<2){
                    Toast.makeText(SearchActivity.this,"请输入至少两个关键字",Toast.LENGTH_SHORT).show();
                    return;
                }

                BmobQuery<All_Post_Data> query = new BmobQuery<>();

                query.findObjects(new FindListener<All_Post_Data>() {  //按行查询，查到的数据放到List<Goods>的集合
                    @Override
                    public void done(List<All_Post_Data> list, BmobException e) {
                        if (e == null){


                            //System.out.println("查询成功"+list.get(0).getName()+list.get(0).getPrice()+list.get(0).getDesc());

                            List<All_Post_Data> post_data= new ArrayList<>();
                            for (int i=0;i<list.size();i++){
                                String str = list.get(i).getPost_title().toUpperCase();
                                int result = str.indexOf( mEt_Start_Search.getText().toString().toUpperCase());
                                if(result!=-1){
                                    post_data.add(list.get(i));
                                }else {
                                    String content = list.get(i).getPost_content().toUpperCase();
                                    int content_result = content.indexOf( mEt_Start_Search.getText().toString().toUpperCase());
                                    if(content_result!=-1){
                                        post_data.add(list.get(i));
                                    }else {

                                    }
                                }
                            }
                            if(post_data.size()==0){
                                Toast.makeText(SearchActivity.this,"目前没有该文章",Toast.LENGTH_SHORT).show();
                            }else {

                                mList_Serach.setAdapter(new Search_Post_Adapter(SearchActivity.this, post_data, mEt_Start_Search.getText().toString().toUpperCase()));
                            }
                            }else {
                            Toast.makeText(SearchActivity.this,"查询失败",Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });

        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }



}

