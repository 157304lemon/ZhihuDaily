package com.example.zhihu_daily.datas;

public class DataArticle {
    private int id;
    private String story;

    public DataArticle(int id) {
        this.id = id;
    }

    public DataArticle(String story) {
        this.story = story;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
