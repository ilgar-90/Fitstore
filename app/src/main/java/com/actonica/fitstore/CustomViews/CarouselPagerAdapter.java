package com.actonica.fitstore.CustomViews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.actonica.fitstore.Models.Program;

import java.util.List;

/**
 * Created by ilgar on 02.07.2016.
 */
public class CarouselPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

    List<Program> data;
    private long baseId = 0;

    public CarouselPagerAdapter(FragmentManager fm, List<Program> data) {
        super(fm);
        this.data = data;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = new ActivePageFragment();
        Bundle args = new Bundle();
        args.putSerializable("program", this.data.get(i));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }


    //this is called when notifyDataSetChanged() is called
    @Override
    public int getItemPosition(Object object) {
        // refresh all fragments when data set changed
        return PagerAdapter.POSITION_NONE;
    }


    @Override
    public long getItemId(int position) {
        // give an ID different from position when position has been changed
        return baseId + position;
    }

    /**
     * Notify that the position of a fragment has been changed.
     * Create a new ID for each position to force recreation of the fragment
     * @param n number of items which have been changed
     */
    public void notifyChangeInPosition(int n) {
        // shift the ID returned by getItemId outside the range of all previous fragments
        baseId += getCount() + n;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    public void onPageSelected(int position) {

    }

    public void onPageScrollStateChanged(int state) {

    }
}