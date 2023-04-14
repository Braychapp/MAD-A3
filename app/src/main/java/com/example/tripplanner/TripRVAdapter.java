package com.example.tripplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// Taken from https://www.geeksforgeeks.org/how-to-read-data-from-sqlite-database-in-android/
public class TripRVAdapter extends RecyclerView.Adapter<TripRVAdapter.ViewHolder> {
    // array list variable and context variable
    private ArrayList<TripModal> tripModalArrayList;
    private Context context;

    // constructor for class
    public TripRVAdapter(ArrayList<TripModal> tripModalArrayList, Context context){
        this.tripModalArrayList = tripModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        // inflate layout file for recycler view items
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trip_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        // setting data for views of recycler view item
        TripModal modal = tripModalArrayList.get(position);
        holder.tripStartTV.setText(modal.getTripStart());
        holder.tripEndTV.setText(modal.getTripEnd());
        holder.tripDateTV.setText(modal.getTripDate());
        holder.tripMethodTV.setText(modal.getTripMethod());
    }

    @Override
    public int getItemCount(){
        // returns the size of the array
        return tripModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // variables for TextView display
        private TextView tripStartTV, tripEndTV, tripDateTV, tripMethodTV;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            // initialize the text views with trip data
            tripStartTV = itemView.findViewById(R.id.idTVTripStart);
            tripEndTV = itemView.findViewById(R.id.idTVTripEnd);
            tripDateTV = itemView.findViewById(R.id.idTVTripDate);
            tripMethodTV = itemView.findViewById(R.id.idTVTripMethod);
        }
    }
}
