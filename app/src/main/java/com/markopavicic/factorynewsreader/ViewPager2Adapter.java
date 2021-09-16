package com.markopavicic.factorynewsreader;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.ViewHolder> {
    private Context ctx;
    private List<String> titles, urls, content;

    ViewPager2Adapter(Context ctx) {
        this.ctx = ctx;
        Intent i = ((Activity) ctx).getIntent();
        titles = i.getStringArrayListExtra("titles");
        urls = i.getStringArrayListExtra("imageUrls");
        content = i.getStringArrayListExtra("content");
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.vp_single_news_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.with(ctx).load(urls.get(position)).into(holder.ivNews);
        holder.tvNewsHeadline.setText(titles.get(position));
        holder.tvNewsContent.setText(content.get(position) + "\nLorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Vestibulum lacinia arcu eget nulla. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur sodales ligula in libero. \nSed dignissim lacinia nunc. Curabitur tortor. Pellentesque nibh. Aenean quam. In scelerisque sem at dolor. Maecenas mattis. Sed convallis tristique sem. Proin ut ligula vel nunc egestas porttitor. Morbi lectus risus, iaculis vel, suscipit quis, luctus non, massa. Fusce ac turpis quis ligula lacinia aliquet. Mauris ipsum. Nulla metus metus, ullamcorper vel, tincidunt sed, euismod in, nibh. \nQuisque volutpat condimentum velit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nam nec ante. Sed lacinia, urna non tincidunt mattis, tortor neque adipiscing diam, a cursus ipsum ante quis turpis. Nulla facilisi. Ut fringilla. Suspendisse potenti. Nunc feugiat mi a tellus consequat imperdiet. Vestibulum sapien. Proin quam. Etiam ultrices. Suspendisse in justo eu magna luctus suscipit. Sed lectus. Integer euismod lacus luctus magna. Quisque cursus, metus vitae pharetra auctor, sem massa mattis sem, at interdum magna augue eget diam. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Morbi lacinia molestie dui. Praesent blandit dolor. Sed non quam. In vel mi sit amet augue congue elementum. Morbi in ipsum sit amet pede facilisis laoreet. Donec lacus nunc, viverra nec.");
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivNews;
        private TextView tvNewsHeadline;
        private TextView tvNewsContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivNews = itemView.findViewById(R.id.ivNewsBig);
            tvNewsHeadline = itemView.findViewById(R.id.tvNewsHeadline);
            tvNewsContent = itemView.findViewById(R.id.tvNewsContent);
        }
    }
}
