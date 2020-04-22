package com.ayaavdews.appcekongkir.Fragment.CekResi;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.ayaavdews.appcekongkir.Adapter.AdapterCourier;
import com.ayaavdews.appcekongkir.ApiService.ApiService;
import com.ayaavdews.appcekongkir.Model.courier.ModelCourier;
import com.ayaavdews.appcekongkir.Model.waybill.ItemWaybill;
import com.ayaavdews.appcekongkir.R;
import com.ayaavdews.appcekongkir.WaybillProcess.AdapterWaybill;
import com.ayaavdews.appcekongkir.WaybillProcess.WaybillModel;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentCekResi extends Fragment implements AdapterView.OnItemSelectedListener {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private ViewModelCekResi viewModelCekResi;
    private ProgressDialog progressDialog;
    private String courier = "", desc = "";
    private EditText etWaybillNumber;
    private Spinner spinner;
    private Button cek;
    ListView listView;
    Dialog dialog;

    private ImageView logo, arrowBack;
    private int waybillLogo = R.drawable.logo_default;
    private TextView waybillNumber, checkTime, status, kurir;

    private AdapterWaybill adapter_waybill;
    private ArrayList<WaybillModel> ListWaybill = new ArrayList<WaybillModel>();

    public View onCreateView(@NonNull LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        viewModelCekResi = ViewModelProviders.of(this).get(ViewModelCekResi.class);
        View root = inflater.inflate(R.layout.fragment_cek_resi, container, false);

        dialog = new Dialog(getActivity());

        etWaybillNumber = (EditText) root.findViewById(R.id.etNomorResi);
        spinner         = (Spinner) root.findViewById(R.id.spinnerCourier);
        cek             = (Button) root.findViewById(R.id.btnResi);

        // Create spinnerItemList for Spinner
        ArrayList<ModelCourier> courierList = new ArrayList<>();

        courierList.add(new ModelCourier("Pilih Kurir", "kosong", R.drawable.logo_default));
        courierList.add(new ModelCourier("JNE Express", "jne", R.drawable.logo_jne));
        courierList.add(new ModelCourier("JNT Express", "jnt", R.drawable.logo_jnt));
        courierList.add(new ModelCourier("JET Express", "jet", R.drawable.logo_jet));
        courierList.add(new ModelCourier("POS Indonesia", "pos", R.drawable.logo_pos));
        courierList.add(new ModelCourier("TIKI (Titipan Kilat)", "tiki", R.drawable.logo_tiki));
        courierList.add(new ModelCourier("NINJA Express", "ninja", R.drawable.logo_ninja));
        courierList.add(new ModelCourier("SICEPAT Express", "sicepat", R.drawable.logo_sicepat));
        courierList.add(new ModelCourier("WAHANA Logistic", "wahana", R.drawable.logo_wahana));

        // Create Adapter for spinner
        AdapterCourier adapterCourier = new AdapterCourier(getActivity(), courierList);

        if(spinner != null) {
            spinner.setAdapter(adapterCourier);
            spinner.setOnItemSelectedListener(this);
        }

        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                closeSoftKeyboard();
                return false;
            }
        });

        cek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String waybill = etWaybillNumber.getText().toString();

                if(waybill.equals("") || courier.equals("kosong")){
                    ToastEmpty();
                } else {
                    ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo.State internet = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
                    NetworkInfo.State wifi     = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();

                    if(internet == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTED){
                        getWaybill(waybill, courier);
                    } else {
                       ToastConnect();
                    }
                }
            }
        });

        return root;
    }

    private void getWaybill(final String waybill, String courier) {
        dialog.setContentView(R.layout.popup_cekresi);

        listView = dialog.findViewById(R.id.lvWaybill);

        ListWaybill.clear();
        adapter_waybill = new AdapterWaybill(getActivity(), ListWaybill);

        progressDialog = new ProgressDialog(getActivity(), R.style.MyAlertDialogStyle);
        progressDialog.setMessage("Please wait..");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<ItemWaybill> call = api.getWaybill(
                "3f3bce6f9e0d62d356f48cb8040b5653",
                waybill,
                courier
        );

        call.enqueue(new Callback<ItemWaybill>() {
            @Override
            public void onResponse(Call<ItemWaybill> call, Response<ItemWaybill> response) {
                progressDialog.dismiss();

                Log.v("Data Resi : ", "Waybill get : " + new Gson().toJson(response));

                if(response.isSuccessful()){
                    int statusCode = response.body().getRajaongkir().getStatus().getCode();

                    if(statusCode == 200){
                        Log.v("Status : ", "200");

                        final String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
                        final String time  = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());

                        logo          = dialog.findViewById(R.id.ivLogo);
                        arrowBack     = dialog.findViewById(R.id.Close);
                        waybillNumber = dialog.findViewById(R.id.WaybillNumber);
                        checkTime     = dialog.findViewById(R.id.WaybillTimeCheck);
                        status        = dialog.findViewById(R.id.WaybillStatus);
                        kurir         = dialog.findViewById(R.id.WaybillCourier);

                        logo.setImageResource(waybillLogo);
                        checkTime.setText(time + " || " + date);
                        waybillNumber.setText(response.body().getRajaongkir().getQuery().getWaybill());
                        status.setText(response.body().getRajaongkir().getResult().getSummary().getStatus());
                        kurir.setText(response.body().getRajaongkir().getResult().getSummary().getCourierName());
                        arrowBack.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
                        dialog.show();

                        int count_data = response.body().getRajaongkir().getResult().getManifest().size()-1;

                        ArrayList<WaybillModel> waybillModelArrayList = new ArrayList<>();
                        for(int i=count_data; i>=0; i--){
                            WaybillModel waybillModel = new WaybillModel();
                            waybillModel.setManifest_date(response.body().getRajaongkir().getResult().getManifest().get(i).getManifestDate());
                            waybillModel.setManifest_time(response.body().getRajaongkir().getResult().getManifest().get(i).getManifestTime());
                            waybillModel.setCity_name(response.body().getRajaongkir().getResult().getManifest().get(i).getCityName());
                            waybillModel.setManifest_description(response.body().getRajaongkir().getResult().getManifest().get(i).getManifestDescription());
                            waybillModelArrayList.add(waybillModel);
                        }
                        adapter_waybill = new AdapterWaybill(getActivity(), waybillModelArrayList);
                        listView.setAdapter(adapter_waybill);
                    } else {}
                } else {
                    int desc = response.code();
                    if(desc == 400){
                        ToastWaybill();
                    } else {
                        ToastUnindexError();
                    }
                }
            }

            @Override
            public void onFailure(Call<ItemWaybill> call, Throwable t) {

            }
        });



    }

    private void closeSoftKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void ToastConnect() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_connection, null);

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

    private void ToastWaybill() {
        LayoutInflater inflater = getLayoutInflater();
        View customToast = inflater.inflate(R.layout.toast_check_waybill, null);

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        ModelCourier modelCourier = (ModelCourier) adapterView.getSelectedItem();
        courier     = modelCourier.getSpinnerID();
        waybillLogo =  modelCourier.getSpinnerImage();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}