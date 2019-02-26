package com.example.zhihu_daily;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhihu_daily.MyAdapters.RecycerAdapter_main;
import com.example.zhihu_daily.Util.ToastUtil;
import com.example.zhihu_daily.datas.DataArticle;
import com.example.zhihu_daily.datas.DataGson;
import com.example.zhihu_daily.datas.DataMain;
import com.example.zhihu_daily.datastorage.MySharedPreferences;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MyfragmentA extends Fragment {

    RecyclerView mRecyclerView;
    private RecycerAdapter_main mRecyclerViewAdapter;
    private List<DataMain> datas = new ArrayList<>();
    private Context mContext;
    private List<DataGson.StoriesBean> storys;
    private List<DataGson.TopStoriesBean> top_stories;
    private List<DataArticle> data_articles = new ArrayList<>();
    private int i = 0;
    private MySharedPreferences spa;
    private String data;

    //接受来自activity的信息，title是链接的前半部分，data是日期
    //历史消息与最新消息的json格式不同，i用于区别这两种消息
    public static MyfragmentA newInstance(String title,String data, int i) {
        MyfragmentA aFragmen = new MyfragmentA();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putInt("int", i);
        bundle.putString("data",data);
        aFragmen.setArguments(bundle);
        return aFragmen;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_fragment_a, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        }
        String url = "https://news-at.zhihu.com/api/4/news/latest";
        //如果有消息从mainactivity传过来，则说明需要更改消息列表，则需要更改url
        if (getArguments() != null) {
            data = getArguments().getString("data");
            url = getArguments().getString("title")+data;
            i = getArguments().getInt("int");

        }
        if (null != getActivity()) {
            mContext = getActivity();
        }
        //判断是否拥有网络连接，进而选择网络加载和本地加载
        if(GetNetConnect.isMobileConnected(mContext)){
            initData(url);
        }else {
            ToastUtil.showMsg(mContext,"请检查您的网络连接");
            spa = new MySharedPreferences("newsData",data,null,mContext);
            if(spa.getData()!=null||spa.getData().equals("")){
                handleResponse(spa.getData());
            }
        }

        //主页底部视图实现
        mRecyclerView =  view.findViewById(R.id.rv_main);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerViewAdapter = new RecycerAdapter_main(datas,mContext);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerViewAdapter.setmOnItemClickListener(new RecycerAdapter_main.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //对fragment中的item点击后跳转到详情页面，id是新闻的唯一标识，需要传递过去
                Intent intent = new Intent(mContext,ArticleActivity.class);
                Integer integer = data_articles.get(position).getId();
                intent.putExtra("Id",integer.toString());

                startActivity(intent);
            }
        });
    }


    private void initData(String url){
        GetNetConnect getNetConnect = new GetNetConnect(url);
        getNetConnect.HttpConnect(new GetNetConnect.Callback() {
            @Override
            public void finsh(String response) {

                spa = new MySharedPreferences("newsData",data,response,mContext);
                spa.saveData();
                //处理得到的json数据
                handleResponse(response);
                //通知RecyclerView进行改变
                Handler mainHander = new Handler(Looper.getMainLooper());
                mainHander.post(new Runnable() {
                    @Override
                    public void run() {
                        mRecyclerViewAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

    private void handleResponse(String response){
        Gson gson = new Gson();
        DataGson dataGson=gson.fromJson(response,DataGson.class);
        storys=dataGson.getStories();
        top_stories = dataGson.getTop_stories();
        for (DataGson.StoriesBean storiesBean:storys) {
            DataMain data=new DataMain(storiesBean.getImages().get(0),storiesBean.getTitle());
            datas.add(data);
            DataArticle data_article=new DataArticle(storiesBean.getId());
            data_articles.add(data_article);
            }
            //因为最新新闻的格式和过往消息的格式有所不同，所需要区分开，最新消息要执行if语句中的代码
        if(i==0){
            for(DataGson.TopStoriesBean topStoriesBean:top_stories){
                DataArticle data_article=new DataArticle(topStoriesBean.getId());
                data_articles.add(data_article);
                }
        }

    }
}
