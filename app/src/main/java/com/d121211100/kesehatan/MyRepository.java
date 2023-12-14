package com.d121211100.kesehatan;

import java.util.ArrayList;
import java.util.List;

public class MyRepository {
    private List<String> dataList = new ArrayList<>();

    public List<String> fetchData() {

        return dataList;
    }


    public void saveData(String newData) {

        dataList.add(newData);
    }
}

