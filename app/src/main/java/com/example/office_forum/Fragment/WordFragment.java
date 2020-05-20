package com.example.office_forum.Fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.Activity.PostContentActivity;
import com.example.office_forum.Post_Adapter.Word_Post_Adapter;
import com.example.office_forum.Post_Date.Word_Post_Data;
import com.example.office_forum.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {
    private ListView mPost_List;
    private TextView Word_Loading_View;
    public WordFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_word, container, false);
        mPost_List=view.findViewById(R.id.post_word_list);
        Word_Loading_View=view.findViewById(R.id.word_loading_view);
        query();
        mPost_List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                TextView post_title=view.findViewById(R.id.post_title);
                TextView post_content=view.findViewById(R.id.post_content);
                TextView post_username=view.findViewById(R.id.post_username);
                TextView post_date=view.findViewById(R.id.post_date);
                TextView url=view.findViewById(R.id.tx_qrcode_url);
                Intent intent = new Intent(getActivity(), PostContentActivity.class);
                intent.putExtra("post_title", post_title.getText().toString());
                intent.putExtra("post_content", post_content.getText().toString());
                intent.putExtra("post_username", post_username.getText().toString());
                intent.putExtra("post_date", post_date.getText().toString());
                intent.putExtra("ImagesUrl",url.getText().toString());
                startActivity(intent);
            }
        });


       return view;
    }

    private void query(){
        BmobQuery<Word_Post_Data> query = new BmobQuery<>();
        query.order("-createdAt");
        query.findObjects(new FindListener<Word_Post_Data>() {  //按行查询，查到的数据放到List<Word_Post_Data>的集合
            @Override
            public void done(List<Word_Post_Data> list, BmobException e) {
                if (e == null){
                    Word_Loading_View.setVisibility(Word_Loading_View.GONE);
                    //System.out.println("查询成功"+list.get(0).getName()+list.get(0).getPrice()+list.get(0).getDesc());


                    mPost_List.setAdapter(new Word_Post_Adapter(getActivity(), list));



                }else {
                    Word_Loading_View.setTextColor(Color.RED);
                    Word_Loading_View.setText("网络错误，请检查网络！");
                }

            }
        });

    }

}
