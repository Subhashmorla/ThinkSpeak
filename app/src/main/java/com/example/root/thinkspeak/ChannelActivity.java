package com.example.root.thinkspeak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class ChannelActivity extends AppCompatActivity {
    private EditText mAddChannelId;
    private DatabaseReference mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

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
        int channelId= Integer.parseInt(mAddChannelId.getText().toString());
        if (channelId != 0){



        }
    }
}
