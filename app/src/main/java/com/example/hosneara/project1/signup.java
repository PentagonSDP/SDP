package com.example.hosneara.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by hosneara on 10/18/16.
 */

public class signup extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private EditText name, email, pass, phone;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Firebase.setAndroidContext(this);
        reference = FirebaseDatabase.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        //firebase = new Firebase("https://project1-68f14.firebaseio.com/UserTemp");

        name = (EditText)findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);


    }

    private void dataEntry()
    {
        String name1 = name.getText().toString();
        String email1 = email.getText().toString();
        String phone1 = phone.getText().toString();
        user u = new user(name1, email1, phone1);
        reference.child(name1).setValue(u);
        //firebase.child(email.toString()).setValue(name.toString());
    }
    public void btnRegistration_Click(View v) {

        {

            final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                    "Processing...", true);
            dataEntry();
            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG)
                                        .show();
                                Intent i = new Intent(signup.this, SignUpSuccess.class);
                                startActivity(i);
                            } else {
                                Log.e("ERROR", task.getException().toString());
                                Toast.makeText(signup.this, task.getException().getMessage(), Toast
                                        .LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }
}
