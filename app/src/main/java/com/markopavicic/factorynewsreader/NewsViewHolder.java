package com.markopavicic.factorynewsreader;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final NewsClickListener clickListener;
    private final ImageView ivNews;
    private final TextView tvNewsHeadline;

    public NewsViewHolder(@NonNull View itemView, NewsClickListener clickListener) {
        super(itemView);
        this.clickListener = clickListener;
        ivNews = itemView.findViewById(R.id.ivNews);
        tvNewsHeadline = itemView.findViewById(R.id.tvNewsHeadlineRecycler);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        clickListener.onNewsClick(getAdapterPosition());
    }
    public void setContent(String imageUri, String headline)
    {
        Picasso.with(itemView.getContext()).load(imageUri).into(ivNews);
        tvNewsHeadline.setText(headline);
    }
}
