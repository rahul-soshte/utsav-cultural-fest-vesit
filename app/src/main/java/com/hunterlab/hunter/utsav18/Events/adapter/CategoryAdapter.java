package com.hunterlab.hunter.utsav18.Events.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunterlab.hunter.utsav18.Events.CategoryEventList;
import com.hunterlab.hunter.utsav18.Events.Events_main;
import com.hunterlab.hunter.utsav18.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Jayesh Saita on 13-Sep-16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<String> list;
    LayoutInflater inflater;

    public CategoryAdapter(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.grid_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.event_name.setText(list.get(position));
        //Static image
        Picasso.with(context)
                .load(R.mipmap.ic_launcher)
                .into(holder.event_image);
    }

    @Override
    public int getItemCount() {
        if(list==null){
            return 0;
        }else {
            return list.size();
        }
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
            String category = list.get(getAdapterPosition());
            Events_main.setCateogory(category);
            CategoryEventList categoryEventList = new CategoryEventList();
            Bundle args = new Bundle();
            args.putString("category",category);
            categoryEventList.setArguments(args);
            FragmentManager manager = ((Activity)context).getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.inside_fragment, categoryEventList);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
