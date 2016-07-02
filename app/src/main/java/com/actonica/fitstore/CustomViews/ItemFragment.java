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

    private static final String POSITON = "position";
    private static final String SCALE = "scale";
    private static final String DRAWABLE_RESOURE = "resource";

    private int screenWidth;
    private int screenHeight;

    private int[] imageArray = new int[]{R.drawable.bgmix, R.drawable.wallp,
            R.drawable.bgmix, R.drawable.wallp, R.drawable.bgmix,
            R.drawable.wallp};

    public static Fragment newInstance(Activity context, int pos, float scale) {
        Bundle b = new Bundle();
        b.putInt(POSITON, pos);
        b.putFloat(SCALE, scale);

        return Fragment.instantiate(context, ItemFragment.class.getName(), b);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWidthAndHeight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        final int postion = this.getArguments().getInt(POSITON);
        float scale = this.getArguments().getFloat(SCALE);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth/2, screenHeight/2);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.active_page, container, false);

        TextView textView = (TextView) linearLayout.findViewById(R.id.text);
        CarouselLinearLayout root = (CarouselLinearLayout) linearLayout.findViewById(R.id.root_container);
        CardView cardView = (CardView) linearLayout.findViewById(R.id.active_card);

        textView.setText("Carousel item: " + postion);
        cardView.setLayoutParams(layoutParams);
        //handling click event
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_LONG).show();
            }
        });

        root.setScaleBoth(scale);

        return linearLayout;
    }

    /**
     * Get device screen width and height
     */
    private void getWidthAndHeight() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;
    }
}