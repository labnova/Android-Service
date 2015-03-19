package com.example.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
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
           int result= DownloadFile(new URL("https://drive.google.com/drive/u/0/#folders/0B95IeH0gS_aVUE92NWpPWDFpZmc/0B95IeH0gS_aVZmV2RllSSHFYRjg"));
           Toast.makeText(getBaseContext(),"Downloaded " +result+ "bytes", Toast.LENGTH_LONG).show();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service è distrutto", Toast.LENGTH_LONG).show();
    }
}
