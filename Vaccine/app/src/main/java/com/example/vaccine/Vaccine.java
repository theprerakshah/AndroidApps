package com.example.vaccine;

public class Vaccine {
    private String title;
    private int image;
    private String price;

    public Vaccine(String title, int image, String price) {
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
