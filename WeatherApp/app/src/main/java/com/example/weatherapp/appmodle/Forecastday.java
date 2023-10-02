package com.example.weatherapp.appmodle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.util.List;

@Generated("jsonschema2pojo")
public class Forecastday {

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("date_epoch")
    @Expose
    private Integer dateEpoch;
    @SerializedName("day")
    @Expose
    private Day day;
//    @SerializedName("astro")
//    @Expose
//    private Astro astro;
//    @SerializedName("hour")
//    @Expose
//    private List<Hour> hour;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDateEpoch() {
        return dateEpoch;
    }

    public void setDateEpoch(Integer dateEpoch) {
        this.dateEpoch = dateEpoch;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

//    public Astro getAstro() {
//        return astro;
//    }
//
//    public void setAstro(Astro astro) {
//        this.astro = astro;
//    }
//
//    public List<Hour> getHour() {
//        return hour;
//    }
//
//    public void setHour(List<Hour> hour) {
//        this.hour = hour;
//    }

}