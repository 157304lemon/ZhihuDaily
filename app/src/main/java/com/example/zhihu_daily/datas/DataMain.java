package com.example.zhihu_daily.datas;

public class DataMain {
    private String title;
    private String imageUrl;

    public DataMain(String imageUrl, String title){
        this.imageUrl = imageUrl;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
