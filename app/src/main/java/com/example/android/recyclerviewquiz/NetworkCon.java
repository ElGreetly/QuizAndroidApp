package com.example.android.recyclerviewquiz;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by ElGreetly on 2/21/18.
 */

public class NetworkCon {
    final static String MAIN_URL = "https://opentdb.com/api.php";
    final static String QUESTIONS_AMOUNT = "amount";
    final static String CATEGORY = "category";
    final static String DIFFICULTY = "difficulty";
    final static String TYPE = "type";

    public static URL BuildUrl(String i) {
        Uri buildUri;
        if(i == "8"){
         buildUri = Uri.parse(MAIN_URL).buildUpon()
                .appendQueryParameter(QUESTIONS_AMOUNT, "10")
                .build();}
                else {
            buildUri = Uri.parse(MAIN_URL).buildUpon()
                .appendQueryParameter(QUESTIONS_AMOUNT, "10")
                .appendQueryParameter(CATEGORY, i).build();}
        URL url = null;
        try{
            url = new URL(buildUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponse(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream inputStream = urlConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter("\\A");
            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }
    }
}
