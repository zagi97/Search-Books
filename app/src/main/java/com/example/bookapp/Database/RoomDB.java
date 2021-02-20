package com.example.bookapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.Models.Volumes;

//Dodavanje podataka u tablicu
@Database(entities = {VolumeInfo.class},version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class RoomDB extends RoomDatabase
{
    //kreiranja instance
    private static RoomDB instance_database;
    //definiranje naziva baze podataka
    private static String DATABASE_NAME = "Books";

    public synchronized static  RoomDB getInstance(Context context)
    {
        if(instance_database == null)
        {
            // kada baza podataka ne postoji, inicijaliziraj bazu
            instance_database = Room.databaseBuilder(context.getApplicationContext()
                    ,RoomDB.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        //vrati bazu
        return instance_database;
    }

    //kreiraj DAO -> data access object
    public abstract MainDao mainDao();
}
