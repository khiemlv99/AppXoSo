package com.example.demoappsoso.LoGan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoappsoso.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class LoGanActivity extends AppCompatActivity {

    String url = "https://xoso.com.vn/thong-ke-lo-gan.html";
    ArrayList<LoGan> loGanArrayList;
    ArrayList<String> arrr;
    ArrayList<DataLoGan> dataLoGans;
    DataLoGanAdapter dataLoGanAdapter;
    TextView txtChonNgay, txtTitle;
    Button btnTraCuu;
    ListView lstData;
    Spinner spinnerLoGan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lo_gan);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Lô gan");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);

        loGanArrayList = new ArrayList<>();
        dataLoGans = new ArrayList<>();

        AnhXa();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        txtChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
        txtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        getIdLoGan();

//        String ngays = txtChonNgay.getText().toString();
//        String[] parts = ngays.split("/");
//        String ngay = parts[0];
//        String thang = parts[1];
//        String nam = parts[2];
//        getDataLogan(loGanArrayList.get(0).getValue(), ngay, thang, nam, loGanArrayList.get(0).getData());

        dataLoGanAdapter = new DataLoGanAdapter(getApplicationContext(),dataLoGans);
        dataLoGanAdapter.notifyDataSetChanged();
        lstData.setAdapter(dataLoGanAdapter);

        btnTraCuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ngays = txtChonNgay.getText().toString();
                String[] parts = ngays.split("/");
                String ngay = parts[0];
                String thang = parts[1];
                String nam = parts[2];

                for (int i = 0; i < loGanArrayList.size(); i++) {
                    if (spinnerLoGan.getSelectedItemId() == i) {
                        getDataLogan(loGanArrayList.get(i).getValue(), ngay, thang, nam, loGanArrayList.get(i).getData());
                        txtTitle.setText("Lô gan "+loGanArrayList.get(i).getTitle()+" ngày "+ngays);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataLoGans.clear();
                                dataLoGanAdapter = new DataLoGanAdapter(getApplicationContext(),dataLoGans);
                                dataLoGanAdapter.notifyDataSetChanged();
                                lstData.setAdapter(dataLoGanAdapter);
                            }
                        });
                    }
                }
            }
        });
    }
    private void AnhXa() {
        txtChonNgay = findViewById(R.id.textViewChonNgay);
        spinnerLoGan = findViewById(R.id.spinerLoGan);
        btnTraCuu = findViewById(R.id.buttonTraCuuLoGan);
        lstData = findViewById(R.id.idListViewLoGan);
        txtTitle = findViewById(R.id.idTitleLoGan);
    }

    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txtChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    public void getIdLoGan() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements getOptions = document.select("div.column2").select("select[id=ddLotteries]").select("option");
                for (Element option : getOptions) {
                    String value = option.attr("value");
                    String data = option.attr("data");

                    loGanArrayList.add(new LoGan(option.text(), value, data));

                    arrr = new ArrayList<>();
                    for (int i = 0; i < loGanArrayList.size(); i++) {
                        arrr.add(loGanArrayList.get(i).getTitle());
                    }

                    final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrr);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLoGan.setAdapter(adapter);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }

    public void getDataLogan(String lotteryId, String date, String month, String year, String lotteryGroupId) {
        String url = "https://xoso.com.vn/ThongKeAjax/AjaxThongKeLoGan?lotteryId=" + lotteryId + "&date=" + date +
                "%2F" + month + "%2F" + year + "&lotteryGroupId=" + lotteryGroupId + "";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements tables = document.select("table[class= table text-center]");
                for (int i = 0; i < tables.size() - 3; i++) {
                    Elements header = tables.get(i).select("table").select("tr");
                    for(Element h : header){
                        Element t1 = h.child(0);
                        Element t2 = h.child(1);
                        dataLoGans.add(new DataLoGan(t1.text(),t2.text()));
                        dataLoGanAdapter.notifyDataSetChanged();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
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