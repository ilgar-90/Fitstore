package com.actonica.fitstore.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actonica.fitstore.Helpers.ImgUrlResolver;
import com.actonica.fitstore.Models.Program;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ilgar on 27.06.2016.
 */
public class HorizontalProgramsAdapter extends RecyclerView.Adapter<HorizontalProgramsAdapter.SingleItemRowHolder> {

    private List<Program> itemsList;
    private Context mContext;

    public HorizontalProgramsAdapter(Context context, List<Program> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.program_card, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        Program program = itemsList.get(i);

        holder.program_title.setText(program.getTitle());
        holder.prod_nick.setText(program.getProducer().getNickname());


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




        public SingleItemRowHolder(View view) {
            super(view);

            this.program_title = (TextView) view.findViewById(R.id.program_title);
            this.program_avatar = (ImageView) view.findViewById(R.id.prog_avatar);
            this.prod_avatar = (CircleImageView) view.findViewById(R.id.prod_avatar);
            this.prod_nick = (TextView) view.findViewById(R.id.prod_nick);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), program_title.getText(), Toast.LENGTH_SHORT).show();
                }
            });


        }

    }

}
