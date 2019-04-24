package com.example.office_forum.Post_Date;

import cn.bmob.v3.BmobUser;

public class Post_User extends BmobUser {
    private String post_username;



    public Post_User(){
        this.setTableName("_User");
    }


    public String getPost_username() {
        return post_username;
    }

    public void setPost_username(String post_username) {
        this.post_username = post_username;
    }


}
