package com.example.office_forum.Activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Fragment.MineFragment;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.office_forum.Activity.PersonalDataActivity.mPersonal_Username;

public class UpdateUserDataActivity extends BaseStatusBarActivity {
    private TextView mTitle;
    private EditText mUpdata;
    private ImageView mReturn;
    private TextView mOK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_data);
        mTitle=findViewById(R.id.update_title);
        mUpdata=findViewById(R.id.edt_update_data);
        mReturn=findViewById(R.id.update_return);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent =getIntent();
        String title=intent.getStringExtra("title_name");
        final String nickname=intent.getStringExtra("username");
        UpdateName(title, nickname);

    }

    private void UpdateName(String title, String nickname) {
        mTitle.setText(title);
        mUpdata.setText(nickname);
        mUpdata.setHint(title);
        mOK=findViewById(R.id.bt_ok_update);
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name = mUpdata.getText().toString().trim();

                if(name.length()<2) {
                    Toast.makeText(UpdateUserDataActivity.this, "请输入至少两个字符，空格无效", Toast.LENGTH_LONG).show();
                }else {

                    Post_User user = Post_User.getCurrentUser(Post_User.class);
                    Post_User post_user = new Post_User();
                    post_user.setPost_username(name);
                    post_user.update(user.getObjectId(), new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(UpdateUserDataActivity.this, "修改成功", Toast.LENGTH_LONG).show();
                                mUpdata.setText(name);
                                mPersonal_Username.setText(name);
                                MineFragment.mLogin.setText(name);
                                finish();
                            } else {
                                Toast.makeText(UpdateUserDataActivity.this, "修改失败" + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
