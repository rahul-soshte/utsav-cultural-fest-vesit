package com.hunterlab.hunter.utsav18.Events_Utsav;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hunterlab.hunter.utsav18.R;

import java.util.ArrayList;

/**
 * Created by hunter on 4/2/18.
 */

public class EventCatAdapter extends RecyclerView.Adapter<EventCatAdapter.ViewHolder> {


    private ArrayList<EventCat> aDataSet;
    private String aId;
    public EventCatAdapter(ArrayList<EventCat> dataSet) {
        aDataSet = dataSet;
    }
    @Override
    public EventCatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        // if (viewType == CHAT_END) {
        //    v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_chat_end, parent, false);
        //  } else {
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listrowcats,parent,false);

        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        EventCat eventCat = aDataSet.get(position);
        holder.mTextView.setText(eventCat.getCatname());
    }

    @Override
    public int getItemCount() {
        return aDataSet.size();
    }

    /**
     * Inner Class for a recycler view
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;

        ViewHolder(View v) {
            super(v);
            mTextView = (TextView) itemView.findViewById(R.id.textEventCat);
        }
    }

    public void updateList(ArrayList<EventCat> list){
        aDataSet = list;
        notifyDataSetChanged();
    }
}
