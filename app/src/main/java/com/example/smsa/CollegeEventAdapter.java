package com.example.smsa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CollegeEventAdapter extends RecyclerView.Adapter<CollegeEventAdapter.ViewHolder> {

    private List<CollegeEvent> eventList;

    public CollegeEventAdapter(List<CollegeEvent> eventList) {
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_college, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CollegeEvent event = eventList.get(position);
        holder.textViewEventName.setText(event.getEventName());
        holder.textViewEventDescription.setText(event.getEventDescription());
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewEventName;
        TextView textViewEventDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewEventName = itemView.findViewById(R.id.textViewEventName);
            textViewEventDescription = itemView.findViewById(R.id.textViewEventDescription);
        }
    }
}
