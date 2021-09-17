package com.markopavicic.factorynewsreader;

import java.util.ArrayList;
import java.util.List;

public class NewsObject {

    public String status;
    public String source;
    public String sortBy;
    public List<Article> articles;

    public ArrayList<String> getTitles() {
        ArrayList<String> titles = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            titles.add(articles.get(i).getTitle());
        return titles;
    }

    public ArrayList<String> getUrlsToImages() {
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            urls.add(articles.get(i).getUrlToImage());
        return urls;
    }

    public ArrayList<String> getContent() {
        ArrayList<String> urls = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++)
            urls.add(articles.get(i).getDescription());
        //Iz API-ja ne dobivam content pa sam stavio description
        return urls;
    }
}
