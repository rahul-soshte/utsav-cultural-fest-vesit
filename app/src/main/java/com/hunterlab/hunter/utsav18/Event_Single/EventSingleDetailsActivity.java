package com.hunterlab.hunter.utsav18.Event_Single;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.hunterlab.hunter.utsav18.R;

public class EventSingleDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_single_details);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");
    }
}
