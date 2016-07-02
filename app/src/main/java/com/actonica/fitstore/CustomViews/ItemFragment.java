package com.actonica.fitstore.CustomViews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.Activities.MainActivity;
import com.actonica.fitstore.R;

/**
 * Created by ilgar on 02.07.2016.
 */
public class ItemFragment extends Fragment {

    private int screenWidth;
    private int screenHeight;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Double)(screenWidth /1.3)).intValue(), ((Double)(screenHeight /1.4)).intValue());
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.active_page, container, false);

        CardView cardView = (CardView) linearLayout.findViewById(R.id.active_card);
        cardView.setLayoutParams(layoutParams);

        //handling click event
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



        return linearLayout;
    }
}