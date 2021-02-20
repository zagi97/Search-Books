package com.example.bookapp.Database;

import androidx.room.TypeConverter;

import com.example.bookapp.Models.ImageLinks;
import com.example.bookapp.Models.SaleInfo;
import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.Models.Volumes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Converters
{
    @TypeConverter
    public static List<String> fromStringtoList(String value)
    {
        Type listType = new TypeToken<List<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list)
    {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }


    @TypeConverter
    public static ImageLinks fromStringToImageLinks(String value)
    {
        Type listType = new TypeToken<String>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromVolumesInfoToString(ImageLinks image_links)
    {
        Gson gson = new Gson();
        String json = gson.toJson(image_links);
        return json;
    }


}
