package com.example.bookapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.Activity.BookSearchActivity;
import com.example.bookapp.Adapters.FavoriteBookAdapter;
import com.example.bookapp.Adapters.RecyclerViewBookAdapter;

import com.example.bookapp.Database.RoomDB;
import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.Models.Volumes;
import com.example.bookapp.Models.VolumesResponse;
import com.example.bookapp.Network.ApiManager;
import com.example.bookapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoriteBookFragment extends Fragment
{

    private TextView tvPogreška;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button btnDeleteBook;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getFavoriteBooks();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_book, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvListaFavoriteKnjiga);
        tvPogreška = (TextView) view.findViewById(R.id.message_display);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.main_toolbar);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);


    }
    @Override
    public void onResume() {
        super.onResume();
        getFavoriteBooks();
    }

    // dohvaca iz baze spremljene knjige..
    private void getFavoriteBooks(){
        class GetFavoriteBooks extends AsyncTask<Void, Void, List<VolumeInfo>>{
            @Override
            protected List<VolumeInfo> doInBackground(Void... voids) {
                List<VolumeInfo> volume_info = RoomDB
                        .getInstance(getActivity().getApplicationContext())
                        .mainDao()
                        .getFavoriteBook();
                return volume_info;
            }

            @Override
            protected void onPostExecute(List<VolumeInfo> volume_info) {
                super.onPostExecute(volume_info);
                mAdapter  = new FavoriteBookAdapter(getContext(),volume_info);
                mRecyclerView.setAdapter(mAdapter);
            }

        }
        GetFavoriteBooks gfb = new GetFavoriteBooks();
        gfb.execute();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;*/
        inflater.inflate(R.menu.favorite_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_delete_all_favorite:
                class DeleteFavorite extends AsyncTask<Void, Void, Void>
                {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        VolumeInfo volumeInfo = new VolumeInfo();
                        RoomDB.getInstance(getActivity())
                                .mainDao()
                                .deleteFavoriteBook(volumeInfo);
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid)
                    {
                        super.onPostExecute(aVoid);
                    }
                }
                DeleteFavorite df = new DeleteFavorite();
                df.execute();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}