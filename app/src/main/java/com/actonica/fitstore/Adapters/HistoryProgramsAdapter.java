package com.actonica.fitstore.Adapters;

import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.actonica.fitstore.Helpers.UrlResolver;
import com.actonica.fitstore.Models.HistoryProgram;
import com.actonica.fitstore.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

/**
 * Created by ilgar on 24.07.2016.
 */
public class HistoryProgramsAdapter extends RecyclerView.Adapter<HistoryProgramsAdapter.SingleItemRowHolder> {
    private List<HistoryProgram> itemsList;
    private Context mContext;
    private int screenWidth;

    public HistoryProgramsAdapter(Context context, List<HistoryProgram> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_list_item, null);
        SingleItemRowHolder mh = new SingleItemRowHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {

        HistoryProgram program = itemsList.get(i);

        holder.program_title.setText(program.getProgram().getTitle());
        holder.part_number.setText(program.getProgram().getPartNo().toString());
        holder.parts_total.setText(program.getProgram().getPartsTotal().toString());
        Glide.with(mContext)
                .load(UrlResolver.getProgramAvatar(program.getProgram().getCover()))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.program_avatar);
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView program_title;
        protected ImageView program_avatar;
        protected TextView part_number;
        protected TextView parts_total;


        public SingleItemRowHolder(View view) {
            super(view);

            this.program_title = (TextView) view.findViewById(R.id.program_title);
            this.program_avatar = (ImageView) view.findViewById(R.id.prog_avatar);
            this.part_number = (TextView) view.findViewById(R.id.part_number);
            this.parts_total = (TextView) view.findViewById(R.id.parts_total);


            int imgWidth = (int)Math.round(screenWidth/3.5);
            this.program_avatar.getLayoutParams().width = imgWidth;
            this.program_avatar.getLayoutParams().height = (int)Math.round(imgWidth/1.5);
            this.program_avatar.requestLayout();


            /*view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ProgramActivity.class);
                    intent.putExtra("program", itemsList.get(getAdapterPosition()));
                    mContext.startActivity(intent);
                }
            });*/
        }

    }
}
