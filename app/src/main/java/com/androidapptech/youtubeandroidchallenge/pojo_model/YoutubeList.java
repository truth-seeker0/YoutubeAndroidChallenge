package com.androidapptech.youtubeandroidchallenge.pojo_model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * http://www.jsonschema2pojo.org/
 * Created by Benjamin on 4/5/2017.
 */

public class YoutubeList {

    @SerializedName("Playlists")
    @Expose
    private List<Playlist> playlists = null;

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

}
