package com.example.drnearme;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.drnearme.Adapter.DateAdapter;
import com.example.drnearme.Adapter.TimeAdapter;
import com.example.drnearme.Domain.DoctorsModel;
import com.example.drnearme.databinding.ActivityDetailBinding;
import com.example.drnearme.databinding.ActivityMainBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private ActivityDetailBinding binding;
    private DoctorsModel items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        items = (DoctorsModel) getIntent().getSerializableExtra("object");

        setVariable();
        initdate();
        inittime();
    }

    private void inittime() {
        binding.timeview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.timeview.setAdapter(new TimeAdapter(generateTimeSlots()));
    }

    private void initdate() {
        binding.dateview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.dateview.setAdapter(new DateAdapter(generateDates()));
    }

    public static List<String> generateTimeSlots() {
        List<String> timeSlots = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        for (int i = 0; i < 24; i += 2) {
            LocalTime time = LocalTime.of(i, 0);
            timeSlots.add(time.format(formatter));
        }
        return timeSlots;
    }
    public static List<String> generateDates(){
        List<String> dates = new ArrayList<>();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE/dd/MMM");
        for (int i = 0; i < 7; i++){
            dates.add(today.plusDays(i).format(formatter));
        }
        return dates;
    }

    private void setVariable() {
        binding.backbtn.setOnClickListener(v -> finish());
        Glide.with(DetailActivity.this).load(items.getPicture()).into(binding.image);
        binding.addresstxt.setText(items.getAddress());
        binding.name.setText(items.getName());
        binding.special.setText(items.getSpecial());
        binding.patienTxt.setText(items.getPatients()+"");
        binding.year.setText(items.getExperience()+" Years");
    }
}