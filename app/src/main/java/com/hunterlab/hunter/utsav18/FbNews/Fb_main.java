package com.hunterlab.hunter.utsav18.FbNews;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.hunterlab.hunter.utsav18.FbNews.adapter.FbAdapter;
import com.hunterlab.hunter.utsav18.FbNews.model.FbGraphResponse;
import com.hunterlab.hunter.utsav18.FbNews.model.FbPic;
import com.hunterlab.hunter.utsav18.FbNews.rest.ApiClient;
import com.hunterlab.hunter.utsav18.FbNews.rest.ApiInterface;
import com.hunterlab.hunter.utsav18.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Jayesh Saita on 12-Sep-16.
 */
public class Fb_main extends Fragment {

    ApiInterface apiService;
    SwipeRefreshLayout refreshLayout;
    FbAdapter adapter;
    RecyclerView recyclerView;
    ImageView nonet;
    ConnectivityManager cm;
    ProgressBar progressBar;
    Context context;

    public Fb_main(){
        //Default Constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fb_main,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getActivity();
        recyclerView = (RecyclerView)getView().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        refreshLayout = (SwipeRefreshLayout)getView().findViewById(R.id.swipeToRefresh);
        refreshLayout.setEnabled(true);
        nonet = (ImageView)getView().findViewById(R.id.no_net);
        cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        progressBar = (ProgressBar)getView().findViewById(R.id.progress_bar);
    }

    @Override
    public void onStart() {
        super.onStart();

        refreshLayout.setEnabled(true);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(true);
                NetworkInfo ni = cm.getActiveNetworkInfo();
                if(ni==null){
                    Toast.makeText(getActivity(),"Check Internet Connection", Toast.LENGTH_SHORT).show();
                }else{
                    //Do something here
                    nonet.setVisibility(View.GONE);
                    loadData();
                }
                refreshLayout.setRefreshing(false);
            }
        });

        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            refreshLayout.setEnabled(true);
            nonet.setImageResource(R.drawable.no_net);
            nonet.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(),"Swipe Down to Refresh", Toast.LENGTH_SHORT).show();
        } else {
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
            final String token = getResources().getString(R.string.fb_app_id) + "|" + getResources().getString(R.string.fb_app_secret);

            apiService = ApiClient.getClient().create(ApiInterface.class);

            String coverfields = "cover,picture";
            Call<FbPic> covercall = apiService.getPic(coverfields, token);

            final String[] profilepic = new String[1];
            covercall.enqueue(new Callback<FbPic>() {
                @Override
                public void onResponse(Call<FbPic> call, Response<FbPic> response) {
                    profilepic[0] = response.body().getPicture().getData().getUrl();
                }

                @Override
                public void onFailure(Call<FbPic> call, Throwable t) {
                    //Show Error image here
                    nonet.setImageResource(R.drawable.no_net);
                    nonet.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });

            String fields = "created_time,message,full_picture,link";
            Call<FbGraphResponse> call = apiService.getFeed(fields, token);
            call.enqueue(new Callback<FbGraphResponse>() {
                @Override
                public void onResponse(Call<FbGraphResponse> call, Response<FbGraphResponse> response) {
                    adapter = new FbAdapter(context, response.body().getData(), profilepic[0]);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<FbGraphResponse> call, Throwable t) {
                    //Add image when no internet connection
                    nonet.setImageResource(R.drawable.no_net);
                    nonet.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    void loadData(){
        //refreshLayout.setEnabled(true);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            nonet.setImageResource(R.drawable.no_net);
            nonet.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        } else {
            progressBar.setIndeterminate(true);
            progressBar.setVisibility(View.VISIBLE);
            final String token = getResources().getString(R.string.fb_app_id) + "|" + getResources().getString(R.string.fb_app_secret);

            apiService = ApiClient.getClient().create(ApiInterface.class);

            String coverfields = "cover,picture";
            Call<FbPic> covercall = apiService.getPic(coverfields, token);

            final String[] profilepic = new String[1];
            covercall.enqueue(new Callback<FbPic>() {
                @Override
                public void onResponse(Call<FbPic> call, Response<FbPic> response) {
                    profilepic[0] = response.body().getPicture().getData().getUrl();
                }

                @Override
                public void onFailure(Call<FbPic> call, Throwable t) {
                    //Show Error image here
                    nonet.setImageResource(R.drawable.no_net);
                    nonet.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });

            String fields = "created_time,message,full_picture,link";
            Call<FbGraphResponse> call = apiService.getFeed(fields, token);
            call.enqueue(new Callback<FbGraphResponse>() {
                @Override
                public void onResponse(Call<FbGraphResponse> call, Response<FbGraphResponse> response) {
                    adapter = new FbAdapter(context, response.body().getData(), profilepic[0]);
                    recyclerView.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<FbGraphResponse> call, Throwable t) {
                    //Add image when no internet connection
                    nonet.setImageResource(R.drawable.no_net);
                    nonet.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}
