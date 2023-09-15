package com.example.mywhatsapp;

public class Status {

    String name, minutes;
    int statusImage;

    public Status(String name, String minutes, int statusImage) {
        this.name = name;
        this.minutes = minutes;
        this.statusImage = statusImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public int getStatusImage() {
        return statusImage;
    }

    public void setStatusImage(int statusImage) {
        this.statusImage = statusImage;
    }
}
