package com.example.hosneara.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hosneara on 10/28/16.
 */

public class BookDetails extends AppCompatActivity{

    private TextView textView;
    private RatingBar ratingBar;
    private String rating;
    private Button button;
    private int position;
    private String bookname;
    private DatabaseReference databaseReference;

    private String author[] = {
            "Mahatma M.K.Gandhi",
            "Thomas Hardy",
            "Rabindra Nath Tagore",
            "William shakespeare",
            "Somerset Maughan",
            "Charles Dickens",
            "Sir Thomas Moor",
            "charles Darwin",
            "Charles Dickens"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookdetails);


        if(savedInstanceState == null){
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
                position = 0;
            }
        else {
                position = extras.getInt("position");
                bookname = extras.getString("book");
             }
        }

        textView = (TextView)findViewById(R.id.bookauthor);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        button = (Button)findViewById(R.id.submit);

        textView.setText(author[position]);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        final issue  Is = new issue();
        int counter = 0;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                Toast.makeText(BookDetails.this, "start"+start, Toast.LENGTH_LONG).show();

                Is.setBookname(bookname);
                //counter++;

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Firebase mrootref = new Firebase(
                        "https://project1-4257e.firebaseio.com/"+user.getUid());

                //Firebase childref = mrootref.child(MainActivity.uid);

                Map newUser = new HashMap();
                newUser.put("Book "+position,bookname);
                mrootref.updateChildren(newUser);

                //childref.setValue(Is);
                //databaseReference.child(MainActivity.uid).setValue(Is);

                rating = String.valueOf((int)ratingBar.getRating());
                Toast.makeText(BookDetails.this, "Your Rating: "+rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
