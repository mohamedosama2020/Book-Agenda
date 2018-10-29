package com.example.moham.bookagenda;

import java.util.ArrayList;

class VolumeInfo {

    private String title;
    private String publisher;
    private String publishedDate;
    private String description;
    private ImageLinks imageLinks;
    private ArrayList<String> authors ;

    public VolumeInfo(String title, String publisher, String publishedDate, String description, ImageLinks imageLinks, ArrayList<String> authors) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.description = description;
        this.imageLinks = imageLinks;
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }
}
