package com.example.demoappsoso.KetQuaDai;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
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
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class DaiMienTrung extends AppCompatActivity {
    ArrayList<KetQuaDMT> ketQuaDMTArrayList;
    ArrayList<LoToDaiMienTrung> loToDaiMienTrungs;
    ArrayList<FullAll> fullAlls;
    DaiMienTrungAdapter adapter;
    ViewPager viewPager;
    TextView txtTitle;
    ListView lstDaiMienTrung;
    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dai_mien_trung);

        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("Kết quả theo đài");
        actionBar.setDisplayHomeAsUpEnabled(true);
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#132a56"));
        actionBar.setBackgroundDrawable(colorDrawable);

        ketQuaDMTArrayList = new ArrayList<>();
        loToDaiMienTrungs = new ArrayList<>();
        fullAlls = new ArrayList<>();
        viewPager = findViewById(R.id.ViewPagerKetQuaDai);
        txtTitle = findViewById(R.id.idNgayThangKetQuaDai);

        viewPager.setRotationY(180);
        viewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                page.setRotationY(180);

            }
        });


        Intent intent = getIntent();
        String link = "https://xosotailoc.com" + intent.getStringExtra("link");
        txtTitle.setText("Xổ số " + intent.getStringExtra("title"));
        Log.d("link", link);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);

                Elements all = document.select("div.box-conten-kqxs").select("div.mien_bac");
                for (int t = 0; t < all.size(); t++) {
                    Elements getAllG = all.get(t).select("div.ketqua_xs");
                    Log.d("allll",getAllG.size()+"");

                    if(getAllG.size() == 1){
                        for (int i = 0; i < getAllG.size(); i++) {
                            Elements getGTitle = getAllG.get(i).select("div.ketqua_tieude_xs");
                            Log.d("sizeofloto", getGTitle.text() + "");
                            Elements getG1 = getAllG.get(i).select("div.xsag_mb_prizetitle");
                            String g8 = getG1.select("div.xsag_mb_g8").text();
                            String g7 = getG1.select("div.xsag_mb_g7").text();
                            String g6 = getG1.select("div.xsag_mb_g6").text();
                            String g5 = getG1.select("div.xsag_mb_g5").text();
                            String g4 = getG1.select("div.xsag_mb_g4").text();
                            String g3 = getG1.select("div.xsag_mb_g3").text();
                            String g2 = getG1.select("div.xsag_mb_g2").text();
                            String g1 = getG1.select("div.xsag_mb_g1").text();
                            String gdb = getG1.select("div.xsag_mb_gdb").text();

                            Elements getG2 = getAllG.get(i).select("div.xsag_mb_prizeitem");
                            String g81 = getG2.select("div.xsag_mb_g8_item_giai").text();
                            String g71 = getG2.select("div.xsag_mb_g7_item_giai").text();
                            String g61 = getG2.select("div.xsag_mb_g6_item_giai").get(0).text();
                            String g62 = getG2.select("div.xsag_mb_g6_item_giai").get(1).text();
                            String g63 = getG2.select("div.xsag_mb_g6_item_giai").get(2).text();
                            String g51 = getG2.select("div.xsag_mb_g5_item_giai").text();
                            String g41 = getG2.select("div.xsag_mb_g4_item_giai").get(0).text();
                            String g42 = getG2.select("div.xsag_mb_g4_item_giai").get(1).text();
                            String g43 = getG2.select("div.xsag_mb_g4_item_giai").get(2).text();
                            String g44 = getG2.select("div.xsag_mb_g4_item_giai").get(3).text();
                            String g45 = getG2.select("div.xsag_mb_g4_item_giai").get(4).text();
                            String g46 = getG2.select("div.xsag_mb_g4_item_giai").get(5).text();
                            String g47 = getG2.select("div.xsag_mb_g4_item_giai").get(6).text();

                            String g31 = getG2.select("div.xsag_mb_g3_item_giai").get(0).text();
                            String g32 = getG2.select("div.xsag_mb_g3_item_giai").get(1).text();

                            String g21 = getG2.select("div.xsag_mb_g2_item_giai").text();
                            String g11 = getG2.select("div.xsag_mb_g1_item_giai").text();
                            String gdb1 = getG2.select("div.xsag_mb_gdb_item").text();

                            ketQuaDMTArrayList.add(new KetQuaDMT(getGTitle.text(), g8, g81, g7, g71, g6, g61, g62, g63, g5, g51, g4, g41, g42, g43, g44, g45, g46, g47, g3
                                    , g31, g32, g2, g21, g1, g11, gdb, gdb1));

                        }

                        Elements getLotos = all.get(t).select("div.bangkqloto");
                        for (int j = 0; j < getLotos.size(); j++) {
                            Elements d1 = getLotos.get(j).select("div.kqlotomb").select("span");
                            String dau = d1.get(0).text();
                            String dau0 = d1.get(1).text();
                            String dau1 = d1.get(2).text();
                            String dau2 = d1.get(3).text();
                            String dau3 = d1.get(4).text();
                            String dau4 = d1.get(5).text();

                            String daudau = d1.get(6).text();
                            String dau5 = d1.get(7).text();
                            String dau6 = d1.get(8).text();
                            String dau7 = d1.get(9).text();
                            String dau8 = d1.get(10).text();
                            String dau9 = d1.get(11).text();

                            Elements d2 = getLotos.get(j).select("div.kqlotomb_duoi").select("span");

                            String duoi = d2.get(0).text();
                            String duoi0 = d2.get(1).text();
                            String duoi1 = d2.get(2).text();
                            String duoi2 = d2.get(3).text();
                            String duoi3 = d2.get(4).text();
                            String duoi4 = d2.get(5).text();

                            String duoiduoi = d2.get(6).text();
                            String duoi5 = d2.get(7).text();
                            String duoi6 = d2.get(8).text();
                            String duoi7 = d2.get(9).text();
                            String duoi8 = d2.get(10).text();
                            String duoi9 = d2.get(11).text();

                            loToDaiMienTrungs.add(new LoToDaiMienTrung(dau, dau0, dau1, dau2, dau3, dau4, daudau, dau5, dau6, dau7, dau8, dau9, duoi, duoi0, duoi1, duoi2,
                                    duoi3, duoi4, duoiduoi, duoi5, duoi6, duoi7, duoi8, duoi9));
                        }
                        fullAlls.add(new FullAll(new ArrayList<KetQuaDMT>(ketQuaDMTArrayList), new ArrayList<LoToDaiMienTrung>(loToDaiMienTrungs)));
                        adapter = new DaiMienTrungAdapter(fullAlls, getApplicationContext());
                        adapter.notifyDataSetChanged();
                        viewPager.setAdapter(adapter);
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