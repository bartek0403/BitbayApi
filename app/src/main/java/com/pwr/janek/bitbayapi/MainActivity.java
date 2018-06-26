package com.pwr.janek.bitbayapi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.pwr.janek.bitbayapi.Adapter.BitbayOrderBookAdapter;
import com.pwr.janek.bitbayapi.ApplicationFeatures.BitbayOrderBookApp;
import com.pwr.janek.bitbayapi.MVP.MVPContract;
import com.pwr.janek.bitbayapi.MVP.Presenter;

import com.pwr.janek.bitbayapi.MainActivityFeatures.DaggerMainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityModule;
import com.pwr.janek.bitbayapi.Model.OrderBook;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Klasa odpowiedzialna za przechowywanie zależności poziomu AKTYWNOŚCI - API i ADAPTER
 */
public class MainActivity extends AppCompatActivity implements MVPContract.View {

    @Inject
    Presenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    BitbayOrderBookAdapter adapter;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent
                .builder()
                .mainActivityModule(new MainActivityModule())
                .bitbayOrderBookComponent(BitbayOrderBookApp.get(this).getBitbayOrderBookAppComponent())
                .build();
        mainActivityComponent.injectMainActivity(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter.setView(this);
        presenter.refresh();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();

            }
        });
    }

    @Override
    public void displayOrderBook(OrderBook orderBook) {
        adapter.setItems(orderBook);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }
}
