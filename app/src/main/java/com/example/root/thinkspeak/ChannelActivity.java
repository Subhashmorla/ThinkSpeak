package com.example.root.thinkspeak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChannelActivity extends AppCompatActivity {
    private EditText mAddChannelId;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        mAuth=FirebaseAuth.getInstance();
        String BASE_URI="https://thinkspeak-7e75c.firebaseio.com/Users/6ZRArtCpiUNBDMUr6vQcDHwhhXn1/Channels";

        mDatabase=FirebaseDatabase.getInstance().getReferenceFromUrl(BASE_URI);


        mAddChannelId = (EditText) findViewById(R.id.addchannelid_edittext);
        Button addButton= (Button) findViewById(R.id.addchannel_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addChannel();
            }
        });
    }

    private void addChannel() {
        String channelId= mAddChannelId.getText().toString();
        if (channelId != null){

            
            DatabaseReference current=mDatabase.child("channel");
            current.setValue(channelId);
            finish();

        }
    }
}
