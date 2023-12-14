package com.d121211100.kesehatan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView addressTextView;
    private TextView regionTextView;
    private TextView phoneTextView;
    private TextView provinceTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);


        nameTextView = findViewById(R.id.nameTextView);
        addressTextView = findViewById(R.id.addressTextView);
        regionTextView = findViewById(R.id.regionTextView);
        phoneTextView = findViewById(R.id.phoneTextView);
        provinceTextView = findViewById(R.id.provinceTextView);

        // Ambil data dari Intent
        Hospital hospital = getIntent().getParcelableExtra("hospital");

        // Tampilkan data di UI
        if (hospital != null) {
            nameTextView.setText(hospital.getName());
            addressTextView.setText(hospital.getAddress());
            regionTextView.setText(hospital.getRegion());
            phoneTextView.setText(hospital.getPhone());
            provinceTextView.setText(hospital.getProvince());
        }
    }
}
