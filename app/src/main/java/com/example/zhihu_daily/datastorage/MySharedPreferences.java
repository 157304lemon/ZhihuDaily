package com.example.zhihu_daily.datastorage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;


@SuppressLint("Registered")
public class MySharedPreferences extends AppCompatActivity {

    private String mName;
    private String content;
    private String local;
    private Context mContext;

    public MySharedPreferences(String local, String id, String content, Context mContext) {
        this.content = content;
        mName = id;
        this.local = local;
        this.mContext = mContext;
    }

    public  void saveData(){
        android.content.SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName+local,Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(mName,content);
        mEditor.apply();
    }

    public String getData(){
        android.content.SharedPreferences mSharedPreferences = mContext.getSharedPreferences(mName+local, MODE_PRIVATE);
        return mSharedPreferences.getString(mName,"");
    }

}
