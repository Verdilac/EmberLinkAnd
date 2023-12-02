//package com.example.emberlinkand;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//
//import java.util.ArrayList;
//
//public class DashboardEventListAdaptor extends RecyclerView.Adapter<DashboardEventListAdaptor.ViewHolder> {
//
//    ArrayList<DashBoardEventList> dashBoardEventLists;
//
//    public DashboardEventListAdaptor(ArrayList<DashBoardEventList> dashBoardEventLists) {
//        this.dashBoardEventLists = dashBoardEventLists;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder, parent, false);
//        return new ViewHolder(inflate);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.feederName.setText(String.valueOf(dashBoardEventLists.get(position).getTitle()));
//
//        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(dashBoardEventLists.get(position).getUrl(), "drawable", holder.itemView.getContext().getPackageName());
//
////        Glide.with(holder.itemView.getContext())
////                .load(drawableResourceId)
////                .into(holder.removeItem);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return dashBoardEventLists.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        TextView feederName;
//        ImageView removeItem;
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            feederName = itemView.findViewById(R.id.dashboardlisteventname);
//            removeItem = itemView.findViewById(R.id.dashboardlisteventdescription);
//        }
//    }
//}