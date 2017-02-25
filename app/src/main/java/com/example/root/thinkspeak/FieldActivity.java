package com.example.root.thinkspeak;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FieldActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Channel> {
    private static  final int FIELD_LOADER_ID=1;
    private String field_url_string;
    private ArrayAdapter mFieldAdapter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        final Bundle extras=getIntent().getExtras();
      field_url_string= extras.getString("field url");

        Log.v(MainActivity.class.getSimpleName(),"CRAZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZY"+field_url_string);

        GridView field_list_view= (GridView) findViewById(R.id.grid_view);

        field_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent mintent=new Intent(FieldActivity.this,GraphActivity.class);


                mintent.putExtra("field id",position+1);
                mintent.putExtra("channel id",extras.getString("channel"));
                startActivity(mintent);
            }
        });







        mFieldAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,new ArrayList());
        field_list_view.setAdapter(mFieldAdapter);

    LoaderManager loaderManager=getLoaderManager();
       loaderManager.initLoader(FIELD_LOADER_ID,null,this);

    }

    @Override
    public Loader<Channel> onCreateLoader(int id, Bundle args) {
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        return new FieldsLoader(this,field_url_string);    }

    @Override
    public void onLoadFinished(Loader<Channel> loader, Channel channel) {
        progressDialog.dismiss();
        mFieldAdapter.clear();

        List<String> fields=new ArrayList<>();
        fields.add(channel.getMfield1());
        fields.add(channel.getMfield2());
        fields.add(channel.getMfield3());
        fields.add(channel.getMfield4());
        fields.add(channel.getMfield5());
        fields.add(channel.getMfield6());
        fields.add(channel.getMfield7());
        fields.add(channel.getMfield8());
        if (fields!= null ) {
            mFieldAdapter.addAll(fields);
        }
    }

    @Override
    public void onLoaderReset(Loader<Channel> loader) {
        mFieldAdapter.clear();


    }



}
