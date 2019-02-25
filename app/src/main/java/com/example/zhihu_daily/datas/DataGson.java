package com.example.zhihu_daily.datas;

import java.util.List;

public class DataGson {

    /**
     * date : 20190206
     * stories : [{"title":"回到现实，太阳真的会变得特别亮吗？我们能点燃木星吗？","ga_prefix":"020621","images":["https://pic2.zhimg.com/v2-c893a07a7314fbf70cbebe76c530d8e9.jpg"],"multipic":true,"type":0,"id":9707343},{"title":"年度热门 · 《创造101》：女孩身体消费的新高峰","ga_prefix":"020619","images":["https://pic4.zhimg.com/v2-f5bcc73bf49882c21c595575a77f2303.jpg"],"multipic":true,"type":0,"id":9706225},{"images":["https://pic3.zhimg.com/v2-0a234843e065ec5f4bf84b9e6cb91462.jpg"],"type":0,"id":9682843,"ga_prefix":"020618","title":"年度热门 · 有人说《复联 3》成了《灭霸传》，这其实是漫威的一贯手法套路"},{"images":["https://pic1.zhimg.com/v2-017d2dc50b3c80e9a17fac3f6cdc2d1c.jpg"],"type":0,"id":9706210,"ga_prefix":"020616","title":"年度热门 · 我就是那个「奥数天才」付云皓，谢谢大家，但我并没有坠落"},{"images":["https://pic3.zhimg.com/v2-6023ec69c5c6c286b0de25b30020681e.jpg"],"type":0,"id":9706207,"ga_prefix":"020610","title":"年度热门 · 中兴不能再向美国买任何零件和技术了，为什么说失去芯片才是重点？"},{"images":["https://pic4.zhimg.com/v2-5df4edab8f88d1c8e6949c94670c979b.jpg"],"type":0,"id":9706203,"ga_prefix":"020609","title":"年度热门 · 医生网上吐槽「鸿茅药酒」被跨省抓捕，是不是太过头了？"},{"images":["https://pic1.zhimg.com/v2-90663931e889a42612aabd890578aac0.jpg"],"type":0,"id":9706376,"ga_prefix":"020608","title":"年度热门 · 2018，创业黄金时代结束的一年"},{"images":["https://pic1.zhimg.com/v2-9828a47da5d140dc77ca5bdc58dcee18.jpg"],"type":0,"id":9706562,"ga_prefix":"020607","title":"年度热门 · 在奢侈品店打工的日子，看透了许多"},{"images":["https://pic4.zhimg.com/v2-d26e5ab5434f42980723a55ce457996f.jpg"],"type":0,"id":9706216,"ga_prefix":"020607","title":"年度热门 · 年轻人，996 都接受不了，你还能吃什么苦？"},{"images":["https://pic4.zhimg.com/v2-b94399da6cf16ea2ff8f2462f7f2eef7.jpg"],"type":0,"id":9707315,"ga_prefix":"020606","title":"年度瞎扯 · 全年最佳吐槽"}]
     * top_stories : [{"image":"https://pic3.zhimg.com/v2-a9d2357d39962baba54d6ccf52bd3cde.jpg","type":0,"id":9707343,"ga_prefix":"020621","title":"回到现实，太阳真的会变得特别亮吗？我们能点燃木星吗？"},{"image":"https://pic2.zhimg.com/v2-36c157970ec1156a6ceda926cde1ce65.jpg","type":0,"id":9706429,"ga_prefix":"020521","title":"作为航天工程师，我会向所有认识的朋友推荐《流浪地球》"},{"image":"https://pic4.zhimg.com/v2-f8a7e91d129ffccc89be42201528dc1b.jpg","type":0,"id":9707331,"ga_prefix":"020423","title":"葛优解构一切"},{"image":"https://pic1.zhimg.com/v2-ee825790bfd1872a55524535febfe82c.jpg","type":0,"id":9706187,"ga_prefix":"020510","title":"年度热门 · 为什么年轻人越来越反感「亲戚」这群人？"},{"image":"https://pic2.zhimg.com/v2-77bec9a3825b3a3de6a65d5b762364e9.jpg","type":0,"id":9706697,"ga_prefix":"020409","title":"年度热门 · 五十万年薪的人是怎么过日子的？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        /**
         * title : 回到现实，太阳真的会变得特别亮吗？我们能点燃木星吗？
         * ga_prefix : 020621
         * images : ["https://pic2.zhimg.com/v2-c893a07a7314fbf70cbebe76c530d8e9.jpg"]
         * multipic : true
         * type : 0
         * id : 9707343
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic3.zhimg.com/v2-a9d2357d39962baba54d6ccf52bd3cde.jpg
         * type : 0
         * id : 9707343
         * ga_prefix : 020621
         * title : 回到现实，太阳真的会变得特别亮吗？我们能点燃木星吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
