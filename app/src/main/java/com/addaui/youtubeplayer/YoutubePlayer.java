package com.addaui.youtubeplayer;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

/**
 * Created by Audai on 9/7/2015.
 *
 * This Activity will be used to play the video
 */

public class YoutubePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final String GOOGLE_API_KEY = "/*GOOGLE_API_KEY*/"; //Change String to GOOGLE YOUTUBE API KEY
    private static String videoId = "K5KAc5CoCuk";

    RelativeLayout layoutbg ;
    SeekBar seekBar;
    TextView vidName;
    TextView fullTime;
    TextView currentTime;
    ImageButton fullScrn;
    ImageButton forward;
    ImageButton pause;
    ImageButton backward;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        overridePendingTransition(android.R.anim.fade_in, R.anim.abc_fade_out);

        YouTubePlayerView youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);

        if (getIntent().getDataString() != null){
            Uri data = getIntent().getData();
            String scheme = data.getScheme();   // "http"
            String host = data.getHost();       // "m.youtube.com"
            videoId = data.getQueryParameter("v");
         }

        layoutbg = (RelativeLayout)findViewById(R.id.layoutbg);
        seekBar = (SeekBar)findViewById(R.id.seekBar);
        vidName = (TextView)findViewById(R.id.vidName);
        fullTime = (TextView)findViewById(R.id.fullTime);
        currentTime = (TextView)findViewById(R.id.currentTime);
        fullScrn = (ImageButton)findViewById(R.id.fullScrn);
        forward = (ImageButton)findViewById(R.id.forward);
        pause = (ImageButton)findViewById(R.id.pause);
        backward = (ImageButton)findViewById(R.id.backward);

        layoutbg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(android.R.anim.fade_in, R.anim.abc_fade_out);
            }
        });


    }



    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.setPlayerStateChangeListener(playerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(playbackEventListener);
        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.MINIMAL);
        if (!b) {
            youTubePlayer.cueVideo(videoId);
            youTubePlayer.play();


            fullScrn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
//                    youTubePlayer.setFullscreen(true);
                }
            });

            forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    youTubePlayer.seekRelativeMillis(10000);
                }
            });

            backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    youTubePlayer.seekRelativeMillis(-10000);
                }
            });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (youTubePlayer.isPlaying()){
                        youTubePlayer.pause();
                    }else{
                        youTubePlayer.play();
                    }
                }
            });
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Cannot Initialize Youtube Player", Toast.LENGTH_LONG).show();
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {

        @Override
        public void onStopped() {

        }

        @Override
        public void onSeekTo(int arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPlaying() {
            pause.setImageResource(R.drawable.ic_media_pause);

        }

        @Override
        public void onPaused() {
            pause.setImageResource(R.drawable.ic_media_play);

        }

        @Override
        public void onBuffering(boolean arg0) {
            // TODO Auto-generated method stub

        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {

        @Override
        public void onVideoStarted() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onVideoEnded() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLoading() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onLoaded(String arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onError(YouTubePlayer.ErrorReason arg0) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onAdStarted() {
            // TODO Auto-generated method stub

        }
    };

//    @Override
//    public void onBackPressed() {
//
//        android.os.Process.killProcess(android.os.Process.myPid());
//        // This above line close correctly
//    }

}
