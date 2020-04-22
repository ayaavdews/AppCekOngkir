package com.ayaavdews.appcekongkir.Fragment.CekOngkir;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelCekOngkir extends ViewModel {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private MutableLiveData<String> mText;

    public ViewModelCekOngkir() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cek Ongkir fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}