package com.markopavicic.factorynewsreader;

import java.util.ArrayList;
import java.util.List;

public class NewsObject{
    public String status;
    public String source;
    public String sortBy;
    public List<Article> articles;

    public List<String> getTitles() {
        List<String> titles = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            titles.add(articles.get(i).getTitle());
        return titles;
    }

    public List<String> getUrlsToImages() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            urls.add(articles.get(i).getUrlToImage());
        return urls;
    }

    public List<String> getContent() {
        List<String> urls = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            urls.add(articles.get(i).getDescription());
        //Iz API-ja ne dobivam content pa sam stavio description
        return urls;
    }
}
