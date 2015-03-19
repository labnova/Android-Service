package com.example.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
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
        Toast.makeText(this, "Service è distrutto", Toast.LENGTH_LONG).show();
        stopSelf();
    }
}
