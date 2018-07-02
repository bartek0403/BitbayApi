package com.pwr.janek.bitbayapi.OrderBookFeatures;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pwr.janek.bitbayapi.OrderBookFeatures.Adapter.OrderBookAdapter;
import com.pwr.janek.bitbayapi.OrderBookFeatures.FragmentsDI.DaggerFragmentComponent;
import com.pwr.janek.bitbayapi.OrderBookFeatures.FragmentsDI.FragmentComponent;
import com.pwr.janek.bitbayapi.OrderBookFeatures.FragmentsDI.FragmentModule;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP.MVPContract;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookMVP.Presenter;
import com.pwr.janek.bitbayapi.Model.OrderBook;
import com.pwr.janek.bitbayapi.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BidFragment extends Fragment implements MVPContract.View, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    Presenter presenter;

    @BindView(R.id.recyclerView_bid)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefreshLayout_bid)
    SwipeRefreshLayout swipeRefreshLayout;

    OrderBookAdapter adapter = new OrderBookAdapter("bid");


    public BidFragment() {
        // Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bid, container,false);
        ButterKnife.bind(this, view);
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule())
                .build();
        fragmentComponent.inject(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.setView(this);
        presenter.refresh();

        swipeRefreshLayout.setOnRefreshListener(this);

        return view;
    }

    @Override
    public void displayOrderBook(OrderBook orderBook) {
        adapter.setItems(orderBook);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        presenter.refresh();
    }
}
