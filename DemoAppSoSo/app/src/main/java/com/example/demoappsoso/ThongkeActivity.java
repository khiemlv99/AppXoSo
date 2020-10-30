package com.example.demoappsoso;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.demoappsoso.thonbao.item;
import com.example.demoappsoso.thonbao.thongbaoAdapter;

import java.util.ArrayList;


public class ThongkeActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<item> arrThongBao;
    thongbaoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongke);
        listView = findViewById(R.id.ListViewThongBao);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Thống kê");
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setDisplayHomeAsUpEnabled(true);
        arrThongBao = new ArrayList<>();
        arrThongBao.add(new item("Thống kê tần suất xuất hiện loto"));
        arrThongBao.add(new item("Thống kê tần suất xuất hiện loto"));
        arrThongBao.add(new item("Thống kê loto về nhiều ít"));
        arrThongBao.add(new item("Thống kê loto từ 00-99"));
        arrThongBao.add(new item("Thống kê theo tổng"));
        arrThongBao.add(new item("Thống kê theo đầu"));
        arrThongBao.add(new item("Thống kê theo đuôi"));
        arrThongBao.add(new item("Thống kê theo giải Đặc biệt"));
        arrThongBao.add(new item("Loto đến kì so với chu kì gần nhất"));

        adapter = new thongbaoAdapter(this, R.layout.dong_thongke,arrThongBao);
        listView.setAdapter(adapter);

    }
}