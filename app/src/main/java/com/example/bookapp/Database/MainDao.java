package com.example.bookapp.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.Models.Volumes;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface MainDao
{
    @Query("SELECT * FROM VolumeInfo")
    List<VolumeInfo> getFavoriteBook();

    //unesi query
    @Insert(onConflict = REPLACE)
    void insertFavoriteBook(VolumeInfo volumeInfo);

    @Delete
    void deleteFavoriteBook(VolumeInfo volumeInfo);

}
