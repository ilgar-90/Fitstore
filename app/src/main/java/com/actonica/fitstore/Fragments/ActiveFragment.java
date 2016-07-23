package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.actonica.fitstore.Activities.ProgramActivity;
import com.actonica.fitstore.CustomViews.CarouselPagerAdapter;
import com.actonica.fitstore.Downloader.Downloader;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ActiveFragment extends Fragment {
    private CarouselPagerAdapter adapter;
    private ViewPager pager;
    private Toolbar toolbar;
    private List<Program> savedPrograms;
    private LinearLayout active_programs;
    private LinearLayout empty_view;

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

        savedPrograms = SharedPrefsHelper.getSavedPrograms(getActivity().getApplicationContext());

        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle("Active");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);

        empty_view = (LinearLayout)v.findViewById(R.id.empty_view);
        active_programs = (LinearLayout)v.findViewById(R.id.active_programs);

        pager = (ViewPager) v.findViewById(R.id.myviewpager);
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int pageMargin = ((metrics.widthPixels / 12) * 2);
        pager.setPageMargin(-pageMargin);

        if (savedPrograms != null) {
            empty_view.setVisibility(View.GONE);
            active_programs.setVisibility(View.VISIBLE);
            adapter = new CarouselPagerAdapter(getChildFragmentManager(), savedPrograms);
            pager.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            pager.addOnPageChangeListener(adapter);
            CirclePageIndicator indicator = (CirclePageIndicator) v.findViewById(R.id.indicator);
            indicator.setViewPager(pager);
            indicator.setSnap(true);
            indicator.setFillColor(Color.GRAY);
            indicator.setStrokeColor(Color.GRAY);
            pager.setOffscreenPageLimit(3);
        }
        return v;
    }

    public void removeProgram(int programId){
        for (Iterator<Program> iter = savedPrograms.listIterator(); iter.hasNext(); ) {
            Program prog = iter.next();
            if (prog.getId() == programId) {
                iter.remove();
            }
        }
        adapter.notifyDataSetChanged();
    }
}
