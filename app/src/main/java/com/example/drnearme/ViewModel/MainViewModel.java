package com.example.drnearme.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.drnearme.Domain.CategoryModel;
import com.example.drnearme.Domain.DoctorsModel;
import com.example.drnearme.Repository.MainRepositry;

import java.util.List;

public class MainViewModel extends ViewModel {
    private final MainRepositry repositry;

    public MainViewModel() {
        this.repositry = new MainRepositry();
    }

    public LiveData<List<CategoryModel>> loadCategory(){
        return repositry.loadCategory();
    }

    public LiveData<List<DoctorsModel>> loadDoctors(){
        return repositry.load();
    }
}
