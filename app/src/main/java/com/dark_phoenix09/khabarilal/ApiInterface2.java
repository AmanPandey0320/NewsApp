package com.dark_phoenix09.khabarilal;

import com.dark_phoenix09.khabarilal.models.Headlines;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface2 {

    @GET("top-headlines")
    Call<Headlines> getHeadlines(
            @Query("country") String country,
            @Query("category") String category,
            @Query("apiKey") String apiKey
    );

}
