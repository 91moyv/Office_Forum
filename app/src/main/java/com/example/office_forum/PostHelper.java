package com.example.office_forum;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PostHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PostBase.db";
    public static final String Word_NAME = "Word_table";
    public static final String PPT_NAME = "PPT_table";
    public static final String Excel_NAME = "Excel_table";
    public static final String[] Post_FROM = new String[]{"post_title","post_content"};
    public PostHelper(Context context) {
        super(context,DATABASE_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE  " +Word_NAME+
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "post_title VARCHAR(32),"+
                    "post_content VARCHAR(64)"+")");
            db.execSQL("CREATE TABLE  " +PPT_NAME+
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "post_title VARCHAR(32),"+
                    "post_content VARCHAR(64)"+")");
         db.execSQL("CREATE TABLE  " +Excel_NAME+
                "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "post_title VARCHAR(32),"+
                "post_content VARCHAR(64)"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
