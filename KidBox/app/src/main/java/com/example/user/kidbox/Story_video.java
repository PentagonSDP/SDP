package com.example.user.kidbox;


import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static android.widget.Toast.LENGTH_LONG;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import java.net.URL;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.net.http.*;
import android.widget.VideoView;

import static android.widget.Toast.LENGTH_LONG;

public class Story_video extends Activity {
    // All static variables
    static final String URL = "http://api.androidhive.info/music/music.xml";


    String youtube = "http://www.youtube.com";

    // XML node keys
    static final String KEY_SONG = "song"; // parent node
    static final String KEY_ID = "id";
    static final String KEY_TITLE = "title";
    static final String KEY_ARTIST = "artist";
    static final String KEY_DURATION = "duration";
    static final String KEY_THUMB_URL = "thumb_url";
    static int idCounter = -1;

    void playvideo()
    {
        /*VideoView view = (VideoView)findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.coldplaylyrics;
        view.setVideoURI(Uri.parse(path));
        view.start();*/

        /*setVolumeControlStream(AudioManager.STREAM_MUSIC);
        final MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource("/home/hosneara/AndroidStudioProjects/KidBox/app/src/main" +
                    "/res/drawable/Coldplay-Yellow-Lyrics.mp4");
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://drive.google.com/file/d/0B3QRL3z8WUuhaXpfYVhtSE1ueVU/view")));
                Log.i("Video", "Video Playing....");

            }
        });*/

    }




    final ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    ListView list;
    LazyAdapter adapter;

    void addData(String response)
    {
        Log.d("RESPONSE ",response);
        ArrayList<String> finalData = new ArrayList<String>();
        response = response.replaceAll("\"", "");
        response = response.replaceAll("\\{", "");
        response = response.replaceAll("\\}","");
        String[] tempData = response.split(",");
        //tempData[0].replaceAll("\\{","===");
        Log.d("ENTERED ", tempData[0]);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(KEY_ID, Integer.toString(++idCounter));

        String[] strs ;
        for(String str : tempData)
        {
            if(str.startsWith("title"))
            {
                strs = str.split("title:");
                map.put(KEY_TITLE, strs[1]);
            }
            else if(str.startsWith("author_name"))
            {
                strs = str.split("author_name:");
                map.put(KEY_ARTIST, strs[1]);
                map.put(KEY_DURATION, "04:00 minutes");
            }
            else if(str.startsWith("url"))
            {
                strs = str.split("url:");
                Log.d("Splitted ",strs[0] +" >>> "+strs[1]);
                map.put(KEY_THUMB_URL, strs[1]);
            }

        }

        songsList.add(map);
        adapter.notifyDataSetChanged();

        Log.d("SONGLIST ", map.toString() +" ||||| " +songsList.size());
    }

    void getData(String link) {

        Log.d("why ", "are you here?");
        String[] temp;
        com.android.volley.RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        link = link.replace("//", "/");
        String[] partsOfURL = link.split("/");
        String url = "https://noembed.com/embed?url=https://www.youtube" +
                ".com/watch?v="+partsOfURL[partsOfURL.length-1];

        Log.d("Ultimate URL ",url);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAGING ", response);
                        if(!response.contains("error"))

                            //if(url.regionMatches(0, "=", 0, ))
                            addData(response);
                        else {
                            Toast.makeText(getApplicationContext(), "Content not found", Toast
                                    .LENGTH_SHORT);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "That didn't work!", Toast.LENGTH_SHORT);
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story);

        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearlayout);

        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("flag");

        HashMap<String, String> map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Hello");
        map.put(KEY_ARTIST, "Adele");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=YQHsXMglC9A");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Mina1");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=zEwQRspiyyo");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Mina2");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=4pf2FiCa6Uo");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Mina3");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=w1XI2R3FgwQ");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Mina4");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=I9jXpuVzdYI");

        // adding HashList to ArrayList
        songsList.add(map);

        // looping through all song nodes <song>
		/*for (int i = 0; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			// adding each child node to HashMap key => value
			map.put(KEY_ID, parser.getValue(e, KEY_ID));
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_ARTIST, parser.getValue(e, KEY_ARTIST));
			map.put(KEY_DURATION, parser.getValue(e, KEY_DURATION));
			map.put(KEY_THUMB_URL, parser.getValue(e, KEY_THUMB_URL));

			// adding HashList to ArrayList
			songsList.add(map);
		}*/


        list=(ListView)findViewById(R.id.list);
        list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        /*list.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/


        // Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);
        list.setAdapter(adapter);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.d("inside", "testing");
                adapter.notifyDataSetChanged();
            }
        };

        this.runOnUiThread(run);

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
                return onitemlong(pos, id);
            }
        });



        list.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Hello long click", Toast.LENGTH_SHORT).show();
                Log.d("onlong", "YES");
                return true;
            }
        });
        // Click event for single list row
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String url = "???";

                for(int i=0; i<songsList.size(); i++)
                {
                    if(songsList.get(i).get(KEY_ID).equals(Long.toString(id)))
                    {
                        url = songsList.get(i).get(KEY_THUMB_URL);
                        Toast.makeText(getApplicationContext(), "Testing  --- "+url,
                                Toast.LENGTH_SHORT).show();
                        Log.d("URL ",url);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        Log.i("Video", "Video Playing....");
                        break;
                    }
                }
            }
        });

        ImageButton imageButton = (ImageButton) findViewById(R.id.addVideo);
        final EditText LinkEditText = (EditText)findViewById(R.id.edittext);
        Button doneButton = (Button)findViewById(R.id.done);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Pressing", "getdata");
                //getData();
                //adapter=new LazyAdapter(this, songsList);
                list.setAdapter(adapter);
                //list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        int m = Integer.valueOf(str);
        //Toast.makeText(getApplicationContext(), String.valueOf(m) , Toast.LENGTH_LONG).show();

        if(m == 0) {
            linearLayout.setVisibility(View.INVISIBLE);
            /*imageButton.setVisibility(View.INVISIBLE);
            LinkEditText.setVisibility(View.INVISIBLE);
            doneButton.setVisibility(View.INVISIBLE);*/
        }
        else
        {
            linearLayout.setVisibility(View.VISIBLE);
            /*imageButton.setVisibility(View.VISIBLE);
            LinkEditText.setVisibility(View.VISIBLE);
            doneButton.setVisibility(View.VISIBLE);*/
        }


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //playvideo();
                //Intent video = new Intent(getApplicationContext() , VideoPlayer.class);
                //video.putExtra("flag", str);//EXTRA_MESSAGE
                //startActivity(video);

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtube)));
                Log.i("Video", "Video Playing....");
            }
        });

        doneButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v)
            {
                String link = LinkEditText.getText().toString();
                if(link.length() > 0)
                {
                    if(URLUtil.isValidUrl(link)) {
                        LinkEditText.setText("");
                        Log.d("Pressing", "getdata");
                        getData(link);
                        //adapter=new LazyAdapter(this, songsList);
                        list.setAdapter(adapter);
                        //list.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        LinkEditText.selectAll();
                        Toast.makeText(getApplicationContext(), "Invalid Link", Toast
                                .LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Provide Link", Toast
                            .LENGTH_SHORT).show();
                }
            }
        });

    }
    boolean onitemlong(int pos, long id)
    {
        Toast.makeText(getApplicationContext(), Integer.toString(pos)+"..."+Long.toString
                (id) , LENGTH_LONG).show();
        return true;
    }
}



















/*import java.util.ArrayList;
import java.util.HashMap;

//import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class Story_video extends Activity {
	// All static variables
	static final String URL = "http://api.androidhive.info/music/music.xml";


    String youtube = "http://www.youtube.com";

	// XML node keys
	static final String KEY_SONG = "song"; // parent node
	static final String KEY_ID = "id";
	static final String KEY_TITLE = "title";
	static final String KEY_ARTIST = "artist";
	static final String KEY_DURATION = "duration";
	static final String KEY_THUMB_URL = "thumb_url";


    final ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();
	
	ListView list;
    LazyAdapter adapter;

    void addData(String response)
    {

        ArrayList<String> finalData = new ArrayList<String>();
        response = response.replaceAll("\"", "");
        response = response.replaceAll("\\{", "");
        response = response.replaceAll("\\}","");
        String[] tempData = response.split(",");
        //tempData[0].replaceAll("\\{","===");
        Log.d("ENTERED ", tempData[0]);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put(KEY_ID, "7");

        for(String str : tempData)
        {
            if(str.startsWith("title"))
            {
                map.put(KEY_TITLE, str.substring(6));
            }
            else if(str.startsWith("author_name"))
            {
                map.put(KEY_ARTIST, str.substring(12));
                map.put(KEY_DURATION, "04:00 minutes");
            }
            else if(str.startsWith("thumbnail_url"))
            {
                map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
            }
        }

        songsList.add(map);
        adapter.notifyDataSetChanged();

        Log.d("SONGLIST ", map.toString());
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.story);

        Bundle bundle = getIntent().getExtras();
        String str = bundle.getString("flag");
        //Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();

        int idCounter = -1;


		HashMap<String, String> map = new HashMap<String, String>();
		// adding each child node to HashMap key => value
		map.put(KEY_ID, Integer.toString(++idCounter));
		map.put(KEY_TITLE, "Hello");
		map.put(KEY_ARTIST, "Adele");
		map.put(KEY_DURATION, "04:00 minutes");
		map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=YQHsXMglC9A");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Video1");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=zEwQRspiyyo");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Video2");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=4pf2FiCa6Uo");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Video3");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=w1XI2R3FgwQ");

        songsList.add(map);

        map = new HashMap<String, String>();
        // adding each child node to HashMap key => value
        map.put(KEY_ID, Integer.toString(++idCounter));
        map.put(KEY_TITLE, "Video4");
        map.put(KEY_ARTIST, "UNICEF");
        map.put(KEY_DURATION, "04:00 minutes");
        map.put(KEY_THUMB_URL, "https://www.youtube.com/watch?v=I9jXpuVzdYI");



		// adding HashList to ArrayList
		songsList.add(map);


		list=(ListView)findViewById(R.id.list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new LazyAdapter(this, songsList);
        list.setAdapter(adapter);

        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.d("inside", "testing");
                adapter.notifyDataSetChanged();
            }
        };

        this.runOnUiThread(run);
        

        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
                String url = "---";

                for(int i=0; i<songsList.size(); i++)
                {
                    if(songsList.get(i).get(KEY_ID).equals(Long.toString(id)))
                    {
                        url = songsList.get(i).get(KEY_THUMB_URL);
                        //url = url.replaceAll("\\s+","");
                        Toast.makeText(getApplicationContext(), "Testing  --- "+url,
                                Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                        Log.i("Video", "Video Playing....");
                        break;
                    }
                }
			}
		});

        ImageButton imageButton = (ImageButton) findViewById(R.id.addVideo);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Pressing", "getdata");
                //getData();
                //adapter=new LazyAdapter(this, songsList);
                list.setAdapter(adapter);
                //list.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });

        int m = Integer.valueOf(str);
        //Toast.makeText(getApplicationContext(), String.valueOf(m) , Toast.LENGTH_LONG).show();

            if(m == 0) {
                imageButton.setVisibility(View.INVISIBLE);
            }
        else
            {
                imageButton.setVisibility(View.VISIBLE);
            }



	}	
}*/