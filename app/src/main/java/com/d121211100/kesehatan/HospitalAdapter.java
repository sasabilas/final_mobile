package com.d121211100.kesehatan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {
    private List<Hospital> hospitals;
    private OnItemClickListener onItemClickListener;

    public HospitalAdapter() {
    }

    public void setData(List<Hospital> hospitals) {
        this.hospitals = hospitals;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public HospitalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalViewHolder holder, int position) {
        if (hospitals != null) {
            Hospital hospital = hospitals.get(position);
            holder.bind(hospital);
        }
    }

    @Override
    public int getItemCount() {
        return hospitals != null ? hospitals.size() : 0;
    }

    public interface OnItemClickListener {
        void onItemClick(Hospital hospital);
    }

    public class HospitalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameTextView;
        private TextView addressTextView;

        public HospitalViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);

            itemView.setOnClickListener(this);
        }

        public void bind(Hospital hospital) {
            nameTextView.setText(hospital.getName());
            addressTextView.setText(hospital.getAddress());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && onItemClickListener != null) {
                onItemClickListener.onItemClick(hospitals.get(position));
            }
        }
    }
}
