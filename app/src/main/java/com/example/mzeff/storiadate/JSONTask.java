package com.example.mzeff.storiadate;

import android.app.DownloadManager;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by azeff on 20/05/2017.
 */

public class JSONTask {

    OkHttpClient client = new OkHttpClient();

    String JSONTask(String url) throws IOException {
                Request request= new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();
        Log.v("JSONTask string",response.body().toString());
        return response.body().string();
    }
}
