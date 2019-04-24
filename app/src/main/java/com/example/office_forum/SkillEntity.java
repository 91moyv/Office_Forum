package com.example.office_forum;

public class SkillEntity {
   private String mTitle;
   private int imgId;
    private String Content;
    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public SkillEntity(String title,String content,int imgId) {
        mTitle = title;
        Content=content;
        this.imgId=imgId;
    }
}
