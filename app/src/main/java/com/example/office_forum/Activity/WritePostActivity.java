package com.example.office_forum.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.office_forum.BaseStatusBarActivity;
import com.example.office_forum.Post_Date.Examine_Post_Data;
import com.example.office_forum.Post_Date.Help_Post_Data;
import com.example.office_forum.Post_Date.Post_Data;
import com.example.office_forum.Post_Date.Excel_Post_Data;
import com.example.office_forum.Post_Date.PPT_Post_Data;
import com.example.office_forum.Post_Date.PS_Post_Data;
import com.example.office_forum.Post_Date.Post_User;
import com.example.office_forum.Post_Date.Word_Post_Data;
import com.example.office_forum.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class WritePostActivity extends BaseStatusBarActivity {
    private Button mReturn;
    private Button mOK;
    private EditText mPost_Title;
    private EditText mPost_Content;

    View mChoose_Type;

    private Button mGONE;
    private View mWord_Block;
    private View mPpt_Block;
    private View mExcle_Block;
    private View mPS_Block;
    private View mHelp_Block;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        mOK=findViewById(R.id.ok_bt);
        mPost_Content=findViewById(R.id.post_content);
        mPost_Title=findViewById(R.id.post_title);
        mReturn = findViewById(R.id.return_bt);
        mHelp_Block=findViewById(R.id.help_block);
        mChoose_Type=findViewById(R.id.choose_type);
        mGONE=findViewById(R.id.cardview_visible);
        mChoose_Type.setVisibility(mChoose_Type.GONE);
        mWord_Block = findViewById(R.id.word_block);
        mPpt_Block = findViewById(R.id.ppt_block);
        mExcle_Block = findViewById(R.id.excle_block);
        mPS_Block=findViewById(R.id.ps_block);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!BmobUser.isLogin()){
                    Intent intent =new Intent(WritePostActivity.this, LoginActivity.class);
                    startActivity(intent);
                    return;
                }

                String Post_Title=mPost_Title.getText().toString();
                String Post_Content=mPost_Content.getText().toString();
                if(Post_Title.equals("")||Post_Content.equals("")){
                    Toast.makeText(WritePostActivity.this,"请输入内容",Toast.LENGTH_SHORT).show();
                    return;
                }
                /*onInsertUpdate();*/
                mPost_Title.setEnabled(false);
                mPost_Content.setEnabled(false);
                mChoose_Type.setVisibility(mChoose_Type.VISIBLE);
            }
        });

        mWord_Block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addWord();

            }
        });

        mPpt_Block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPPT();
            }
        });

        mExcle_Block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExcel();
            }
        });

        mPS_Block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addPS();
            }
        });
        mHelp_Block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHelp();
            }
        });
        mGONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChoose_Type.setVisibility(mChoose_Type.GONE);
                mPost_Title.setEnabled(true);
                mPost_Content.setEnabled(true);
            }
        });
    }

    public void addPPT(){


        final ZLoadingDialog dialog = new ZLoadingDialog(WritePostActivity.this);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setCanceledOnTouchOutside(false)
                .setHintText("Loading...")
                .show();
        Post_User user = Post_User.getCurrentUser(Post_User.class);



       Examine_Post_Data examine_post_data =new Examine_Post_Data();
        examine_post_data.setPost_content(mPost_Content.getText().toString());
        examine_post_data.setPost_title(mPost_Title.getText().toString());
        examine_post_data.setPost_username(user.getPost_username());
        examine_post_data.setPost_type("PPT");
        examine_post_data.setPost_phone(user.getUsername());
        examine_post_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {





                if(e==null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(WritePostActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(3000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布失败",Toast.LENGTH_SHORT).show();

                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            /**
                             * 延时执行的代码
                             */

                        }
                    }).start();
                }

            }
        });




    }
    public void addExcel(){


        final ZLoadingDialog dialog = new ZLoadingDialog(WritePostActivity.this);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setCanceledOnTouchOutside(false)
                .setHintText("Loading...")
                .show();
        Post_User user = Post_User.getCurrentUser(Post_User.class);



        Examine_Post_Data examine_post_data =new Examine_Post_Data();
        examine_post_data.setPost_content(mPost_Content.getText().toString());
        examine_post_data.setPost_title(mPost_Title.getText().toString());
        examine_post_data.setPost_username(user.getPost_username());
        examine_post_data.setPost_phone(user.getUsername());
        examine_post_data.setPost_type("Excel");
        examine_post_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {





                if(e==null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(WritePostActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(3000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布失败",Toast.LENGTH_SHORT).show();

                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            /**
                             * 延时执行的代码
                             */

                        }
                    }).start();
                }

            }
        });




    }
    public void addWord(){


        final ZLoadingDialog dialog = new ZLoadingDialog(WritePostActivity.this);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setCanceledOnTouchOutside(false)
                .setHintText("Loading...")
                .show();
        Post_User user = Post_User.getCurrentUser(Post_User.class);



        Examine_Post_Data examine_post_data =new Examine_Post_Data();
        examine_post_data.setPost_content(mPost_Content.getText().toString());
        examine_post_data.setPost_title(mPost_Title.getText().toString());
        examine_post_data.setPost_username(user.getPost_username());
        examine_post_data.setPost_phone(user.getUsername());
        examine_post_data.setPost_type("Word");
        examine_post_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {





                if(e==null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(WritePostActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(3000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布失败",Toast.LENGTH_SHORT).show();

                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            /**
                             * 延时执行的代码
                             */

                        }
                    }).start();
                }

            }
        });




    }

    public void addPS(){


        final ZLoadingDialog dialog = new ZLoadingDialog(WritePostActivity.this);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setCanceledOnTouchOutside(false)
                .setHintText("Loading...")
                .show();
        Post_User user = Post_User.getCurrentUser(Post_User.class);



        Examine_Post_Data examine_post_data =new Examine_Post_Data();
        examine_post_data.setPost_content(mPost_Content.getText().toString());
        examine_post_data.setPost_title(mPost_Title.getText().toString());
        examine_post_data.setPost_username(user.getPost_username());
        examine_post_data.setPost_type("PS");
        examine_post_data.setPost_phone(user.getUsername());
        examine_post_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {





                if(e==null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(WritePostActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();

                }else{
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(3000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布失败",Toast.LENGTH_SHORT).show();

                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }

                            /**
                             * 延时执行的代码
                             */

                        }
                    }).start();
                }

            }
        });




    }

    private void addHelp(){
        final ZLoadingDialog dialog = new ZLoadingDialog(WritePostActivity.this);
        dialog.setLoadingBuilder(Z_TYPE.DOUBLE_CIRCLE)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setCanceledOnTouchOutside(false)
                .setHintText("Loading...")
                .show();
        Post_User user = Post_User.getCurrentUser(Post_User.class);
        Help_Post_Data help_post_data = new Help_Post_Data();
        help_post_data.setPost_content(mPost_Content.getText().toString());
        help_post_data.setPost_title(mPost_Title.getText().toString());
        help_post_data.setPost_username(user.getPost_username());
        help_post_data.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e==null){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(WritePostActivity.this, MainActivity.class);
                                startActivity(intent);
                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();
                }else{

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                Looper.prepare();
                                Thread.sleep(1000); // 休眠1秒

                                dialog.dismiss();
                                Toast.makeText(WritePostActivity.this,"发布失败",Toast.LENGTH_SHORT).show();

                                Looper.loop();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            }


                        }
                    }).start();
                }
            }
        });

    }




}
