package com.example.root.thinkspeak;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by root on 25/2/17.
 */

public class FieldsLoader extends AsyncTaskLoader<Channel>{

    private static final String LOG_TAG=FieldsLoader.class.getSimpleName();
    private  String mUrl;

    public FieldsLoader(Context context,String url) {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Channel loadInBackground() {
        if (mUrl==null){
            return null;}

        Channel field=QueryUtils.fetchFieldsData(mUrl);
        return field;
    }
}
