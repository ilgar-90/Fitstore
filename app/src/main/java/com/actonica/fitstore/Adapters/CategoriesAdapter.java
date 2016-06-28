package com.actonica.fitstore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.actonica.fitstore.Activities.CategoryActivity;
import com.actonica.fitstore.Models.Category;
import com.actonica.fitstore.R;

import java.util.List;

/**
 * Created by ilgar on 27.06.2016.
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ItemRowHolder> {

    private List<Category> dataList;
    private Context mContext;

    public CategoriesAdapter(Context context, List<Category> dataList) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_section, null);
        ItemRowHolder mh = new ItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(ItemRowHolder itemRowHolder, int i) {

        final String sectionName = dataList.get(i).getTitle();

        List singleSectionItems = dataList.get(i).getPrograms();

        itemRowHolder.categoryTitle.setText(sectionName);

        ProgramsAdapter itemListDataAdapter = new ProgramsAdapter(mContext, singleSectionItems);

        itemRowHolder.programs_recycler_view.setHasFixedSize(true);
        itemRowHolder.programs_recycler_view.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        itemRowHolder.programs_recycler_view.setAdapter(itemListDataAdapter);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView categoryTitle;
        protected RecyclerView programs_recycler_view;
        protected LinearLayout category_header;



        public ItemRowHolder(View view) {
            super(view);
            this.categoryTitle = (TextView) view.findViewById(R.id.category_title);
            this.programs_recycler_view = (RecyclerView) view.findViewById(R.id.programs_recycler_view);
            this.category_header = (LinearLayout) view.findViewById(R.id.category_header);
            this.category_header.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext, CategoryActivity.class);
                    intent.putExtra("category", dataList.get(getAdapterPosition()));
                    mContext.startActivity(intent);

                    //Toast.makeText(v.getContext(), dataList.get(getAdapterPosition()).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

}
