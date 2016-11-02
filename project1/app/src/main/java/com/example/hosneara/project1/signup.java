package com.example.hosneara.project1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by hosneara on 10/18/16.
 */

public class signup extends AppCompatActivity{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference reference;
    private EditText name, email, pass, phone;
    public static int flag = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Firebase.setAndroidContext(this);

        Log.d("Ref", "db ref: "+reference);
        firebaseAuth = FirebaseAuth.getInstance();
        //firebase = new Firebase("https://project1-68f14.firebaseio.com/UserTemp");

        name = (EditText)findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        phone = (EditText)findViewById(R.id.phone);


    }


    public void btnRegistration_Click(View v) {

        {

            final ProgressDialog progressDialog = ProgressDialog.show(signup.this, "Please wait...",
                    "Processing...", true);

            (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText()
                    .toString()))
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {

                                Toast.makeText(signup.this, "Registration successful", Toast.LENGTH_LONG)
                                        .show();

                                Intent i = new Intent(signup.this, MainActivity.class);


                                i.putExtra("name", name.getText().toString());
                                i.putExtra("email", email.getText().toString());
                                i.putExtra("phone", phone.getText().toString());

                                flag = 1;

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
