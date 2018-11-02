package com.a987genfm.genfm;


import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {

//    final List<Radio> listRadio = new ArrayList<>();
//    Radio radio1 = new Radio("MQ FM Bandung", "http://125.160.17.86:8022/;");
//    Radio radio2 = new Radio("Radio Rodja", "http://live.radiorodja.com/;stream.mp3");
//    Radio radio3 = new Radio("Radio Muslim", "http://128.199.156.6/;stream/1");
//    Radio radio4 = new Radio("Radio KITA Cirebon", "http://live.radiosunnah.net/;");
//    Radio radio5 = new Radio("Radio Hidayah", "http://radio.hidayahfm.com:9988/;stream.mp3");

    private String url_radio = "http://205.164.62.22:8075";
    private ProgressBar playSeekBar;
    private TextView tvRadioUrl;
    private FloatingActionButton buttonPlay;
    private FloatingActionButton buttonStop;
    private MediaPlayer mediaPlayer;
    RadioService radioService;

    private Intent bindIntent;private TelephonyManager telephonyManager;

    private boolean wasPlayingBeforePhoneCall = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Intent intent = new Intent(MainActivity.this, RadioService.class);
        startService(intent);

        //bottom
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.ic_arrow_back:

                        break;

//

                    case R.id.ic_books:
                        Intent intent2 = new Intent(MainActivity.this, ActivityTwo.class);
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent2);
                        break;

                    case R.id.ic_center_focus:

                        Intent intent3 = new Intent(MainActivity.this, ActivityThree.class);
                        intent3.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent3);
                        break;

                    case R.id.ic_backup:

                        Intent intent4 = new Intent(MainActivity.this, ActivityFour.class);
                        intent4.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(intent4);
                        break;
                }

                return false;
            }
        });
        //agar come back in main
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        //radio
        initializeUIElements();
        initializeMediaPlayer();
    }

    private void initializeMediaPlayer() {
        mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url_radio);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                playSeekBar.setIndeterminate(false);
                playSeekBar.setSecondaryProgress(100);
                Log.i("Buffering", "" + percent);
            }
        });

    }

    private void initializeUIElements() {
        playSeekBar = (ProgressBar) findViewById(R.id.progresBar);
        playSeekBar.setMax(100);
        playSeekBar.setVisibility(View.INVISIBLE);
        playSeekBar.setIndeterminate(true);

        buttonPlay = (FloatingActionButton) findViewById(R.id.floatingActionButtonPlay);
        buttonPlay.setOnClickListener(this);

        buttonStop = (FloatingActionButton) findViewById(R.id.floatingActionButtonStop);
        buttonStop.setEnabled(false);
        buttonStop.setOnClickListener(this);

        tvRadioUrl = (TextView) findViewById(R.id.textUrlRadio);
        tvRadioUrl.setText("Radio url : " + url_radio);

    }



    @Override
    public void onClick(View v) {
        if (v == buttonPlay) {
            startPlaying();
//            startService(new Intent(this, StreamingService.class));
        } else if (v == buttonStop) {
            stopPlaying();
//            stopService(new Intent(this, StreamingService.class));
        }

    }

    private void stopPlaying() {
        if (mediaPlayer == null) return;
        try{
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                mediaPlayer.release();
                initializeMediaPlayer();
            }
        } catch (IllegalStateException e) {

        }
        buttonPlay.setEnabled(true);
        buttonStop.setEnabled(false);
        playSeekBar.setIndeterminate(true);
        playSeekBar.setVisibility(View.INVISIBLE);

//        Intent intent = new Intent(MainActivity.this, RadioService.class);
//        stopService(intent);
    }

    private void startPlaying() {
        buttonStop.setEnabled(true);
        buttonPlay.setEnabled(false);
        playSeekBar.setVisibility(View.VISIBLE);
        mediaPlayer.prepareAsync();
        mediaPlayer.stop();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();

            }
        });



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        mediaPlayer.pause();

    }



}

