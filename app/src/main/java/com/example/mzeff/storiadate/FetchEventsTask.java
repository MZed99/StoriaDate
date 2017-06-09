package com.example.mzeff.storiadate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by azeff on 21/05/2017.
 */

public class FetchEventsTask extends AsyncTask<String,Void,ArrayList<Evento>> {


    @Override
    protected void onPreExecute() {
        super.onPreExecute();



    }

    @Override
    protected ArrayList<Evento> doInBackground(String... params) {
        return null;
    }
}
