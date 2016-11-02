package com.example.hosneara.project1;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by hosneara on 10/28/16.
 */

public class BookList extends Activity {

    private String bookname[] = {"My experiments with Truth",
            "Far from the Madding Crowd",
            "Geetanjali",
            "The Merchant of venice",
            "The Moon and Six pense",
            "A Tale of Two Cities",
            "Utopia",
            "Origin of species",
            "David Copperfield"};

    private ListView lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booklist);

        lv = (ListView) findViewById(R.id.booklist);

        ArrayAdapter<String> arrayadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                android.R.id.text1, bookname);
        lv.setAdapter(arrayadapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(BookList.this, BookDetails.class);
                i.putExtra("position", position);
                i.putExtra("book",bookname[position]);
                startActivity(i);
            }

        });
    }
}