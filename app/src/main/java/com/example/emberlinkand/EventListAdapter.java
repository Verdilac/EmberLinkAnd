package com.example.emberlinkand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emberlinkand.DB.Event;

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
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);

        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, int position) {

        holder.tvOrganizerName.setText(this.eventList.get(position).organizerName);
        holder.tvEventName.setText(this.eventList.get(position).eventName);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView  tvOrganizerName;
        TextView  tvEventName;
        public MyViewHolder(View view){
            super(view);
              tvOrganizerName =view.findViewById(R.id.tvOrganizerName);
              tvEventName =view.findViewById(R.id.tvEventName);

        }
    }
}
