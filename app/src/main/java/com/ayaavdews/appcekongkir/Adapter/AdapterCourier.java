package com.ayaavdews.appcekongkir.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayaavdews.appcekongkir.Model.courier.ModelCourier;
import com.ayaavdews.appcekongkir.R;

import java.util.ArrayList;

/*
 * Create By : Aya Avdews
 * XII RPL A / 17.006892
 * SMKN 2 Surakarta
 * */

public class AdapterCourier extends ArrayAdapter<ModelCourier> {
    public AdapterCourier(@NonNull Context context, ArrayList<ModelCourier> courierList) {
        super(context, 0, courierList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_spinner_layout, parent, false);
        }
        ModelCourier modelCourier = getItem(position);

        ImageView spinnerImage = convertView.findViewById(R.id.ivCourierLogo);
        TextView spinnerText   = convertView.findViewById(R.id.tvCourierName);

        if(modelCourier != null) {
            spinnerImage.setImageResource(modelCourier.getSpinnerImage());
            spinnerText.setText(modelCourier.getSpinnerText());
        }

        return convertView;
    }
}
