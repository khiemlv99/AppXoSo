package com.example.demoappsoso.phantich;

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
import android.widget.Switch;

import com.example.demoappsoso.R;
import com.example.demoappsoso.fragment.ThamKhaoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PhanTichActivity extends AppCompatActivity {

    private BottomNavigationView ketqua_main;
    private FrameLayout ketqua_frame;
    private PhanTichFragment phanTichFragment;
    private PhanTichMienTrung phanTichMienTrung;
    private PhanTichMienNam phanTichMienNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua_activity);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Phân tích");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        ketqua_frame = findViewById(R.id.frame_ketqua);
        ketqua_main = findViewById(R.id.bottomKetqua);
        phanTichFragment = new PhanTichFragment();
        phanTichMienTrung = new PhanTichMienTrung();
        phanTichMienNam = new PhanTichMienNam();
        setFragment(phanTichFragment);

        ketqua_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.mienbacid:
                        setFragment(phanTichFragment);
                        return  true;
                    case R.id.mientrungid:
                        setFragment(phanTichMienTrung);
                        return true;
                    case R.id.miennamid:
                        setFragment(phanTichMienNam);
                        return true;
                }
                return false;
            }
        });

    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_ketqua, fragment);
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