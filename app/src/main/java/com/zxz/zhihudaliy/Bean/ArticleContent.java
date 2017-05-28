package com.zxz.zhihudaliy.Bean;

import java.util.List;

//章详细内容，在链接后缀加上文章ID即可获取文章内容
//链接：http://news-at.zhihu.com/api/4/news/1747159
public class ArticleContent {
//    body : HTML 格式的新闻
    private String body;
//    image-source : 图片的内容提供方。为了避免被起诉非法使用图片，在显示图片时最好附上其版权信息。
    private String image_source;
//    title : 新闻标题
    private String title;
//    image : 获得的图片同 最新消息 获得的图片分辨率不同。这里获得的是在文章浏览界面中使用的大图。
    private String image;
//    share_url : 供在线查看内容与分享至 SNS 用的 URL
    private String share_url;
//    js : 供手机端的 WebView(UIWebView) 使用
    private List<?> js;
//    ga_prefix : 供 Google Analytics 使用
    private String ga_prefix;
//    thumbnail : 栏目的缩略图
    private List<String> images;
//    type : 新闻的类型
    private int type;
//    id : 新闻的 id
    private int id;
//    css : 供手机端的 WebView(UIWebView) 使用
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
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

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }
}
