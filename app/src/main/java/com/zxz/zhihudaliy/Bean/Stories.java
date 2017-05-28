package com.zxz.zhihudaliy.Bean;

import java.util.List;

//普通新闻
public class Stories {
//    type : 新闻类型
    private int type;
//    id : url 与 share_url 中最后的数字（应为内容的 id）
    private int id;
//    ga_prefix : 供 Google Analytics 使用
    private String ga_prefix;
    //    title : 新闻标题
    private String title;
//    images : 图像地址（官方 API 使用数组形式。目前暂未有使用多张图片的情形出现，曾见无 images 属性的情况，请在使用中注意 ）
    private List<String> images;

    public int getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
