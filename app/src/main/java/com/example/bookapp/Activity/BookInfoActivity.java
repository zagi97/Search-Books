package com.example.bookapp.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookapp.Adapters.FavoriteBookAdapter;
import com.example.bookapp.Database.RoomDB;
import com.example.bookapp.Models.ImageLinks;
import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.Models.Volumes;
import com.example.bookapp.Models.VolumesResponse;
import com.example.bookapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookInfoActivity extends AppCompatActivity  {

    Button btnAddFavorites;
    TextView tvTitle;
    TextView tvAuthors;
    TextView tvDescription;
    TextView tvCategory;
    TextView tvInfo;
    TextView tvPublishDate;
    TextView tvAverageRating;
    TextView tvRatingsCount;
    TextView tvMaturityRating;
    ImageView ivThumbnail;


    private Context mContext;
    private RequestOptions options;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info);

        Toolbar toolbar = findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);

        //hide the default actionBar
//        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        String title ="";
        String authors ="";
        String description ="";
        String categories ="";
        String publishDate ="";
        String info ="";
        String thumbnail ="";
        String averageRating="";
        String ratingsCount="";
        String maturityRating="";
      //  String pagecount="";

        if(extras != null)
        {
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            thumbnail = extras.getString("book_thumbnail");
            averageRating = extras.getString("book_average_rating");
            ratingsCount = extras.getString("book_rating");
            maturityRating = extras.getString("book_maturity_rating");
        }
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_id);
        collapsingToolbarLayout.setTitleEnabled(true);



        tvTitle = findViewById(R.id.aa_book_name);
        tvAuthors = findViewById(R.id.aa_author);
        tvDescription = findViewById(R.id.aa_description);
        tvCategory = findViewById(R.id.aa_category);
        tvPublishDate = findViewById(R.id.aa_publish_date);
        tvInfo = findViewById(R.id.aa_info);
        tvAverageRating = findViewById(R.id.aa_average_rating);
        tvRatingsCount = findViewById(R.id.aa_ratings_count);
        tvMaturityRating = findViewById(R.id.aa_maturity_rating);

        ivThumbnail = findViewById(R.id.aa_thumbnail);

        btnAddFavorites = findViewById(R.id.aa_button);

        tvTitle.setText(title);
        tvAuthors.setText(authors
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim());
        tvDescription.setText(description);
        tvCategory.setText(categories
                .replace("[", "")  //remove the right bracket
                .replace("]", "")  //remove the left bracket
                .trim());
        tvPublishDate.setText(publishDate);
        tvAverageRating.setText(averageRating);
        tvRatingsCount.setText(ratingsCount);
        tvMaturityRating.setText(maturityRating);

        final String bodyy =description;
        final String bodyyy = thumbnail;

        Log.d("ADebugTag0", "Naslov: " + tvTitle.toString());
        Log.d("ADebugTag1", "Autori: " + tvAuthors.toString());
        Log.d("ADebugTag2", "Kategorije: " + tvCategory.toString());
        Log.d("ADebugTag3", "Kategorije: " + tvDescription.toString());

        Log.d("ADebugTag5", "Kategorije: " + bodyy);
        Log.d("ADebugTag5", "Slika: " + bodyyy);

        this.mContext = mContext;
        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.book_cover_loading)
                .error(R.drawable.book_cover_loading);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);
            }
        });

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.book_cover_loading)
                .error(R.drawable.ic_round_not_interested_24);

        if (ivThumbnail !=null)
        {
            Glide.with(this)
                    .load(thumbnail)
                    .apply(requestOptions)
                    .into(ivThumbnail);
        }
        else if(ivThumbnail == null)
        {
            Glide.with(this)
                    .load(ivThumbnail);
        }

        btnAddFavorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddToFavorites();
                deleteMovie();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;*/
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Bundle extras = getIntent().getExtras();
        String description ="";
        if(extras != null)
        {
            description = extras.getString("book_desc");
        }
        switch (item.getItemId())
        {
            case R.id.btn_share:
                Intent sharingIntent =  new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = description;
                String shareSubject = "Your Subject Here";

                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(sharingIntent, "Share Using"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void AddToFavorites()
    {
        Bundle extras = getIntent().getExtras();
        String title ="";
        String authors ="";
        String description ="";
        String categories ="";
        String publishDate ="";
        String info ="";
        String thumbnail ="";
        String pagecount="";
        String averageRating="";
        String ratingsCount="";
        String maturityRating="";
//favorite_
        if(extras != null)
        {
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            categories = extras.getString("book_categories");
            description = extras.getString("book_desc");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            thumbnail = extras.getString("book_thumbnail");
            pagecount = extras.getString("book_page_count");
            averageRating = extras.getString("book_average_rating");
            ratingsCount = extras.getString("book_rating");
            maturityRating = extras.getString("book_maturity_rating");
        }


        final String Title = title;
        final String Authors = authors;
        final String Category = categories;
        final String PageCount = pagecount;
        final String Thumbnail = thumbnail;
        final String Description = description;
        final String PublishDate = publishDate;
        final String Info = info;
        final String AverageRating = averageRating;
        final String RatingsCount = ratingsCount;
        final String MaturityRating = maturityRating;


        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                VolumeInfo volumeInfo = new VolumeInfo();
                volumeInfo.setTitle(Title);
                volumeInfo.setAuthors(Collections.singletonList(Collections.singletonList(Authors).toString().
                        replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim()));
                volumeInfo.setCategories(Collections.singletonList(Collections.singletonList(Category).toString().
                        replace("[", "")  //remove the right bracket
                        .replace("]", "")  //remove the left bracket
                        .trim()));
                //volumeInfo.setThumbnails(Thumbnail);
                volumeInfo.setPageCount(PageCount);
                volumeInfo.setDescription(Description);
                volumeInfo.setPublishedDate(PublishDate);
                volumeInfo.setInfoLink(Info);
                volumeInfo.setAverageRating(AverageRating);
                volumeInfo.setRatingsCount(RatingsCount);
                volumeInfo.setMaturityRating(MaturityRating);
                RoomDB.getInstance(getApplicationContext())
                        .mainDao()
                        .insertFavoriteBook(volumeInfo);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }
        SaveTask st = new SaveTask();
        st.execute();
    }
    private void deleteMovie()
    {
        class DeleteFavorite extends AsyncTask<Void, Void, Void>{
            @Override
            protected Void doInBackground(Void... voids) {
                VolumeInfo volumeInfo = new VolumeInfo();
                RoomDB.getInstance(getApplicationContext())
                        .mainDao()
                        .deleteFavoriteBook(volumeInfo);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
            }
        }
        DeleteFavorite df = new DeleteFavorite();
        df.execute();
    }

}