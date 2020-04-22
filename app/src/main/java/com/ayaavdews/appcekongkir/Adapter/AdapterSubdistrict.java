package com.ayaavdews.appcekongkir.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ayaavdews.appcekongkir.Model.subdistrict.ModelSubdistrict;
import com.ayaavdews.appcekongkir.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Create By : Aya Avdews
 * XII RPL A / 17.006892
 * SMKN 2 Surakarta
 * */

public class AdapterSubdistrict extends ArrayAdapter<ModelSubdistrict> {

    private List<ModelSubdistrict> subdistrictList;
    private List<ModelSubdistrict> tempList;
    private List<ModelSubdistrict> suggestionList;


    public AdapterSubdistrict(@NonNull Context context, int resource, @NonNull List<ModelSubdistrict> objects) {
        super(context, resource, objects);

        subdistrictList = objects;
        tempList        = new ArrayList<>(subdistrictList);
        suggestionList  = new ArrayList<>();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_subdistrict_layout, parent, false);

        TextView textView = convertView.findViewById(R.id.simple_text);

        ModelSubdistrict subdistrict = subdistrictList.get(position);

        textView.setText(subdistrict.getSubdistrict_name() + ", " + subdistrict.getCity() + ", " + subdistrict.getProvince());

        return convertView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return userFilter;
    }

    Filter userFilter = new Filter() {

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            ModelSubdistrict subdistrict = (ModelSubdistrict) resultValue;

            return subdistrict.getSubdistrict_name() + ", " + subdistrict.getCity() + ", " + subdistrict.getProvince();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults filterResults = new FilterResults();

            if(constraint != null && constraint.length() > 0){
                suggestionList.clear();
                constraint = constraint.toString().trim().toLowerCase();

                for(ModelSubdistrict subdistrict : tempList){
                    if(     subdistrict.getSubdistrict_name().toLowerCase().contains(constraint) ||
                            subdistrict.getSubdistrict_id().toLowerCase().contains(constraint) ||
                            subdistrict.getProvince().toLowerCase().contains(constraint) ||
                            subdistrict.getCity().toLowerCase().contains(constraint)
                    ){
                        suggestionList.add(subdistrict);
                    }
                }

                filterResults.count = suggestionList.size();
                filterResults.values = suggestionList;
            }
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            ArrayList<ModelSubdistrict> uList = (ArrayList<ModelSubdistrict>) results.values;
            if(results != null && results.count > 0){
                clear();
                addAll(uList);
                notifyDataSetChanged();
            }
        }
    };
}
