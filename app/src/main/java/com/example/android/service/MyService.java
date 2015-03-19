package com.example.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // vogliamo che il service continui a girare finché non è stoppato in maniera esplicita.
        Toast.makeText(this, "Service è partito", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service è distrutto", Toast.LENGTH_LONG).show();
    }
}
