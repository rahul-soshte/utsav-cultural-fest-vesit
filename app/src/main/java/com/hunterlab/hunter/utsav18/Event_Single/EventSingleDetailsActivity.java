package com.hunterlab.hunter.utsav18.Event_Single;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hunterlab.hunter.utsav18.Portfolio_Events_Utsav.PortfolioEvent;
import com.hunterlab.hunter.utsav18.R;

public class EventSingleDetailsActivity extends AppCompatActivity {

    TextView tName,tDesc,tSize,tNoofClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.for_single_event);

        tName=(TextView)findViewById(R.id.event_name);
        tDesc=(TextView)findViewById(R.id.event_desc3);
        tSize=(TextView)findViewById(R.id.teamsize);
        tNoofClass=(TextView)findViewById(R.id.entriesperclass);


        Intent intent = getIntent();
        String id_event = intent.getStringExtra("id_event");
        String id = intent.getStringExtra("id");


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.child("Utsav18").child(id).child("Events").child(id_event).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                EventSingleDetails eventSingleDetails = dataSnapshot.getValue(EventSingleDetails.class);
//                Toast.makeText(EventSingleDetailsActivity.this,eventSingleDetails.getName(),Toast.LENGTH_LONG).show();
         //       Toast.makeText(EventSingleDetailsActivity.this,Long.toString(eventSingleDetails.getEntriesperclass()),Toast.LENGTH_LONG).show();

                 tName.setText("Event Name:"+eventSingleDetails.getName());
                tDesc.setText(eventSingleDetails.getEvent_desc());
                tSize.setText("Team Size:"+eventSingleDetails.getTeamsize());
                tNoofClass.setText("Entries Per Class:"+Long.toString(eventSingleDetails.getEntriesperclass()));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

