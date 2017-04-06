package com.androidapptech.youtubeandroidchallenge.pojo_model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * http://www.jsonschema2pojo.org/
 * Created by Benjamin on 4/5/2017.
 */


public class Playlist {

    @SerializedName("ListTitle")
    @Expose
    private String listTitle;
    @SerializedName("ListItems")
    @Expose
    private List<ListItem> listItems = null;

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public List<ListItem> getListItems() {
        return listItems;
    }

    public void setListItems(List<ListItem> listItems) {
        this.listItems = listItems;
    }

}