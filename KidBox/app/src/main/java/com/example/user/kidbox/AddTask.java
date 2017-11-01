package com.example.user.kidbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by emma on 10/19/17.
 */

public class  AddTask  extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_task);

        EditText et1 = (EditText) findViewById(R.id.task);
        EditText et2 = (EditText) findViewById(R.id.point);

        Button submit = (Button) findViewById(R.id.sub);

        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent Image = new Intent(getApplicationContext(), ToDoActivity.class);
                String str = "1";
                Image.putExtra("flag", str);
                startActivity(Image);
            }
        });


    }
}
