package com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityMVP;

import com.github.mikephil.charting.data.LineData;
import com.pwr.janek.bitbayapi.Model.Ticker;

public class MVPMainActivityContract  {
    public interface View{
        void showData(Ticker ticker);
        void showFailureMsg(String msg);
        void showChart(LineData chart);
        void launchOrderBook();
        void showCurrentPriceDialog();
        void showLowDialog();
        void showVolumeDialog();
        void showHighDialog();
    }
    public interface Presenter{
        void setView(MVPMainActivityContract.View view);
        void fetchData();
        void saveCryptoTicker(String crypoTicker);
        void saveFiatTicker(String fiatTicker);
        String getFiatTicker();

        String getCryptoTicker();

        void orderBookPressed();
        void lowPressed();
        void volumePressed();
        void highPressed();
        void currentPricePressed();
    }

}
