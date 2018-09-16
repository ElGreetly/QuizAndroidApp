package com.example.android.recyclerviewquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class Main2Activity extends AppCompatActivity{
    private String[] questions= {"1- I ___ watching TV when Paul and Simon arrived.",
            "2 - Do you think he ___ what I said?",
            "3 - She ___ to learn English in Malta next summer."};
    private String[] ans11 = {"is","understand","hoping"};
    private String[] ans22 = {"was","understanding","hopes"};
    private String[] ans33 = {"were","understood","hope"};
    private String[] ans44 = {"am","",""};
    private LinearLayout view;
    TextView tt;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle hey = getIntent().getExtras();
        String me = hey.getString("JSON");
        Log.v("data: ",me);
        String questions1[] = new String[10];
        final String RESULTS = "results";
        final String QUESTION = "question";
        final String INCORRECT_ANSWERS = "incorrect_answers";
        final String CORRECT_ANSWER = "correct_answer";
        JSONObject questionsData;
        String[] ans1 = new String[10];
        String[] ans2 = new String[10];
        String[] ans3 = new String[10];
        String[] ans4 = new String[10];
        try {

            questionsData = new JSONObject(me);
            JSONArray results = questionsData.getJSONArray(RESULTS);
            for(int i = 0; i <= 9; i++){
                JSONObject question = (JSONObject) results.get(i);
                questions1[i] = question.optString(QUESTION).replaceAll("&quot;","").replaceAll("&#039;","'");
                JSONArray ans = question.optJSONArray(INCORRECT_ANSWERS);
                ans1[i] = ans.getString(0).replaceAll("&quot;","").replaceAll("&#039;","'");
                if(ans.length() < 2){
                   ans2[i] = "1";
                } else {ans2[i] = ans.getString(1).replaceAll("&quot;","").replaceAll("&#039;","'");}
                if(ans.length() < 3){
                    ans3[i] = "1";
                } else {ans3[i] = ans.getString(2).replaceAll("&quot;","").replaceAll("&#039;","'");}
                ans4[i] = question.optString(CORRECT_ANSWER).replaceAll("&quot;","").replaceAll("&#039;","'");
                Log.v("yyyyyyyy: ", ans4[i]);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle_id);
        final CustomGridLayoutManager layoutManager = new CustomGridLayoutManager(this, 0, false);
        Recycler recycler = new Recycler(questions1,ans1,ans2,ans3,ans4,layoutManager,recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                CustomGridLayoutManager.isScrollEnabled = false;
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            Button b = (Button) findViewById(R.id.result);
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                if(layoutManager.findLastCompletelyVisibleItemPosition()>8){
                    b.setVisibility(View.VISIBLE);

                } else {
                    b.setVisibility(View.GONE);
                }
            }
        });
        Button b = (Button) findViewById(R.id.result);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = 0;
                for(int i = 0; i < Recycler.t.length; i++){
                    total = total + Recycler.t[i];
                }
                Intent intent = new Intent(Main2Activity.this, ResultActivity.class).putExtra("total",total);
                startActivity(intent);
                finish();
            }
        });


    }

}

