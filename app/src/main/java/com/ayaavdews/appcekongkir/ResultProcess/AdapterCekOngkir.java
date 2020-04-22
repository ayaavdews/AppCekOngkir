package com.ayaavdews.appcekongkir.ResultProcess;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayaavdews.appcekongkir.R;

import java.util.ArrayList;

public class AdapterCekOngkir extends BaseAdapter {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private Context context;
    private ArrayList<ModelListView> dataModelArrayList;

    public AdapterCekOngkir(Context context, ArrayList<ModelListView> dataModelArrayList) {

        this.context = context;
        this.dataModelArrayList = dataModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return dataModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.raw_result_layout, null, true);

            holder.result_kurir       = (TextView) convertView.findViewById(R.id.tvResutKurir);
            holder.result_code        = (TextView) convertView.findViewById(R.id.tvResutCode);
            holder.result_description = (TextView) convertView.findViewById(R.id.tvResutDescription);
            holder.result_value       = (TextView) convertView.findViewById(R.id.tvResutCost);
            holder.result_estimation  = (TextView) convertView.findViewById(R.id.tvResutEstimation);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        holder.result_kurir.setText(dataModelArrayList.get(position).getCode().toUpperCase());
        if(dataModelArrayList.get(position).getService().length() > 18){
            holder.result_code.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
            holder.result_code.setText(dataModelArrayList.get(position).getService());
        } else {
            holder.result_code.setText(dataModelArrayList.get(position).getService());
        }
        holder.result_description.setText(dataModelArrayList.get(position).getDescription());
        holder.result_value.setText("Rp. " +dataModelArrayList.get(position).getValue().toString()+",00");
        if(dataModelArrayList.get(position).getEtd().isEmpty()){
            holder.result_estimation.setText("- hari");
        } else {
            holder.result_estimation.setText(dataModelArrayList.get(position).getEtd()+ " hari");
        }

        return convertView;
    }

    private class ViewHolder{
        protected TextView
                result_code,
                result_value,
                result_kurir,
                result_estimation,
                result_description;
    }
}
