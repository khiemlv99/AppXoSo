package com.example.demoappsoso.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.demoappsoso.R;
import com.example.demoappsoso.XSMienNam.MienNam;
import com.example.demoappsoso.XSMienNam.MienNamAdapter;
import com.example.demoappsoso.XSMienTrung.MienTrung;
import com.example.demoappsoso.ketqua.loto;
import com.example.demoappsoso.ketqua.lotoAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MienNamKetQua extends Fragment {

    String url = "https://xosodaiphat.com/xsmn-xo-so-mien-nam.html";
    ArrayList<MienNam> arrayListMienNam;
    MienNamAdapter mienNamAdapter;
    ArrayList<loto> lotoArrayList;
    com.example.demoappsoso.ketqua.lotoAdapter lotoAdapter;

    public MienNamKetQua() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final TextView txtTitle, txtNgayThang, txtgiai,txtdong1, txtdong2, txtdong3 ,txtdong4;
        final ListView lstMienNam, lstLotoMienNam;

        txtTitle = view.findViewById(R.id.miennamTitle);
        txtNgayThang = view.findViewById(R.id.mienNamNgayThang);
        txtgiai = view.findViewById(R.id.giaiMienNam);
        txtdong1 = view.findViewById(R.id.mienNamDong1) ;
        txtdong2 = view.findViewById(R.id.mienNamDong2) ;
        txtdong3 = view.findViewById(R.id.mienNamDong3);
        txtdong4 = view.findViewById(R.id.mienNamDong4);

        lstMienNam = view.findViewById(R.id.ListViewMienNam);
        lstLotoMienNam = view.findViewById(R.id.ListViewMienNamLoTo);

        arrayListMienNam = new ArrayList<>();
        lotoArrayList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Element elements = document.select("div.block-main-heading").select("h1").first();

                txtTitle.setText(elements.text());

                Element elementNgayThang = document.select("div.list-link").get(0).select("h2").first();
                txtNgayThang.setText(elementNgayThang.text());

                //Header of table
                Elements elementsHead = document.select("thead").get(0).select("tr");
                for(Element element :elementsHead)
                {
                    Elements data = element.select("th");
                    Element giai1 = data.first();
                    Element dong1 = data.get(1);
                    Element dong2 = data.get(2);
                    Element dong3 = data.get(3);
                    Element dong4 = data.last();

                    txtgiai.setText(giai1.text());
                    txtdong1.setText(dong1.text());
                    txtdong2.setText(dong2.text());
                    txtdong3.setText(dong3.text());
                    txtdong4.setText(dong4.text());
                }

                Elements elementBody = document.select("tbody").get(0).select("tr");
                for(Element element: elementBody)
                {
                    Elements data = element.select("td");
                    Element giai = data.select("td").first();
                    Element dong1 = data.select("td").get(1);
                    Element dong2  = data.select("td").get(2);
                    Element dong3  = data.select("td").get(3);
                    Element dong4  = data.select("td").last();
                    Log.d("giai",giai.text());
                    arrayListMienNam.add(new MienNam(giai.text(),dong1.text(),dong2.text(),dong3.text(),dong4.text()));
                }
                mienNamAdapter = new MienNamAdapter(getContext(),arrayListMienNam);
                lstMienNam.setAdapter(mienNamAdapter);

                Elements LotoElement = document.select("tbody").get(1).select("tr");
                for(Element element : LotoElement)
                {
                    Element element1 = element.child(0);
                    Element element2 = element.child(1);
                    Log.d("elm",element1.text());
                    lotoArrayList.add(new loto(element1.text(),element2.text()));
                }
                lotoAdapter = new lotoAdapter(lotoArrayList,getContext());
                lstLotoMienNam.setAdapter(lotoAdapter);



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
        return inflater.inflate(R.layout.fragment_mien_nam_ket_qua, container, false);
    }
}