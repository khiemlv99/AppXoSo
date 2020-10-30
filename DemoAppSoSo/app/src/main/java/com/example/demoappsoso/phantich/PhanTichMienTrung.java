package com.example.demoappsoso.phantich;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.demoappsoso.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class PhanTichMienTrung extends Fragment {

    String url = "https://xoso.com.vn/phan-tich-kqxs-mien-trung-c413-p1.html";
    ImageView imgHinhTitle;
    TextView txtTitle, txtDescription;
    ListView lstPhanTich;
    ArrayList<PhanTich> phanTichArrayList;
    PhanTichAdapter adapter;

    public PhanTichMienTrung() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgHinhTitle = view.findViewById(R.id.ImagePhanTichFirst);
        txtTitle = view.findViewById(R.id.TextViewTitle);
        txtDescription = view.findViewById(R.id.TextViewDescription);
        lstPhanTich = view.findViewById(R.id.ThamKhaoListView);
        phanTichArrayList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements elements = document.select("article[class=article-list]");
                Elements title = elements.first().select("a");
                txtTitle.setText(title.text());

                Elements description = elements.get(0).select("div.article-summary");
                txtDescription.setText(description.text());

                Elements images = elements.select("img[src~=(?i)\\.(png|jpe?g|gif)]");
                String img = images.attr("src");
                Glide.with(getContext()).load("https:" + img).into(imgHinhTitle);

                Elements data = document.select("div.section-content").select("article[class=article-list]");
                Elements image = document.getElementsByTag("img");
                Log.d("hihihi", image.get(0).attr("src"));

                for (int i=1;i<elements.size();i++)
                {
                    phanTichArrayList.add(new PhanTich(elements.get(i).select("img").attr("data-src"), elements.get(i).select("header").text()));
                    Log.d("title ", elements.get(i).select("header").text());
                    Log.d("description", elements.get(i).select("div.article-summary").text());
                    Log.d("item", elements.get(i).select("img").attr("data-src"));
                }
                adapter = new PhanTichAdapter(getContext(),phanTichArrayList);

                lstPhanTich.setAdapter(adapter);
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
        return inflater.inflate(R.layout.fragment_phan_tich, container, false);
    }
}