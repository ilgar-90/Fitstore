package com.actonica.fitstore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.Activities.ProgramActivity;
import com.actonica.fitstore.Helpers.ImgUrlResolver;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ilgar on 28.06.2016.
 */
public class CategoryProgramsAdapter extends RecyclerView.Adapter<CategoryProgramsAdapter.SingleItemRowHolder> {

    private List<Program> itemsList;
    private Context mContext;

    public CategoryProgramsAdapter(Context context, List<Program> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_programs_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Program program = itemsList.get(i);

        holder.program_title.setText(program.getTitle());
        holder.prod_nick.setText(program.getProducer().getNickname());
        holder.description.setText(program.getDescription());
        holder.trains_number.setText(program.getTrainingsTotal());
        holder.part_number.setText(program.getPartNo().toString());
        holder.parts_total.setText(program.getPartsTotal().toString());


        Glide.with(mContext)
                .load(ImgUrlResolver.getProgramAvatar(program.getCover()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.program_avatar);

        Glide.with(mContext)
                .load(ImgUrlResolver.getProducerAvatar(program.getProducer().getAvatar()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.prod_avatar);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView program_title;
        protected ImageView program_avatar;
        protected CircleImageView prod_avatar;
        protected TextView prod_nick;
        protected TextView part_number;
        protected TextView parts_total;
        protected TextView trains_number;
        protected TextView description;




        public SingleItemRowHolder(View view) {
            super(view);

            this.program_title = (TextView) view.findViewById(R.id.program_title);
            this.program_avatar = (ImageView) view.findViewById(R.id.prog_avatar);
            this.prod_avatar = (CircleImageView) view.findViewById(R.id.prod_avatar);
            this.prod_nick = (TextView) view.findViewById(R.id.prod_nick);

            this.part_number = (TextView) view.findViewById(R.id.part_number);
            this.parts_total = (TextView) view.findViewById(R.id.parts_total);
            this.trains_number = (TextView) view.findViewById(R.id.trains_count);
            this.description = (TextView) view.findViewById(R.id.prog_description);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProgramActivity.class);
                    intent.putExtra("program", itemsList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });


        }

    }

}
