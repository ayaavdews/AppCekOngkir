package com.ayaavdews.appcekongkir;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.ayaavdews.appcekongkir.Adapter.AdapterViewPager;
import com.ayaavdews.appcekongkir.Fragment.CekOngkir.FragmentCekOngkir;
import com.ayaavdews.appcekongkir.Fragment.CekResi.FragmentCekResi;
import com.ayaavdews.appcekongkir.Fragment.Info.FragmentInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    // Create By Aya Avdews
    // XII RPL A
    // SMKN 2 Surakarta

    // Deklarai BottomNavigation (Fungsi click)
    BottomNavigationView bottomNavigationView;

    // Deklarasi ViewPager (Fungsi slide)
    private ViewPager viewPager;

    // Deklarasi Fragment
    FragmentCekOngkir fragmentCekOngkir;
    FragmentCekResi fragmentCekResi;
    FragmentInfo fragmentInfo;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_cekOngkir:
                        viewPager.setCurrentItem(0);
                        break;

                    case R.id.navigation_cekResi:
                        viewPager.setCurrentItem(1);
                        break;

                    case R.id.navigation_info:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return false;
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                final InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(viewPager.getWindowToken(), 0);
            }

            @Override
            public void onPageSelected(int position) {


                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.v("Page", "onPageSelected : " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager();
    }
    private void setupViewPager() {
        AdapterViewPager adapter = new AdapterViewPager(getSupportFragmentManager());

        fragmentCekOngkir        = new FragmentCekOngkir();
        fragmentCekResi          = new FragmentCekResi();
        fragmentInfo             = new FragmentInfo();

        adapter.addFragment(fragmentCekOngkir);
        adapter.addFragment(fragmentCekResi);
        adapter.addFragment(fragmentInfo);
        viewPager.setAdapter(adapter);
    }
}




