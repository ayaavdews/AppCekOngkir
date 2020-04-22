package com.ayaavdews.appcekongkir.WaybillProcess;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ayaavdews.appcekongkir.R;

import java.util.ArrayList;

// Create By Aya Avdews
// SMKN 2 Surakarta
// XII RPL A

public class AdapterWaybill extends BaseAdapter {

    private Context context;
    private ArrayList<WaybillModel> waybillModelArrayList;

    public AdapterWaybill(Context context, ArrayList<WaybillModel> waybillModelArrayList){
        this.context = context;
        this.waybillModelArrayList = waybillModelArrayList;
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
        return waybillModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return waybillModelArrayList.get(position);
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
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.raw_waybill_layout, null, true);

            holder.waybillTime        = (TextView) convertView.findViewById(R.id.WaybillTime);
            holder.waybillLocation    = (TextView) convertView.findViewById(R.id.WaybillLocation);
            holder.waybillDescription = (TextView) convertView.findViewById(R.id.WaybillDescription);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.waybillTime.setText(waybillModelArrayList.get(position).getManifest_time() + " || " +waybillModelArrayList.get(position).getManifest_date());
        holder.waybillLocation.setText(waybillModelArrayList.get(position).getCity_name());
        holder.waybillDescription.setText(waybillModelArrayList.get(position).getManifest_description());

        return convertView;
    }

    private class ViewHolder {
        protected TextView waybillTime, waybillLocation, waybillDescription;
    }
}
