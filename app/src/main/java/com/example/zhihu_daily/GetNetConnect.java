package com.example.zhihu_daily;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

class GetNetConnect {
    private String url;

    GetNetConnect(String url) {
        this.url = url;
    }
    interface Callback{
        void finsh(String response);
    }

    void HttpConnect(final Callback callback){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection=null;
                BufferedReader reader;
                InputStream inputStream = null;
                try {
                    URL url1 = new URL(url);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("GET");
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

    public static boolean isMobileConnected(Context context){
        if (context!=null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (info!=null){
                return info.isConnected();
            }
        }
        return false;
    }

}
