package com.example.demoappsoso.LoKet;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

public class LoKetFragment extends Fragment {
    String url = "https://xoso.com.vn/lo-ket.html";
    String urlReport = "https://xoso.com.vn/LoKet/LoKetReport";
    private String LOG = "GROUP";
    ArrayList<String> arrTinh;
    TextView txtThongbao, txtTitle1, txtTitle2, txtTitle3,txtKetQua;
    Spinner spinner;
    LinearLayout linearLayoutTh;
    EditText edtInput;
    RadioGroup radioGroupGiai;
    RadioButton radAll, radDB;
    ArrayList<SoKet> soKetArrayList;
    SoKetAdapter soKetAdapter;
    ListView lstSoKet;
    ArrayList<String> valueArray;
    Button btnXemKetQua, btnGui;

    public LoKetFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        arrTinh = new ArrayList<>();
        valueArray = new ArrayList<>();
        spinner = view.findViewById(R.id.ComboboxTinh);
        txtThongbao = view.findViewById(R.id.idthongbaoLoKet);
        txtTitle1 = view.findViewById(R.id.idTitleDong1);
        txtTitle2 = view.findViewById(R.id.idTitleDong2);
        txtTitle3 = view.findViewById(R.id.idTitleDong3);
        lstSoKet = view.findViewById(R.id.ListViewSoKet);
        btnXemKetQua = view.findViewById(R.id.buttonXemKetQua);
        AnhXa();
        soKetArrayList = new ArrayList<>();
        soKetAdapter = new SoKetAdapter(getContext(), soKetArrayList);
        lstSoKet.setAdapter(soKetAdapter);
        edtInput.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements tinh = document.select("div.column2");
                for (Element element : tinh) {
                    Elements tong = element.select("option");
                    for (Element elm : tong) {
                        Element elms = elm.select("option").first();
                        Log.d("ihihi", elms.attr("value"));
                        valueArray.add(elm.attr("value"));
                        arrTinh.add(elms.text());
                    }
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrTinh);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);

                btnGui.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loto = String.valueOf(edtInput.getText());
                        if(edtInput.length() ==0)
                        {
                            Toast.makeText(getContext(), "Bạn phải nhập cặp số", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            txtThongbao.setVisibility(View.GONE);
                            lstSoKet.setVisibility(View.GONE);
                            linearLayoutTh.setVisibility(View.GONE);
                            txtKetQua.setVisibility(View.VISIBLE);
                            postDataGui(loto, "2");
                        }
                    }
                });

                btnXemKetQua.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.d("valueofspin", spinner.getSelectedItemId() + "");
                        txtThongbao.setVisibility(View.VISIBLE);
                        lstSoKet.setVisibility(View.VISIBLE);
                        linearLayoutTh.setVisibility(View.VISIBLE);
                        txtKetQua.setVisibility(View.GONE);
                        for (int i = 0; i < valueArray.size(); i++) {
                            final int j = i;
                            if (i == spinner.getSelectedItemId()) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (radAll.isChecked()) {

                                            soKetArrayList.clear();
                                            soKetAdapter.notifyDataSetChanged();
                                            postData("2", valueArray.get(j));
                                        }
                                        else if(radDB.isChecked()){
                                            soKetArrayList.clear();
                                            soKetAdapter.notifyDataSetChanged();
                                            postData("1", valueArray.get(j));
                                        }

                                    }
                                });

                            }
                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);


    }
    public void postData(final String prizeType, final String lotteryId) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlReport, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                //table lo ket
                Element theP = document.select("p").first();
                txtThongbao.setText(theP.text());

                //Get t head
                Elements tables = document.select("table[class=table text-center]");
                Log.d("tableshihi", tables.text());
                for (Element element : tables) {
                    Elements dongHead = element.select("thead").select("tr");
                    for (Element tr : dongHead) {
                        Element t1 = tr.select("th").first();
                        Element t2 = tr.select("th").get(1);
                        Element t3 = tr.select("th").last();
                        txtTitle1.setText(t1.text());
                        txtTitle2.setText(t2.text());
                        txtTitle3.setText(t3.text());
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
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("prizeType", prizeType);
                hashMap.put("lotteryId", lotteryId);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void postDataGui(final String lotoNumber, final String prizeType) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlReport, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                //table lo ket
                Element theP = document.select("p").first();
                txtKetQua.setText(theP.text());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("lotoNumber", lotoNumber);
                hashMap.put("prizeType", prizeType);
                return hashMap;
            }
        };

        requestQueue.add(stringRequest);
    }

    public void AnhXa() {
        radAll = getView().findViewById(R.id.radioAll);
        radDB = getView().findViewById(R.id.radioDB);
        btnGui = getView().findViewById(R.id.buttonLoKetGui1);
        edtInput = getView().findViewById(R.id.loketText);
        linearLayoutTh = getView().findViewById(R.id.LinearLayoutThLoto);
        txtKetQua = getView().findViewById(R.id.loketKetQua);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lo_ket, container, false);
    }
}