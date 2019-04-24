package com.example.office_forum.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.R;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

import static com.example.office_forum.Fragment.MineFragment.mLogin;

public class PersonalDataActivity extends BaseStatusBarActivity {
  public static TextView mPersonal_Username;
  private View mUpdate_Name;
  private View mPersonalized_signature;
  private TextView mReturn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);

        mUpdate_Name=findViewById(R.id.data_up_name);
        mPersonal_Username=findViewById(R.id.data_username);
        mPersonal_Username.setText(mLogin.getText().toString());
        mReturn=findViewById(R.id.bt_data_return);
        mPersonalized_signature=findViewById(R.id.personalized_signature);
        mUpdate_Name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDataActivity.this,UpdateUserDataActivity.class);
                intent.putExtra("title_name","修改昵称");
                intent.putExtra("username",mLogin.getText().toString());
                startActivity(intent);

            }

        });
        mPersonalized_signature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonalDataActivity.this,UpdateUserDataActivity.class);
                intent.putExtra("title_name","修改个性签名");
                intent.putExtra("username",mLogin.getText().toString());
                startActivity(intent);
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
