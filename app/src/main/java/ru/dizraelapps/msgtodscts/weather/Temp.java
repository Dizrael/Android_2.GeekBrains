package ru.dizraelapps.msgtodscts.weather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temp {

    public Temp(Double day, Double night) {
        this.day = day;
        this.night = night;
    }

    @SerializedName("day")
    @Expose
    private Double day;
    @SerializedName("night")
    @Expose
    private Double night;

    public Double getDay() {
        return day;
    }

    public void setDay(Double day) {
        this.day = day;
    }


    public Double getNight() {
        return night;
    }

    public void setNight(Double night) {
        this.night = night;
    }

}