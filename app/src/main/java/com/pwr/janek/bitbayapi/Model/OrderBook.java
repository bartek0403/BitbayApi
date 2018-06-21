
package com.pwr.janek.bitbayapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderBook {

    @SerializedName("bids")
    @Expose
    private List<List<Double>> bids;
    @SerializedName("asks")
    @Expose
    private List<List<Double>> asks ;

    public List<List<Double>> getBids() {
        return bids;
    }

    public void setBids(List<List<Double>> bids) {
        this.bids = bids;
    }

    public List<List<Double>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<Double>> asks) {
        this.asks = asks;
    }

}
