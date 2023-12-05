package com.example.emberlinkand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.emberlinkand.DB.Event;

import org.w3c.dom.Text;

import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.MyViewHolder> {

    private Context context;
    private List<Event> eventList;

    private final EventListItemInterface eventListItemInterface;

    public EventListAdapter(Context context, EventListItemInterface eventListItemInterface){
        this.context = context;
        this.eventListItemInterface = eventListItemInterface;
    }

    public void setEventList(List<Event> eventList){
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    public int getEventId(int position) {
        Event current = this.eventList.get(position);
        return current.uid;
    }

    @NonNull
    @Override
    public EventListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.event_card_dash_board,parent,false);

        return  new MyViewHolder(view, this.eventListItemInterface);

    }

    @Override
    public void onBindViewHolder(@NonNull EventListAdapter.MyViewHolder holder, int position) {

        holder.eventName.setText(this.eventList.get(position).eventName);
        holder.eventLocation.setText(this.eventList.get(position).eventvenue);
        holder.eventDate.setText(this.eventList.get(position).eventTime);
    }

    @Override
    public int getItemCount() {
        if (this.eventList == null) {
            return 0;
        }

        return this.eventList.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder{

        TextView  eventName;
        TextView  eventLocation;
        TextView  eventDate;
        Button detailsButton;

        public MyViewHolder(View view, EventListItemInterface eventListItemInterface){
            super(view);
            eventName =view.findViewById(R.id.eventName);
            eventLocation =view.findViewById(R.id.eventLocation);
            eventDate =view.findViewById(R.id.eventDate);
            detailsButton = itemView.findViewById(R.id.moreDetailsBtn);

            detailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (eventListItemInterface != null) {
                        int position = getBindingAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            eventListItemInterface.onEventDetailsClick(position);
                        }
                    }
                }
            });
        }
    }
}
