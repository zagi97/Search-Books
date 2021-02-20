package com.example.bookapp.Adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bookapp.Fragments.BookAuthorFragment;
import com.example.bookapp.Fragments.BookTitleFragment;
import com.example.bookapp.Fragments.FavoriteBookFragment;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter
{
    private static int NUM_ITEMS;

    public PagerAdapter(@NonNull FragmentManager fm, int NUM_ITEMS)
    {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.NUM_ITEMS = NUM_ITEMS;
    }
    // Returns the fragment to display for that page
    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        switch (position)
        {
            case 0: // Fragment # 0 - This will show FirstFragment
                return new BookTitleFragment();
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return new BookAuthorFragment();
            case 2: // Fragment # 1 - This will show SecondFragment
                return new FavoriteBookFragment();
            default:
                return null;
        }
    }

    // Returns total number of pages
    @Override
    public int getCount()
    {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        switch (position)
        {
            case 0: // Fragment # 0 - This will show FirstFragment
                return "Pretraga po nazivu knjige";
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return "Pretraga po nazivu autoru";
            case 2: // Fragment # 1 - This will show SecondFragment
                return "Moji favoriti";
            default:
                return null;
        }
    }


/*
        @NonNull
    @Override
    public Fragment getItem(int position)
    {
        if(position == 0)
        {
            return new BookTitleFragment();
        }
        else if(position == 1)
        {
            return new BookAuthorFragment();
        }
        else if(position == 2)
        {
            return new FavoriteBookFragment();
        }
        else
        {
            return null;
        }
    }

 */

}
