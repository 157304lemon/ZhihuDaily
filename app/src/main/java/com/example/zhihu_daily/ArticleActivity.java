package com.example.zhihu_daily;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zhihu_daily.Util.HtmlUtil;
import com.example.zhihu_daily.datas.DataGsonForArticle;
import com.example.zhihu_daily.datastorage.MySharedPreferences;
import com.example.zhihu_daily.weight.ShareDialog;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {

    private String id,path,htmls,imageUrlForArticle,image_source,article_title,context;
    private String numComments,numPopularity;
    private WebView mWebView;
    private ImageView mImShare,mImArticle;
    private  Context mContext;
    private TextView mTvPopularity,mTvComment;
    private List<String> cssList;
    private List<String> jsList;
    private MySharedPreferences SPA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_article);
        mImShare = findViewById(R.id.im_share);
            mImShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //弹出分享弹窗
                    ShareDialog dialog = new ShareDialog(ArticleActivity.this);
                    dialog.show();
                }
            });
        mWebView = findViewById(R.id.wb);
        mImArticle = findViewById(R.id.im_article);
        mTvComment = findViewById(R.id.tv_comment);
           mTvComment.setOnClickListener(this);
        mTvPopularity = findViewById(R.id.tv_praise);
        mContext = getBaseContext();

        Toolbar mToolbar = findViewById(R.id.tb_delete);
        setSupportActionBar(mToolbar);
        mWebView.setWebViewClient(new MyWebViewClient());

        //通过该方法进行每个新闻的独有的id获取
        getId();
//        if(GetNetConnect.isMobileConnected(mContext)){
//            //进行网络请求获取文章的具体信息，并进行设置以及使用webview加载html文件
            initData();
//        }else {
//            ToastUtil.showMsg(mContext,"请检查您的网络连接");
//            //没有网络时加载本地资源
//            withoutInternet();
//        }
    }


//***********************************************************************************************************************
//以下为自定义方法


    // 进行网络请求，将请求下来的数据存为文本
    private void initData() {
        GetNetConnect getNetConnect = new GetNetConnect("https://news-at.zhihu.com/api/4/news/"+id);
        getNetConnect.HttpConnect(new GetNetConnect.Callback() {
            @Override
            public void finsh(String response) {
                context = response;
                //通过该方法得到新闻的赞、评论数等消息
//                SPA = new MySharedPreferences("news",id,response,mContext);
//                SPA.saveData();
               getExtraMsg();
            }
        });
    }


    //通过该方法得到新闻的赞、评论数等消息
    private void getExtraMsg(){
        GetNetConnect getNetConnect = new GetNetConnect("https://news-at.zhihu.com/api/4/story-extra/"+id);
        getNetConnect.HttpConnect(new GetNetConnect.Callback() {
            @Override
            public void finsh(String response) {
                try {
//                    SPA = new MySharedPreferences("comment",id,response,mContext);
//                        SPA.saveData();

                    //对文章的额外消息的解析
                    JSONObject jsonObject = new JSONObject(response);
                    numComments =  jsonObject.getString("comments");
                    numPopularity = jsonObject.getString("popularity");

                    //通过该方法进行各种数据设置
                    setDatas(context);

                    //返回主线程刷新UI
                    Handler mainHander = new Handler(Looper.getMainLooper());
                    mainHander.post(new Runnable() {
                        @Override
                        public void run() {
                            //加载文章大图
                            Glide.with(mContext).load(imageUrlForArticle).into(mImArticle);
                            useWebview(path);
                            mTvComment.setText(numComments);
                            mTvPopularity.setText(numPopularity);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setDatas(String response){
        Gson gson= new Gson();
        DataGsonForArticle dataGson=gson.fromJson(response,DataGsonForArticle.class);
        imageUrlForArticle = dataGson.getImage();
        htmls = dataGson.getBody();
        image_source = dataGson.getImage_source();
        article_title = dataGson.getTitle();
        cssList = dataGson.getCss();
        jsList = dataGson.getJs();

    }

    // 文章大图的加载和webview的加载
    private void useWebview(String path){
        String htmlData = HtmlUtil.createHtmlData(htmls,cssList,jsList);
        //通过该方法，WebView加载html文件
        mWebView.loadData(htmlData,HtmlUtil.MIME_TYPE,HtmlUtil.ENCODING);
    }

    private void withoutInternet(){
        SPA = new MySharedPreferences("news",id,null,mContext);
        context = SPA.getData();
        SPA = new MySharedPreferences("comment",id,null,mContext);
        String response = SPA.getData();
        try {
            //解析得到对应的新闻的评论、点赞等消息
            JSONObject  jsonObject = new JSONObject(response);
            numComments =  jsonObject.getString("comments");
            numPopularity = jsonObject.getString("popularity");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //通过该方法进行各种数据设置
        setDatas(context);

        //加载文章大图
        Glide.with(mContext).load(imageUrlForArticle).into(mImArticle);
        useWebview(path);
        mTvComment.setText(numComments);
        mTvPopularity.setText(numPopularity);
    }

    private void getId(){
        Intent intent = getIntent();
        id = intent.getStringExtra("Id");
    }

    @Override//点击事件设置
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_comment:{
                Intent intent = new Intent(ArticleActivity.this,CommentsActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        }
    }


    class MyWebViewClient extends WebViewClient{
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }
    }
}
