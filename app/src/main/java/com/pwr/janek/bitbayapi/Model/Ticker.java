package com.pwr.janek.bitbayapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker {

    @SerializedName("max")
    @Expose
    private Double max;
    @SerializedName("min")
    @Expose
    private Double min;
    @SerializedName("last")
    @Expose
    private Double last;
    @SerializedName("bid")
    @Expose
    private Double bid;
    @SerializedName("ask")
    @Expose
    private Double ask;
    @SerializedName("vwap")
    @Expose
    private Double vwap;
    @SerializedName("average")
    @Expose
    private Double average;
    @SerializedName("volume")
    @Expose
    private Double volume;

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    public Double getVwap() {
        return vwap;
    }

    public void setVwap(Double vwap) {
        this.vwap = vwap;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

}


