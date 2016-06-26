package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actonica.fitstore.R;
public class ActiveFragment extends Fragment {
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
        return v;
    }
}
