package com.markopavicic.factorynewsreader;

public class Article {

    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public Object publishedAt;

    public Article(String author, String title, String description, String url, String urlToImage, Object publishedAt) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getDescription() {
        return description;
    }
}
