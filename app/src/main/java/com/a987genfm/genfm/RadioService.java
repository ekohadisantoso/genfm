package com.a987genfm.genfm;

/**
 * Created by eko on 8/4/17.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class RadioService extends Service {

    private static final String TAG = "HaiService";
    private boolean isRunning  = false;

    private NotificationManager notifyMgr = null;
    private static final int NOTIFY_ME_ID = 12345;

    public static final String MODE_PLAYING = "PLAYING";
    public static final String MODE_PAUSED = "PAUSED";
    private boolean isPrepared = false;
    private boolean isPreparingStarted = false;
    private boolean isRadioPlaying = false;
    MediaPlayer mediaPlayer;

    //private NotificationCompat.Builder notifyBuilde
    android.support.v4.app.NotificationCompat.Builder notifyBuilder;



    @Override
    public void onCreate() {
//        Toast.makeText(this, "RadioService telah dibuat", Toast.LENGTH_LONG).show();

        isRunning = true;


    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Toast.makeText(this, "RadioService di jalankan", Toast.LENGTH_LONG).show();
//        showNotif();


		/* Starts playback at first time or resumes if it is restarted */
//        if (mediaPlayer.isPlaying())
//            sendBroadcast(new Intent(MODE_PLAYING));
//        else if (isPrepared) {
//            sendBroadcast(new Intent(MODE_PAUSED));
//        }

        return android.app.Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "RadioService onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        isRunning = false;
        Toast.makeText(this, "RadioService di Destroy", Toast.LENGTH_LONG).show();
        Log.i(TAG, "RadioService berhenti");
        exitNotification();

    }

    private void showNotif() {
        NotificationManager notificationManager;

        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("fromnotif", "notif");
        mIntent.putExtras(bundle);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setColor(getResources().getColor(R.color.colorAccent));
        builder.setContentIntent(pendingIntent)
//                .setSmallIcon()
//                .setColor(R.color.colorAccent)
//                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.ic_home_white))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.mipmap.ic_launcher))
                .setTicker("")
                .setAutoCancel(true)
//                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
//                .setDefaults(Notification.DEFAULT_SOUND)
//                .setContentTitle("Notification Android")
//                .setContentText("by imamfarisi.com")
                ;

//        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        notificationManager.notify(115, builder.build());
        notifyMgr = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notifyMgr.notify(NOTIFY_ME_ID, builder.build());


    }

    public void clearNotification() {
        if (notifyMgr != null)
            notifyMgr.cancel(NOTIFY_ME_ID);
    }

    public void exitNotification() {
        clearNotification();
        notifyBuilder = null;
        notifyMgr = null;
    }

}



