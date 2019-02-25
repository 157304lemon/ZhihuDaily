package com.example.zhihu_daily.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostNetConnect {
    private String url;

    public PostNetConnect(String url) {
        this.url = url;
    }
    public interface Callback{
        void finsh(String response);
    }

    public void HttpConnect(final PostNetConnect.Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader;
                InputStream inputStream = null;
                try {
                    URL url1 = new URL(url);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setReadTimeout(5000);
                    connection.setConnectTimeout(5000);
                    connection.setRequestProperty("Content-type", "application/json");

                    inputStream = connection.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String getOnline;
                    while(null!=(getOnline=reader.readLine())){
                        stringBuilder.append(getOnline);
                    }
                    if(null!=callback){
                        callback.finsh(stringBuilder.toString());
                    }
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(null!=inputStream){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
