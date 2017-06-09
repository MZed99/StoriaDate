package com.example.mzeff.storiadate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by azeff on 27/05/2017.
 */

public class DetailActivity extends AppCompatActivity {
    private Evento evento;
    private TextView dataInizio;
    private TextView dataFine;
    private TextView heading;
    private TextView descrizione;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intentThatStarted=getIntent();
        evento=intentThatStarted.getExtras().getParcelable("event");

        dataInizio=(TextView) findViewById(R.id.data_inizio_tv);
        dataFine=(TextView) findViewById(R.id.data_fine_tv);
        heading=(TextView) findViewById(R.id.heading_tv);
        descrizione=(TextView) findViewById(R.id.descrizione_tv);
        imageView = (ImageView) findViewById(R.id.iv_detailActivity);

        Log.v("Detail activity mese: ",evento.getMese());
        Picasso.with(this).load(evento.getUrlImmagine()).centerInside().fit().into(imageView);
        setDataInizio(evento);
        setDataFine(evento);
        heading.setText(evento.getTitolo());
        descrizione.setText(evento.getDescrizione());

    }

    private void setDataInizio(Evento evento){
        String data;
        if(evento.getMese().isEmpty()){
            data=evento.getAnno();
        }else if(!(evento.getMese().isEmpty()) && evento.getGiorno().isEmpty()){
            data=evento.getMese();
            data=data.concat("-").concat(evento.getAnno());
        }else {
            data=evento.getGiorno();
            data=data.concat("-").concat(evento.getMese()).concat("-").concat(evento.getAnno());
        }
        dataInizio.setText(data);
    }
    private void setDataFine(Evento evento){
        String data;
        if(evento.getMeseF().isEmpty()){
            data=evento.getAnnoF();
        }else if(!(evento.getMeseF().isEmpty()) && evento.getGiornoF().isEmpty()){
            data=evento.getMeseF();
            data=data.concat("-").concat(evento.getAnnoF());
        }else {
            data=evento.getGiornoF();
            data=data.concat("-").concat(evento.getMeseF()).concat("-").concat(evento.getAnnoF());
        }
        dataFine.setText(data);

    }
}
