package com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP;

import com.pwr.janek.bitbayapi.Model.OrderBook;

public class MVPOrderBOokContract {

    public interface View{
        void displayOrderBook(OrderBook orderBook);
    }

    public interface Presenter{
        void setView(MVPOrderBOokContract.View view);
        void refresh();
        void refreshCompleted(OrderBook orderBook);

    }



}
