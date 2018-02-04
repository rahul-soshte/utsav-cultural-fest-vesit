package com.hunterlab.hunter.utsav18.Events.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.hunterlab.hunter.utsav18.Events.Events;
import com.hunterlab.hunter.utsav18.R;
import com.hunterlab.hunter.utsav18.SingleEvent;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Jayesh Saita on 13-Sep-16.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<Events> list;
    LayoutInflater inflater;
    Activity activity;

    public RecyclerViewAdapter(Context context, List<Events> list){
        this.context = context;
        this.list = list;
        activity = (Activity)context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.grid_item,parent,false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.event_name.setText(list.get(position).getEvent_name());
        String image = list.get(position).getImage();
        String base="http://praxisvesit.com/deep/deeper/deepest/api/";
        Transformation transformation = new Transformation() {

            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.event_image.getWidth();

                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };

        if(image==null){
            Picasso.with(context)
                    .load(R.mipmap.ic_launcher)
                    .into(holder.event_image);
        }else{
            Picasso.with(context)
                    .load(base+image)
                    /*.resize(holder.event_image.getWidth(),holder.event_image.getHeight())
                    .centerCrop()*/
                    .transform(transformation)
                    .into(holder.event_image);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView event_image;
        TextView event_name;
        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            event_image = (ImageView)itemView.findViewById(R.id.event_image);
            event_name = (TextView)itemView.findViewById(R.id.event_name);
            view = itemView;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, SingleEvent.class);
            Bundle bundle = new Bundle();
            bundle.putString("event_name",list.get(getAdapterPosition()).getEvent_name());
            bundle.putString("event_image",list.get(getAdapterPosition()).getImage());
            bundle.putString("event_category",list.get(getAdapterPosition()).getCategory());
            bundle.putString("event_cost",list.get(getAdapterPosition()).getCost());
            bundle.putString("event_prize",list.get(getAdapterPosition()).getPrize());
            bundle.putString("event_teams_of",list.get(getAdapterPosition()).getTeams_of());
            bundle.putString("event_description",list.get(getAdapterPosition()).getDescription());
            bundle.putString("event_date",list.get(getAdapterPosition()).getDate());
            intent.putExtra("bundle",bundle);
            context.startActivity(intent);
        }
    }
}
