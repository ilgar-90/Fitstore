package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actonica.fitstore.CustomViews.CarouselPagerAdapter;
import com.actonica.fitstore.R;
import com.viewpagerindicator.TitlePageIndicator;

public class ActiveFragment extends Fragment {
    public CarouselPagerAdapter adapter;
    public ViewPager pager;
    public static int count = 6; //ViewPager items size
    public static int FIRST_PAGE = 6;


    public static ActiveFragment newInstance() {
        ActiveFragment fragment = new ActiveFragment();
        return fragment;
    }

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_active, container, false);



        pager = (ViewPager) v.findViewById(R.id.myviewpager);

        //set page margin between pages for viewpager
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 4) * 2);
        pager.setPageMargin(-pageMargin);

        /*adapter = new CarouselPagerAdapter(this, getChildFragmentManager());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);*/

        TitlePageIndicator titleIndicator = (TitlePageIndicator)v.findViewById(R.id.indicator);
        titleIndicator.setViewPager(pager);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setCurrentItem(FIRST_PAGE);
        pager.setOffscreenPageLimit(3);



        return v;
    }
}
