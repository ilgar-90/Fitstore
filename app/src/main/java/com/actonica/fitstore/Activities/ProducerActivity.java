package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.graphics.Point;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Adapters.ExtendedProgramsAdapter;
import com.actonica.fitstore.ApiResponsesGson.GetProducerProgramsResponse;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Helpers.UrlResolver;
import com.actonica.fitstore.Models.Producer;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProducerActivity extends AppCompatActivity {

    RecyclerView programs_rv;

    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ImageView prod_avatar;
    private TextView prod_name;
    private TextView prod_nick;
    private TextView prod_about;
    private CardView prod_about_card;


    private void findViews() {
        appbar = (AppBarLayout)findViewById( R.id.appbar );
        toolbar = (Toolbar)findViewById( R.id.toolbar );
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
        toolbar.setTitle(producer.getName());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prod_name.setText(producer.getName());
        prod_nick.setText(producer.getNickname());
        if(producer.getDescription() != null && !producer.getDescription().isEmpty()) {
            prod_about.setText(producer.getDescription());
            prod_about_card.setVisibility(View.VISIBLE);
        }


        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int calculatedSize = (int) Math.round(size.x / 3.3);
        prod_avatar.getLayoutParams().height = calculatedSize;
        prod_avatar.getLayoutParams().width = calculatedSize;


        Glide.with(this)
                .load(UrlResolver.getProducerAvatar(producer.getAvatar()))
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}


