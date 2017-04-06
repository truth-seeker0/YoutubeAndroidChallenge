package com.androidapptech.youtubeandroidchallenge.retrofit;

import com.androidapptech.youtubeandroidchallenge.pojo_model.YoutubeList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Benjamin on 4/5/2017.
 */

public interface YoutubeService {
    String API_BASE_URL = "http://www.razor-tech.co.il/";

    @GET("hiring/youtube-api.json")
    Call<YoutubeList> getLists();


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
