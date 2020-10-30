package com.example.demoappsoso.LoKet;

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

public class ActivityLoKet extends AppCompatActivity {

    private BottomNavigationView loket_main;
    private FrameLayout loket_frame;
    private LoKetFragment loKetFragment;
    private LoKetXien2Fragment loKetXien2Fragment;
    private LoKetXien3Fragment loKetXien3Fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_ket2);
        setContentView(R.layout.activity_lo_ket2);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Lô Kết hôm nay");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        loket_main = findViewById(R.id.bottomLoKet);
        loket_frame = findViewById(R.id.frame_LoKet);
        loKetFragment = new LoKetFragment();
        loKetXien2Fragment = new LoKetXien2Fragment();
        loKetXien3Fragment = new LoKetXien3Fragment();
        setFragment(loKetFragment);

        loket_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.loKet1:
                        setFragment(loKetFragment);
                        return true;
                    case R.id.xien2:
                        setFragment(loKetXien2Fragment);
                        return  true;
                    case R.id.xien3:
                        setFragment(loKetXien3Fragment);
                        return true;
                }
                return false;
            }
        });

    }
    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_LoKet, fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return  true;
    }
}