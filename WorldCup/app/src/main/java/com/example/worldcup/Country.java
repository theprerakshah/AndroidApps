package com.example.worldcup;

public class Country {
    private String countryName, win;
    private int flag;

    public Country(String countryName, String win, int flag) {
        this.countryName = countryName;
        this.win = win;
        this.flag = flag;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
