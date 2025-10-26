package com.example.drnearme.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.drnearme.DetailActivity;
import com.example.drnearme.Domain.DoctorsModel;
import com.example.drnearme.databinding.ViewholderTopDoctorsBinding;

import java.io.Serializable;
import java.util.List;

public class TopDoctorsAdapter extends RecyclerView.Adapter<TopDoctorsAdapter.ViewHolder> {
    private final List<DoctorsModel> items;
    private Context context;

    public TopDoctorsAdapter(List<DoctorsModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TopDoctorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        ViewholderTopDoctorsBinding binding = ViewholderTopDoctorsBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopDoctorsAdapter.ViewHolder holder, int position) {
        DoctorsModel doctorsModel = items.get(position);
        holder.binding.nametxt.setText(doctorsModel.getName());
        holder.binding.specialtxt.setText(doctorsModel.getSpecial());
        holder.binding.ratingtxt.setText(doctorsModel.getRating()+"");
        holder.binding.patiensTxt.setText(doctorsModel.getPatients()+" Years");
        Glide.with(holder.itemView.getContext()).load(doctorsModel.getPicture()).apply(new RequestOptions().transform(new CenterCrop())).into(holder.binding.img);

        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("object", doctorsModel);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderTopDoctorsBinding binding;
        public ViewHolder(ViewholderTopDoctorsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
