package com.example.root.thinkspeak;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText mEmailReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        mEmailReset= (EditText) findViewById(R.id.email_reset);

        mAuth = FirebaseAuth.getInstance();
        Button resetButton= (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassResetViaEmail();
            }
        });


    }

    private void PassResetViaEmail(){
        String emailResetText=mEmailReset.getText().toString();
        if(mAuth != null && !TextUtils.isEmpty(emailResetText)) {
            mAuth.sendPasswordResetEmail(emailResetText);
            Toast.makeText(ResetPasswordActivity.this,"Password ResetLin sent to Email:"+emailResetText,Toast.LENGTH_LONG).show();
            Intent mainIntent=new Intent(ResetPasswordActivity.this,MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(mainIntent);

        } else {
            Toast.makeText(ResetPasswordActivity.this,"Bad entry",Toast.LENGTH_SHORT).show();
        }
    }
}
