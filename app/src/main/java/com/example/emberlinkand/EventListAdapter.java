package com.example.emberlinkand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emberlinkand.DB.Event;

import org.w3c.dom.Text;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {

    private Context context;
    private List<Event> eventList;

    public EventListAdapter(Context context){
        this.context = context;
    }

    public void setEventList(List<Event> eventList){
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EventListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_card_dash_board,parent,false);

        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, int position) {

        holder.eventName.setText(this.eventList.get(position).eventName);
        holder.eventLocation.setText(this.eventList.get(position).eventvenue);
        holder.eventDate.setText(this.eventList.get(position).eventTime);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView  eventName;
        TextView  eventLocation;
        TextView  eventDate;

        public MyViewHolder(View view){
            super(view);
            eventName =view.findViewById(R.id.eventName);
            eventLocation =view.findViewById(R.id.eventLocation);
            eventDate =view.findViewById(R.id.eventDate);
        }
    }
}
