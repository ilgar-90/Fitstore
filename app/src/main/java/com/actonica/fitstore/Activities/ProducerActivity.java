package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.media.Image;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Adapters.ExtendedProgramsAdapter;
import com.actonica.fitstore.ApiResponsesGson.GetProducerProgramsResponse;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Helpers.ImgUrlResolver;
import com.actonica.fitstore.Models.Producer;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    RecyclerView programs_rv;

    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR  = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS     = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION              = 200;

    private boolean mIsTheTitleVisible          = false;
    private boolean mIsTheTitleContainerVisible = true;

    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsing;
    private ImageView coverImage;
    private FrameLayout framelayoutTitle;
    private LinearLayout linearlayoutTitle;
    private Toolbar toolbar;
    private TextView textviewTitle;
    private ImageView prod_avatar;
    private TextView prod_name;
    private TextView prod_nick;
    private TextView prod_about;
    private CardView prod_about_card;


    private void findViews() {
        appbar = (AppBarLayout)findViewById( R.id.appbar );
        collapsing = (CollapsingToolbarLayout)findViewById( R.id.collapsing );
        coverImage = (ImageView)findViewById( R.id.imageview_placeholder );
        framelayoutTitle = (FrameLayout)findViewById( R.id.framelayout_title );
        linearlayoutTitle = (LinearLayout)findViewById( R.id.linearlayout_title );
        toolbar = (Toolbar)findViewById( R.id.toolbar );
        textviewTitle = (TextView)findViewById( R.id.title );

        prod_avatar = (ImageView)findViewById( R.id.prod_avatar );
        prod_name = (TextView)findViewById( R.id.prod_name );
        prod_nick = (TextView)findViewById( R.id.prod_nick );
        prod_about = (TextView)findViewById( R.id.prod_about );
        prod_about_card = (CardView)findViewById(R.id.prod_about_card);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer);


        Intent i = getIntent();
        final Producer producer = (Producer) i.getSerializableExtra("producer");

        findViews();
        toolbar.setTitle("");
        appbar.addOnOffsetChangedListener(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        startAlphaAnimation(textviewTitle, 0, View.INVISIBLE);

        textviewTitle.setText(producer.getName());
        prod_name.setText(producer.getName());
        prod_nick.setText(producer.getNickname());
        if(producer.getDescription() != null && !producer.getDescription().isEmpty()) {
            prod_about.setText(producer.getDescription());
            prod_about_card.setVisibility(View.VISIBLE);
        }
        Glide.with(this)
                .load(ImgUrlResolver.getProducerAvatar(producer.getAvatar()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(prod_avatar);

        programs_rv = (RecyclerView) findViewById(R.id.programs);
        programs_rv.setHasFixedSize(true);
        JuiceFitAPIHandler.getProducerPrograms(producer.getId().toString(), this, new Callback<GetProducerProgramsResponse>() {
            @Override
            public void onResponse(Call<GetProducerProgramsResponse> call, Response<GetProducerProgramsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Program> related = response.body().producer.getPrograms();
                    if (related.size() > 0) {
                        for (Program prog : related){
                            prog.setProducer(producer);
                        }
                        ExtendedProgramsAdapter adapter = new ExtendedProgramsAdapter(ProducerActivity.this, related, false);
                        programs_rv.setLayoutManager(new LinearLayoutManager(ProducerActivity.this, LinearLayoutManager.VERTICAL, false));
                        programs_rv.addItemDecoration(
                                new DividerItemDecoration(ProducerActivity.this, R.drawable.divider));
                        programs_rv.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProducerProgramsResponse> call, Throwable t) {
                Toast.makeText(ProducerActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(offset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }

    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR) {

            if(!mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }

        } else {

            if (mIsTheTitleVisible) {
                startAlphaAnimation(textviewTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= PERCENTAGE_TO_HIDE_TITLE_DETAILS) {
            if(mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }

        } else {

            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(linearlayoutTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }

    public static void startAlphaAnimation (View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }
}


