package com.example.mzeff.storiadate;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by azeff on 21/05/2017.
 */

public class Evento implements Parcelable{
    private String anno;
    private String mese;
    private String giorno;
    private String annoF;
    private String meseF;
    private String giornoF;
    private String titolo;
    private String descrizione;
    private String urlImmagine;


    Evento(String a, String m, String g,String aF,String mF,String gF,String t,String d,String i){
        anno=a;
        mese=m;
        giorno=g;
        annoF=aF;
        meseF=mF;
        giornoF=gF;
        titolo=t;
        descrizione=d;
        urlImmagine=i;
    }

    public String getAnno() {
        return anno;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getGiorno() {
        return giorno;
    }

    public String getAnnoF() {
        return annoF;
    }

    public String getUrlImmagine() {
        return urlImmagine;
    }

    public String getMeseF() {
        return meseF;
    }

    public String getGiornoF() {
        return giornoF;
    }

    public String getMese() {
        return mese;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setGiorno(String giorno) {
        this.giorno = giorno;
    }

    public void setMese(String mese) {
        this.mese = mese;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setUrlImmagine(String urlImmagine) {
        this.urlImmagine = urlImmagine;
    }

    public Evento(Parcel in) {
        String[] data = new String[9];

        in.readStringArray(data);
        anno = data[0];
        mese = data[1];
        giorno = data[2];
        annoF = data[3];
        meseF = data[4];
        giornoF = data[5];
        titolo = data[6];
        descrizione = data[7];
        urlImmagine = data[8];


    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[]{anno,mese,giorno,annoF,meseF,giornoF, titolo,descrizione,urlImmagine});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Evento createFromParcel(Parcel in) {
            return new Evento(in);
        }

        public Evento[] newArray(int size) {
            return new Evento[size];
        }
    };
}

