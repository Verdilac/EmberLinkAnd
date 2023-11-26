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
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent,false);

        return  new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, int position) {

        holder.tvOrganizerName.setText(this.eventList.get(position).organizerName);
       holder.tvEventName.setText(this.eventList.get(position).eventName);
        holder.tvEventParticipationLimit.setText(this.eventList.get(position).participantLimit);
        holder.tvEventTime.setText(this.eventList.get(position).eventTime);
        holder.tvEventVenue.setText(this.eventList.get(position).eventvenue);
        holder.tvEventDescription.setText(this.eventList.get(position).eventDescription);
        holder.tvEventTag.setText(this.eventList.get(position).eventtag);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView  tvOrganizerName;
        TextView  tvEventName;

        TextView  tvEventParticipationLimit;
        TextView  tvEventTime;
        TextView  tvEventVenue;
        TextView  tvEventDescription;

        TextView tvEventTag;


        public MyViewHolder(View view){
            super(view);
              tvOrganizerName =view.findViewById(R.id.tvOrganizerName);
             tvEventName =view.findViewById(R.id.tvEventName);
            tvEventParticipationLimit =view.findViewById(R.id.tvEventParticipationLimit);
            tvEventTime =view.findViewById(R.id.tvEventTime);
            tvEventVenue =view.findViewById(R.id.tvEventVenue);
            tvEventDescription =view.findViewById(R.id.tvEventDescription);
            tvEventTag =view.findViewById(R.id.tvEventTag);


        }
    }
}
