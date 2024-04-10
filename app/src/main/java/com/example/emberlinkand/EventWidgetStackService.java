package com.example.emberlinkand;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.emberlinkand.DB.Event;
import com.example.emberlinkand.DB.EventViewModel;


public class EventWidgetStackService extends RemoteViewsService {

    private static final String TAG = "EventListActivity";

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new EventWidgetStackViewFactory(this.getApplicationContext(), intent);
    }
}

class EventWidgetStackViewFactory implements RemoteViewsService.RemoteViewsFactory, ViewModelStoreOwner {

    private EventListAdapter eventListAdapter;
    private EventViewModel eventViewModel;

    private static final String TAG = "EventWidgetStackViewFactory";


    public Context context;
    LiveData<Event> lastEvent;
    public EventWidgetStackViewFactory(Context context,  Intent intent) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        lastEvent = eventViewModel.getLastEvent();
        Log.d(TAG, "Event list size: " + lastEvent);
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {

        Intent eventIntent = new Intent();
        eventIntent.putExtra("eventName", lastEvent.getValue().eventName);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.event_widget);
        remoteViews.setTextViewText(R.id.eventNameTextView, lastEvent.getValue().eventName);
        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return null;
    }
}
