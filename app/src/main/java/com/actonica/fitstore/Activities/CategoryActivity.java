package com.actonica.fitstore.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.actonica.fitstore.Adapters.CategoriesAdapter;
import com.actonica.fitstore.Adapters.CategoryProgramsAdapter;
import com.actonica.fitstore.Adapters.ProgramsAdapter;
import com.actonica.fitstore.DividerItemDecoration;
import com.actonica.fitstore.Models.Category;
import com.actonica.fitstore.R;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView programs_rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent i = getIntent();
        Category category = (Category)i.getSerializableExtra("category");

        programs_rv = (RecyclerView)findViewById(R.id.category_programs);
        programs_rv.setHasFixedSize(true);

        CategoryProgramsAdapter adapter = new CategoryProgramsAdapter(CategoryActivity.this, category.getPrograms());
        programs_rv.setLayoutManager(new LinearLayoutManager(CategoryActivity.this, LinearLayoutManager.VERTICAL, false));
        programs_rv.addItemDecoration(
                new DividerItemDecoration(CategoryActivity.this, R.drawable.divider));
        programs_rv.setAdapter(adapter);

    }
}
