package com.example.android.recyclerviewquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textView = (TextView) findViewById(R.id.total_result);
        Bundle bundle = getIntent().getExtras();
        int total = bundle.getInt("total");
        textView.setText("Your score is: " + total);
    }

}
