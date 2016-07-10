package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actonica.fitstore.CustomViews.CarouselPagerAdapter;
import com.actonica.fitstore.Downloader.Downloader;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class ActiveFragment extends Fragment {
    private CarouselPagerAdapter adapter;
    private ViewPager pager;
    private Toolbar toolbar;

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

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle("Active");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        pager = (ViewPager) v.findViewById(R.id.myviewpager);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 12) * 2);
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

        pager.setOffscreenPageLimit(3);



        return v;
    }

    private List<Program> getData() {
        List<Program> data = new ArrayList<>();
        return data;
    }
}
