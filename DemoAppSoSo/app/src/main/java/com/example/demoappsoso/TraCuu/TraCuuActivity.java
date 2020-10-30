package com.example.demoappsoso.TraCuu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.demoappsoso.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TraCuuActivity extends AppCompatActivity {

    private BottomNavigationView traCuu_main;
    private FrameLayout frame_Tracuu;

    private TheoTinh_Fragment theoTinh_fragment;
    private DoSoLoto_Fragment doSoLoto_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tra_cuu);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Tra cứu");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        traCuu_main = findViewById(R.id.bottomTraCuu);
        frame_Tracuu = findViewById(R.id.frame_traCuu);
        theoTinh_fragment = new TheoTinh_Fragment();
        doSoLoto_fragment = new DoSoLoto_Fragment();
        setFragment(theoTinh_fragment);

        traCuu_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.tracuutheotinh:
                        setFragment(theoTinh_fragment);
                        return true;
                    case R.id.tracuudoso:
                        setFragment(doSoLoto_fragment);
                        return  true;
                }
                return false;
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_traCuu, fragment);
        fragmentTransaction.commit();
    }

}