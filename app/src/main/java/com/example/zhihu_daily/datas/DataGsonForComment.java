package com.example.zhihu_daily.datas;

import java.util.List;

public class DataGsonForComment {


    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 刘shock不是thunder
         * content : 洗地水平有待提高啊小编
         * avatar : http://pic1.zhimg.com/a6a3abd0ff39c97a564603515e7394a0_im.jpg
         * time : 1550210980
         * id : 32910774
         * likes : 0
         * reply_to : {"content":"为啥给作者钱？期刊给的话稿费期刊出过了吧，有的还要作者自己出钱，知网和期刊合作，怎么变成知网还要出钱给作者了呵呵。下载收费能理解，文章说了，这是个私企，怎么也要钱才能维持啊，下载想不收费很简单，去你所在地区的图书馆电阅室，一般都是能免费的","status":0,"id":32909266,"author":"icesunx"}
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private int id;
        private int likes;
        private ReplyToBean reply_to;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public static class ReplyToBean {
            /**
             * content : 为啥给作者钱？期刊给的话稿费期刊出过了吧，有的还要作者自己出钱，知网和期刊合作，怎么变成知网还要出钱给作者了呵呵。下载收费能理解，文章说了，这是个私企，怎么也要钱才能维持啊，下载想不收费很简单，去你所在地区的图书馆电阅室，一般都是能免费的
             * status : 0
             * id : 32909266
             * author : icesunx
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
