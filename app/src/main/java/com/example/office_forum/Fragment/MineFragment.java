package com.example.office_forum.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.office_forum.Activity.LoginActivity;
import com.example.office_forum.Activity.MyPostActivity;
import com.example.office_forum.Activity.PersonalDataActivity;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;
import com.example.office_forum.Activity.Set_UpActivity;

import cn.bmob.v3.BmobUser;




/**
 * A simple {@link Fragment} subclass.
 */
public class MineFragment extends Fragment {
  View mSet_up;
  View mUser_Data;
  View mMy_Post;
  public static TextView mLogin;

    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mine, container, false);
        mSet_up=view.findViewById(R.id.set_up);
        mLogin=view.findViewById(R.id.mine_login);
        mUser_Data=view.findViewById(R.id.user_data);
        mMy_Post=view.findViewById(R.id.view_my_post);
        islogin();
        mUser_Data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BmobUser.isLogin()) {
                    startActivity(new Intent(getActivity(), PersonalDataActivity.class));
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        mSet_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getActivity(), Set_UpActivity.class);
               startActivity(intent);
            }
        });
        mMy_Post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(BmobUser.isLogin()) {
                    startActivity(new Intent(getActivity(), MyPostActivity.class));
                }else {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        return view;
    }

    public void islogin(){
        if(Post_User.isLogin()){
            Post_User user = Post_User.getCurrentUser(Post_User.class);
            mLogin.setText(user.getPost_username());
        }
    }

}
