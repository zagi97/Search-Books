package com.example.bookapp.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class VolumesResponse
{
    @SerializedName("items")
    @Expose
    private ArrayList<Volumes> items;

    public ArrayList<Volumes> getVolumes()
    {
        return items;
    }

}
