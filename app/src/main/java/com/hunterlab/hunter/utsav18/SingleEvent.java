package com.hunterlab.hunter.utsav18;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class SingleEvent extends AppCompatActivity {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    ImageView imageView;
    TextView description;
    TextView category;
    TextView teams;
    TextView cost;
    TextView prize;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_event);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        imageView = (ImageView)findViewById(R.id.event_image);
        description = (TextView)findViewById(R.id.event_description);
        category = (TextView)findViewById(R.id.event_category);
        teams = (TextView)findViewById(R.id.event_teams_of);
        cost = (TextView)findViewById(R.id.event_cost);
        prize = (TextView)findViewById(R.id.event_prize);
        date = (TextView)findViewById(R.id.event_date);


        Bundle bundle = getIntent().getBundleExtra("bundle");
        setSupportActionBar(toolbar);
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            collapsingToolbarLayout.setTitle(bundle.getString("event_name"));
        }else{
            //collapsingToolbarLayout.setTitle("");
            TextView textView = (TextView)findViewById(R.id.event_title);
            textView.setText(bundle.getString("event_name"));
        }
        //collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expandedappbar);
        MainActivity.tab=true;

        String base="http://praxisvesit.com/deep/deeper/deepest/api/";
        String image = bundle.getString("event_image");
        if(image==null) {
            Picasso.with(this)
                    .load(R.mipmap.ic_launcher)
                    .into(imageView);
        }else{
            Picasso.with(this)
                    .load(base+image)
                    .into(imageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                @Override
                                public void onGenerated(Palette palette) {
                                    collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                                    //collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimaryDark)));
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                        getWindow().setStatusBarColor(palette.getMutedColor(getResources().getColor(R.color.colorPrimary)));
                                    }
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }


        String desc = bundle.getString("event_description");
        if(desc == null){
            description.setText("No information");
        }else{
            description.setText(desc);
        }
        category.setText("Category: "+bundle.getString("event_category"));
        String teamsof = bundle.getString("event_teams_of");
        if (teamsof != null) {

            if (teamsof.equals("1")) {
                teams.setText("Individual Event");
            } else {
                teams.setText("Teams of " + teamsof + " people");
            }
        }else if(teamsof==null){
            teams.setText("No information");
        }

        String c = bundle.getString("event_cost");
        if(c==null) {
            cost.setText("Cost:\n₹0");
        }else{
            cost.setText("Cost:\n₹" + c);
        }
        String p = bundle.getString("event_prize");
        if(p==null) {
            prize.setText("Prize:\n₹0");
        }else{
            prize.setText("Prize:\n₹" + p);
        }
        date.setText("Date: "+bundle.getString("event_date") + " Sept '16");

    }}
