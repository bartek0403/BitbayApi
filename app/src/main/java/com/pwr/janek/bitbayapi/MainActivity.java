package com.pwr.janek.bitbayapi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pwr.janek.bitbayapi.Adapter.BitbayOrderBookAdapter;
import com.pwr.janek.bitbayapi.ApiInterface.BitbayOrderBookApi;
import com.pwr.janek.bitbayapi.ApplicationFeatures.BitbayOrderBookApp;
import com.pwr.janek.bitbayapi.MainActivityFeatures.DaggerMainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityModule;
import com.pwr.janek.bitbayapi.Model.OrderBook;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * Klasa odpowiedzialna za przechowywanie zależności poziomu AKTYWNOŚCI - API i ADAPTER
 */
public class MainActivity extends AppCompatActivity {

    @Inject
    BitbayOrderBookApi bitbayOrderBookApi;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    BitbayOrderBookAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent
                .builder()
                .mainActivityModule(new MainActivityModule(this))
                .bitbayOrderBookComponent(BitbayOrderBookApp.get(this).getBitbayOrderBookAppComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fillView();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fillView();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void fillView() {
        Call<OrderBook> orderBookCall = bitbayOrderBookApi.getBitbayOrderBook();
        orderBookCall.enqueue(new Callback<OrderBook>() {
            @Override
            public void onResponse(Call<OrderBook> call, Response<OrderBook> response) {
                    adapter.setItems(response);
                    recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<OrderBook> call, Throwable t) {

            }
        });
    }
}
