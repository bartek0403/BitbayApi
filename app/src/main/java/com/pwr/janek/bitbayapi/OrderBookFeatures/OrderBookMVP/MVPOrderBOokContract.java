package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP;

import android.os.Bundle;

import com.pwr.janek.bitbayapi.Model.OrderBook;

public class MVPOrderBOokContract {

    public interface View{
        void displayOrderBook(OrderBook orderBook);
        void passTickers();
    }

    public interface Presenter{
        void setView(MVPOrderBOokContract.View view);
        void refresh();
        void refreshCompleted(OrderBook orderBook);

        void saveTickers(Bundle bundle);
    }



}
