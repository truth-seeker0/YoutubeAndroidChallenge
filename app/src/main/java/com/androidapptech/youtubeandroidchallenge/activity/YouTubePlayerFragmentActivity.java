package com.androidapptech.youtubeandroidchallenge.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.androidapptech.youtubeandroidchallenge.Config;
import com.androidapptech.youtubeandroidchallenge.util.LogUtil;
import com.androidapptech.youtubeandroidchallenge.R;
import com.androidapptech.youtubeandroidchallenge.util.PreferencesHelper;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

/**
 * Created by Benjamin on 4/5/2017.
 */

public class YouTubePlayerFragmentActivity extends YouTubeBaseActivity {


    private String video_id;
    private MyPlayerStateChangeListener playerStateChangeListener;
    private MyPlaybackEventListener playbackEventListener;
    private YouTubePlayer player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_fragment_play);

        playerStateChangeListener = new MyPlayerStateChangeListener();
        playbackEventListener = new MyPlaybackEventListener();

        Intent intent = getIntent();
        if (intent != null) {
            video_id = intent.getStringExtra(Config.VIDEO_ID);
        }

        //initializing and adding YouTubePlayerFragment
        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        YouTubePlayerFragment playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);
        if (playerFragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            playerFragment = YouTubePlayerFragment.newInstance();
            ft.add(android.R.id.content, playerFragment, tag);
            ft.commit();
        }

        playerFragment.initialize(Config.API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
                player=youTubePlayer;
                player.setPlayerStateChangeListener(playerStateChangeListener);
                player.setPlaybackEventListener(playbackEventListener);
                if (!wasRestored) {
                    player.cueVideo(video_id);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(YouTubePlayerFragmentActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {
        @Override
        public void onPlaying() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onPaused() {
            LogUtil.logMethodCalled();
            LogUtil.d("getCurrentTimeMillis: "+player.getCurrentTimeMillis()+" getDurationMillis: "+ player.getDurationMillis());
            //Need save getCurrentTimeMillis to of each record
            PreferencesHelper.setSharedPreferenceInt(getApplicationContext(),video_id, player.getCurrentTimeMillis());
        }

        @Override
        public void onStopped() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onBuffering(boolean b) {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onSeekTo(int i) {
            LogUtil.logMethodCalled();
        }
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {
        @Override
        public void onLoading() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onLoaded(String s) {
            LogUtil.logMethodCalled();
            if(PreferencesHelper.isSharedPreferenceContainKey(getApplicationContext(), video_id)){
                int time=PreferencesHelper.getSharedPreferenceInt(getApplicationContext(),video_id, 0);
                player.seekToMillis(time);

            }
            player.play();
        }

        @Override
        public void onAdStarted() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onVideoStarted() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onVideoEnded() {
            LogUtil.logMethodCalled();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            LogUtil.logMethodCalled();
        }
    }
}
