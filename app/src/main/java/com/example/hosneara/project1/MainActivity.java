package com.example.hosneara.project1;


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/*
import android.app.Application;

import com.firebase.client.FirebaseApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;



import com.firebase.ui.database.FirebaseListAdapter;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.hosneara.project1.R.id.listview;
*/

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private EditText email, pass;
    private Button signin, signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();

        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        signin = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

    }

   public void SignUp(View v) {

        Intent i = new Intent(MainActivity.this, signup.class);
        startActivity(i);
    }

    public void btnLogin_Click(View v) {
        if(TextUtils.isEmpty(email.getText()) || TextUtils.isEmpty(pass.getText()))
        {
            Toast.makeText(this, "Empty Field", Toast.LENGTH_SHORT).show();
        }
        else {
            final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Please wait...",
                    "Proccessing...", true);

            (firebaseAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(MainActivity.this, LogInSuccess.class);
                                //i.putExtra("Email", firebaseAuth.getCurrentUser().getEmail());
                                startActivity(i);
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();

                            }
                        }
                    });
        }
    }






        /*** Normal data retrieval ***/
        /*ref = new Firebase("https://project1-68f14.firebaseio.com/Users");
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout
                .simple_list_item_1, usernames);
        listview.setAdapter(arrayAdapter);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String value = dataSnapshot.getValue(String.class);
                usernames.add(value);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });





        /**** Authentication ****/
        /* firebaseAuth = FirebaseAuth.getInstance();
        authlistener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null)
                {
                    startActivity(new Intent(MainActivity.this, second.class));
                }
            }
        };

        textemail = (EditText)findViewById(R.id.email);
        textpassword = (EditText)findViewById(R.id.Password);
        login = (Button)findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startlogin();
            }
        });*/


    }
    /**** Authentication ****/
    /*@Override
    protected void onStart()
    {
        super.onStart();
        firebaseAuth.addAuthStateListener(authlistener);
    }

     /**** Authentication ****/
    /*private void startlogin()
    {
        String email = textemail.getText().toString();
        String password = textpassword.getText().toString();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password))
        {
            Toast.makeText(MainActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this, "Sign In Problem", Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        }
    }*/



