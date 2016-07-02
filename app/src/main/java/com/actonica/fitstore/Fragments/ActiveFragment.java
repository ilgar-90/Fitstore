package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.graphics.Color;
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
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class ActiveFragment extends Fragment {
    public CarouselPagerAdapter adapter;
    public ViewPager pager;

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
        int pageMargin = ((metrics.widthPixels / 10) * 2);
        pager.setPageMargin(-pageMargin);

        adapter = new CarouselPagerAdapter(getChildFragmentManager(), getData());
        pager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        pager.addOnPageChangeListener(adapter);

        CirclePageIndicator indicator = (CirclePageIndicator)v.findViewById(R.id.indicator);
        indicator.setViewPager(pager);
        indicator.setSnap(true);
        indicator.setFillColor(Color.GRAY);
        indicator.setStrokeColor(Color.GRAY);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        pager.setOffscreenPageLimit(3);



        return v;
    }

    private List<String> getData() {
        List<String> data = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            data.add("Item number " + i);
        }

        return data;
    }
}
