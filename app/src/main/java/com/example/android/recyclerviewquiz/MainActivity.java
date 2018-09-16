package com.example.android.recyclerviewquiz;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.URL;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity {


String[] items = new String[] {"Any Category","General Knowledge", "Books", "Film", "Music", "Musicals & Theatres",
        "Television", "Video Games", "Board Games",
        "Science & Nature", "Computer Science", "Mathematics",
        "Mythology", "Sports", "Geography",
        "History", "Politics", "Art", "Celebrities", "Animals",
        "Vehicles", "Comics", "Gadgets", "Japanese Anime & Manga", "Cartoon & Animations"};
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (Spinner) findViewById(R.id.spinner1);
        Button button = (Button) findViewById(R.id.start);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.getBackground().setAlpha(193);
    }
    public void Start(View view) {
        Connect();

    }
    public void Connect(){
        int i = spinner.getSelectedItemPosition()+8;
        Log.v("spinner",toString().valueOf(i));
        URL url = NetworkCon.BuildUrl(toString().valueOf(i));
        new Connection().execute(url);

    }
    public class Connection extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls[0];
            String json = null;
            try{
                json = NetworkCon.getResponse(url);

            } catch (IOException e){
                e.printStackTrace();
            }

            return json;
        }
        @Override
        protected void onPostExecute(String json) {
            Intent intent = new Intent(MainActivity.this, Main2Activity.class).putExtra("JSON", json);
            startActivity(intent);

            }
        }

}
