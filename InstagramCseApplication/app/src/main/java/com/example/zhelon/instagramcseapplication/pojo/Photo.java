package com.example.zhelon.instagramcseapplication.pojo;

/**
 * Created by zhelon on 23-11-17.
 */

public class Photo {

    private byte[] image;
    private String comment;

    public Photo(byte[] image, String comment) {
        this.image = image;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
