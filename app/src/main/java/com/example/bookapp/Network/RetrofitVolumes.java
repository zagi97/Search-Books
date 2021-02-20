package com.example.bookapp.Network;

import com.example.bookapp.Models.VolumesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitVolumes
{
    //kako se rade query parametri u retrofitu
    //?q=tolkien&key=AIzaSyDlnJ6n2kWpkqfb2RZTtlsMtPW_l-HU72g
    @GET("books/v1/volumes")
    Call<VolumesResponse> getMainVolumes(
            @Query("q") String book_name
    );

    @GET("books/v1/volumes")
    Call<VolumesResponse> getEveryVolumesOfAuthor(
            @Query("q") String in_author
    );
}
