package com.example.librarygp2.Models;

public class Books {
    private String bookKey ;
    private String title ;
    private String description ;
    private String userId ;
    private String category ;
    private String uname ;
    private String picture ;


    public Books() {
    }

    public Books(String title,String picture, String description,String category, String userId,String uname) {
        this.title = title;
        this.picture = picture ;
        this.description = description;
        this.category = category ;
        this.userId = userId;
        this.uname = uname;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

    public String getBookKey() {
        return bookKey;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
