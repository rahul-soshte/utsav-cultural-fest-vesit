package com.hunterlab.hunter.utsav18.Events;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.hunterlab.hunter.utsav18.R;

/**
 * Created by Jayesh Saita on 12-Sep-16.
 */
public class Events_main extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner sort_spinner;
    SwipeRefreshLayout event_refresh;
    final String[] SpinnerList={
            "Aesthetics",
            "Literature",
            "Visual Arts",
            "InterCollege Events",
            "Music",
            "Fun Events",
            "Performing Arts"

    };
    ConnectivityManager cm;
    ImageView nonet;
    static int selectedCat=1;
    static String category="";

    public Events_main(){
        //Default Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.events_main_layout,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState==null) {
            sort_spinner = (Spinner) getView().findViewById(R.id.sort_spinner);
            sort_spinner.setVisibility(View.GONE);
            //ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, SpinnerList);
            //sort_spinner.setAdapter(arrayAdapter);
            nonet = (ImageView) getView().findViewById(R.id.no_net_events);
            cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            event_refresh = (SwipeRefreshLayout) getView().findViewById(R.id.event_refresh);
            event_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    NetworkInfo ni = cm.getActiveNetworkInfo();
                    if (ni == null) {
                        Toast.makeText(getActivity(), "Check internet connection", Toast.LENGTH_SHORT).show();
                    } else {
                        nonet.setVisibility(View.GONE);
                        sort_spinner.setVisibility(View.VISIBLE);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, SpinnerList);
                        sort_spinner.setAdapter(arrayAdapter);
                        sort_spinner.setOnItemSelectedListener(Events_main.this);
                        sort_spinner.setSelection(1, true);
                        CategoryEvents categoryEvents = new CategoryEvents();
                        FragmentManager manager1 = getFragmentManager();
                        FragmentTransaction transaction1 = manager1.beginTransaction();
                        manager1.popBackStackImmediate();
                        transaction1.replace(R.id.inside_fragment, categoryEvents);
                        transaction1.commit();
                    }
                    event_refresh.setRefreshing(false);
                }
            });
            event_refresh.setEnabled(true);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni==null){
            //No active networks
            nonet.setImageResource(R.drawable.no_net);
            nonet.setVisibility(View.VISIBLE);
            sort_spinner.setVisibility(View.GONE);
            Toast.makeText(getActivity(),"Swipe Down to Refresh", Toast.LENGTH_SHORT).show();
        }else{
            sort_spinner.setVisibility(View.VISIBLE);
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.support_simple_spinner_dropdown_item, SpinnerList);
            sort_spinner.setAdapter(arrayAdapter);
            sort_spinner.setOnItemSelectedListener(this);
            if(selectedCat == 3){
                sort_spinner.setSelection(1,true);
            }else {
                Events_main.setCateogory("");
                sort_spinner.setSelection(selectedCat, true);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedCat = i;
        switch(i){
            case 0:
                AllEvents allEvents = new AllEvents();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                manager.popBackStackImmediate();
                transaction.replace(R.id.inside_fragment, allEvents);
                transaction.commit();
                break;
            case 1:
                CategoryEvents categoryEvents = new CategoryEvents();
                FragmentManager manager1 = getFragmentManager();
                FragmentTransaction transaction1 = manager1.beginTransaction();
                manager1.popBackStackImmediate();
                transaction1.replace(R.id.inside_fragment, categoryEvents);
                if(!category.equals("")){
                    CategoryEventList eventList = new CategoryEventList();
                    Bundle args = new Bundle();
                    args.putString("category",category);
                    eventList.setArguments(args);
                    transaction1.replace(R.id.inside_fragment, eventList);
                    transaction1.addToBackStack(null);
                }
                transaction1.commit();
                break;
            case 2:
                TodayEvents todayEvents = new TodayEvents();
                FragmentManager manager2 = getFragmentManager();
                FragmentTransaction transaction2 = manager2.beginTransaction();
                manager2.popBackStackImmediate();
                transaction2.replace(R.id.inside_fragment, todayEvents);
                transaction2.commit();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public static void setCateogory(String cat){
        category = cat;
    }
}
