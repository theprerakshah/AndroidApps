package com.example.mywhatsapp;

public class Chat {
    private String name, message;
    private int dp;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    public Chat(String name, String message, int dp) {
        this.name = name;
        this.message = message;
        this.dp = dp;
    }
}
