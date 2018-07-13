package com.pwr.janek.bitbayapi.OrderBookFeatures;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.pwr.janek.bitbayapi.FontHelper;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookDI.DaggerOrderBookActivityComponent;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookDI.OrderBookActivityComponent;
import com.pwr.janek.bitbayapi.OrderBookFeatures.OrderBookDI.OrderBookActivityModule;
import com.pwr.janek.bitbayapi.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Klasa odpowiedzialna za przechowywanie zależności poziomu AKTYWNOŚCI - API i ADAPTER
 */
public class OrderBookActivity extends FragmentActivity {

    @BindView(R.id.pager)
    ViewPager viewPager;

    @Inject
    FragmentManager fragmentManager;

    @Inject
    BidFragment bidFragment;

    @Inject
    AskFragment askFragment;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    PagerAdapter pagerAdapter;

    private static final int NUM_PAGES = 2;

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderbook);
        ButterKnife.bind(this);

        OrderBookActivityComponent orderBookActivityComponent = DaggerOrderBookActivityComponent.builder()
                .orderBookActivityModule(new OrderBookActivityModule(this))
                .build();
        orderBookActivityComponent.inject(this);

        Typeface iconFont = FontHelper.getTypeface(getApplicationContext(), FontHelper.FONTAWESOME);
        FontHelper.markAsIconContainer(findViewById(R.id.icon_orderBook2), iconFont);

        pagerAdapter = new FragmentPagerAdapter(fragmentManager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    private class FragmentPagerAdapter extends FragmentStatePagerAdapter {
        public FragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position == 0){
                Bundle bundle = new Bundle();
                bundle.putString("fiatTicker", getIntent().getStringExtra("fiatTicker"));
                bundle.putString("cryptoTicker", getIntent().getStringExtra("cryptoTicker"));
                bidFragment.setArguments(bundle);
                return bidFragment;
            }

            else{
                Bundle bundle = new Bundle();
                bundle.putString("fiatTicker", getIntent().getStringExtra("fiatTicker"));
                bundle.putString("cryptoTicker", getIntent().getStringExtra("cryptoTicker"));
                askFragment.setArguments(bundle);
                return askFragment;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Bid";
                case 1:
                    return "Ask";
                default:
                    return null;
            }
        }
    }

}
