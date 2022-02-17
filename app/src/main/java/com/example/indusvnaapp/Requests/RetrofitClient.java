package com.example.indusvnaapp.Requests;

import com.google.android.gms.common.api.Api;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    public void generateClient(){

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(6000, TimeUnit.SECONDS)
                .connectTimeout(6000, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()

                //add yout pyhton base  url here

                .baseUrl("http://192.168.70.117/tutorial/public/") //Make sure to include your local machine or server ip/url
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }


    public Api getApi(){
        return retrofit.create(Api.class);
    }
}