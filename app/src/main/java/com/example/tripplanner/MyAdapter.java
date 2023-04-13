package com.example.tripplanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Trip> tripList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView startLocationTextView;
        public TextView destinationTextView;

        public MyViewHolder(View view) {
            super(view);
            startLocationTextView = view.findViewById(R.id.start_location_textview);
            destinationTextView = view.findViewById(R.id.destination_textview);
        }
    }

    public MyAdapter(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.startLocationTextView.setText(trip.getStartLocation());
        holder.destinationTextView.setText(trip.getDestination());
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}
