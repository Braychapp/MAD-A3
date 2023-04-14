package com.example.tripplanner;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.util.Log;
import android.widget.Toast;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    private List<Trip> tripList;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView startLocationTextView;
        private TextView endLocationTextView;
        private TextView dateTextView;
        private EditText name;

        public ViewHolder(View view) {
            super(view);
            startLocationTextView = view.findViewById(R.id.start_location_textview);
            endLocationTextView = view.findViewById(R.id.end_location_textview);
            dateTextView = view.findViewById(R.id.date_textview);
            name = view.findViewById(R.id.username);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            EditText editText = view.getRootView().findViewById(R.id.username);
            String username = editText.getText().toString();
            if(!username.isEmpty()) {
                Trip trip = tripList.get(getAdapterPosition());
                Intent intent = new Intent(view.getContext(), DateActivity.class);
                intent.putExtra("start_location", trip.getStartLocation());
                intent.putExtra("end_location", trip.getEndLocation());
                intent.putExtra("date", trip.getDate());
                Global.startLocation = trip.getStartLocation();
                Global.endLocation = trip.getEndLocation();
                Global.username = username;
                //if they put in their name then this happens
                Log.i(Global.MTAG, "Trip selected, Opening DateActivity");
                view.getContext().startActivity(intent);
            }
            else {
                Toast.makeText(view.getContext(), "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public TripAdapter(List<Trip> tripList) {
        this.tripList = tripList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_list_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Trip trip = tripList.get(position);
        holder.startLocationTextView.setText(trip.getStartLocation());
        holder.endLocationTextView.setText(trip.getEndLocation());
        holder.dateTextView.setText(trip.getDate());
    }

    @Override
    public int getItemCount() {
        return tripList.size();
    }
}
