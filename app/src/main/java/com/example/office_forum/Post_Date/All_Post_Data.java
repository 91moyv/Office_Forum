package com.example.office_forum.Post_Date;

import cn.bmob.v3.BmobObject;

public class All_Post_Data extends BmobObject {
    private String post_title;
    private String post_content;
    private String post_username;


    public String getPost_username(){
        return post_username;
    }


    public String getPost_title(){
        return post_title;
    }

    public String getPost_content(){
        return post_content;
    }

    public void setPost_title(String post_title){
        this.post_title=post_title;
    }

    public void setPost_content(String post_content){
        this.post_content=post_content;
    }
    public void setPost_username(String post_username) {
        this.post_username = post_username;
    }
}