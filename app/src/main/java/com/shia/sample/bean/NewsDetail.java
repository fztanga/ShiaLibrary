package com.shia.sample.bean;

/**
 * Created by administrator on 2017/4/6.
 */
public class NewsDetail extends WHKPBResponse{
    private String id;
    private String title;
    private String author;
    private String description;
    private String date;
    private String txt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        if (date != null && date.length() > 20) {
            return date.substring(0, 19);
        }
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
