package com.hunterlab.hunter.utsav18.FbNews.rest;


import com.hunterlab.hunter.utsav18.FbNews.model.FbGraphResponse;
import com.hunterlab.hunter.utsav18.FbNews.model.FbPic;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jayesh Saita on 04-Sep-16.
 */
public interface ApiInterface {

    @GET("praxistechfest/posts")
    Call<FbGraphResponse> getFeed(@Query("fields") String fields, @Query("access_token") String token);

    @GET("praxistechfest")
    Call<FbPic> getPic(@Query("fields") String fields, @Query("access_token") String token);
}

