package com.example.bookapp.Models;

import com.google.gson.annotations.SerializedName;

public class ImageLinks
{
    @SerializedName("thumbnail")
    String thumbnail;

    public String getThumbnail()
    {
        return thumbnail;
    }

}
