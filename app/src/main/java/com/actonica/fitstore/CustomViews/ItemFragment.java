package com.actonica.fitstore.CustomViews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Activities.MainActivity;
import com.actonica.fitstore.Activities.ProgramActivity;
import com.actonica.fitstore.ApiResponsesGson.ProgramInteractionResponse;
import com.actonica.fitstore.Helpers.SharedPrefsHelper;
import com.actonica.fitstore.Helpers.UrlResolver;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private ImageView more_btn;

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
        more_btn = (ImageView) linearLayout.findViewById(R.id.more_button);

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

        more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(getActivity(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.program_actions, popup.getMenu());
                popup.show();

                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.remove_program:
                                removeProgram(program.getId());
                                break;
                            case R.id.about_program:
                                Intent intent = new Intent(getActivity(), ProgramActivity.class);
                                intent.putExtra("program", program);
                                getActivity().startActivity(intent);
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });

            }
        });

        return linearLayout;
    }


    private void removeProgram(final int programId){
        JuiceFitAPIHandler.removeProgram(programId, getActivity(), new Callback<ProgramInteractionResponse>() {
            @Override
            public void onResponse(Call<ProgramInteractionResponse> call, Response<ProgramInteractionResponse> response) {
                if (response.body() != null && response.isSuccessful()) {
                    SharedPrefsHelper.removeSavedProgram(programId, getActivity());
                    ((MainActivity)getActivity()).removeProgram(programId);
                }
            }
            @Override
            public void onFailure(Call<ProgramInteractionResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}