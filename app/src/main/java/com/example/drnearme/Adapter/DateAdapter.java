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

import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.TimeViewHolder> {

    private final List<String> timeSlot;
    private int selectPosition = -1;
    private int lastSelectPosition = -1;

    public DateAdapter(List<String> timeSlot) {
        this.timeSlot = timeSlot;
    }

    @NonNull
    @Override
    public DateAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewholderDateBinding binding = ViewholderDateBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new TimeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DateAdapter.TimeViewHolder holder, int position) {
        holder.bind(timeSlot.get(position),position,this);
    }

    @Override
    public int getItemCount() {
        return timeSlot.size();
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderDateBinding binding;

        public TimeViewHolder(ViewholderDateBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(String date, int position, DateAdapter adapter){
            String[] dateParts = date.split("/");
            if(dateParts.length == 3){
                binding.daytxt.setText(dateParts[0]);
                binding.dayMonthtxt.setText(dateParts[1] +" " +dateParts[2]);

                Context context = binding.getRoot().getContext();
                if(adapter.selectPosition==position){
                    binding.mainLayout.setBackgroundResource(R.drawable.blue_btn_bg);
                    binding.daytxt.setTextColor(ContextCompat.getColor(context,R.color.white));
                    binding.dayMonthtxt.setTextColor(ContextCompat.getColor(context,R.color.white));
                }else{
                    binding.mainLayout.setBackgroundResource(R.drawable.light_grey_bg);
                    binding.daytxt.setTextColor(ContextCompat.getColor(context, R.color.black));
                    binding.dayMonthtxt.setTextColor(ContextCompat.getColor(context,R.color.black));
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
}
