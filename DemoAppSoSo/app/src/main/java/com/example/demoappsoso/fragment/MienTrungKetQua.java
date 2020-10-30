package com.example.demoappsoso.fragment;

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
import com.example.demoappsoso.XSMienTrung.MienTrung;
import com.example.demoappsoso.XSMienTrung.MienTrungAdapter;
import com.example.demoappsoso.ketqua.loto;
import com.example.demoappsoso.ketqua.lotoAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MienTrungKetQua extends Fragment {


    String url = "https://xosodaiphat.com/xsmt-xo-so-mien-trung.html";
    ArrayList<MienTrung> mienTrungArrayList;
    MienTrungAdapter mienTrungAdapter;
    ArrayList<loto> lotoArrayList;
    com.example.demoappsoso.ketqua.lotoAdapter lotoAdapter;

    TextView txtTitle, txtNgayThang, txtgiai, txtdonng2, txtdong3 ;
    ListView lstMienTrung, lstLotoMienTrung;


    public MienTrungKetQua() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTitle = view.findViewById(R.id.mienTrungTitle);
        txtNgayThang = view.findViewById(R.id.mienTrungNgayThang);
        lstMienTrung = view.findViewById(R.id.ListViewMienTrung);
        lstLotoMienTrung = view.findViewById(R.id.ListViewMienTrungLoTo);
        txtgiai = view.findViewById(R.id.giai);
        txtdonng2 = view.findViewById(R.id.gialai);
        txtdong3 = view.findViewById(R.id.ninhthuan);
        mienTrungArrayList = new ArrayList<>();
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

                //table
                //Header of table
                Elements elementsHead = document.select("thead").get(0).select("tr");
                for(Element element :elementsHead)
                {
                    Elements data = element.select("th");
                    Element dong1 = data.first();
                    Element dong2 = data.get(1);
                    Element dong3 = data.last();
                    txtgiai.setText(dong1.text());
                    txtdonng2.setText(dong2.text());
                    txtdong3.setText(dong3.text());
                }
                //get body table
                Elements elementBody = document.select("tbody").get(0).select("tr");
                for(Element element: elementBody)
                {
                    Elements data = element.select("td");
                    Element dong1 = data.select("td").first();
                    Element dong2 = data.select("td").get(1);
                    Element dong3  = data.select("td").last();
                    mienTrungArrayList.add(new MienTrung(dong1.text(),dong2.text(),dong3.text()));

                }
                mienTrungAdapter = new MienTrungAdapter(mienTrungArrayList,getContext());
                lstMienTrung.setAdapter(mienTrungAdapter);

                //get table loto
                Elements LotoElement = document.select("tbody").get(1).select("tr");
                for(Element element : LotoElement)
                {
                    Element element1 = element.child(0);
                    Element element2 = element.child(1);
                    Log.d("elm",element1.text());
                    lotoArrayList.add(new loto(element1.text(),element2.text()));
                }
                lotoAdapter = new lotoAdapter(lotoArrayList,getContext());
                lstLotoMienTrung.setAdapter(lotoAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mien_trung_ket_qua, container, false);
    }
}