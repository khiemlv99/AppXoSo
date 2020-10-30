package com.example.demoappsoso.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoappsoso.MainActivity;
import com.example.demoappsoso.R;
import com.example.demoappsoso.ketqua.XoSo;
import com.example.demoappsoso.ketqua.XoSoAdapter;
import com.example.demoappsoso.ketqua.loto;
import com.example.demoappsoso.ketqua.lotoAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class fragment_mienbac extends Fragment {

    String url = "https://xosodaiphat.com/xsmb-xo-so-mien-bac.html";
    ArrayList<XoSo> arrayList;
    ArrayList<String> ngays;
    TextView txtTitleMienBac;
    TableRow tblRow, tblRowNgay;
    TextView txtColumn1, txtColumn2, txtNgay;
    public fragment_mienbac() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrayList = new ArrayList<XoSo>();
        ngays = new ArrayList<>();
        txtTitleMienBac = view.findViewById(R.id.mienbacTitle);
        final TableLayout ll = (TableLayout) view.findViewById(R.id.tableLayout);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @SuppressLint("ResourceType")
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);

                //get heading
                Elements getHeading = document.select("div.block-main-heading").select("h1");
                txtTitleMienBac.setText(getHeading.text());
                Log.d("Heading", getHeading.text());
                Elements tableHeading = document.select("div.list-link");
                for (Element heading : tableHeading) {
                    Elements listLink = heading.select("h2");
                    ngays.add(listLink.text());
                }

                Elements table = document.select("div.block-main-content");
                Elements title = document.select("div.list-link").select("h2");
                for (Element t : title) {
                    Log.d("h1", t.text());
                }
                for (Element dong : table) {
                    Log.d("titleT", title.text() + "");
                    Elements tr = dong.select("tbody").select("tr");
                    for (Element dongTable : tr) {
                        Element d1 = dongTable.child(0);
                        Element d2 = dongTable.child(1);

                        Log.d("dd111", d1.select("span").text());
                        Log.d("dd12", d2.text());
                        Log.d("sizearr", d1.text());
                        arrayList.add(new XoSo(d1.text(), d2.text()));
                    }

                }



                Log.d("arrraylist", arrayList.size() + "");

                ll.setShrinkAllColumns(true);




                TableRow tblTitle = new TableRow(getContext());

                TextView textTile = new TextView(getContext());
                textTile.setTextColor(-65536);
                textTile.setText(ngays.get(0));
                tblTitle.addView(textTile);
                ll.addView(tblTitle);
                int tam = 20;
                for (int i = 0; i < tam; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 1 || i == 19) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==0) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);

                }

                TableRow tblTitle1 = new TableRow(getContext());
                TextView textTile1 = new TextView(getContext());
                textTile1.setTextColor(-65536);
                textTile1.setText(ngays.get(1));
                tblTitle1.addView(textTile1);
                ll.addView(tblTitle1);
                for (int i = 20; i < 40; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 21 || i == 39) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==20) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }
                TableRow tblTitle2 = new TableRow(getContext());
                TextView textTile2 = new TextView(getContext());
                textTile2.setTextColor(-65536);
                textTile2.setText(ngays.get(2));
                tblTitle2.addView(textTile2);
                ll.addView(tblTitle2);
                for (int i = 40; i < 60; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 41 || i == 59) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==40) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }
                TableRow tblTitle3 = new TableRow(getContext());
                TextView textTile3 = new TextView(getContext());
                textTile3.setTextColor(-65536);
                textTile3.setText(ngays.get(3));
                tblTitle3.addView(textTile3);
                ll.addView(tblTitle3);
                for (int i = 60; i < 80; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 61 || i == 79) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==60) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }
                TableRow tblTitle4 = new TableRow(getContext());
                TextView textTile4 = new TextView(getContext());
                textTile4.setTextColor(-65536);
                textTile4.setText(ngays.get(4));
                tblTitle4.addView(textTile4);
                ll.addView(tblTitle4);
                for (int i = 80; i < 100; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 81 || i == 99) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==80) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }
                TableRow tblTitle5 = new TableRow(getContext());
                TextView textTile5 = new TextView(getContext());
                textTile5.setTextColor(-65536);
                textTile5.setText(ngays.get(5));
                tblTitle5.addView(textTile5);
                ll.addView(tblTitle5);
                for (int i = 100; i < 120; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 101 || i == 119) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==100) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }
                TableRow tblTitle6 = new TableRow(getContext());
                TextView textTile6 = new TextView(getContext());
                textTile6.setTextColor(-65536);
                textTile6.setText(ngays.get(6));
                tblTitle6.addView(textTile6);
                ll.addView(tblTitle6);
                for (int i = 120; i < 140; i++) {
                    TableRow tblRow = new TableRow(getContext());
                    TextView txtColumn1 = new TextView(getContext());
                    TextView txtColumn2 = new TextView(getContext());
                    txtColumn1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    txtColumn1.setText(arrayList.get(i).getDong1());
                    txtColumn2.setText(arrayList.get(i).getDong2());
                    txtColumn2.setTextColor(Color.BLACK);
                    txtColumn2.setTextSize(20);
                    txtColumn1.setTextColor(Color.BLACK);
                    if (i == 121 || i == 139) {
                        txtColumn2.setTextColor(-65536);
                    }
                    if(i ==120) { txtColumn2.setTextColor(Color.BLUE);}
                    tblRow.addView(txtColumn1);
                    tblRow.addView(txtColumn2);
                    ll.addView(tblRow);
                }


//                }


//                for (int i = 0; i < ngays.size(); i++) {
//                    tblRow = new TableRow(getApplicationContext());
//                    txtNgay = new TextView(getApplicationContext());
//                    txtNgay.setText(ngays.get(i));
//                    tblRow.addView(txtNgay);
//                    for (int j = i; j < arrayList.size(); j++) {
//                        txtColumn1 = new TextView(getApplicationContext());
//                        txtColumn2 = new TextView(getApplicationContext());
//                        txtColumn1.setText(arrayList.get(j).getDong1());
//                        tblRow.addView(txtColumn1);
//                        txtColumn2.setText(arrayList.get(j).getDong2());
//                        tblRow.addView(txtColumn2);
//                    }
//                    ll.addView(tblRow);
//                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mienbac, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_ketqua, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection
        switch (item.getItemId()) {
            case R.id.itemcalender:
                // do s.th.
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}