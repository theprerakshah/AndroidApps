package com.example.instagram;

public class Insta {
    private String instaId, count, caption;
    int myImage, like, share;

    public Insta(String instaId, String count, String caption, int myImage, int like, int share) {
        this.instaId = instaId;
        this.count = count;
        this.caption = caption;
        this.myImage = myImage;
        this.like = like;
        this.share = share;
    }

    public String getInstaId() {
        return instaId;
    }

    public void setInstaId(String instaId) {
        this.instaId = instaId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getMyImage() {
        return myImage;
    }

    public void setMyImage(int myImage) {
        this.myImage = myImage;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }
}
