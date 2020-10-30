package com.example.demoappsoso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.demoappsoso.fragment.canhan_fragment;
import com.example.demoappsoso.fragment.soket_fragment;
import com.example.demoappsoso.fragment.them_fragment;
import com.example.demoappsoso.fragment.uytin_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainView;
    private FrameLayout mMainFrame;
    private canhan_fragment canhan_fragment;
    private soket_fragment soket_fragment;
    private uytin_fragment uytin_fragment;
    private them_fragment them_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Xổ số Live 3.0");
        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(getLayoutInflater().inflate(R.layout.activity_main, null),
//                new ActionBar.LayoutParams(
//                        ActionBar.LayoutParams.WRAP_CONTENT,
//                        ActionBar.LayoutParams.MATCH_PARENT,
//                        Gravity.CENTER
//                )
//        );
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_sports_basketball_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setLogo(R.drawable.iconxs);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setBackgroundDrawable(colorDrawable);

        mMainFrame = findViewById(R.id.frame_main);
        mMainView = findViewById(R.id.nav_main);
        canhan_fragment = new canhan_fragment();
        soket_fragment = new soket_fragment();
        uytin_fragment = new uytin_fragment();
        them_fragment = new them_fragment();
        setFragment(canhan_fragment);

        mMainView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_canhan:
                        setFragment(canhan_fragment);
                        return true;
                    case R.id.nav_soket:
                        setFragment(soket_fragment);
                        return true;
                    case R.id.nav_uytin:
                        setFragment(uytin_fragment);
                        return true;
                    case R.id.nav_them:
                        setFragment(them_fragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_main, fragment);
        fragmentTransaction.commit();
    }
}