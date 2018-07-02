package com.pwr.janek.bitbayapi;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.pwr.janek.bitbayapi.MainActivityFeatures.DaggerMainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityComponent;
import com.pwr.janek.bitbayapi.MainActivityFeatures.MainActivityModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Klasa odpowiedzialna za przechowywanie zależności poziomu AKTYWNOŚCI - API i ADAPTER
 */
public class MainActivity extends FragmentActivity {

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
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        MainActivityComponent mainActivityComponent = DaggerMainActivityComponent.builder()
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mainActivityComponent.inject(this);

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
            if(position == 0)
                 return bidFragment;
            else return askFragment;
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
