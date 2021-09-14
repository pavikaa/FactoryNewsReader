package com.markopavicic.factorynewsreader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsClickListener {

    private RecyclerView rvNews;
    private NewsRecyclerAdapter rvAdapter;
    private NewsObject news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new NewsRecyclerAdapter(this);
        rvNews.setAdapter(rvAdapter);
        loadData();
    }
    private void loadData()
    {
        Call<NewsObject> call = RetrofitClient.getInstance().getApi().loadData();
        call.enqueue(new Callback<NewsObject>() {
            @Override
            public void onResponse(Call<NewsObject> call, Response<NewsObject> response) {
                news = response.body();
                rvAdapter.addData(news.getTitles(),news.getUrlsToImages());
            }
            @Override
            public void onFailure(Call<NewsObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
                Log.d("error",t.toString());
            }
        });
    }

    @Override
    public void onNewsClick(int position) {

    }
}