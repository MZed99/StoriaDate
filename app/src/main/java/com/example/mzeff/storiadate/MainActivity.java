package com.example.mzeff.storiadate;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EventiAdapter.EventiAdapterOnClickHandler{
    public String stringUrl = "https://script.googleusercontent.com/macros/echo?user_content_key=YUm908_F4USanZMXYLM5SSNopIaGpE9lsPLN4Gd76Ev-ps1HtglllhSfOon8LijwbaCtE5yErItehXcvQml5MHPUIoB-gnlVOJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1ZsYSbt7G4nMhEEDL32U4DxjO7V7yvmJPXJTBuCiTGh3rUPjpYM_V0PJJG7TIaKp6DlhKiyTgeD37GVXNPmWi9BLr90SAXNavw2PD7IFU7Gool08_D5VdRirKpWCIA4qy6ynGhL6kv9iPAz3iOzAmo&lib=MbpKbbfePtAVndrs259dhPT7ROjQYJ8yx";
    public ProgressBar mLoadingIndicator;

    //Elements for building the url to json
    public String scriptBaseUrl = "https://script.google.com/macros/s/AKfycbygukdW3tt8sCPcFDlkMnMuNu9bH5fpt7bKV50p2bM/exec?id=";
    public String scriptSheetPage ="&sheet=";
    public String sheetKey="1meJ7ZxHvP8Oe4qFQxQ6l2CxXN38H2Q6Pnh7CZz96B-4";
    public String sheetPage="od1";

    public String urlGoogleDocs="https://docs.google.com/spreadsheets/d/";
    public String txtJsonToParse;
    private TextView anno;
    private TextView head;
    private TextView body;
    private RecyclerView recyclerView;
    private EventiAdapter eventiAdapter;
    private ArrayList<Evento> event;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoadingIndicator= (ProgressBar) findViewById(R.id.loading_indicator);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView=(RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        event=new ArrayList<>();

        eventiAdapter = new EventiAdapter(event,this);
        recyclerView.setAdapter(eventiAdapter);



        new FetchEvents().execute(scriptBaseUrl+sheetKey+scriptSheetPage+sheetPage);


    }


    @Override
    public void onClick(Evento evento) {
        Log.v("ON CLICK","CLICKED SDASDJA");
        Context context = this;
        Class classDestination = DetailActivity.class;
        Intent intentToStartEventoDetails = new Intent(context, classDestination);
        intentToStartEventoDetails.putExtra("event", evento);
        startActivity(intentToStartEventoDetails);
    }


    public class FetchEvents extends AsyncTask<String,Void,ArrayList<Evento>>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
            Log.v("ON PRE EXECUTE","finished on pre execute");
        }

        @Override
        protected ArrayList<Evento> doInBackground(String... params) {
            Uri uri = Uri.parse(params[0]).buildUpon().build();
            Log.v("DO IN BACKGROUND","Uri build: "+uri.toString());

            if (params.length == 0) {
                return null;
            }
            Log.v("DO IN BACKGROUND","READY TO PARSE TXT");
            try{
                txtJsonToParse=new JSONTask().JSONTask(uri.toString());
            }catch(Exception e){
                e.printStackTrace();
            }

            if(txtJsonToParse!=null){
                try{
                    ArrayList<Evento> eventi=new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(txtJsonToParse);
                    JSONArray listOfEvents = jsonObject.getJSONArray("od1");

                    for(int i=0;i<listOfEvents.length();i++){
                        JSONObject jsonObject1 = listOfEvents.getJSONObject(i);
                        String dataAnno=jsonObject1.getString("Year");
                        String dataMese=jsonObject1.getString("Month");
                        String dataGiorno=jsonObject1.getString("Day");
                        String annoFine = jsonObject1.getString("End_Year");
                        String meseFine = jsonObject1.getString("End_Month");
                        String giornoFine = jsonObject1.getString("End_Day");
                        String titolo=jsonObject1.getString("Headline");
                        String descrizione=jsonObject1.getString("Text");
                        String urlImmagine=jsonObject1.getString("Media");

                        eventi.add(new Evento(dataAnno,dataMese,dataGiorno,annoFine,meseFine,giornoFine,titolo,descrizione,urlImmagine));

                        Log.v("DO IN BACKGROUND LIST:",eventi.get(i).getTitolo().toString());
                    }
                    
                    return eventi;

                }catch(JSONException e){
                   e.printStackTrace();
                    return null;
                }

            }else {return null;}

        }

        @Override
        protected void onPostExecute(ArrayList<Evento> eventos) {
            super.onPostExecute(eventos);
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);

            Log.v("ON POST EXECUTE", "LAUNCHED");
            eventiAdapter.eventiAdapterSetData(eventos);
            recyclerView.setAdapter(eventiAdapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.resource,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();

        if(id==R.id.action_add){
            Intent i=new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(urlGoogleDocs+sheetKey));
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
