package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.actonica.fitstore.Adapters.ExtendedProgramsAdapter;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Models.Category;
import com.actonica.fitstore.R;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView programs_rv;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent i = getIntent();
        Category category = (Category)i.getSerializableExtra("category");

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle(category.getTitle());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        programs_rv = (RecyclerView)findViewById(R.id.category_programs);
        programs_rv.setHasFixedSize(true);

        ExtendedProgramsAdapter adapter = new ExtendedProgramsAdapter(CategoryActivity.this, category.getPrograms(), true);
        programs_rv.setLayoutManager(new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false));
        programs_rv.addItemDecoration(
                new DividerItemDecoration(CategoryActivity.this, R.drawable.divider));
        programs_rv.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
