package com.example.counterviewmodelapp;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private int counter = 0;
    private MutableLiveData<Integer> countLiveData = new MutableLiveData<>();

    public MutableLiveData<Integer> oneTimeOfInitial() {
        countLiveData.setValue(counter);
        return countLiveData;

    }

    public void onIncreament() {
        counter++;
        countLiveData.setValue(counter);

    }

}
