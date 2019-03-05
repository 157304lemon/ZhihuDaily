package com.example.zhihu_daily.datastorage;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * 设计初衷只是为了适应特定的储存
 */

public class MySharedPreferences {

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
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(mName+local,MODE_PRIVATE);
        SharedPreferences.Editor mEditor = sharedPreferences.edit();
        mEditor.putString(mName,content);
        mEditor.apply();
    }

    public String getData(){
        SharedPreferences mSharedPreferences = mContext.getSharedPreferences(mName+local, MODE_PRIVATE);
        return mSharedPreferences.getString(mName,"");
    }

}
