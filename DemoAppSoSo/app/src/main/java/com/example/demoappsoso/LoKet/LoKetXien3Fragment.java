package com.example.demoappsoso.LoKet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.Map;

public class LoKetXien3Fragment extends Fragment {
    String url = "https://xoso.com.vn/LoKet/Xien3KetSend";
    String urlXemKetQua = "https://xoso.com.vn/xien-3-ket.html";
    TextView txtKetQua, txtth1, txtth2, txtth3, txtThongbao;
    ListView lstKetQua;
    LinearLayout linearLayoutTh;
    EditText edtLo1, edtLo2, edtLo3;
    Button btnGui, btnXemKetQua;
    ArrayList<SoKet> soKetArrayList;
    SoKetAdapter soKetAdapter;

    public LoKetXien3Fragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        AnhXa();
        soKetArrayList = new ArrayList<>();
        soKetAdapter = new SoKetAdapter(getContext(), soKetArrayList);
        lstKetQua.setAdapter(soKetAdapter);
        edtLo1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        edtLo2.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
        edtLo3.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        edtLo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtLo1.length() ==2){edtLo2.setFocusableInTouchMode(true);edtLo2.requestFocus();}
            }
        });
        edtLo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edtLo2.length() ==2){edtLo3.setFocusableInTouchMode(true);edtLo3.requestFocus();}
            }
        });

        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lo1 = String.valueOf(edtLo1.getText());
                String lo2 = String.valueOf(edtLo2.getText());
                String lo3 = String.valueOf(edtLo3.getText());
                if (lo1.length() == 0 && lo2.length() == 0 && lo3.length() == 0) {
                    Toast.makeText(getContext(), "Vui lòng nhập ba cặp số", Toast.LENGTH_SHORT).show();
                } else if (lo1.length() == 0 || lo2.length() == 0 || lo3.length() == 0) {
                    Toast.makeText(getContext(), "Không được để trống 1 cặp số", Toast.LENGTH_SHORT).show();
                } else {
                    txtThongbao.setVisibility(View.GONE);
                    lstKetQua.setVisibility(View.GONE);
                    linearLayoutTh.setVisibility(View.GONE);
                    txtKetQua.setVisibility(View.VISIBLE);
                    postDataXien2(lo1, lo2, lo3);
                }
            }
        });

        btnXemKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtKetQua.setVisibility(View.GONE);
                txtThongbao.setVisibility(View.VISIBLE);
                linearLayoutTh.setVisibility(View.VISIBLE);
                lstKetQua.setVisibility(View.VISIBLE);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getDataXemKetQua();
                    }
                });
                getDataXemKetQua();
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lo_ket_xien3, container, false);
    }

    private void AnhXa() {
        txtKetQua = getView().findViewById(R.id.xien3KetQua);
        btnGui = getView().findViewById(R.id.buttonGuiQuaXien3);
        btnXemKetQua = getView().findViewById(R.id.buttonXemKetQuaXien3);
        edtLo1 = getView().findViewById(R.id.editLo1Xien3);
        edtLo2 = getView().findViewById(R.id.editLo2Xien3);
        edtLo3 = getView().findViewById(R.id.editLo3Xien3);
        txtth1 = getView().findViewById(R.id.idTitleDong1Xien2);
        txtth2 = getView().findViewById(R.id.idTitleDong2Xien2);
        txtth3 = getView().findViewById(R.id.idTitleDong3Xien2);
        txtThongbao = getView().findViewById(R.id.idthongbaoLoKetXien3);
        lstKetQua = getView().findViewById(R.id.ListViewSoKetXien3);
        linearLayoutTh = getView().findViewById(R.id.LinearLayoutThXien3);
    }

    public void postDataXien2(final String loto1, final String loto2, final String loto3) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                //table lo ket
                try {
                    Element theP = document.select("p").first();
                    txtKetQua.setText(theP.text());
                } catch (Exception ex) {
                    Toast.makeText(getContext(), "Vui lòng nhập cặp số", Toast.LENGTH_SHORT).show();
                }


                //Get t head

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("lotoNumber1", loto1);
                hashMap.put("lotoNumber2", loto2);
                hashMap.put("lotoNumber3", loto3);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void getDataXemKetQua() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlXemKetQua, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements list = document.select("div.list-statistic");
                //get title
                Element title = list.select("p").first();
                txtThongbao.setText(title.text());

                Elements tables = document.select("table[class=table text-center]");
                for (Element element : tables) {
                    Elements dongHead = element.select("thead").select("tr");
                    Log.d("hihiDong", dongHead.text());
                    for (Element tr : dongHead) {
                        Element t1 = tr.select("th").first();
                        Element t2 = tr.select("th").get(1);
                        Element t3 = tr.select("th").last();
                        txtth1.setText(t1.text());
                        txtth2.setText(t2.text());
                        txtth3.setText(t3.text());
                    }
                }

                for (Element element : tables) {
                    Elements dongHead = element.select("tbody").select("tr");
                    Log.d("yy111", dongHead.text() + "");
                    for (Element body : dongHead) {
                        Elements data = body.select("td");
                        Element t1 = data.select("td").first();
                        Element t2 = data.select("td").get(1);
                        Element t3 = data.select("td").last();
                        soKetArrayList.add(new SoKet(t1.text(), t2.text(), t3.text()));
                    }

                }
                soKetAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);
    }
}