package com.markopavicic.factorynewsreader;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NewsClickListener {

    private RecyclerView rvNews;
    private NewsRecyclerAdapter rvAdapter;
    private NewsObject news;
    private long previousTimeMillis;
    private ArrayList<String> titles, urls, content;
    private ProgressBar progressBar;
    Boolean checkInternetConnection = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        rvNews = findViewById(R.id.rvNews);
        rvNews.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new NewsRecyclerAdapter(this);
        rvNews.setAdapter(rvAdapter);

        previousTimeMillis = 0;
        checkInternetConnection = false;

        loadDataFromSharedPrefs();
        checkInternet();

        if (previousTimeMillis == 0 || System.currentTimeMillis() - previousTimeMillis > 300000) {
            if (checkInternetConnection)
                loadData();
            else
                Toast.makeText(getApplicationContext(), "Za prikaz novih vijesti potrebna je veza s Internetom.", Toast.LENGTH_LONG).show();
        }
    }

    private void checkInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        checkInternetConnection = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    private void loadData() {
        Call<NewsObject> call = RetrofitClient.getInstance().getApi().loadData();
        progressBar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<NewsObject>() {
            @Override
            public void onResponse(Call<NewsObject> call, Response<NewsObject> response) {

                news = response.body();

                if (news == null) {
                    showAlert();
                } else {
                    titles = new ArrayList<>();
                    urls = new ArrayList<>();
                    content = new ArrayList<>();
                    titles = news.getTitles();
                    urls = news.getUrlsToImages();
                    content = news.getContent();
                    saveDataToSharedPrefs();
                    rvAdapter.addData(titles, urls);
                }
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<NewsObject> call, Throwable t) {
                showAlert();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Greška");
        alertBuilder.setMessage("Ups, došlo je do pogreške.");
        alertBuilder.setPositiveButton("U redu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Dodati po potrebi
            }
        });
        alertBuilder.show();
    }

    @Override
    public void onNewsClick(int position) {
        Intent i = new Intent(getApplicationContext(), NewsActivity.class);
        i.putStringArrayListExtra("titles", titles);
        i.putStringArrayListExtra("imageUrls", urls);
        i.putStringArrayListExtra("content", content);
        i.putExtra("position", position);
        startActivity(i);
    }

    private void saveDataToSharedPrefs() {
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String titles = gson.toJson(news.getTitles());
        String urls = gson.toJson(news.getUrlsToImages());
        String content = gson.toJson(news.getContent());
        long currentTimeMillis = System.currentTimeMillis();
        sharedPreferences.edit().clear().commit();
        editor.putString("titles", titles);
        editor.putString("urls", urls);
        editor.putString("content", content);
        editor.putLong("previousTimeMillis", currentTimeMillis);
        editor.apply();
    }

    private void loadDataFromSharedPrefs() {
        titles = new ArrayList<>();
        urls = new ArrayList<>();
        content = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        if (sharedPreferences.contains("titles")) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<String>>() {
            }.getType();
            String json = sharedPreferences.getString("titles", null);
            titles = gson.fromJson(json, type);
            json = sharedPreferences.getString("urls", null);
            urls = gson.fromJson(json, type);
            json = sharedPreferences.getString("content", null);
            content = gson.fromJson(json, type);
            previousTimeMillis = sharedPreferences.getLong("previousTimeMillis", 0);
            rvAdapter.addData(titles, urls);
        }
    }
}