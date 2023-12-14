package com.d121211100.kesehatan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText cari;
    private RecyclerView recyclerView;
    private HospitalAdapter adapter;
    private List<Hospital> allHospitals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cari = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        allHospitals = new ArrayList<>();


        adapter = new HospitalAdapter();


        recyclerView.setAdapter(adapter);

        ApiService apiService = RetrofitClient.getApiService();
        Call<List<Hospital>> call = apiService.getHospitals();

        call.enqueue(new Callback<List<Hospital>>() {
            @Override
            public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Hospital> hospitals = response.body();
                    allHospitals.addAll(hospitals);
                    adapter.setData(hospitals);
                }
            }

            @Override
            public void onFailure(Call<List<Hospital>> call, Throwable t) {

            }
        });


        cari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                filterData(editable.toString());
            }
        });

        // Pastikan adapter tidak null sebelum memanggil setOnItemClickListener
        if (adapter != null) {
            // Menambahkan onClickListener untuk menangani klik item di RecyclerView
            adapter.setOnItemClickListener(new HospitalAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Hospital hospital) {
                    // Buka DetailActivity dan kirim data rumah sakit
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("hospital", hospital);
                    startActivity(intent);
                }
            });
        }
    }

    // Metode untuk memfilter data di RecyclerView berdasarkan teks yang dimasukkan
    private void filterData(String query) {
        List<Hospital> filteredList = new ArrayList<>();

        for (Hospital hospital : allHospitals) {
            if (hospital.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(hospital);
            }
        }

        adapter.setData(filteredList);
    }
}