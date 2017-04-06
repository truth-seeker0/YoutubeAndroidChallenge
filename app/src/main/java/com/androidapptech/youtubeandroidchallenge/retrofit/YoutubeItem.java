package com.androidapptech.youtubeandroidchallenge.retrofit;

/**
 * Created by Benjamin on 4/5/2017.
 */

public class YoutubeItem {
    private String title;
    private String link;
    private String thumb;

    public YoutubeItem(String title, String link, String thumb) {
        this.title = title;
        this.link = link;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
