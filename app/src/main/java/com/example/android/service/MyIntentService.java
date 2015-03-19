package com.example.android.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by innocenzo on 19/03/15.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentServiceName");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            int result = DownloadFile(new URL("http://www.labnova.it"));
            Log.d("IntentService", "Downloaded" +result+"bytes");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private int DownloadFile(URL url) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 100;
    }



}
