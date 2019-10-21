package com.example.librarygp2.Models;

public class Comments {
    private String content,uid,uname;

    public Comments() {
    }

    public Comments(String content, String uid, String uname) {
        this.content = content;
        this.uid = uid;
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
