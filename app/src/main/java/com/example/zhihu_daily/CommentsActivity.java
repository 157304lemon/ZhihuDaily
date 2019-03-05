package com.example.zhihu_daily;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.example.zhihu_daily.adapters.RVAdapterComments;
import com.example.zhihu_daily.datas.DataComments;
import com.example.zhihu_daily.datas.DataGsonForComment;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity {

    private List<DataComments> dataCommentsShort = new ArrayList<>();
    private List<DataComments> replyCommentsShort = new ArrayList<>();
    private List<DataComments> dataCommentsLong = new ArrayList<>();
    private List<DataComments> replyCommentsLong = new ArrayList<>();
    private RVAdapterComments adapterCommentsShort = null;
    private RVAdapterComments adapterCommentsLong = null;
    private RecyclerView recyclerViewShort;
    private RecyclerView recyclerViewLong;
    private Toolbar mToolbar;
    private String id = null;
    private TextView mTvCommentLongNum;
    private TextView mTvCommentShortNum;
    public static final int LONG = 1;
    public static final int SHORT = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        mToolbar = findViewById(R.id.tb_comments);
            setSupportActionBar(mToolbar);
        mTvCommentLongNum = findViewById(R.id.tv_comment_long_num);
        mTvCommentShortNum = findViewById(R.id.tv_comment_short_num);
        //该方法用于得到文章的id，后面通过文章的id获取相应的评论
        getId();
        initdata("https://news-at.zhihu.com/api/4/story/"+id+"/long-comments",dataCommentsLong,replyCommentsLong,LONG);
        initdata("https://news-at.zhihu.com/api/4/story/"+id+"/short-comments",dataCommentsShort,replyCommentsShort,SHORT);

        //长评RecyclerView设置
        recyclerViewLong = findViewById(R.id.rv_long_comments);
        adapterCommentsLong = new RVAdapterComments(dataCommentsLong,replyCommentsLong, this);
        LinearLayoutManager managerLong = new LinearLayoutManager(this);
        recyclerViewLong.setAdapter(adapterCommentsLong);
        recyclerViewLong.setLayoutManager(managerLong);
        //长评论条数设置
        mTvCommentLongNum.setText(String.valueOf(adapterCommentsLong.getItemCount()));


        //短评RecyclerView设置
        recyclerViewShort = findViewById(R.id.rv_short_comments);
        adapterCommentsShort = new RVAdapterComments(dataCommentsShort, replyCommentsShort,this);
        LinearLayoutManager managerShort = new LinearLayoutManager(this);
        recyclerViewShort.setAdapter(adapterCommentsShort);
        recyclerViewShort.setLayoutManager(managerShort);
        //短评论条数设置
        mTvCommentShortNum.setText(String.valueOf(adapterCommentsShort.getItemCount()));
    }


    private void initdata(String url, final List<DataComments> dataComments,final List<DataComments> replyComments, final int i){
        GetNetConnect getNetConnect = new GetNetConnect(url);
        getNetConnect.HttpConnect(new GetNetConnect.Callback() {
            @Override
            public void finsh(String response) {
                handleMessage(response,dataComments,replyComments,i);
            }
        });
    }

    private void handleMessage(String response, final List<DataComments> dataComments,final List<DataComments> replyComments, final int i){
        Gson gson = new Gson();
        DataGsonForComment dataGson = gson.fromJson(response,DataGsonForComment.class);

        List<DataGsonForComment.CommentsBean> comments = dataGson.getComments();
        for(DataGsonForComment.CommentsBean commentsBean:comments){
            DataComments data1= new DataComments(commentsBean.getAuthor(),commentsBean.getContent(),commentsBean.getAvatar()
                                                        ,commentsBean.getTime(),commentsBean.getLikes());

            if(commentsBean.getReply_to()!=null){
                 String content = commentsBean.getReply_to().getContent();
                 String name = commentsBean.getReply_to().getAuthor();
                 DataComments data2 = new DataComments(name,content);
                 replyComments.add(data2);
            }else {
                //为了方便后面操作，所以设置当请求的数据中没有评论时，添加评论为空
                 DataComments data2 = new DataComments("","");
                 replyComments.add(data2);
            }
            dataComments.add(data1);
        }



        Handler mainhander = new Handler(Looper.getMainLooper());
        mainhander.post(new Runnable() {
            @Override
            public void run() {
                if(LONG==i){
                    mTvCommentLongNum.setText(String.valueOf(dataComments.size()));
                }else {
                    mTvCommentShortNum.setText(String.valueOf(dataComments.size()));
                }
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(LONG==i){
                    adapterCommentsLong.notifyDataSetChanged();
                }else {
                    adapterCommentsShort.notifyDataSetChanged();
                }
            }
        });
    }

    private void getId(){
        Intent intent = getIntent();
        id = intent.getStringExtra("ID");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
