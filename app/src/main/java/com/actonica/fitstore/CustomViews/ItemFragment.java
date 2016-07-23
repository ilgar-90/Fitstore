package com.actonica.fitstore.CustomViews;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.Activities.MainActivity;
import com.actonica.fitstore.Helpers.UrlResolver;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by ilgar on 02.07.2016.
 */
public class ItemFragment extends Fragment {

    private int screenWidth;
    private int screenHeight;
    private Program program;
    private TextView program_title;
    private TextView part_number;
    private TextView parts_total;
    private ImageView prod_avatar;
    private ImageView program_cover;
    private TextView prod_nick;
    private Button trainButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        program = (Program)getArguments().getSerializable("program");

        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        screenHeight = displaymetrics.heightPixels;
        screenWidth = displaymetrics.widthPixels;


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(((Double)(screenWidth /1.3)).intValue(), ((Double)(screenHeight /1.4)).intValue());
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.active_page, container, false);
        CardView cardView = (CardView) linearLayout.findViewById(R.id.active_card);
        cardView.setLayoutParams(layoutParams);

        program_title = (TextView) linearLayout.findViewById(R.id.program_title);
        part_number = (TextView) linearLayout.findViewById(R.id.part_number);
        parts_total = (TextView) linearLayout.findViewById(R.id.parts_total);
        prod_avatar = (ImageView) linearLayout.findViewById(R.id.prod_avatar);
        program_cover = (ImageView) linearLayout.findViewById(R.id.program_cover);
        prod_nick = (TextView) linearLayout.findViewById(R.id.prod_nick);
        trainButton = (Button) linearLayout.findViewById(R.id.gotrain_button);

        program_title.setText(program.getTitle());
        part_number.setText(program.getPartNo().toString());
        parts_total.setText(program.getPartsTotal().toString());
        prod_nick.setText(program.getProducer().getNickname());

        Glide.with(getActivity())
                .load(UrlResolver.getProgramAvatar(program.getCover()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(program_cover);

        Glide.with(getActivity())
                .load(UrlResolver.getProducerAvatar(program.getProducer().getAvatar()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(prod_avatar);

        //handling click event
        trainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), program.getId().toString(), Toast.LENGTH_SHORT).show();
            }
        });



        return linearLayout;
    }
}