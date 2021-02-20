package com.example.bookapp.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManager
{
    static  ApiManager instance;
    private RetrofitVolumes service;

    private ApiManager()
    {
        Retrofit.Builder builder = new Retrofit.Builder();
        //postavljanje retroﬁt-a
        Retrofit retrofit = builder.baseUrl("https://www.googleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitVolumes.class);
    }

    public static  ApiManager getInstance()
    {
        if (instance == null)
        {
            instance = new ApiManager();
        }
        return  instance;
    }
    //DOHVAĆA NAM API
    public RetrofitVolumes service()
    {
        return service;
    }
}
