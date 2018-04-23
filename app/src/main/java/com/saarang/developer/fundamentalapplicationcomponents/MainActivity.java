package com.saarang.developer.fundamentalapplicationcomponents;

import android.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks{
    private String filePath="/storage/emulated/0/Download/appname.txt";
    private Loader loader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loader=getSupportLoaderManager().initLoader(0,null,MainActivity.this);
    }

    @Override
    public android.content.Loader onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(android.content.Loader loader, Object data) {

    }

    @Override
    public void onLoaderReset(android.content.Loader loader) {

    }
}
