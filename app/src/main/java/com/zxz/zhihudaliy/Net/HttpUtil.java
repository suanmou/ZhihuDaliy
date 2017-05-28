package com.zxz.zhihudaliy.Net;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.alibaba.fastjson.JSON;
import com.zxz.zhihudaliy.Bean.ArticleBefore;
import com.zxz.zhihudaliy.Bean.ArticleContent;
import com.zxz.zhihudaliy.Bean.ArticleLatest;
import com.zxz.zhihudaliy.Bean.ArticleTheme;
import com.zxz.zhihudaliy.Bean.Others;
import com.zxz.zhihudaliy.Bean.Theme;
import com.zxz.zhihudaliy.Listener.OnLoadArticleContentListener;
import com.zxz.zhihudaliy.Listener.OnLoadBeforeArticleListener;
import com.zxz.zhihudaliy.Listener.OnLoadLatestArticleListener;
import com.zxz.zhihudaliy.Listener.OnLoadThemeContentListener;
import com.zxz.zhihudaliy.Listener.OnLoadThemesListener;
import com.zxz.zhihudaliy.Utility.Constant;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.util.TextUtils;

/**
 * Created by ZY on 2016/7/26.
 * 所有的网络操作
 */
public class HttpUtil {

    public static AsyncHttpClient client = new AsyncHttpClient();

    static {
        client.setConnectTimeout(1000 * 30);
        client.setTimeout(1000 * 30);
    }

    //判断当前网络状态
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    //获取最新文章
    public static void getLatestArticleList(final OnLoadLatestArticleListener listener) {


        client.get(Constant.LatestArticleUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ArticleLatest articleLatest = JSON.parseObject(responseString, ArticleLatest.class);
                if (articleLatest != null && articleLatest.getStories() != null && articleLatest.getStories().size() > 0
                        && articleLatest.getTop_stories() != null && articleLatest.getTop_stories().size() > 0) {
                    if (listener != null) {
                        listener.onSuccess(articleLatest);
                        return;
                    }
                }
                if (listener != null) {
                    listener.onFailure();
                }

            }
        });
    }

    //获取过去文章
    public static void getBeforeArticleList(final String date, final OnLoadBeforeArticleListener listener) {
        client.get(Constant.BeforeArticleUrl + date, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ArticleBefore articleBefore = JSON.parseObject(responseString, ArticleBefore.class);
                if (articleBefore != null && articleBefore.getStories() != null && articleBefore.getStories().size() > 0) {
                    if (listener != null) {
                        listener.onSuccess(articleBefore);
                        return;
                    }
                }
                if (listener != null) {
                    listener.onFailure();
                }

            }
        });
    }

    //获取文章内容
    public static void getArticleContent(int id, final OnLoadArticleContentListener listener) {
        client.get(Constant.ArticleContentUrl + id, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ArticleContent content = JSON.parseObject(responseString, ArticleContent.class);
                if (content != null && !TextUtils.isEmpty(content.getBody())) {
                    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/src.css\" type=\"text/css\">";
                    String html = "<html><head>" + css + "</head><body>" + content.getBody() + "</body></html>";
                    html = html.replace("<div class=\"img-place-holder\">", "");
                    content.setBody(html);
                    if (listener != null) {
                        listener.onSuccess(content);
                        return;
                    }
                }
                if (listener != null) {
                    listener.onFailure();
                }

            }
        });
    }

    //获取文章主题
    public static void getThemes(final OnLoadThemesListener listener) {
        client.get(Constant.ArticleThemesUrl, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Theme theme = JSON.parseObject(responseString, Theme.class);
                List<Others> othersList = theme.getOthers();
                if (theme != null && othersList != null && othersList.size() > 0) {
                    if (othersList != null && othersList.size() > 0) {
                        if (listener != null) {
                            listener.onSuccess(othersList);
                            return;
                        }
                    }
                }
                if (listener != null) {
                    listener.onFailure();
                }
            }
        });
    }


    //获取指定主题下的文章
    public static void getArticleListByTheme(int id, final OnLoadThemeContentListener listener) {
        client.get(Constant.ThemeContentUrl + id, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onFailure();
                }
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                ArticleTheme articleTheme = JSON.parseObject(responseString, ArticleTheme.class);
                if (articleTheme != null && articleTheme.getStories() != null && articleTheme.getStories().size() > 0) {
                    if (listener != null) {
                        listener.onSuccess(articleTheme);
                        return;
                    }
                }
                if (listener != null) {
                    listener.onFailure();
                }
            }
        });
    }
}
