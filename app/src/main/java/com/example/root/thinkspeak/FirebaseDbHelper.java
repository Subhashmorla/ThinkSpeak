package com.example.root.thinkspeak;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

/**
 * Created by root on 24/2/17.
 */

public class FirebaseDbHelper {
    DatabaseReference db;
    boolean saved;
    ArrayList<Channel> channels= new ArrayList<Channel>();

    public  FirebaseDbHelper(DatabaseReference db){
        this.db=db;
    }

    public boolean save(Channel channel){

        if (channel==null){
            return false;
        }
        else{

            try{
                db.child("Channel").push().setValue(channel);
                saved=true;
            }
            catch (DatabaseException e){
                e.printStackTrace();
                saved=false;
            }
        }
        return saved;


    }



}
