package com.example.bookapp.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.bookapp.Database.Converters;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//Definirali smo ime tablice
@Entity(tableName = "VolumeInfo")
//@TypeConverters(Converters.class)
public class VolumeInfo implements Serializable
{
    //Kreiramo identifikator reda u tablici
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int ID_book;

    @ColumnInfo(name = "title")
    @SerializedName("title")
    @Expose
    public String title;

    @ColumnInfo(name = "author")
    @SerializedName("authors")
    @Expose
    public List<String> authors;

    @SerializedName("publishedDate")
    @Expose
    public String publishedDate;

    @SerializedName("description")
    @Expose
    public String description;

    @ColumnInfo(name = "page_count")
    @SerializedName("pageCount")
    @Expose
    public String pageCount;

    @ColumnInfo(name = "category")
    @SerializedName("categories")
    @Expose
    public List<String> categories;

    @ColumnInfo(name = "thumbnail")
    @SerializedName("imageLinks")
    public ImageLinks thumbnails;

    @SerializedName("previewLink")
    @Expose
    public String previewLink;

    @SerializedName("infoLink")
    @Expose
    public String infoLink;

    @SerializedName("averageRating")
    @Expose
    public String averageRating;

    @SerializedName("ratingsCount")
    @Expose
    public String ratingsCount;

    @SerializedName("maturityRating")
    @Expose
    public String maturityRating;

    public String getTitle()
    {
        return title;
    }

    public List<String> getAuthors()
    {
        return authors;
    }

    public String getPublishedDate()
    {
        return publishedDate;
    }

    public String getDescription()
    {
        return description;
    }

    public String getPageCount()
    {
        return pageCount;
    }

    public List<String> getCategories()
    {
        return categories;
    }

    public ImageLinks getThumbnails()
    {
        return thumbnails;
    }

    public String getPreviewLink(){ return  previewLink; }

    public String getInfoLink(){ return  infoLink; }

    public String getAverageRating(){ return  averageRating; }

    public String getRatingsCount(){ return  ratingsCount; }

    public String getMaturityRating(){ return  maturityRating; }


    public int getID_book()
    {
        return ID_book;
    }

    public void setID_book(int ID_book)
    {
        this.ID_book = ID_book;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public void setPageCount(String pageCount) {
        this.pageCount = pageCount;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public void setThumbnails(ImageLinks thumbnails) {
        this.thumbnails = thumbnails;
    }


    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setInfoLink(String infoLink) {
        this.infoLink = infoLink;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public void setRatingsCount(String ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public void setMaturityRating(String maturityRating) {
        this.maturityRating = maturityRating;
    }


}
