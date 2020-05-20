package com.example.office_forum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cpiz.android.bubbleview.BubblePopupWindow;
import com.cpiz.android.bubbleview.BubbleStyle;
import com.cpiz.android.bubbleview.BubbleTextView;
import com.cpiz.android.bubbleview.RelativePos;
import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.QRcode_ppwindow;
import com.example.office_forum.R;
import com.example.office_forum.TransApi.TransAPI;
import com.example.office_forum.TransApi.TranslateBD;

import com.google.gson.Gson;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostContentActivity extends BaseStatusBarActivity {
    private TextView mTitle;
    private TextView mContent;
    private TextView mUserName;
    private TextView mDate;
    private Button mReturn;
    private Button mBtReWard;

    private Spinner mSpinner;

    private List<String> List_Sort;
    private ArrayAdapter<String> adapter;
    private Handler handler = new Handler();
    private Switch mTrans;
    String post_content;
    String trans="";
    BubbleTextView mBubbleTextSample;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:

                    mBubblePopupWindow.dismiss();
                    break;
                default:
                    break;
            }
        }
    };

    private BubblePopupWindow mBubblePopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_content);

        Intent intent = getIntent();
        String post_title=intent.getStringExtra("post_title");
         post_content=intent.getStringExtra("post_content");
        String post_username=intent.getStringExtra("post_username");
        String post_date=intent.getStringExtra("post_date");
        final String ImagesUrl=intent.getStringExtra("ImagesUrl");
        mTrans=findViewById(R.id.trans);
        mBtReWard=findViewById(R.id.bt_reword);
        mReturn=findViewById(R.id.post_content_return);
        mTrans.setVisibility(mTrans.INVISIBLE);
        mSpinner=findViewById(R.id.spinner_comment);
        mTitle=findViewById(R.id.post_content_title);
        mContent=findViewById(R.id.post_content_content);
        mUserName=findViewById(R.id.post_content_username);

        mDate=findViewById(R.id.post_content_date);
    /*    mBubbleTextSample=findViewById(R.id.bubble_text_sample);*/
        List_Sort = new ArrayList<String>();
        List_Sort.add("按时间倒序");
        List_Sort.add("按时间正序");
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,List_Sort);

        //为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //为spinner绑定我们定义好的数据适配器
        mSpinner.setAdapter(adapter);

        mTitle.setText(post_title);
        mContent.setText(post_content);
        mUserName.setText(post_username);
        mDate.setText(post_date.substring(0,10));
        View rootView = LayoutInflater.from(this).inflate(R.layout.trans_ppwindow, null);
        mBubbleTextSample = (BubbleTextView) rootView.findViewById(R.id.bubble_text_sample1);
        mBubblePopupWindow = new BubblePopupWindow(rootView, mBubbleTextSample);
        mTitle.post(new Runnable(){
            @Override
            public void run() {
                Is_English(post_content);
            }



        });

        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mBtReWard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ImagesUrl.length()<10){
                    Toast.makeText(PostContentActivity.this,"作者没有上传二维码！",Toast.LENGTH_SHORT).show();
                }else {
                    new QRcode_ppwindow(PostContentActivity.this, ImagesUrl).showAtBottom(mBtReWard);
                }
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBubblePopupWindow != null) {
            mBubblePopupWindow.dismiss();
        }
    }
    private void Is_English(String string){

        char[] chars=string.toCharArray();
        int num=0;
        for(int i=0;i<string.length();i++){
            if(chars[i]>='a'&&chars[i]<='z'||chars[i]>='A'&&chars[i]<='Z') {
                num++;
            }
        }
                if (num>string.length()*0.1) {




                    mTrans.setVisibility(mTrans.VISIBLE);
                    showppwindow();
                    mTrans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                mBubblePopupWindow.dismiss();
                                if (!trans.equals("")) {

                                    mContent.setText(trans);
                                } else {
                                    final ZLoadingDialog dialog = new ZLoadingDialog(PostContentActivity.this);
                                    dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                                            .setLoadingColor(Color.BLACK)//颜色
                                            .setCanceledOnTouchOutside(false)
                                            .setHintText("正在翻译...")
                                            .show();
                                    toTrans2(post_content,dialog);
                                }

                            } else {
                                mContent.setText(post_content);
                            }
                        }
                    });
                }


    }

    private void showppwindow() {
        mBubblePopupWindow.showArrowTo(mTrans, BubbleStyle.ArrowDirection.Up,10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg =new Message();
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    private void toTrans2(String inText, final ZLoadingDialog dialog){
        final String query = inText;
        new Thread(new Runnable() {
            @Override
            public void run() {
                String resultJson = new TransAPI().getTransResult(query, "auto", "zh");
                //拿到结果，对结果进行解析。
                Gson gson = new Gson();
                TranslateBD translateResult = gson.fromJson(resultJson, TranslateBD.class);
                final List<TranslateBD.TransResultBean> trans_result = translateResult.getTrans_result();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        String dst = "";
                        for (TranslateBD.TransResultBean s : trans_result
                        ) {
                            dst = dst + s.getDst()+"\n";
                            trans=dst;
                            mContent.setText(trans);

                            dialog.dismiss();
                        }
}
                });
            }
        }).start();

    }



}
