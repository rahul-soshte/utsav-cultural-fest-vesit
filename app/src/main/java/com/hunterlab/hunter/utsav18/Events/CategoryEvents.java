package com.hunterlab.hunter.utsav18.Events;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hunterlab.hunter.utsav18.Events.adapter.CategoryAdapter;
import com.hunterlab.hunter.utsav18.Events.models.Event;
import com.hunterlab.hunter.utsav18.Events.models.EventsResponse;
import com.hunterlab.hunter.utsav18.MyRecyclerView;
import com.hunterlab.hunter.utsav18.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jayesh Saita on 13-Sep-16.
 */

public class CategoryEvents extends Fragment {

    MyRecyclerView recyclerView;
    CategoryAdapter adapter;
    JSONObject request;
    ProgressBar progressBar;
    static List<String> elist;
    ImageView nonet;
    ConnectivityManager cm;

    public CategoryEvents(){
        //Default Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.event_list,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar = (ProgressBar)getView().findViewById(R.id.progress_bar);
        nonet = (ImageView)getView().findViewById(R.id.no_events);
        cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        recyclerView = (MyRecyclerView) getView().findViewById(R.id.event_recycler_view);
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onStart() {
        super.onStart();

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni==null){
            nonet.setImageResource(R.drawable.no_net);
            nonet.setVisibility(View.VISIBLE);
        }else{
            loadData();
        }
    }

    void loadData(){
        request = new JSONObject();
        try {
            request.put("method", "GET");
            request.put("url", "events");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new APIMiddleware(new APIMiddleware.TaskListener() {

            @Override
            public void onTaskBegin() {
                progressBar.setIndeterminate(true);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onTaskCompleted(JSONObject response) {

                if(response!=null){
                    String code="";
                    try {
                        code = response.get("code").toString();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if(code.equals("200")){
                        nonet.setVisibility(View.GONE);
                        String resjson = response.toString();
                        Gson gson = new GsonBuilder().create();
                        EventsResponse eventsResponse = gson.fromJson(resjson, EventsResponse.class);
                        List<Event> list = eventsResponse.getData();

                        elist = new ArrayList<String>();
                        for(int i=0;i<list.size();i++){

                            if(elist.contains(list.get(i).getCategory())){
                                continue;
                            }else{
                                elist.add(list.get(i).getCategory());
                            }
                        }
                    }else{
                        Toast.makeText(getActivity(),"Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    nonet.setImageResource(R.drawable.no_net);
                    nonet.setVisibility(View.VISIBLE);
                    //Toast.makeText(getActivity(),"Check Internet Connection",Toast.LENGTH_SHORT).show();
                }
                progressBar.setVisibility(View.GONE);
                adapter = new CategoryAdapter(getActivity(),elist);
                recyclerView.setAdapter(adapter);
            }
        }).execute(request);
    }
}
