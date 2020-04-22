package com.ayaavdews.appcekongkir;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PrimaryActivity extends AppCompatActivity {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primary);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar = findViewById(R.id.progress_bar);
        textView    = findViewById(R.id.text_view);

        progressBar.setMax(100);
        progressBar.setScaleY(3);

        progressAnimation();
    }

    public void progressAnimation() {
        animation_progressBar anim = new animation_progressBar(this, progressBar, textView, 0f, 100f);
        anim.setDuration(4000);
        progressBar.setAnimation(anim);
    }
}
