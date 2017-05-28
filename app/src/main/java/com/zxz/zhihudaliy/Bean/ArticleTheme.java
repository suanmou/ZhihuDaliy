package com.zxz.zhihudaliy.Bean;

import java.util.List;

 //特定文章主题下的详细内容，在链接后缀加上主题ID即可
 //链接：http://news-at.zhihu.com/api/4/theme/13

public class ArticleTheme {

    private String description;

    private String background;

    private int color;

    private String name;

    private String image;

    private String image_source;

    private List<Stories> stories;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

}
