package com.example.demoappsoso.SoMo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

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

import java.util.ArrayList;

public class SomoActivity extends AppCompatActivity {

    String url = "https://verbalearn.com/hoc-phong-thuy/so-mo-lo-de/";

    ListView lstSoMo;
    EditText edtSoMo;
    ArrayList<SoMo> soMoArrayList;
    SoMoAdapter soMoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_somo);
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Số mơ");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);
        lstSoMo = findViewById(R.id.listSoMo);
        edtSoMo = findViewById(R.id.idSearch);
        soMoArrayList = new ArrayList<>();

        getData();
        soMoAdapter = new SoMoAdapter(getApplicationContext(),soMoArrayList);
        lstSoMo.setAdapter(soMoAdapter);

        edtSoMo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SomoActivity.this.soMoAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void getData(){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements tables = document.select("table[id = tablepress-2]");
                Elements trs = tables.select("tbody[class=row-hover]").select("tr");
                for(Element td : trs){
                    Elements tds = td.select("td");
                    Element td1 = tds.select("td[class= column-2]").first();
                    Element td2 = tds.select("td[class= column-3]").first();
                    soMoArrayList.add(new SoMo(td1.text(),td2.text()));
                    soMoAdapter.notifyDataSetChanged();
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