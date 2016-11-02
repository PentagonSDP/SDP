package com.example.hosneara.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hosneara on 10/28/16.
 */

public class BookDetails extends AppCompatActivity{

    private TextView textView;
    private RatingBar ratingBar;
    private String rating;
    private Button button;
    private int position;

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
             }
        }

        textView = (TextView)findViewById(R.id.bookauthor);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        button = (Button)findViewById(R.id.submit);

        textView.setText(author[position]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(ratingBar.getRating());
                rating = String.valueOf((int)ratingBar.getRating());
                Toast.makeText(BookDetails.this, "Your Rating: "+rating, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
