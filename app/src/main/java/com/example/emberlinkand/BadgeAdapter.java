package com.example.emberlinkand;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BadgeAdapter extends RecyclerView.Adapter<BadgeAdapter.BadgeViewHolder> {

    private List<Badge> badgeList;
    private Context context;

    public BadgeAdapter(List<Badge> badgeList, Context context) {
        this.badgeList = badgeList;
        this.context = context;
    }

    @NonNull
    @Override
    public BadgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.badge_card, parent, false);
        return new BadgeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BadgeViewHolder holder, int position) {
        Badge badge = badgeList.get(position);
        holder.imageView.setImageResource(badge.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return badgeList.size();
    }

    static class BadgeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        BadgeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
