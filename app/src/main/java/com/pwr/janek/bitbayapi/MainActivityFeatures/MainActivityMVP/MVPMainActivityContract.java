package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP;

import com.github.mikephil.charting.data.CandleData;
import com.pwr.janek.bitbayapi.Model.Ticker;

public class MVPMainActivityContract  {
    public interface View{
        void showData(Ticker ticker);
        void showFailureMsg(String msg);
        void showChart(CandleData chart);
    }
    public interface Presenter{
        void setView(MVPMainActivityContract.View view);
        void fetchData();
        void saveCryptoTicker(String crypoTicker);
        void saveFiatTicker(String fiatTicker);
    }

}
