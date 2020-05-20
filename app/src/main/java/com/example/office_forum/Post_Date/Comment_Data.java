package com.example.office_forum.Post_Date;

import cn.bmob.v3.BmobObject;

public class Comment_Data extends BmobObject {

    private String comment_user;
    private String comment_post;
    private String comment_content;

    public String getComment_user() {
        return comment_user;
    }

    public void setComment_user(String comment_user) {
        this.comment_user = comment_user;
    }

    public String getComment_post() {
        return comment_post;
    }

    public void setComment_post(String comment_post) {
        this.comment_post = comment_post;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }



}
