package com.ayaavdews.appcekongkir.Fragment.Info;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ayaavdews.appcekongkir.R;

public class FragmentInfo extends Fragment {

    // Create By Aya Avdews
    // SMKN 2 Surakarta
    // XII RPL A

    private ViewModelInfo viewModelInfo;
    private ImageView iv_ig, iv_wa, iv_fb;
    private TextView tv_ig, tv_wa, tv_fb;

    public static String FACEBOOK_URL = "https://www.facebook.com/AyaAvdews";
    public static String FACEBOOK_PAGE_ID = "AyaAvdews";


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModelInfo = ViewModelProviders.of(this).get(ViewModelInfo.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        iv_ig = (ImageView) root.findViewById(R.id.iv_user_ig);
        iv_wa = (ImageView) root.findViewById(R.id.iv_user_wa);
        iv_fb = (ImageView) root.findViewById(R.id.iv_user_fb);

        tv_ig = (TextView) root.findViewById(R.id.tv_user_ig);
        tv_wa = (TextView) root.findViewById(R.id.tv_user_wa);
        tv_fb = (TextView) root.findViewById(R.id.tv_user_fb);

        iv_ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenInstagram();
            }
        });

        tv_ig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenInstagram();
            }
        });

        iv_wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenWhatsapp();
            }
        });

        tv_wa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenWhatsapp();
            }
        });

        iv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFacebook();
            }
        });

        tv_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenFacebook();
            }
        });

        return root;
    }

    private void OpenInstagram() {
        Uri uri = Uri.parse("http://instagram.com/_u/aya_avdews");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/aya_avdews")));
        }
    }

    private void OpenWhatsapp() {
        String contact = "+62 87854542117";
        String url = "https://api.whatsapp.com/send?phone=" + contact;

        try {
            PackageManager pm = getActivity().getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getActivity(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void OpenFacebook() {
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        String facebookUrl = getFacebookUrl(getActivity());
        facebookIntent.setData(Uri.parse(facebookUrl));
        startActivity(facebookIntent);
    }

    public String getFacebookUrl(Context context) {

        PackageManager packageManager = context.getPackageManager();

        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) {
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

}