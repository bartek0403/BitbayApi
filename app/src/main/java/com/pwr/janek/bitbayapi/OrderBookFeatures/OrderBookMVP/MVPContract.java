package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP;

import com.pwr.janek.bitbayapi.Model.OrderBook;

public class MVPContract {

    public interface View{
        void displayOrderBook(OrderBook orderBook);
    }

    public interface Presenter{
        void setView(MVPContract.View view);
        void refresh();
        void refreshCompleted(OrderBook orderBook);

    }



}