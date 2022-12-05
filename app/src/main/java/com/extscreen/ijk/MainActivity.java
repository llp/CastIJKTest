package com.extscreen.ijk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.widget.ijkVideoView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private tv.danmaku.ijk.media.widget.ijkVideoView ijkVideoView;

    private long startTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initView();
    }

    private void initView() {
        this.ijkVideoView = this.findViewById(R.id.ijk_video_view);
        this.ijkVideoView.setUsePlayer(1);
//        this.ijkVideoView.setVideoPath("rtp://239.3.1.124:8128");
        this.ijkVideoView.setVideoPath("https://qcloudcdn-moss.cp47.ott.cibntv.net/data_center/videos/LIB/SPORT/2022/02/23/0be6f058-0e78-44ea-86a4-24330fe25ff7_transcode_1137860.m3u8");

//        this.ijkVideoView.setVideoPath("http://hwdata-center-input-1251413187.cos.ap-guangzhou.myqcloud.com/data_center/videos/SHORT/DEFAULT/2022/12/01/4e8cd532-d8a4-497a-8f11-7b52e51227e7.mp4");
        this.ijkVideoView.setOnInfoListener(new IMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                if (i == IMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    long endTime = System.currentTimeMillis();
                    Log.e(TAG, "-------启播时间-------->>>>" + (endTime - startTime));
                }
                return false;
            }
        });

        startTime = System.currentTimeMillis();
        this.ijkVideoView.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.ijkVideoView != null) {
            this.ijkVideoView.stopPlayback();
            this.ijkVideoView.release(true);
        }
    }
}