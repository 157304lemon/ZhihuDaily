package com.example.zhihu_daily.datas;

import android.annotation.SuppressLint;
import android.text.SpannableString;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataComments {

//    {
//        "author": "板砖横飞",
//            "content": "Mac为啥是C口不用lightning呢？\nlightning除了前几年正反插秒杀microUSB，并没有多牛逼。现在C口各方面（充电、传输）完全秒杀lightning。",
//            "avatar": "http://pic3.zhimg.com/v2-a38bb0c865974e512ae8b3d1e06a9072_im.jpg",
//            "time": 1550196108,
//            "reply_to": {
//        "content": "为啥换成接口c，我觉得lightning 更耐用的感觉呀，也好看。",
//                "status": 0,
//                "id": 32909008,
//                "author": "佛佛肉"
//    },
//        "id": 32909811,
//            "likes": 0
//    },
    private String authorName;
    private String comment;
    private String avatarUrl;
    private String time;
    private String likes;
    private String replyAuthorName,replyContent;

    public DataComments(String authorName, String comment, String avatarUrl, int time, int likes) {
        this.authorName = authorName;
        this.comment = comment;
        this.avatarUrl = avatarUrl;
        this.time = String.valueOf(time);
        this.likes = String.valueOf(likes);
    }

    //ReplyComment
    public DataComments(String replyAuthorName,String replyContent) {
        this.replyAuthorName = replyAuthorName;
        this.replyContent = replyContent;
    }

    public String getReplyAuthorName() {
        return replyAuthorName;
    }

    public String getReplyContent() {
        return replyContent;
    }


    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getTime() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdr = new SimpleDateFormat("MM月dd日  HH:mm");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
