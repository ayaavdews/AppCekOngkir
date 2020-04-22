package com.ayaavdews.appcekongkir.Fragment.CekResi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelCekResi extends ViewModel {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private MutableLiveData<String> mText;

    public ViewModelCekResi() {
        mText = new MutableLiveData<>();
        mText.setValue("This is cek Resi fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}