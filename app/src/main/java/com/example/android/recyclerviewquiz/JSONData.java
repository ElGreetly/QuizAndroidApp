package com.example.android.recyclerviewquiz;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by ElGreetly on 3/3/18.
 */

public final class JSONData {


    public static String[] getJsonData(String Json){
        String questions[];
        final String RESULTS = "results";
        final String QUESTION = "question";
        final String INCORRECT_ANSWERS = "incorrect_answers";
        final String CORRECT_ANSWER = "correct_answer";
        try {

            JSONObject questionsData = new JSONObject(Json);
            JSONArray results = questionsData.getJSONArray(RESULTS);
            questions = new String[results.length()];
            for(int i = 0; i <= results.length(); i++){
                JSONObject question = (JSONObject) results.get(i);
                questions[i] = question.optString(QUESTION);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


        return questions;
    }

}
