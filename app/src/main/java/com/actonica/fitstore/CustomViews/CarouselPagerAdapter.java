package com.actonica.fitstore.CustomViews;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.actonica.fitstore.Activities.MainActivity;
import com.actonica.fitstore.Fragments.ActiveFragment;
import com.actonica.fitstore.R;

import java.util.List;

/**
 * Created by ilgar on 02.07.2016.
 */
public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    List<String> data;

    public CarouselPagerAdapter(FragmentManager fm, List<String> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ItemFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Item " + (position + 1);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {

    }

    public void onPageScrollStateChanged(int state) {

    }
}