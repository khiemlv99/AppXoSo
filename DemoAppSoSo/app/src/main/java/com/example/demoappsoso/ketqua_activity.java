package com.example.demoappsoso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.demoappsoso.fragment.MienNamKetQua;
import com.example.demoappsoso.fragment.MienTrungKetQua;
import com.example.demoappsoso.fragment.fragment_mienbac;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ketqua_activity extends AppCompatActivity {

    private fragment_mienbac fragment_mienbac;
    private BottomNavigationView ketqua_main;
    private FrameLayout ketqua_frame;
    private MienTrungKetQua fragment_mienTrung;
    private MienNamKetQua fragment_mienNam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_ketqua_activity);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Kết quả xổ số");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        ketqua_frame = findViewById(R.id.frame_ketqua);
        ketqua_main = findViewById(R.id.bottomKetqua);
        fragment_mienbac = new fragment_mienbac();
        fragment_mienTrung = new MienTrungKetQua();
        fragment_mienNam = new MienNamKetQua();
        setFragment(fragment_mienbac);

        ketqua_main.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.mienbacid:
                        setFragment(fragment_mienbac);
                        return true;
                    case R.id.mientrungid:
                        setFragment(fragment_mienTrung);
                        return true;
                    case R.id.miennamid:
                        setFragment(fragment_mienNam);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ketqua, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        finish();
        return true;
    }
}