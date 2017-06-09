package com.example.mzeff.storiadate;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by azeff on 27/05/2017.
 */

public class EventiAdapter extends RecyclerView.Adapter<EventiAdapter.EventiViewHolder> {
    private ArrayList<Evento> eventi=new ArrayList<>();
    private final EventiAdapterOnClickHandler mClickHandler;


    public interface EventiAdapterOnClickHandler{
        void onClick(Evento evento);
    }

    public  class EventiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CardView cv;
        TextView data;
        TextView avvenimento;

        EventiViewHolder(View itemView){
            super (itemView);
            cv=(CardView) itemView.findViewById(R.id.card_view_item);
            data=(TextView) itemView.findViewById(R.id.data_item_textview);
            avvenimento=(TextView) itemView.findViewById(R.id.titolo_item_textview);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            Log.v("ONCLICK","CLICKED CLIEDK");
            int adapterPosition= getAdapterPosition();
            Evento evento=eventi.get(adapterPosition);
            mClickHandler.onClick(evento);
        }
    }

    EventiAdapter(ArrayList<Evento> ev,EventiAdapterOnClickHandler eClickHandler){
        this.eventi=ev;
        this.mClickHandler=eClickHandler;
    }
    public void eventiAdapterSetData(ArrayList<Evento> ev){
        this.eventi=ev;
    }

    @Override
    public int getItemCount() {
        return eventi.size();
    }

    @Override
    public EventiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.evento_item,parent,false);
        EventiViewHolder vh=new EventiViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventiViewHolder holder, int position) {
        String data=eventi.get(position).getAnno();

        holder.data.setText(data);
        holder.avvenimento.setText(eventi.get(position).getTitolo());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
