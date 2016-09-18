package by.contacts.app;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import adapters.ContactsAdapter;
import data.BaseEntity;
import loaders.ContactsLoader;

public class MainActivity extends
        BaseActivity
        implements LoaderManager.LoaderCallbacks<List<BaseEntity>> {

    Map<String, Integer> mapIndex;

    RecyclerView mRecycleView;
    private RecyclerView.LayoutManager mLayoutManager;

    private RecyclerView.Adapter mAdapter;

    private static final short CONTACT_LOADER_ID = 0x01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecycleView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(mLayoutManager);
        getSupportLoaderManager().restartLoader(CONTACT_LOADER_ID,null,this);
    }

    @Override
    public Loader<List<BaseEntity>> onCreateLoader(int id, Bundle args) {
        return new ContactsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<data.BaseEntity>> loader, List<data.BaseEntity> data) {
        mAdapter = new ContactsAdapter(data);
        mRecycleView.setAdapter(mAdapter);
    }

    @Override
    public void onLoaderReset(Loader<List<BaseEntity>> loader) {

    }

}
