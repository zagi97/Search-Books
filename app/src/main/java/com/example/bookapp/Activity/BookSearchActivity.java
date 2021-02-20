package com.example.bookapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.Adapters.PagerAdapter;
import com.example.bookapp.Adapters.RecyclerViewBookAdapter;
import com.example.bookapp.Fragments.BookAuthorFragment;
import com.example.bookapp.Fragments.BookTitleFragment;
import com.example.bookapp.Fragments.FavoriteBookFragment;
import com.example.bookapp.Models.Volumes;
import com.example.bookapp.Models.VolumesResponse;
import com.example.bookapp.Network.ApiManager;
import com.example.bookapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookSearchActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        //TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabBar);
        viewPager = (ViewPager) findViewById(R.id.vpBookPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());

        //Log.d("ADebugTag", "Value: " + Integer.toString(tabLayout.getTabCount()));
        tabLayout.setupWithViewPager(viewPager);
        //When you use the default implementation of setOffscreenPageLimit() it is only loading the one fragment which is to the right of it.
        viewPager.setOffscreenPageLimit(2);

        viewPager.setAdapter(pagerAdapter);
            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
            {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
}}