package com.example.mp3player;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service {
    MediaPlayer mPlayer;
    static int position = 0;
    public static final String BROADCAST_ACTION = "com.example.mp3player.updateUI";
    
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    
    @Override
    public void onCreate() {
        //Toast.makeText(this, "Service Created", Toast.LENGTH_SHORT).show(); 
        mPlayer = MediaPlayer.create(this, R.raw.music);
        mPlayer.setLooping(false);
        
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                //Toast.makeText(PlayService.this, "Music Ended", Toast.LENGTH_SHORT).show();
                position = 0;
                sendBroadcast(new Intent(BROADCAST_ACTION));
            }
        });
    }

    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
        position = mPlayer.getCurrentPosition();
        mPlayer.stop();
    }
    
    @Override
    public void onStart(Intent intent, int startid) {
        //Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
        mPlayer.seekTo(position);
        mPlayer.start();
    }
}
