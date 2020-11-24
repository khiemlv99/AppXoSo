package com.example.demoappsoso.KetQuaDai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoappsoso.MainActivity;
import com.example.demoappsoso.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class KetquadaiActivity extends AppCompatActivity {
    String urlMienTrung = "https://xosotailoc.com/xo-so-theo-dai.html";
    ListView lstDMT;
    TextView txtTitle;

    ArrayList<DMT> dmtArrayList;
    ArrayList<String> ngayTheoTinhs;

    DMTAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketquadai);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Kết quả theo đài");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);

        dmtArrayList = new ArrayList<>();
        ngayTheoTinhs = new ArrayList<>();
        lstDMT = findViewById(R.id.ListViewDMT);


        adapter = new DMTAdapter(dmtArrayList,this);
        lstDMT.setAdapter(adapter);
        getAllMienTrung();

        lstDMT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(KetquadaiActivity.this,DaiMienTrung.class);
                intent.putExtra("link",dmtArrayList.get(position).getHref());
                intent.putExtra("title",dmtArrayList.get(position).getDong1());
                startActivity(intent);
//                finish();
            }
        });

    }

    public void getAllMienTrung() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlMienTrung, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);

                //get cac tinh mien trung
                Elements tinhs = document.select("div.mien_bac").select("div.ketqua_xs").select("div.cau3tinh");

                Elements allTinhs = tinhs.get(1).select("div.cau3tinh_td").select("a");


                for (Element t : allTinhs) {
                    dmtArrayList.add(new DMT(t.text(),t.attr("href")));
                    ngayTheoTinhs.add(t.attr("href"));
                }
                adapter.notifyDataSetChanged();



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