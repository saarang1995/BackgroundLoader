package com.saarang.developer.fundamentalapplicationcomponents;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import static android.content.ContentValues.TAG;

public class GetFiles extends AsyncTaskLoader<List<String>> implements Observer {
    public static final String ACTION = "com.loaders.GETFILES";
    private String filePath = "/storage/emulated/0/Download";
    private List<String> filenames;

    public GetFiles(Context context) {
        super(context);
    }

    @Override
    public List<String> loadInBackground() {
        filenames=new ArrayList<>();
        Observable observable = new Observable();
        observable.addObserver(this);

        File file = new File(filePath);
        File[] files = file.listFiles();
        for (File f : files) {
            observable.notifyObservers();
            filenames.add(f.getName());


        }
        return filenames;
    }

    @Override
    public void setUpdateThrottle(long delayMS) {
        super.setUpdateThrottle(delayMS);
    }

    @Override
    public void deliverResult(List<String> data) {
        super.deliverResult(data);

    }

    @Override
    protected void onStartLoading() {
        forceLoad();
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter intentFilter = new IntentFilter(ACTION);
        localBroadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                forceLoad();
            }}
    , intentFilter);
    }

    @Override
    protected void onReset() {
        super.onReset();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                forceLoad();
            }}
    );

    }

    @Override
    public void update(Observable o, Object arg) {
        Log.d(TAG,  o.toString());

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            forceLoad();
        }
    };

}
