package com.example.bookapp.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.bookapp.Database.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

//Definirali smo ime tablice
@Entity(tableName = "volumes")
//@TypeConverters(Converters.class)
public class Volumes implements Serializable
{
    //Kreiramo identifikator reda u tablici
    @PrimaryKey(autoGenerate = true)
    private int ID_book;

   // @ColumnInfo(name = "volumeInfo")
    @SerializedName("volumeInfo")
    @Expose
    public VolumeInfo volume_info;

    @SerializedName("saleInfo")
    @Expose
    public SaleInfo sale_info;

    public int getID_book()
    {
        return ID_book;
    }

    public VolumeInfo getVolume_info()
    {
        return volume_info;
    }

    public SaleInfo getSale_info()
    {
        return sale_info;
    }

    public void setID_book(int ID_book) {
        this.ID_book = ID_book;
    }

}

