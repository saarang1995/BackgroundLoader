package com.saarang.developer.fundamentalapplicationcomponents;

import android.app.LoaderManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;
public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>> {
    private static final String TAG = "MainActivity";
    private LoaderManager loaderManager;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recylerView);
        SelectionTracker tracker = new SelectionTracker.Builder<>(
                "my-selection-id",
                recyclerView,
                new StableIdKeyProvider(recyclerView),
                new MyDetailsLookup(recyclerView),
                StorageStrategy.createLongStorage())
                .withOnItemActivatedListener(myItemActivatedListener)
                .build();

        getLoaderManager().initLoader(0, null, this);
        Intent intent = new Intent(GetFiles.ACTION);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @Override
    public android.content.Loader<List<String>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "Loader created");
        return new GetFiles(this);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<String>> loader, List<String> data) {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyAdapter adapter=new MyAdapter(data);
        recyclerView.setAdapter(adapter);
        Log.d(TAG, Integer.toString(data.size()));

    }

    @Override
    public void onLoaderReset(android.content.Loader<List<String>> loader) {

    }
}
