package com.dark_phoenix09.khabarilal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient2 {

    private static final String BASE_URL="https://newsapi.org/v2/";
    private static ApiClient2 apiClient;
    private static Retrofit retrofit;

    private  ApiClient2(){
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public  static  synchronized  ApiClient2 getInstance(){
        if(apiClient==null){
            apiClient=new ApiClient2();
        }
        return apiClient;
    }

    public ApiInterface2 getApi(){
        return retrofit.create(ApiInterface2.class);
    }

}
