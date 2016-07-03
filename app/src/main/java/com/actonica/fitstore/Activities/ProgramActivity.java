package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.API.JuiceFitAPIHandler;
import com.actonica.fitstore.Adapters.ProgramsAdapter;
import com.actonica.fitstore.ApiResponsesGson.GetRelatedProgramsResponse;
import com.actonica.fitstore.Downloader.Downloader;
import com.actonica.fitstore.Helpers.UrlResolver;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProgramActivity extends AppCompatActivity {

    Button download_button;
    RecyclerView related_rv;
    LinearLayout related_block;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);

        Intent i = getIntent();
        final Program program = (Program)i.getSerializableExtra("program");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(program.getTitle());

        collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);


        ImageView avatar = (ImageView)findViewById(R.id.prog_avatar);
        Glide.with(this)
                .load(UrlResolver.getProgramAvatar(program.getCover()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(avatar);


        ImageView producer_avatar = (ImageView)findViewById(R.id.prod_avatar);
        Glide.with(this)
                .load(UrlResolver.getProducerAvatar(program.getProducer().getAvatar()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(producer_avatar);

        TextView prog_title = (TextView)findViewById(R.id.program_title);
        CircleImageView prod_avatar = (CircleImageView) findViewById(R.id.prod_avatar);
        TextView prod_nick = (TextView) findViewById(R.id.prod_nick);
        TextView part_number = (TextView) findViewById(R.id.part_number);
        TextView parts_total = (TextView) findViewById(R.id.parts_total);
        TextView trains_number = (TextView) findViewById(R.id.trains_count);
        TextView description = (TextView) findViewById(R.id.prog_description);
        TextView schedule = (TextView) findViewById(R.id.schedule);
        related_block = (LinearLayout)findViewById(R.id.related_programs);
        TextView prog_size = (TextView)findViewById(R.id.prog_size);
        download_button = (Button)findViewById(R.id.download_button);

        prog_title.setText(program.getTitle());
        prod_nick.setText(program.getProducer().getNickname());
        part_number.setText(program.getPartNo().toString());
        parts_total.setText(program.getPartsTotal().toString());
        trains_number.setText(program.getTrainingsTotal());
        description.setText(program.getDescription());
        schedule.setText(program.getPlan());
        prog_size.setText(String.format("Объем %s Мб", ((double)(program.getZipFileSize()/1048576))).toString());

        related_rv = (RecyclerView)findViewById(R.id.related_programs_recycler_view);

        LinearLayout producer_view = (LinearLayout)findViewById(R.id.producer_view);
        producer_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProgramActivity.this, ProducerActivity.class);
                intent.putExtra("producer", program.getProducer());
                startActivity(intent);
            }
        });

        download_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Downloader.getInstance(ProgramActivity.this).startDownload(program);
            }
        });


        JuiceFitAPIHandler.getRelatedPrograms(program.getId().toString(), this, new Callback<GetRelatedProgramsResponse>() {
            @Override
            public void onResponse(Call<GetRelatedProgramsResponse> call, Response<GetRelatedProgramsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Program> related = response.body().related_programs;
                    if  (related.size() > 0) {
                        ProgramsAdapter itemListDataAdapter = new ProgramsAdapter(ProgramActivity.this, related);
                        related_rv.setHasFixedSize(true);
                        related_rv.setLayoutManager(new LinearLayoutManager(ProgramActivity.this, LinearLayoutManager.HORIZONTAL, false));
                        related_rv.setAdapter(itemListDataAdapter);
                        related_block.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetRelatedProgramsResponse> call, Throwable t) {
                Toast.makeText(ProgramActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
