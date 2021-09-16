package com.markopavicic.factorynewsreader;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class NewsActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPager2Adapter pagerAdapter;
    private Integer position;
    private List<String> titles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        viewPager = findViewById(R.id.vpNews);
        pagerAdapter = new ViewPager2Adapter(this);
        Intent i = getIntent();
        position = i.getIntExtra("position", 0);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(position);
        titles = i.getStringArrayListExtra("titles");
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                getSupportActionBar().setTitle(StringUtils.abbreviate(titles.get(position), 30));
            }
        });
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.backbutton);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}