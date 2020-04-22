package com.ayaavdews.appcekongkir.Fragment.Info;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelInfo extends ViewModel {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private MutableLiveData<String> mText;

    public ViewModelInfo() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}