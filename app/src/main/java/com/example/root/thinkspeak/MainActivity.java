package com.example.root.thinkspeak;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private ListView mChannelList;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChannelList= (ListView) findViewById(R.id.channel_id_list);



        mAuth=FirebaseAuth.getInstance();

        mAuthStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()==null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
                else {
                    populateList();
                }

            }
        };



    }

    private void populateList() {

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://thinkspeak-7e75c.firebaseio.com/Users");
        FirebaseListAdapter<String> firebaseListAdapter=new FirebaseListAdapter<String>(this,
                String.class,android.R.layout.simple_expandable_list_item_1,databaseReference) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView= (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);

            }
        };
        mChannelList.setAdapter(firebaseListAdapter);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_logout){
           logout();
        }
        if (item.getItemId()==R.id.addchannel_menu_item){
            startActivity(new Intent(MainActivity.this,ChannelActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }

    private void logout() {
        mAuth.signOut();
    }



}
