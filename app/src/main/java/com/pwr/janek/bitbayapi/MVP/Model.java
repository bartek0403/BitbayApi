package com.pwr.janek.bitbayapi.MVP;

/*public class Model implements MVPContract.Model {

    //TODO bitbayOrderBookApi = null;

    MVPContract.Presenter presenter;






    @Override
    public void fetchData() {

        Call<OrderBook> orderBookCall = bitbayOrderBookApi.getBitbayOrderBook();
        orderBookCall.enqueue(new Callback<OrderBook>() {
            @Override
            public void onResponse(Call<OrderBook> call, Response<OrderBook> response) {
                OrderBook orderBook = response.body();
                presenter.refreshCompleted(orderBook);

            }

            @Override
            public void onFailure(Call<OrderBook> call, Throwable t) {

            }
        });
    }

    @Override
    public void setPresenter(MVPContract.Presenter presenter) {
        this.presenter = presenter;
    }

}
*/