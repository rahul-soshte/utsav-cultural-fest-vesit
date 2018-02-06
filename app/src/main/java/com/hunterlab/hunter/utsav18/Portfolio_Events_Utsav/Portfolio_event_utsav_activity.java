package com.hunterlab.hunter.utsav18.Portfolio_Events_Utsav;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hunterlab.hunter.utsav18.Event_Single.EventSingleDetailsActivity;
import com.hunterlab.hunter.utsav18.R;
import com.hunterlab.hunter.utsav18.RecyclerTouchListener;
import com.hunterlab.hunter.utsav18.SimpleDividerItemDecoration;
import java.util.ArrayList;

public class Portfolio_event_utsav_activity extends AppCompatActivity {

    ArrayList<PortfolioEvent> eventsList=new ArrayList<>();
    RecyclerView recyclerView;
    PortfolioEventAdapter portfolioEventAdapter;
    LinearLayoutManager linearLayoutManager;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_event_utsav_activity);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView=(RecyclerView)findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        portfolioEventAdapter = new PortfolioEventAdapter(eventsList);

        recyclerView.setAdapter(portfolioEventAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        //   recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PortfolioEvent portfolioEvent= eventsList.get(position);
                //    Toast.makeText(getActivity().getApplicationContext(),author.getId(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),EventSingleDetailsActivity.class);
                intent.putExtra("id_event",portfolioEvent.getId());
                intent.putExtra("id",id);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.child("Utsav18").child(id).child("Events").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventsList.clear();
                for(DataSnapshot authorsnapshot:dataSnapshot.getChildren())
                {
                     PortfolioEvent portfolioEvent=authorsnapshot.getValue(PortfolioEvent.class);
                     String id=authorsnapshot.getKey();
                     portfolioEvent.setId(id);
                     eventsList.add(portfolioEvent);
                }
                portfolioEventAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
