package com.example.bookapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import com.example.bookapp.Activity.BookInfoActivity;
import com.example.bookapp.Models.Volumes;
import com.example.bookapp.R;

import java.util.List;

public class RecyclerViewBookAdapter extends RecyclerView.Adapter<RecyclerViewBookAdapter.MyViewHolder>
{
    Context mContext;
    List<Volumes> oBooksList;
    private RequestOptions options;

    public RecyclerViewBookAdapter(Context mContext, List<Volumes> oBooksList)
    {
        this.mContext = mContext;
        this.oBooksList = oBooksList;

        options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.book_cover_loading)
                .error(R.drawable.error_image_book);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(mContext).inflate(R.layout.book_information, viewGroup, false);
        final MyViewHolder holder = new MyViewHolder (view);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext , BookInfoActivity.class);
                int pos = holder.getAdapterPosition();

                if (oBooksList.get(pos).getVolume_info().getTitle() !=null)
                {
                    i.putExtra("book_title", oBooksList.get(pos).getVolume_info().getTitle());
                }
                else
                {
                    i.putExtra("book_title", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getAuthors() !=null)
                {
                    i.putExtra("book_author", String.valueOf(oBooksList.get(pos).getVolume_info().getAuthors()));
                }
                else
                {
                    i.putExtra("book_author", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getDescription() !=null)
                {
                    i.putExtra("book_desc", oBooksList.get(pos).getVolume_info().getDescription());
                }
                else
                {
                    i.putExtra("book_desc", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getCategories() !=null)
                {
                    i.putExtra("book_categories",String.valueOf(oBooksList.get(pos).getVolume_info().getCategories()));
                }
                else
                {
                    i.putExtra("book_categories","N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getPublishedDate() !=null)
                {
                    i.putExtra("book_publish_date", oBooksList.get(pos).getVolume_info().getPublishedDate());
                }
                else
                {
                    i.putExtra("book_publish_date", "N/A");
                }

                if ( oBooksList.get(pos).getVolume_info().getInfoLink() !=null)
                {
                    i.putExtra("book_info", oBooksList.get(pos).getVolume_info().getInfoLink());
                }
                else
                {
                    i.putExtra("book_info","N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getPreviewLink() !=null)
                {
                    i.putExtra("book_preview", oBooksList.get(pos).getVolume_info().getPreviewLink());
                }
                else
                {
                    i.putExtra("book_preview", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getThumbnails() !=null)
                {
                    i.putExtra("book_thumbnail", oBooksList.get(pos).getVolume_info().getThumbnails().getThumbnail());
                }
                else
                {
                    i.putExtra("book_thumbnail", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getAverageRating() !=null)
                {
                    i.putExtra("book_average_rating", oBooksList.get(pos).getVolume_info().getAverageRating());
                }
                else
                {
                    i.putExtra("book_average_rating", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getRatingsCount() !=null)
                {
                    i.putExtra("book_rating", oBooksList.get(pos).getVolume_info().getRatingsCount());
                }
                else
                {
                    i.putExtra("book_rating", "N/A");
                }

                if (oBooksList.get(pos).getVolume_info().getMaturityRating() !=null)
                {
                    i.putExtra("book_maturity_rating", oBooksList.get(pos).getVolume_info().getMaturityRating());
                }
                else
                {
                    i.putExtra("book_maturity_rating", "N/A");
                }
                if (oBooksList.get(pos).getVolume_info().getPageCount() !=null)
                {
                    i.putExtra("book_page_count", oBooksList.get(pos).getVolume_info().getPageCount());
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

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBookAdapter.MyViewHolder holder, int i)
    {
        Volumes volume = oBooksList.get(i);

        if (volume.getVolume_info().getThumbnails() !=null)
        {
            Glide.with(mContext)
                    .load(volume.getVolume_info().getThumbnails().getThumbnail())
                    .apply(options)
                    .into(holder.ivThumbnail);
        }

        else
        {
            Glide.with(mContext)
                    .load(R.drawable.not_available_image_book)
                    .into(holder.ivThumbnail);
        }

        if (volume.getVolume_info().getTitle() !=null)
        {
            holder.tvTitle.setText(volume.getVolume_info().getTitle());
        }
        else
        {
            holder.tvTitle.setText("N/A");
        }

        if (volume.getVolume_info().getAuthors() !=null)
        {
            holder.tvAuthor.setText(String.valueOf(volume.getVolume_info().getAuthors())
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim());
        }
        else
        {
            holder.tvAuthor.setText("N/A");
        }

        if (volume.getVolume_info().getCategories() !=null)
        {
            holder.tvCategory.setText(String.valueOf(volume.getVolume_info().getCategories())
                    .replace("[", "")  //remove the right bracket
                    .replace("]", "")  //remove the left bracket
                    .trim());
        }
        else
        {
            holder.tvCategory.setText("N/A");
        }

        if (volume.getVolume_info().getPageCount() !=null)
        {
            holder.tvPageCount.setText(volume.getVolume_info().getPageCount());
        }
        else
        {
            holder.tvPageCount.setText("N/A");
        }
    }

    @Override
    public int getItemCount()
    {
        return oBooksList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ivThumbnail ;
        TextView tvTitle;
        TextView tvCategory;
        TextView tvPageCount;
        TextView tvAuthor;
        LinearLayout container ;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.thumbnail);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvCategory = itemView.findViewById(R.id.category);
            tvPageCount = itemView.findViewById(R.id.page_count);
            container = itemView.findViewById(R.id.container);

        }
    }

}
