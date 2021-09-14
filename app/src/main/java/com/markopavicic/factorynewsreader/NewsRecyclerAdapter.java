package com.markopavicic.factorynewsreader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsViewHolder> {
    private final List <String> titles = new ArrayList<>();
    private final List <String> urls = new ArrayList<>();
    private final NewsClickListener clickListener;

    public NewsRecyclerAdapter(NewsClickListener listener) {
        this.clickListener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row_layout, parent, false);
        return new NewsViewHolder(cellView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.setContent(urls.get(position),titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }
    public void addData(List <String> titles,List <String> urls) {
        this.titles.clear();
        this.urls.clear();
        this.titles.addAll(titles);
        this.urls.addAll(urls);
        notifyDataSetChanged();
    }
}
