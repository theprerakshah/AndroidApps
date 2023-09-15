package com.example.cardapp;

public class Game {
    String gameName, downloads;
    int gameImage;

    public Game(String gameName, int gameImage, String downloads) {
        this.gameName = gameName;
        this.gameImage = gameImage;
        this.downloads = downloads;
    }

    public String getDownloads() {
        return downloads;
    }

    public void setDownloads(String downloads) {
        this.downloads = downloads;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public int getGameImage() {
        return gameImage;
    }

    public void setGameImage(int gameImage) {
        this.gameImage = gameImage;
    }


}
