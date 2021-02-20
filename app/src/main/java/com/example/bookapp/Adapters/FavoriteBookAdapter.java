package com.example.bookapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bookapp.Activity.BookInfoActivity;
//import com.example.bookapp.Database.Book;
import com.example.bookapp.Database.RoomDB;
import com.example.bookapp.Models.VolumeInfo;
import com.example.bookapp.R;

import java.util.List;

public class FavoriteBookAdapter extends RecyclerView.Adapter<FavoriteBookAdapter.FavoriteViewHolder>
{
    private static final String TAG ="nema slike " ;

    Context mContext;
    List<VolumeInfo> oBooksList;
    private RoomDB database;
    private RequestOptions options;

    //kreiranje konstruktora
    public FavoriteBookAdapter(Context mContext, List<VolumeInfo> oBooksList)
    {
        this.mContext = mContext;
        this.oBooksList = oBooksList;
        notifyDataSetChanged();

        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.book_cover_loading)
                .error(R.drawable.error_image_book);
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.favorite_book_information, viewGroup, false);
        final FavoriteViewHolder holder = new FavoriteViewHolder(view);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext , BookInfoActivity.class);
                int pos = holder.getAdapterPosition();

                if (oBooksList.get(pos).getTitle() !=null)
                {
                    i.putExtra("book_title", oBooksList.get(pos).getTitle());
                }
                else
                {
                    i.putExtra("book_title", "N/A");
                }

                if (oBooksList.get(pos).getAuthors() !=null)
                {
                    Log.d( String.valueOf(oBooksList.get(pos).getAuthors()), "onClick: ");
                    i.putExtra("book_author", String.valueOf(oBooksList.get(pos).getAuthors()));
                }
                else
                {
                    i.putExtra("book_author", "NE/A");
                }

                if (oBooksList.get(pos).getDescription() !=null)
                {
                    i.putExtra("book_desc", oBooksList.get(pos).getDescription());
                }
                else
                {
                    i.putExtra("book_desc", "N/A");
                }

                if (oBooksList.get(pos).getCategories() !=null)
                {
                    i.putExtra("book_categories",String.valueOf(oBooksList.get(pos).getCategories()));
                }
                else
                {
                    i.putExtra("book_categories","N/A");
                }

                if (oBooksList.get(pos).getPublishedDate() !=null)
                {
                    i.putExtra("book_publish_date", oBooksList.get(pos).getPublishedDate());
                }
                else
                {
                    i.putExtra("book_publish_date", "N/A");
                }

                if ( oBooksList.get(pos).getInfoLink() !=null)
                {
                    i.putExtra("book_info", oBooksList.get(pos).getInfoLink());
                }
                else
                {
                    i.putExtra("book_info","N/A");
                }

                if (oBooksList.get(pos).getPreviewLink() !=null)
                {
                    i.putExtra("book_preview", oBooksList.get(pos).getPreviewLink());
                }
                else
                {
                    i.putExtra("book_preview", "N/A");
                }

                if (oBooksList.get(pos).getThumbnails() !=null)
                {
                    i.putExtra("book_thumbnail", oBooksList.get(pos).getThumbnails().getThumbnail());
                }
                else
                {
                    i.putExtra("book_thumbnail", "N/A");
                }

                if (oBooksList.get(pos).getAverageRating() !=null)
                {
                    i.putExtra("book_average_rating", oBooksList.get(pos).getAverageRating());
                }
                else
                {
                    i.putExtra("book_average_rating", "N/A");
                }

                if (oBooksList.get(pos).getRatingsCount() !=null)
                {
                    i.putExtra("book_rating", oBooksList.get(pos).getRatingsCount());
                }
                else
                {
                    i.putExtra("book_rating", "N/A");
                }

                if (oBooksList.get(pos).getMaturityRating() !=null)
                {
                    i.putExtra("book_maturity_rating", oBooksList.get(pos).getMaturityRating());
                }
                else
                {
                    i.putExtra("book_maturity_rating", "N/A");
                }
                if (oBooksList.get(pos).getPageCount() !=null)
                {
                    i.putExtra("book_page_count", oBooksList.get(pos).getPageCount());
                }
                else
                {
                    i.putExtra("book_page_count", "N/A");
                }
                mContext.startActivity(i);
            }
        });
        return holder;
    }

    //final holder
    @Override
    public void onBindViewHolder(@NonNull FavoriteBookAdapter.FavoriteViewHolder holder, int i)
    {
        VolumeInfo volume_info = oBooksList.get(i);

        if (volume_info.getThumbnails() !=null)
        {
            Log.d(TAG, "Ovo je kontekst koji smo prosljedili: " + mContext);
            Glide.with(mContext)
                    .load(volume_info.getThumbnails().getThumbnail())
                    .apply(options)
                    .into(holder.ivThumbnail);
        }

        else
        {
            Log.d(TAG, "Ovo je kontekst koji smo prosljedili: " + mContext);
            Glide.with(mContext)
                    .load(R.drawable.not_available_image_book)
                    .into(holder.ivThumbnail);
        }

        if (volume_info.getTitle() !=null)
        {
            holder.tvTitle.setText(volume_info.getTitle());
        }
        else
        {
            holder.tvTitle.setText("N/A");
        }

        if (volume_info.getAuthors() !=null)
        {
            holder.tvAuthor.setText(String.valueOf(volume_info.getAuthors())
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim());
        }
        else
        {
            holder.tvAuthor.setText("N/A");
        }

        if (volume_info.getCategories() !=null)
        {
            holder.tvCategory.setText(String.valueOf(volume_info.getCategories())
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim());
        }
        else
        {
            holder.tvCategory.setText("N/A");
        }

        if (volume_info.getPageCount() !=null)
        {
            holder.tvPageCount.setText(volume_info.getPageCount());
        }
        else
        {
            holder.tvPageCount.setText("N/A");
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                class DeleteFavorite extends AsyncTask<Void, Void, Void>
                {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        VolumeInfo volumeInfo = new VolumeInfo();
                        RoomDB.getInstance(mContext)
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
        });
    }

    @Override
    public int getItemCount()
    {
        return oBooksList.size();
    }

    class FavoriteViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivThumbnail ;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvPageCount;
        TextView tvAuthor;
        LinearLayout container ;
        ImageView btnDelete;
        public FavoriteViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.favorite_thumbnail);
            tvTitle = itemView.findViewById(R.id.favorite_title);
            tvAuthor = itemView.findViewById(R.id.favorite_author);
            tvCategory = itemView.findViewById(R.id.favorite_category);
            tvPageCount = itemView.findViewById(R.id.favorite_page_count);
            container = itemView.findViewById(R.id.favorite_container);
            btnDelete = itemView.findViewById(R.id.iv_delete_favorite_book);
        }
    }
}
