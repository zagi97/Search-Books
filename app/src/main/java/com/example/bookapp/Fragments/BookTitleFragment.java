package com.example.bookapp.Fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookapp.Activity.BookSearchActivity;
import com.example.bookapp.Adapters.RecyclerViewBookAdapter;
import com.example.bookapp.Models.Volumes;
import com.example.bookapp.Models.VolumesResponse;
import com.example.bookapp.Network.ApiManager;
import com.example.bookapp.R;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookTitleFragment extends Fragment implements Callback<VolumesResponse>
{
    private TextView tvPogreška;
    private TextInputEditText tietNazivKnjigeAutora;
    private Button oBtnPretraga;

    private static final String LOG_TAG = BookSearchActivity.class.getSimpleName();
    private VolumesResponse volumesResponse = new VolumesResponse();
    private ArrayList<Volumes> volumes;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_title, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        tietNazivKnjigeAutora = (TextInputEditText) view.findViewById(R.id.tietUpisPodataka);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.rvListaKnjiga);
        tvPogreška = (TextView) view.findViewById(R.id.message_display);
        volumes = new ArrayList<>();
        oBtnPretraga = (Button) view.findViewById(R.id.btnPretraga);


        oBtnPretraga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volumes.clear();
                getBooks();
            }
        });

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

    }

    private void setup_recyclerview(ArrayList<Volumes> volumes)
    {
        mAdapter = new RecyclerViewBookAdapter(getActivity(), volumes);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void getBooks()
    {
        // Get the search string from the input field.
        String search_query = tietNazivKnjigeAutora.getText().toString();
        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        boolean is_connected = Read_network_state(this);
        if(!is_connected)
        {
            tvPogreška.setText(R.string.Failed_to_Load_data);
            mRecyclerView.setVisibility(View.INVISIBLE);
            tvPogreška.setVisibility(View.VISIBLE);
            return;
        }

        Log.d("QUERY",search_query);

        if(search_query.equals(""))
        {
            Toast.makeText(getActivity(),"Unesite naziv knjige", Toast.LENGTH_SHORT).show();
            return;
        }

        String final_query = search_query.replace(" ","+");

        ApiManager.getInstance().service().getMainVolumes(final_query).enqueue(this);
    }

    private boolean Read_network_state(BookTitleFragment context)
    {
        boolean is_connected;
        ConnectivityManager cm =(ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        is_connected=info!=null&&info.isConnectedOrConnecting();
        return is_connected;
    }

    @Override
    public void onResponse(Call<VolumesResponse> call, Response<VolumesResponse> response)
    {
        if (!response.isSuccessful())
        {
            tvPogreška.setText("Code: " + response.code());
            Log.e(LOG_TAG, "Neuspjesna konekcija " + response.code() + ": " + response.errorBody());
            return;
        }

        Log.d(LOG_TAG, "Uspjesna kon " + response.code());
        Log.d(LOG_TAG, "Podaci iz " + response.body().toString());

        volumesResponse = response.body();
        volumes = volumesResponse.getVolumes();

        setup_recyclerview(volumes);

    }
    @Override
    public void onFailure(Call<VolumesResponse> call, Throwable t)
    {
        try
        {
            throw t;
        }
        catch (Throwable throwable)
        {
            throwable.printStackTrace();
        }
    }
}