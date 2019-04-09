package com.example.office_forum;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.office_forum.PostHelper.Excel_NAME;
import static com.example.office_forum.PostHelper.PPT_NAME;
import static com.example.office_forum.PostHelper.Post_FROM;

import static com.example.office_forum.PostHelper.Word_NAME;

public class WritePostActivity extends BaseStatusBarActivity {
    private Button mReturn;
    private Button mOK;
    private EditText mPost_Title;
    private EditText mPost_Content;
    Cursor Post_cur;
    PostHelper mPostHelper;
    SQLiteDatabase db;
    View mChoose_Type;
    SimpleCursorAdapter mPost_adapter;
    private Button mGONE;
    private View mWord_Block;
    private View mPpt_Block;
    private View mExcle_Block;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        mOK=findViewById(R.id.ok_bt);
        mPost_Content=findViewById(R.id.post_content);
        mPost_Title=findViewById(R.id.post_title);
        mReturn = findViewById(R.id.return_bt);
        mPostHelper=new PostHelper(this);
        db=mPostHelper.getWritableDatabase();
        mChoose_Type=findViewById(R.id.choose_type);
        mGONE=findViewById(R.id.cardview_visible);
        mChoose_Type.setVisibility(mChoose_Type.GONE);
        mWord_Block = findViewById(R.id.word_Block);
        mPpt_Block = findViewById(R.id.ppt_Block);
        mExcle_Block = findViewById(R.id.excle_Block);
        Post_cur=db.rawQuery("SELECT*FROM "+Word_NAME+" ORDER BY _id DESC ",null);
        mPost_adapter=new SimpleCursorAdapter(this,
                R.layout.home_list_item,Post_cur,
                Post_FROM,
                new int[]{R.id.post_title,R.id.post_content,},0);
        mReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        mGONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mChoose_Type.setVisibility(mChoose_Type.GONE);
                mPost_Title.setEnabled(true);
                mPost_Content.setEnabled(true);
            }
        });
    }

    public void addWord(){
        String Post_Title=mPost_Title.getText().toString();
        String Post_Content=mPost_Content.getText().toString();

        ContentValues cv = new ContentValues(2);
        cv.put(Post_FROM[0],Post_Title);
        cv.put(Post_FROM[1],Post_Content);
        db.insert(Word_NAME,null, cv);

        Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(WritePostActivity.this,MainActivity.class);
        startActivity(intent);

        Post_cur=db.rawQuery("SELECT*FROM "+Word_NAME+" ORDER BY _id DESC ",null);
        mPost_adapter.changeCursor(Post_cur);
    }

    public void addPPT(){
        String Post_Title=mPost_Title.getText().toString();
        String Post_Content=mPost_Content.getText().toString();

        ContentValues cv = new ContentValues(2);
        cv.put(Post_FROM[0],Post_Title);
        cv.put(Post_FROM[1],Post_Content);
        db.insert(PPT_NAME,null, cv);

        Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(WritePostActivity.this,MainActivity.class);
        startActivity(intent);

        Post_cur=db.rawQuery("SELECT*FROM "+PPT_NAME+" ORDER BY _id DESC ",null);
        mPost_adapter.changeCursor(Post_cur);
    }


    public void addExcel(){
        String Post_Title=mPost_Title.getText().toString();
        String Post_Content=mPost_Content.getText().toString();

        ContentValues cv = new ContentValues(2);
        cv.put(Post_FROM[0],Post_Title);
        cv.put(Post_FROM[1],Post_Content);
        db.insert(Excel_NAME,null, cv);

        Toast.makeText(WritePostActivity.this,"发布成功",Toast.LENGTH_SHORT).show();

        Intent intent =new Intent(WritePostActivity.this,MainActivity.class);
        startActivity(intent);

        Post_cur=db.rawQuery("SELECT*FROM "+Excel_NAME+" ORDER BY _id DESC ",null);
        mPost_adapter.changeCursor(Post_cur);
    }
}
