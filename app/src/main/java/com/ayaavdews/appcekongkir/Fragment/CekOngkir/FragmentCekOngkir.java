package com.ayaavdews.appcekongkir.Fragment.CekOngkir;

import android.app.Dialog;
import android.app.ProgressDialog;

import android.content.Context;
import android.content.DialogInterface;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;

import com.ayaavdews.appcekongkir.Model.subdistrict.ModelSubdistrict;
import com.ayaavdews.appcekongkir.ResultProcess.AdapterCekOngkir;
import com.ayaavdews.appcekongkir.ResultProcess.ModelListView;
import com.ayaavdews.appcekongkir.Adapter.AdapterSubdistrict;
import com.ayaavdews.appcekongkir.ApiService.ApiService;
import com.ayaavdews.appcekongkir.Model.cost.ItemCost;
import com.ayaavdews.appcekongkir.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentCekOngkir extends Fragment{

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private ArrayList<Integer> mUserItems = new ArrayList<>();
    private ArrayList<ModelSubdistrict> subdistrictList;
    private ViewModelCekOngkir viewModelCekOngkir;
    private ProgressDialog progressDialog;
    private AutoCompleteTextView Tujuan;
    private AutoCompleteTextView Asal;
    private boolean[] checkedItems;
    private String[] listItems;
    private ListView listView;
    private Button process;
    Dialog dialog;

    private AdapterCekOngkir adapter_result;
    private ArrayList<ModelListView> ListResult = new ArrayList<ModelListView>();

    private ImageView arrowBack;
    private EditText etCourier, etWeight;
    private TextView ResultOrigin, ResultDestination, ResultWeight, ResultTime;
    private String origin, originType, destination, destinationType, weight, courier;

    final String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
    final String time = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModelCekOngkir = ViewModelProviders.of(this).get(ViewModelCekOngkir.class);
        final View root    = inflater.inflate(R.layout.fragment_cek_ongkir, container, false);

        dialog = new Dialog(getActivity());

        Tujuan    = root.findViewById(R.id.lokasitujuan);
        Asal      = root.findViewById(R.id.lokasiasal);
        etCourier = root.findViewById(R.id.etCourier);
        etWeight  = root.findViewById(R.id.etWeight);
        process   = root.findViewById(R.id.process);

        subdistrictList = new ArrayList<>();
        subdistrictList = extractUser();

        AdapterSubdistrict subdistrictAdapter = new AdapterSubdistrict(getActivity(), R.layout.raw_subdistrict_layout, subdistrictList);

        Tujuan.setThreshold(2);
        Tujuan.setValidator(new Validator());
        Tujuan.setAdapter(subdistrictAdapter);
        Tujuan.setOnFocusChangeListener(new FocusListenerTujuan());

        Asal.setThreshold(2);
        Asal.setValidator(new Validator());
        Asal.setAdapter(subdistrictAdapter);
        Asal.setOnFocusChangeListener(new FocusListenerAsal());

        listItems    = getResources().getStringArray(R.array.courier_list);
        checkedItems = new boolean[listItems.length];

        DefaultChecked();

        Asal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ModelSubdistrict subdistrict = (ModelSubdistrict) parent.getItemAtPosition(position);

                Log.v("ID Subdistrict 1 = ", subdistrict.getSubdistrict_id());
                Asal.setTag(subdistrict.getSubdistrict_id());
            }
        });

        Tujuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                ModelSubdistrict subdistrict = (ModelSubdistrict) parent.getItemAtPosition(position);

                Log.v("ID Subdistrict 2 = ", subdistrict.getSubdistrict_id());
                Tujuan.setTag(subdistrict.getSubdistrict_id());
            }
        });

        etCourier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupCourier();
            }
        });

        process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo.State internet = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                NetworkInfo.State wifi     = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

                if(internet == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED) {

                    String pAsal    = Asal.getText().toString();
                    String pTujuan  = Tujuan.getText().toString();
                    String pWeight  = etWeight.getText().toString();
                    String pCourier = etCourier.getText().toString();

                    if(pAsal.equals("") || pTujuan.equals("") || pWeight.equals("") || pCourier.equals("")) {
                        ToastEmpty();
                    } else {
                        int weightvalid = Integer.parseInt(pWeight);
                        if(weightvalid > 30000) {
                            ToastWeight();
                            etWeight.requestFocus();
                            etWeight.setText("");
                            showSoftKeyboard();
                        } else {
                            origin          = (String) Asal.getTag();
                            originType      = getResources().getString(R.string.type);
                            destination     = (String) Tujuan.getTag();
                            destinationType = getResources().getString(R.string.type);
                            weight          = etWeight.getText().toString();
                            courier         = splitListCourier();

                            closeSoftKeyboard();
                            getCost(origin, originType, destination, destinationType, weight, courier);
                        }
                    }
                } else {
                    ToastConnect();
                }
            }
        });

        return root;
    }

    private void getCost(final String origin, String originType, String destination, String destinationType, String weight, String courier) {
        dialog.setContentView(R.layout.popup_cekongkir);

        listView = (ListView) dialog.findViewById(R.id.lvResult);

        ListResult.clear();
        adapter_result = new AdapterCekOngkir(getActivity(), ListResult);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Please wait");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<ItemCost> call = api.getCost(
                "3f3bce6f9e0d62d356f48cb8040b5653",
                origin,
                originType,
                destination,
                destinationType,
                weight,
                courier);

        call.enqueue(new Callback<ItemCost>() {
            @Override
            public void onResponse(Call<ItemCost> call, Response<ItemCost> response) {

                progressDialog.dismiss();

                Log.v("Data Ongkir", "Cost get : " + new Gson().toJson(response));

                if(response.isSuccessful()){
                    if(response.body() != null){
                        int statusCode = response.body().getRajaongkir().getStatus().getCode();

                        if(statusCode == 200){
                            Log.v("Status : ", "200");

                            String response1, response2, response3, response4, response5,response6;

                            response1 = response.body().getRajaongkir().getOriginDetails().getSubdistrictName();
                            response2 = response.body().getRajaongkir().getOriginDetails().getCity();
                            response3 = response.body().getRajaongkir().getOriginDetails().getProvince();

                            response4 = response.body().getRajaongkir().getDestinationDetails().getSubdistrictName();
                            response5 = response.body().getRajaongkir().getDestinationDetails().getCity();
                            response6 = response.body().getRajaongkir().getDestinationDetails().getProvince();

                            float intWeight   = Integer.parseInt(etWeight.getText().toString());
                            float finalWeight = intWeight/1000;

                            arrowBack             = dialog.findViewById(R.id.Close);
                            ResultTime            = dialog.findViewById(R.id.ResultTime);
                            ResultWeight          = dialog.findViewById(R.id.ResultWeight);
                            ResultOrigin          = dialog.findViewById(R.id.ResultOrigin);
                            ResultDestination     = dialog.findViewById(R.id.ResultDestination);

                            ResultOrigin.setSelected(true);
                            ResultDestination.setSelected(true);

                            arrowBack.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    dialog.dismiss();
                                }
                            });

                            ResultTime.setText(time + " || " + date);
                            ResultWeight.setText(String.valueOf(finalWeight + " KG"));
                            ResultOrigin.setText(response1 + ", " + response2 + ", " + response3);
                            ResultDestination.setText(response4 + ", " + response5 + ", " + response6);

                            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                            dialog.show();

                            int count_data1 = response.body().getRajaongkir().getResults().size();

                            ArrayList<ModelListView> modelListViewArrayList = new ArrayList<>();
                            for(int i=0; i<count_data1; i++){
                                for (int j=0; j < response.body().getRajaongkir().getResults().get(i).getCosts().size(); j++ ){
                                    for(int k=0; k<response.body().getRajaongkir().getResults().get(i).getCosts().get(j).getCost().size(); k++){
                                        ModelListView modelListView = new ModelListView();
                                        modelListView.setCode(response.body().getRajaongkir().getResults().get(i).getCode());
                                        modelListView.setService(response.body().getRajaongkir().getResults().get(i).getCosts().get(j).getService());
                                        modelListView.setDescription(response.body().getRajaongkir().getResults().get(i).getCosts().get(j).getDescription());
                                        modelListView.setValue(response.body().getRajaongkir().getResults().get(i).getCosts().get(j).getCost().get(k).getValue());
                                        modelListView.setEtd(response.body().getRajaongkir().getResults().get(i).getCosts().get(j).getCost().get(k).getEtd());
                                        modelListViewArrayList.add(modelListView);
                                    }
                                }
                            }
                            Log.v("Perulangan", "success");
                            adapter_result = new AdapterCekOngkir(getActivity(), modelListViewArrayList);
                            listView.setAdapter(adapter_result);
                        }
                        else if(statusCode == 400){
                            ToastLimit();
                        }
                    }
                    else{
                        ToastUnindexError();
                    }
                }
                else{
                    ToastLimit();
                }
            }

            @Override
            public void onFailure(Call<ItemCost> call, Throwable t) {

            }
        });
    }

    private void ToastEmptyCourier() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_empty_courier, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastUnindexError() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_unindex_error, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastValidate() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_validate_autocomplete, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastConnect() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_connection, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastWeight() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_weight, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastEmpty() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_empty, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    private void ToastLimit() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_limit_key, null);

        Toast toast = Toast.makeText(customToast.getContext(), "", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL,0, 0);
        toast.setView(customToast);
        toast.show();
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("cache.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void showSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etWeight, InputMethodManager.SHOW_IMPLICIT);
    }

    private void closeSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void popupCourier() {
        closeSoftKeyboard();
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialog);
        mBuilder.setTitle("Select Your Courier");

        mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                if(isChecked){
                    if(!mUserItems.contains(position)){
                        mUserItems.add(position);
                    }
                }

                else if(mUserItems.contains(position)){
                    mUserItems.remove((Integer) position);
                }
            }
        });
        mBuilder.setCancelable(false);

        mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                if(mUserItems.size() == 0){
                    ToastEmptyCourier();
                    closeSoftKeyboard();
                    AlertDialog mDialog = mBuilder.create();
                    mDialog.show();
                } else {
                    String item = "";
                    for(int i=0; i<mUserItems.size(); i++){
                        item = item + listItems[mUserItems.get(i)];
                        if(i != mUserItems.size() -1){
                            item = item + ", ";
                        }
                    }
                    etCourier.setText(item);
                }
            }
        });

        mBuilder.setNeutralButton("Select All", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {

                mUserItems.clear();
                for(int i=0; i<checkedItems.length; i++){
                    checkedItems[i] = true;
                    mUserItems.add(i);
                }
                String item = "";
                for(int i=0; i<mUserItems.size(); i++){
                    item = item + listItems[mUserItems.get(i)];
                    if(i != mUserItems.size() -1){
                        item = item + ", ";
                    }
                }
                etCourier.setText(item);
            }
        });

        mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(mUserItems.size() == 0){
                    DefaultChecked();
                    dialogInterface.dismiss();
                } else {
                    dialogInterface.dismiss();
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void DefaultChecked() {
        checkedItems[0] = true;
        checkedItems[1] = true;
        checkedItems[2] = true;
        checkedItems[4] = true;

        mUserItems.add(0);
        mUserItems.add(1);
        mUserItems.add(2);
        mUserItems.add(4);

        String item = "";
        for(int i=0; i<mUserItems.size(); i++){
            item = item + listItems[mUserItems.get(i)];
            if(i != mUserItems.size() -1){
                item = item + ", ";
            }
        }
        etCourier.setText(item);
    }

    private String splitListCourier() {
        // Method yang digunakan untuk mengubah isi pada EditText Courier menjadi lowerCase()
        // Menjadikan String dengan cara split(pisah) dan append(gabung) dengan parameter ":"
        // Mengirim ke method cek Ongkir untuk digunakan sebagai parameter untuk getApi
        String courier = etCourier.getText().toString();

        String[] courierlist = courier.split(", ");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < courierlist.length; i++) {
            sb.append(courierlist[i]);
            if (i != courierlist.length - 1) {
                sb.append(":");
            }
        }
        String courierList = sb.toString();

        return courierList.toLowerCase();
    }

    private CharSequence messageError() {
        ToastValidate();
        return "";
    }

    private ArrayList<String> ValidationInput() {
        ArrayList<String> list = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray jar  = obj.getJSONArray("data_detail");

            for(int i =0; i<jar.length(); i++){
                JSONObject jo = jar.getJSONObject(i);

                String subdistrict = jo.getString("subdistrict_name");
                String city        = jo.getString("city");
                String province    = jo.getString("province");

                String model = subdistrict + ", " + city + ", " + province;
                list.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    private ArrayList<ModelSubdistrict> extractUser() {
        ArrayList<ModelSubdistrict> list = new ArrayList<>();

        try {
            JSONObject obj = new JSONObject(readJSONFromAsset());
            JSONArray jar  = obj.getJSONArray("data_detail");

            for(int i =0; i<jar.length(); i++){
                JSONObject jo = jar.getJSONObject(i);

                String subdistrict_name = jo.getString("subdistrict_name");
                String subdistrict_id   = jo.getString("subdistrict_id");
                String province         = jo.getString("province");
                String city             = jo.getString("city");

                ModelSubdistrict subdistrict = new ModelSubdistrict(subdistrict_name, subdistrict_id, province, city);
                list.add(subdistrict);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    class Validator implements AutoCompleteTextView.Validator{

        @Override
        public boolean isValid(CharSequence text) {
            ArrayList ValidWords = ValidationInput();

            String[] validWord = new String[ValidWords.size()];
            validWord = (String[]) ValidWords.toArray(validWord);

            Arrays.sort(validWord);
            if (Arrays.binarySearch(validWord, text.toString()) > 0) {
                return true;
            }
            return false;
        }

        @Override
        public CharSequence fixText(CharSequence charSequence) {
            return messageError();
        }
    }

    class FocusListenerAsal implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            if(view.getId() == R.id.lokasiasal && !b){
                ((AutoCompleteTextView)view).performValidation();
            }
        }
    }

    class FocusListenerTujuan implements View.OnFocusChangeListener{

        @Override
        public void onFocusChange(View view, boolean b) {
            if(view.getId() == R.id.lokasitujuan && !b){
                ((AutoCompleteTextView)view).performValidation();
            }
        }
    }
}
