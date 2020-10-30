package com.example.demoappsoso.TraCuu;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
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

public class TheoTinh_Fragment extends Fragment {

    String url = "https://xoso.com.vn/xo-so-theo-dai.html";
    EditText edtChonNgay;
    TextView txtDong1,txtDong2,txtTitle,txtListLink, txtLotoD1, txtLoToD2, txtLoToD3,txtLoToD4;
    Spinner spinnerTinh;
    Button btnTraCuu;
    ListView lstXoSo, lstLoTo;
    ArrayList<String> arrTinh;
    ArrayList<String> arrLinkTinh;
    ArrayList<TraCuu> traCuus;
    ArrayList<LoToTraCuu> loToTraCuus;
    LoToTraCuuAdapter loToTraCuuAdapter;
    TraCuuTheoTinhAdapter traCuuTheoTinhAdapter;

    public TheoTinh_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Calendar calendar = Calendar.getInstance();
        arrTinh = new ArrayList<>();
        arrLinkTinh = new ArrayList<>();
        traCuus = new ArrayList<>();
        loToTraCuus = new ArrayList<>();
        AnhXa();
        getAllCity();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
        edtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });
        traCuuTheoTinhAdapter = new TraCuuTheoTinhAdapter(getContext(), traCuus);
        lstXoSo.setAdapter(traCuuTheoTinhAdapter);
        loToTraCuuAdapter = new LoToTraCuuAdapter(getContext(),loToTraCuus);
        lstLoTo.setAdapter(loToTraCuuAdapter);
    }

    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtChonNgay.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, nam, thang, ngay);
        datePickerDialog.show();
    }

    private void AnhXa() {
        edtChonNgay = getView().findViewById(R.id.editTextChonNgayTheoTinh);
        spinnerTinh = getView().findViewById(R.id.spinerTraCuuTheoTinh);
        btnTraCuu = getView().findViewById(R.id.buttonTraCuuTheoTinh);
        txtDong1 = getView().findViewById(R.id.textViewTraCuuTheoTinhDong1);
        txtDong2 = getView().findViewById(R.id.textViewTraCuuTheoTinhDong2);
        lstXoSo = getView().findViewById(R.id.ListViewKetQuaTraCuuTheoTinh);
        lstLoTo = getView().findViewById(R.id.ListViewLoToTraCuuTheoTinh);
        txtTitle = getView().findViewById(R.id.TitleTraCuu);
        txtListLink = getView().findViewById(R.id.ListLinkTraCuu);
        txtLotoD1 = getView().findViewById(R.id.idLotoTraCuuDong1);
        txtLoToD2 = getView().findViewById(R.id.idLotoTraCuuDong2);
        txtLoToD3 = getView().findViewById(R.id.idLotoTraCuuDong3);
        txtLoToD4 = getView().findViewById(R.id.idLotoTraCuuDong4);
    }

    //get thành phố
    private void getAllCity() {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements getTable = document.select("table[class = table text-center]").select("tbody");
                for (Element td : getTable) {
                    Elements tds = td.select("td");
                    for (Element t1 : tds) {
                        Elements hihi = t1.select("a");
                        if (hihi.size() == 1) {
                            arrTinh.add(hihi.text());
                            arrLinkTinh.add(hihi.attr("href"));
                        }
                    }

                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrTinh);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTinh.setAdapter(adapter);

                btnTraCuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < arrLinkTinh.size(); i++) {
                            final int j =i;
                            if (i == spinnerTinh.getSelectedItemId() && spinnerTinh.getSelectedItemId() == 0) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        traCuus.clear();
                                        traCuuTheoTinhAdapter.notifyDataSetChanged();

                                        loToTraCuus.clear();
                                        loToTraCuuAdapter.notifyDataSetChanged();
                                        GetDataFromLinkTinhMienBac(arrLinkTinh.get(j));
                                    }
                                });

                            } else if (i == spinnerTinh.getSelectedItemId()) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        traCuus.clear();
                                        traCuuTheoTinhAdapter.notifyDataSetChanged();
                                        loToTraCuus.clear();
                                        loToTraCuuAdapter.notifyDataSetChanged();
                                        GetDataFromLinkTinhGetFirst(arrLinkTinh.get(j));
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

    public void GetDataFromLinkTinhMienBac(String url) {
        String full = "https://xoso.com.vn" + url;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, full, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements getSection = document.select("section[class=section]");
                Elements getheader = document.select("header[class=section-header]");
                //title
                Element getTitle = getheader.select("H1").first();
                txtTitle.setText(getTitle.text());
                //list link
                Element getListLink = getheader.select("div.site-link").get(1);
                txtListLink.setText(getListLink.text());

                Elements tables = getSection.select("div.section-content").get(1).select("table[class = table-result]");
                Elements lotos = getSection.select("div.section-content").get(1).select("table[class = table-loto]");
                Elements trsDongTitle = tables.select("tr");

//                dong dau tien
                Element d1 = trsDongTitle.get(0).select("th").first();
                Element d2 = trsDongTitle.get(0).select("td").first();
                txtDong1.setText(d1.text());
                txtDong2.setText(d2.text());

                //table ket qua mien bac

                for (int i = 1; i < trsDongTitle.size(); i++) {
                    Elements tds = trsDongTitle.get(i).select("td");
                    Element t1 = tds.select("td").first();
                    Element t2 = tds.select("td").last();
                    Log.d("reHIHIHI1", t1.text() + "");
                    Log.d("reHIHIHI2", t2.text() + "");

                    traCuus.add(new TraCuu(t1.text(),t2.text()));
                }

                traCuuTheoTinhAdapter.notifyDataSetChanged();

                //getloto

                Elements trsDongLotos  = lotos.select("tr");

                Element th1 = trsDongLotos.get(0).select("th").first();
                Element th2 = trsDongLotos.get(0).select("th").get(1);
                Element th3 = trsDongLotos.get(0).select("th").get(2);
                Element th4 = trsDongLotos.get(0).select("th").last();
                txtLotoD1.setText(th1.text());
                txtLoToD2.setText(th2.text());
                txtLoToD3.setText(th4.text());
                txtLoToD4.setText(th3.text());

                for(int i =1;i<trsDongLotos.size();i++){
                    Elements ths = trsDongLotos.get(i).select("th");
                    Elements tds = trsDongLotos.get(i).select("td");
                    Element dauDau = ths.select("th").first();
                    Element dauCuoi = ths.select("th").last();
                    Element duoiDau = tds.select("td").first();
                    Element duoiCuoi = tds.select("td").last();

                    loToTraCuus.add(new LoToTraCuu(dauDau.text(),duoiDau.text(),duoiCuoi.text(),dauCuoi.text()));
                }

                loToTraCuuAdapter.notifyDataSetChanged();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }


    public void GetDataFromLinkTinhGetFirst(String url) {
        String full = "https://xoso.com.vn" + url;
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, full, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements getSection = document.select("section[class=section]");
                Elements getheader = document.select("header[class=section-header]");
                Element getTitle = getheader.select("H1").first();
                Element getListLink = getheader.select("div.site-link").first();
                txtTitle.setText(getTitle.text());
                //list link
                txtListLink.setText(getListLink.text());

                Elements tables = getSection.select("div.section-content").get(0).select("table[class = table-result]");
                Elements lotos = getSection.select("div.section-content").get(0).select("table[class = table-loto]");
                Elements trsDongTitle = tables.select("tr");

//                dong dau tien
                Element d1 = trsDongTitle.get(0).select("th").first();
                Element d2 = trsDongTitle.get(0).select("td").first();
                txtDong1.setText(d1.text());
                txtDong2.setText(d2.text());
//                Log.d("reHIHIHI", d1.text()+ "\n" );
//                Log.d("reHIHIHI", d2.text()+ "\n" );
                //table ket qua mien bac

                for (int i = 1; i < trsDongTitle.size(); i++) {
                    Elements tds = trsDongTitle.get(i).select("td");
                    Element t1 = tds.select("td").first();
                    Element t2 = tds.select("td").last();
                    Log.d("reHIHIHI1", t1.text() + "");
                    Log.d("reHIHIHI2", t2.text() + "");

                    traCuus.add(new TraCuu(t1.text(), t2.text()));
                }

                traCuuTheoTinhAdapter.notifyDataSetChanged();
                //get tolo

                Elements trsDongLotos  = lotos.select("tr");

                Element th1 = trsDongLotos.get(0).select("th").first();
                Element th2 = trsDongLotos.get(0).select("th").get(1);
                Element th3 = trsDongLotos.get(0).select("th").get(2);
                Element th4 = trsDongLotos.get(0).select("th").last();
                txtLotoD1.setText(th1.text());
                txtLoToD2.setText(th2.text());
                txtLoToD3.setText(th4.text());
                txtLoToD4.setText(th3.text());

                for(int i =1;i<trsDongLotos.size();i++){
                    Elements ths = trsDongLotos.get(i).select("th");
                    Elements tds = trsDongLotos.get(i).select("td");
                    Element dauDau = ths.select("th").first();
                    Element dauCuoi = ths.select("th").last();
                    Element duoiDau = tds.select("td").first();
                    Element duoiCuoi = tds.select("td").last();

                    loToTraCuus.add(new LoToTraCuu(dauDau.text(),duoiDau.text(),duoiCuoi.text(),dauCuoi.text()));
                }

                loToTraCuuAdapter.notifyDataSetChanged();

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
        return inflater.inflate(R.layout.fragment_theo_tinh_, container, false);
    }
}