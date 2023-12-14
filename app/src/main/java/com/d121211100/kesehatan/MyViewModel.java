package com.d121211100.kesehatan;

import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private String hospital;

    public String getData() {
        return hospital;
    }

    public void setData(String newData) {
        hospital = newData;
    }
}