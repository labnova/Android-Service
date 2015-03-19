package com.example.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    int counter = 0;
    static final int UPDATE_INTERVAL = 1000;
    private Timer timer = new Timer();
    public MyService() {
    }

    private void doSomethingRepeatedly() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Log.d("MyService", String.valueOf(++counter));
            }
        }, 0, UPDATE_INTERVAL);
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        doSomethingRepeatedly();

        try {
          new DoBackgroundTask().execute(
                  new URL("http://www.labnova.it"),
                  new URL("http://www.labnova.it"),
                  new URL("http://www.labnova.it"),
                  new URL("http://www.labnova.it"),
                  new URL("http://www.labnova.it"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return START_STICKY;

    }

    private int DownloadFile(URL url) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 100;
    }

    public class DoBackgroundTask extends AsyncTask<URL, Integer, Long> {
       @Override
        protected Long doInBackground(URL... urls) {
           int count = urls.length;
           long totalBytesDownloaded = 0;
           for (int i=0; i < count; i++) {
               totalBytesDownloaded += DownloadFile(urls[i]);
               publishProgress((int)(((i+1)/ (float) count) *100));
           }
            return totalBytesDownloaded;
        }

        @Override
        protected void onProgressUpdate(Integer...progress) {
            Log.d("Downloading", String.valueOf(progress[0])+ "% downloaded");
            Toast.makeText(getBaseContext(), String.valueOf(progress[0])+ "% downloaded", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(Long result) {
            Toast.makeText(getBaseContext(), "Downloaded " +result+ "bytes", Toast.LENGTH_LONG).show();
            stopSelf();
        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        if(timer != null) {
            timer.cancel();
        }

        Toast.makeText(this, "Service è distrutto", Toast.LENGTH_LONG).show();
        stopSelf();
    }
}
