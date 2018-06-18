
package com.pwr.janek.bitbayapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderBook {

    @SerializedName("bids")
    @Expose
    private List<List<Integer>> bids = null;
    @SerializedName("asks")
    @Expose
    private List<List<Double>> asks = null;

    public List<List<Integer>> getBids() {
        return bids;
    }

    public void setBids(List<List<Integer>> bids) {
        this.bids = bids;
    }

    public List<List<Double>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Double>> asks) {
        this.asks = asks;
    }

}
