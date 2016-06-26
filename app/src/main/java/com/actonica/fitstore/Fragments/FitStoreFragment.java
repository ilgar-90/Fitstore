package com.actonica.fitstore.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actonica.fitstore.R;

import org.w3c.dom.Text;

public class FitStoreFragment extends Fragment {
    public static FitStoreFragment newInstance() {
        FitStoreFragment fragment = new FitStoreFragment();
        return fragment;
    }

    public FitStoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fitstore, container, false);
        return v;
    }
}
