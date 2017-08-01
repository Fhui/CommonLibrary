package com.example.huifeng.library.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ShineF on 2017/8/1 0001.
 */

public class AllContentBean implements Parcelable {


    /**
     * error : false
     * results : [{"_id":"5978ad1d421aa90ca3bb6b7a","createdAt":"2017-07-26T22:54:21.793Z","desc":"电影最TOP 65: 热血！盘点最经典的二战电影","publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av12595919/","used":true,"who":"LHF"},{"_id":"59793c3a421aa90ca209c4f2","createdAt":"2017-07-27T09:04:58.783Z","desc":"Android 安全逆向:篡改你的位置信息","publishedAt":"2017-07-27T14:16:33.773Z","source":"web","type":"Android","url":"http://url.cn/4CUJgaw","used":true,"who":"陈宇明"},{"_id":"59793d0e421aa90ca3bb6b7c","createdAt":"2017-07-27T09:08:30.637Z","desc":"RxJava2.X 源码分析（四）：观察者线程切换原理","publishedAt":"2017-07-27T14:16:33.773Z","source":"web","type":"Android","url":"http://url.cn/4CUJs26","used":true,"who":"陈宇明"},{"_id":"59793ea1421aa90ca209c4f3","createdAt":"2017-07-27T09:15:13.183Z","desc":"轻量 Youtube 客户端","images":["http://img.gank.io/04e6c264-b0e4-4c1d-a5cd-88d770a13605"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"App","url":"https://github.com/TeamNewPipe/NewPipe","used":true,"who":"jp1017"},{"_id":"597943f1421aa97de5c7c9ba","createdAt":"2017-07-27T09:37:53.351Z","desc":"运行在 vscode 上的印象笔记客户端，支持 markdown","publishedAt":"2017-07-27T14:16:33.773Z","source":"web","type":"前端","url":"http://monkey.yoryor.me","used":true,"who":"Yao Yao"},{"_id":"59795568421aa90ca3bb6b7e","createdAt":"2017-07-27T10:52:24.636Z","desc":"获取百度网盘（高速）下载链接的chrome插件","images":["http://img.gank.io/d4f08c18-fcd3-400d-8def-e17383ee7874"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"瞎推荐","url":"https://github.com/Kyle-Kyle/baidudl","used":true,"who":"galois"},{"_id":"59796bc5421aa90c9203d411","createdAt":"2017-07-27T12:27:49.649Z","desc":"iOS上的一个简单，实用的无限循环轮播图组件","images":["http://img.gank.io/2a48d6b2-83c5-4993-ab5e-e56b19658070"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"web","type":"iOS","url":"https://github.com/12207480/TYCyclePagerView","used":true,"who":"yeBlueColor"},{"_id":"5979762e421aa90c9203d412","createdAt":"2017-07-27T13:12:14.840Z","desc":"在 Aws Lamda 上运行 Chromeless。","images":["http://img.gank.io/d824a6ed-04c6-476f-a1c4-5d8cdd98536a"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"瞎推荐","url":"https://github.com/graphcool/chromeless","used":true,"who":"代码家"},{"_id":"59797f06421aa90ca3bb6b81","createdAt":"2017-07-27T13:49:58.542Z","desc":"基于终端实现的音乐播放器，酷酷的。","images":["http://img.gank.io/99eb9577-6a41-43e4-b469-6a2418991132"],"publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"拓展资源","url":"https://github.com/clangen/musikcube","used":true,"who":"代码家"},{"_id":"597982e0421aa90ca3bb6b82","createdAt":"2017-07-27T14:06:24.790Z","desc":"类似 Instagram Story 的插件，展示照片集合。","publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"Android","url":"https://github.com/shts/StoriesProgressView","used":true,"who":"代码家"},{"_id":"59798364421aa90ca3bb6b83","createdAt":"2017-07-27T14:08:36.930Z","desc":"PLDroidShortVideo 是七牛推出的一款适用于 Android 平台的短视频 SDK，提供了包括美颜、滤镜、水印、断点录制、分段回删、视频编辑、混音特效、本地/云端存储在内的多种功能，支持高度定制以及二次开发。","publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"Android","url":"https://github.com/pili-engineering/PLDroidShortVideo","used":true,"who":"代码家"},{"_id":"5979848e421aa90ca209c4f7","createdAt":"2017-07-27T14:13:34.914Z","desc":"7-27","publishedAt":"2017-07-27T14:16:33.773Z","source":"chrome","type":"福利","url":"http://ww1.sinaimg.cn/large/610dc034ly1fhyeyv5qwkj20u00u0q56.jpg","used":true,"who":"代码家"},{"_id":"59760e4f421aa90ca209c4d3","createdAt":"2017-07-24T23:12:15.465Z","desc":"亚裔退伍军人参加《美国忍者勇士》，这身体素质全程跪着看完！","publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"休息视频","url":"http://www.bilibili.com/video/av12532105/","used":true,"who":"LHF"},{"_id":"5977c8d3421aa90ca209c4e1","createdAt":"2017-07-26T06:40:19.426Z","desc":"Javascript 实现的图片压缩工具。","publishedAt":"2017-07-26T16:57:39.343Z","source":"api","type":"前端","url":"https://github.com/xkeshi/image-compressor","used":true,"who":"代码家"},{"_id":"5977f131421aa90ca209c4e3","createdAt":"2017-07-26T09:32:33.352Z","desc":"仿百度外卖的酷炫水波纹效果","publishedAt":"2017-07-26T16:57:39.343Z","source":"web","type":"Android","url":"http://url.cn/4CRffxv","used":true,"who":"陈宇明"},{"_id":"5977f142421aa97de5c7c9b0","createdAt":"2017-07-26T09:32:50.969Z","desc":"RxJava2.X 源码分析（三）：订阅线程切换","publishedAt":"2017-07-26T16:57:39.343Z","source":"web","type":"Android","url":"http://url.cn/4CRfiE6","used":true,"who":"陈宇明"},{"_id":"5978543e421aa90ca209c4e6","createdAt":"2017-07-26T16:35:10.594Z","desc":"利用 VRKit 绘制银河系","images":["http://img.gank.io/1050dd14-c326-44ed-8f10-b8ff06eb1c65"],"publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"iOS","url":"https://github.com/miliPolo/ARSolarPlay","used":true,"who":"代码家"},{"_id":"59785476421aa90c9203d409","createdAt":"2017-07-26T16:36:06.383Z","desc":"Shadowsocks 流量嗅探，恩，嗅探。","publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"拓展资源","url":"https://github.com/madeye/sssniff","used":true,"who":"代码家"},{"_id":"5978568d421aa90c9203d40a","createdAt":"2017-07-26T16:45:01.572Z","desc":"少见的 Android 游戏开源项目，沙漠风暴。","publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"Android","url":"https://github.com/HurTeng/StormPlane","used":true,"who":"代码家"},{"_id":"597856b1421aa97de5c7c9b4","createdAt":"2017-07-26T16:45:37.467Z","desc":"轻松学习正则表达式。","images":["http://img.gank.io/ff13f377-91c5-48e7-9449-370ab2d664ae"],"publishedAt":"2017-07-26T16:57:39.343Z","source":"chrome","type":"拓展资源","url":"https://github.com/zeeshanu/learn-regex","used":true,"who":"代码家"}]
     */

    private boolean error;
    private ArrayList<ResultsBean> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<ResultsBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean implements Parcelable {
        /**
         * _id : 5978ad1d421aa90ca3bb6b7a
         * createdAt : 2017-07-26T22:54:21.793Z
         * desc : 电影最TOP 65: 热血！盘点最经典的二战电影
         * publishedAt : 2017-07-27T14:16:33.773Z
         * source : chrome
         * type : 休息视频
         * url : http://www.bilibili.com/video/av12595919/
         * used : true
         * who : LHF
         * images : ["http://img.gank.io/04e6c264-b0e4-4c1d-a5cd-88d770a13605"]
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;
        private List<String> images;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this._id);
            dest.writeString(this.createdAt);
            dest.writeString(this.desc);
            dest.writeString(this.publishedAt);
            dest.writeString(this.source);
            dest.writeString(this.type);
            dest.writeString(this.url);
            dest.writeByte(this.used ? (byte) 1 : (byte) 0);
            dest.writeString(this.who);
            dest.writeStringList(this.images);
        }

        public ResultsBean() {
        }

        protected ResultsBean(Parcel in) {
            this._id = in.readString();
            this.createdAt = in.readString();
            this.desc = in.readString();
            this.publishedAt = in.readString();
            this.source = in.readString();
            this.type = in.readString();
            this.url = in.readString();
            this.used = in.readByte() != 0;
            this.who = in.readString();
            this.images = in.createStringArrayList();
        }

        public static final Creator<ResultsBean> CREATOR = new Creator<ResultsBean>() {
            @Override
            public ResultsBean createFromParcel(Parcel source) {
                return new ResultsBean(source);
            }

            @Override
            public ResultsBean[] newArray(int size) {
                return new ResultsBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(this.error ? (byte) 1 : (byte) 0);
        dest.writeList(this.results);
    }

    public AllContentBean() {
    }

    protected AllContentBean(Parcel in) {
        this.error = in.readByte() != 0;
        this.results = new ArrayList<ResultsBean>();
        in.readList(this.results, ResultsBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<AllContentBean> CREATOR = new Parcelable.Creator<AllContentBean>() {
        @Override
        public AllContentBean createFromParcel(Parcel source) {
            return new AllContentBean(source);
        }

        @Override
        public AllContentBean[] newArray(int size) {
            return new AllContentBean[size];
        }
    };
}
