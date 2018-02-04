package com.hunterlab.hunter.utsav18.FbNews.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hunterlab.hunter.utsav18.FbNews.model.Datum;
import com.hunterlab.hunter.utsav18.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Jayesh Saita on 05-Sep-16.
 */
public class FbAdapter extends RecyclerView.Adapter<FbAdapter.MyViewHolder> {

    Context context;
    List<Datum> posts;
    LayoutInflater inflater;
    String profilepic;

    public FbAdapter(Context context, List<Datum> posts, String profilepic){
        this.context = context;
        this.posts = posts;
        inflater = LayoutInflater.from(context);
        this.profilepic = profilepic;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.fb_indie_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Picasso.with(context)
                .load(profilepic)
                .into(holder.profilepic);

        SimpleDateFormat fb_dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ssZZZZZ");

        SimpleDateFormat my_dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");


        String localDateString = null;

        long when = 0;
        try {
            String utcLongDateTime = posts.get(position).getCreatedTime();
            when = fb_dateFormat.parse(utcLongDateTime).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        localDateString = my_dateFormat.format(new Date(when + TimeZone.getDefault().getRawOffset() + (TimeZone.getDefault().inDaylightTime(new Date()) ? TimeZone.getDefault().getDSTSavings() : 0)));
        holder.time.setText(localDateString);

        holder.message.setText(posts.get(position).getMessage());
        holder.link.setText(posts.get(position).getLink());
        //Check posts.fullPicture() here instead of holder.image
        if(!holder.image.equals("")) {

            Picasso.with(context)
                    .load(posts.get(position).getFullPicture())
                    .into(holder.image);
        }else{
            holder.image.setVisibility(View.GONE);
        }

        final String link = posts.get(position).getLink();
        if(link!=null) {
            holder.link.setText(link);
            holder.link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                 }
            });
        }else
            holder.link.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView time;
        TextView message;
        ImageView image;
        ImageView profilepic;
        TextView link;

        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView)itemView.findViewById(R.id.created_time);
            message = (TextView)itemView.findViewById(R.id.message);
            image = (ImageView)itemView.findViewById(R.id.image);
            profilepic = (ImageView)itemView.findViewById(R.id.profile_pic);
            link = (TextView)itemView.findViewById(R.id.link);
        }
    }
}
