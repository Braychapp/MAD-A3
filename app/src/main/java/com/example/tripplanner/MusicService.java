package com.example.tripplanner;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MusicService extends Service {

    MediaPlayer mediaPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        Toast.makeText(this, "Service Created!", Toast.LENGTH_SHORT).show();
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
    }

    @Override
    public int onStartCommand (Intent intent, int flags, int startID) {

        Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();
        mediaPlayer.start();
        return super.onStartCommand(intent, flags, startID);
    }

    @Override
    public void onDestroy() {

        Toast.makeText(this, "Service destroyed!", Toast.LENGTH_SHORT).show();
        mediaPlayer.release();
        super.onDestroy();
    }
}
