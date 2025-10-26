package com.example.drnearme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drnearme.R;
import com.example.drnearme.databinding.ViewholderDateBinding;
import com.example.drnearme.databinding.ViewholderTimeBinding;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.TimeViewHolder> {

    private final List<String> timeSlot;
    private int selectPosition = -1;
    private int lastSelectPosition = -1;

    public TimeAdapter(List<String> timeSlot) {
        this.timeSlot = timeSlot;
    }

    @NonNull
    @Override
    public TimeAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderTimeBinding binding = ViewholderTimeBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeAdapter.TimeViewHolder holder, int position) {
        holder.bind(timeSlot.get(position),position,this);
    }

    @Override
    public int getItemCount() {
        return timeSlot.size();
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderTimeBinding binding;

        public TimeViewHolder(ViewholderTimeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(String time, int position, TimeAdapter adapter){
            binding.timetxt.setText(time);
            if (adapter.selectPosition == position) {
                binding.timetxt.setBackgroundResource(R.drawable.blue_btn_bg);
                binding.timetxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.white));
            }else{
                binding.timetxt.setBackgroundResource(R.drawable.light_grey_bg);
                binding.timetxt.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(),R.color.black));
            }

                binding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.lastSelectPosition = adapter.selectPosition;
                        adapter.selectPosition = position;
                        adapter.notifyItemChanged(adapter.lastSelectPosition);
                        adapter.notifyItemChanged(adapter.selectPosition);
                    }
                });

        }
    }
}
