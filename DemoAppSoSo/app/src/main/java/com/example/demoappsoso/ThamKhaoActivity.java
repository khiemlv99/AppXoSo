package com.example.demoappsoso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.demoappsoso.fragment.ThamKhaoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ThamKhaoActivity extends AppCompatActivity {

    private BottomNavigationView ketqua_main;
    private FrameLayout ketqua_frame;
    private ThamKhaoFragment fragment_tham_khao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua_activity);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Tham khảo");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        ketqua_frame = findViewById(R.id.frame_ketqua);
        ketqua_main = findViewById(R.id.bottomKetqua);
        fragment_tham_khao = new ThamKhaoFragment();
        setFragment(fragment_tham_khao);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_ketqua, fragment);
        fragmentTransaction.commit();
    }
}