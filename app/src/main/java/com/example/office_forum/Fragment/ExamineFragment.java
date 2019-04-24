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

import com.example.office_forum.Activity.PostContentActivity;
import com.example.office_forum.Post_Adapter.Examine_Post_Adapter;
import com.example.office_forum.Post_Adapter.Post_Adapter;
import com.example.office_forum.Post_Date.Examine_Post_Data;
import com.example.office_forum.Post_Date.Post_Data;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExamineFragment extends Fragment {
private ListView mListView;
private TextView mTextView;

    public ExamineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_examine, container, false);
        mListView=view.findViewById(R.id.examine_list);
        mTextView=view.findViewById(R.id.examine_loading_view);
        query();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView post_title=view.findViewById(R.id.post_title);
                TextView post_content=view.findViewById(R.id.post_content);
                TextView post_username=view.findViewById(R.id.post_username);
                TextView post_date=view.findViewById(R.id.post_date);

                Intent intent = new Intent(getActivity(), PostContentActivity.class);
                intent.putExtra("post_title", post_title.getText().toString());
                intent.putExtra("post_content", post_content.getText().toString());
                intent.putExtra("post_username", post_username.getText().toString());
                intent.putExtra("post_date", post_date.getText().toString());
                startActivity(intent);
            }
        });
        return view;
    }
    private void query(){
        BmobQuery<Examine_Post_Data> query = new BmobQuery<>();
        Post_User user = Post_User.getCurrentUser(Post_User.class);
        query.order("-createdAt");
        query.addWhereEqualTo("post_phone", user.getUsername());
        query.findObjects(new FindListener<Examine_Post_Data>() {  //按行查询，查到的数据放到List<Goods>的集合
            @Override
            public void done(List<Examine_Post_Data> list, BmobException e) {
                if (e == null){
                    mTextView.setVisibility(mTextView.GONE);
                    //System.out.println("查询成功"+list.get(0).getName()+list.get(0).getPrice()+list.get(0).getDesc());
                    mListView.setAdapter(new Examine_Post_Adapter(getActivity(), list));
                }else {
                    mTextView.setTextColor(Color.RED);
                    mTextView.setText("网络错误，请检查网络！");
                }

            }
        });

    }
}
