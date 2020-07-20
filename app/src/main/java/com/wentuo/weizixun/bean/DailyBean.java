package com.wentuo.weizixun.bean;

import java.util.List;

public class DailyBean {
    /**
     * date : 20200612
     * stories : [{"image_hue":"","title":"美学是什么？有什么用？","url":"https://daily.zhihu.com/story/9724809","hint":"黄Isabel · 7 分钟阅读","ga_prefix":"061207","images":["https://pic3.zhimg.com/v2-665d183ec5e42a9827aa61dd43b84526.jpg"],"type":0,"id":9724809},{"image_hue":"","title":"什么是「人造肉」？它是如何生产的？","url":"https://daily.zhihu.com/story/9724813","hint":"营养师顾中一 · 3 分钟阅读","ga_prefix":"061207","images":["https://pic3.zhimg.com/v2-4c60ef655a1f6c964400806b55737d2a.jpg"],"type":0,"id":9724813},{"image_hue":"","title":"如何看待《乱世佳人》因涉嫌「种族争议」被下架？","url":"https://daily.zhihu.com/story/9724825","hint":"庄泽曦 · 1 分钟阅读","ga_prefix":"061207","images":["https://pic3.zhimg.com/v2-773268835d9049ff673daf7eb73a4bde.jpg"],"type":0,"id":9724825},{"image_hue":"","title":"如何看待北京地铁机场线使用 EUHT 通信系统服务？ ","url":"https://daily.zhihu.com/story/9724817","hint":"司马望田 · 5 分钟阅读","ga_prefix":"061207","images":["https://pic3.zhimg.com/v2-c4201bba3ddc148a7f7ded9fe09ac1e6.jpg"],"type":0,"id":9724817},{"image_hue":"","title":"大误 · 如果世上所有鸡融合为一只巨鸡，能打败它吗","url":"https://daily.zhihu.com/story/9724806","hint":"龙牙 · 3 分钟阅读","ga_prefix":"061207","images":["https://pic4.zhimg.com/v2-820ce2cabba6b5c2d7aef3a32bc3de1f.jpg"],"type":0,"id":9724806},{"image_hue":"","title":"瞎扯 · 如何正确地吐槽","url":"https://daily.zhihu.com/story/9724770","hint":"VOL.2418","ga_prefix":"061206","images":["https://pic4.zhimg.com/v2-d79c3b07a35e83ab003ff5c98b53d36f.jpg"],"type":0,"id":9724770}]
     * top_stories : [{"image_hue":"","hint":"作者 / 苗华栋","url":"https://daily.zhihu.com/story/9724781","image":"https://pic3.zhimg.com/v2-f04d87e0735140857e2da22f99fcb4de.jpg","title":"1000 桶水中 1 桶有毒，猪喝毒水会死，想找到这桶毒水需要几头猪？","ga_prefix":"061107","type":0,"id":9724781},{"image_hue":"","hint":"作者 / 二氧化硅","url":"https://daily.zhihu.com/story/9724725","image":"https://pic1.zhimg.com/v2-b850e8e6e33ad7f6ef7879fe92dff554.jpg","title":"纯棉的衣服既不天然，也不环保","ga_prefix":"061007","type":0,"id":9724725},{"image_hue":"","hint":"作者 / 刀杨同学","url":"https://daily.zhihu.com/story/9724689","image":"https://pic2.zhimg.com/v2-a49e89b86afe35db2ff182cd7a6cc709.jpg","title":"大口呼吸可以减肥吗？","ga_prefix":"060907","type":0,"id":9724689},{"image_hue":"","hint":"作者 / 混乱博物馆","url":"https://daily.zhihu.com/story/9724667","image":"https://pic4.zhimg.com/v2-7fcb8297595b7b79db388bdb5d45dc77.jpg","title":"咖啡因是提升人的精力还是预支人的精力？","ga_prefix":"060807","type":0,"id":9724667},{"image_hue":"","hint":"作者 / 司马懿","url":"https://daily.zhihu.com/story/9724591","image":"https://pic3.zhimg.com/v2-b23d9db32efcd9813c64b81ad55c88fe.jpg","title":"地摊经济要崛起了吗?","ga_prefix":"060607","type":0,"id":9724591}]
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
         * image_hue :
         * title : 美学是什么？有什么用？
         * url : https://daily.zhihu.com/story/9724809
         * hint : 黄Isabel · 7 分钟阅读
         * ga_prefix : 061207
         * images : ["https://pic3.zhimg.com/v2-665d183ec5e42a9827aa61dd43b84526.jpg"]
         * type : 0
         * id : 9724809
         */

        private String image_hue;
        private String title;
        private String url;
        private String hint;
        private String ga_prefix;
        private int type;
        private int id;
        private List<String> images;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
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
         * image_hue :
         * hint : 作者 / 苗华栋
         * url : https://daily.zhihu.com/story/9724781
         * image : https://pic3.zhimg.com/v2-f04d87e0735140857e2da22f99fcb4de.jpg
         * title : 1000 桶水中 1 桶有毒，猪喝毒水会死，想找到这桶毒水需要几头猪？
         * ga_prefix : 061107
         * type : 0
         * id : 9724781
         */

        private String image_hue;
        private String hint;
        private String url;
        private String image;
        private String title;
        private String ga_prefix;
        private int type;
        private int id;

        public String getImage_hue() {
            return image_hue;
        }

        public void setImage_hue(String image_hue) {
            this.image_hue = image_hue;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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
    }
}
