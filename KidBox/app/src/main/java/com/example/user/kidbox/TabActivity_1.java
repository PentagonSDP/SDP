package com.example.user.kidbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ListView;

/**
 * Created by emma on 10/19/17.
 */

public class TabActivity_1 extends Activity {

    ListView lv;
    Context context_daily;
    private int flag = 0;
    //public static int [] prgmImages={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,R.drawable.pic6,R.drawable.pic7};
    public static String [] dailyTaskList={"Tidy Up Bed","Brushing Teeth","Eating Breakfast","Go for School","Do homework"};
    public static String[] buttonList1 = {"Approve","Approve","Approve","",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);

        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("flag");
        flag = Integer.valueOf(str);


            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(), AddTask.class));

                }
            });

        if( flag == 0 ) {
            fab.setVisibility(View.INVISIBLE);
        }


        context_daily = this;
        lv = (ListView) findViewById(R.id.list_daily);
        lv.setAdapter(new CustomAdapter_daily(this, dailyTaskList,buttonList1,flag));
    }
}
