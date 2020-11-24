package com.example.demoappsoso.TraCuu;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
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
import java.util.Date;
import java.util.Locale;

public class TheoTinh_Fragment extends Fragment {

    String url = "https://xoso.com.vn/xo-so-theo-dai.html";
    TextView edtChonNgay;
    TextView txtListLink, txtLotoD1, txtLoToD2, txtLoToD3, txtLoToD4;
    TextView gdb, gdb1, g1, g11, g2, g21, g22, g3, g31, g311, g32, g322, g33, g333, g4, g41, g42, g43,
            g44, g5, g51, g511, g52, g522, g53, g533, g6, g61, g62, g63, g7, g71, g72, g73, g74;
    TextView tt1, tt2, tt3, tt4, tt5, tt6;

    TextView gdbi, gdb1i, g1i, g11i, g2i, g21i, g3i, g31i, g32i, g4i, g41i, g411i, g42i, g422i, g4222i, g43i,
            g433i, g5i, g51i, g6i, g61i, g62i, g63i, g7i, g71i, g8i, g81i;
    Spinner spinnerTinh;
    Button btnTraCuu;
    ListView  lstLoTo;
    ScrollView scrollViewMain;
    ArrayList<String> arrTinh;
    LinearLayout linearLayoutMienBac, linearLayoutMienNam,linearLayoutMain;
    ArrayList<String> arrLinkTinh;
    ArrayList<TraCuu> traCuus;
    ArrayList<LoToTraCuu> loToTraCuus;
    LoToTraCuuAdapter loToTraCuuAdapter;

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
        Hide();
        lstLoTo.setFocusable(false);

        linearLayoutMienBac.setVisibility(View.GONE);
        linearLayoutMienNam.setVisibility(View.GONE);
        String currentTime = new SimpleDateFormat("h:mm", Locale.getDefault()).format(new Date());
        if(currentTime.compareTo("5:15") >0 && currentTime.compareTo("5:31")<0){
            return;
        }
        else {
            linearLayoutMienBac.setVisibility(View.GONE);
            linearLayoutMienNam.setVisibility(View.GONE);
            getAllCity();
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChonNgay();
            }
        });


        loToTraCuuAdapter = new LoToTraCuuAdapter(getContext(), loToTraCuus);
        lstLoTo.setAdapter(loToTraCuuAdapter);

//        scrollViewMain.post(new Runnable() {
//            @Override
//            public void run() {
//                scrollViewMain.fullScroll(ScrollView.FOCUS_UP);
//            }
//        });
    }

    private void ChonNgay() {
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), AlertDialog.THEME_HOLO_LIGHT, new DatePickerDialog.OnDateSetListener() {
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
        scrollViewMain = getView().findViewById(R.id.idScrollViewMain);
        lstLoTo = getView().findViewById(R.id.ListViewLoToTraCuuTheoTinh);
        txtListLink = getView().findViewById(R.id.ListLinkTraCuu);
        txtLotoD1 = getView().findViewById(R.id.idLotoTraCuuDong1);
        txtLoToD2 = getView().findViewById(R.id.idLotoTraCuuDong2);
        txtLoToD3 = getView().findViewById(R.id.idLotoTraCuuDong3);
        txtLoToD4 = getView().findViewById(R.id.idLotoTraCuuDong4);
        linearLayoutMienBac = getView().findViewById(R.id.LinearLayoutMienBac);
        linearLayoutMienNam = getView().findViewById(R.id.LinearLaoutMienNam);
        linearLayoutMain = getView().findViewById(R.id.linearLayoutMain);
        AnhXaXoSo();
        AnhXaXoSoKhacMienBac();
    }

    public void AnhXaXoSo() {
        gdb = getView().findViewById(R.id.gdb);
        gdb1 = getView().findViewById(R.id.gdb1);
        g1 = getView().findViewById(R.id.g1);
        g11 = getView().findViewById(R.id.g11);
        g2 = getView().findViewById(R.id.g2);
        g21 = getView().findViewById(R.id.g21);
        g22 = getView().findViewById(R.id.g22);
        g3 = getView().findViewById(R.id.g3);
        g31 = getView().findViewById(R.id.g31);
        g311 = getView().findViewById(R.id.g311);
        g32 = getView().findViewById(R.id.g32);
        g322 = getView().findViewById(R.id.g322);
        g33 = getView().findViewById(R.id.g33);
        g333 = getView().findViewById(R.id.g333);
        g4 = getView().findViewById(R.id.g4);
        g41 = getView().findViewById(R.id.g41);
        g42 = getView().findViewById(R.id.g42);
        g43 = getView().findViewById(R.id.g43);
        g44 = getView().findViewById(R.id.g44);
        g5 = getView().findViewById(R.id.g5);
        g51 = getView().findViewById(R.id.g51);
        g511 = getView().findViewById(R.id.g511);
        g52 = getView().findViewById(R.id.g52);
        g522 = getView().findViewById(R.id.g522);
        g53 = getView().findViewById(R.id.g53);
        g533 = getView().findViewById(R.id.g533);

        g6 = getView().findViewById(R.id.g6);
        g61 = getView().findViewById(R.id.g61);
        g62 = getView().findViewById(R.id.g62);
        g63 = getView().findViewById(R.id.g63);
        g7 = getView().findViewById(R.id.g7);
        g71 = getView().findViewById(R.id.g71);
        g72 = getView().findViewById(R.id.g72);
        g73 = getView().findViewById(R.id.g73);
        g74 = getView().findViewById(R.id.g74);

        tt1 = getView().findViewById(R.id.tt1);
        tt2 = getView().findViewById(R.id.tt2);
        tt3 = getView().findViewById(R.id.tt3);
        tt4 = getView().findViewById(R.id.tt4);
        tt5 = getView().findViewById(R.id.tt5);
        tt6 = getView().findViewById(R.id.tt6);

    }

    public void AnhXaXoSoKhacMienBac() {
        gdbi = getView().findViewById(R.id.gdbi);
        gdb1i = getView().findViewById(R.id.gdb1i);
        g1i = getView().findViewById(R.id.g1i);
        g11i = getView().findViewById(R.id.g11i);
        g2i = getView().findViewById(R.id.g2i);
        g21i = getView().findViewById(R.id.g21i);
        g3i = getView().findViewById(R.id.g3i);
        g31i = getView().findViewById(R.id.g31i);
        g32i = getView().findViewById(R.id.g32i);
        g4i = getView().findViewById(R.id.g4i);
        g41i = getView().findViewById(R.id.g41i);
        g411i = getView().findViewById(R.id.g411i);
        g42i = getView().findViewById(R.id.g42i);
        g422i = getView().findViewById(R.id.g422i);
        g4222i = getView().findViewById(R.id.g4222i);
        g43i = getView().findViewById(R.id.g43i);
        g433i = getView().findViewById(R.id.g433i);
        g5i = getView().findViewById(R.id.g5i);
        g51i = getView().findViewById(R.id.g51i);
        g6i = getView().findViewById(R.id.g6i);
        g61i = getView().findViewById(R.id.g61i);
        g62i = getView().findViewById(R.id.g62i);
        g63i = getView().findViewById(R.id.g63i);
        g7i = getView().findViewById(R.id.g7i);
        g71i = getView().findViewById(R.id.g71i);
        g8i = getView().findViewById(R.id.g8i);
        g81i = getView().findViewById(R.id.g81i);
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

                linearLayoutMienBac.setVisibility(View.VISIBLE);
                linearLayoutMienNam.setVisibility(View.GONE);
                loToTraCuus.clear();
                loToTraCuuAdapter.notifyDataSetChanged();
                GetDataFromLinkTinhMienBac(arrLinkTinh.get(0));

                btnTraCuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for (int i = 0; i < arrLinkTinh.size(); i++) {
                            final int j = i;
                            if (i == spinnerTinh.getSelectedItemId() && spinnerTinh.getSelectedItemId() == 0) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        linearLayoutMienNam.setVisibility(View.GONE);
                                        linearLayoutMienBac.setVisibility(View.VISIBLE);
                                        loToTraCuus.clear();

                                        loToTraCuuAdapter.notifyDataSetChanged();
                                        GetDataFromLinkTinhMienBac(arrLinkTinh.get(j));
                                    }
                                });

                            } else if (i == spinnerTinh.getSelectedItemId()) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        linearLayoutMienNam.setVisibility(View.VISIBLE);
                                        linearLayoutMienBac.setVisibility(View.GONE);
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
//                txtTitle.setText(getTitle.text());
                //list link
                Elements getListLink = getheader.select("div.site-link").get(1).select("a");
                Element getTitleSiteLink = getheader.select("div.site-link").get(1);
                String partTitle = getTitleSiteLink.text();
                String [] titles = partTitle.split(" ");
                String lastTitle = titles[titles.length-2]+" "+titles[titles.length-1];



                txtListLink.setText(getListLink.get(2).text() + "\n" + getListLink.get(1).text() + " " + lastTitle);



                String str = getListLink.get(2).text();
                String [] parts = str.split(" ");
                String part2 = parts[1];
                edtChonNgay.setText(part2);

                Elements tables = getSection.select("div.section-content").get(1).select("table[class = table-result]");
                Elements lotos = getSection.select("div.section-content").get(1).select("table[class = table-loto]");
                Elements trsDongTitle = tables.select("tr");

//                dong dau tien
                Element d1 = trsDongTitle.get(0).select("th").first();
                Elements d2 = trsDongTitle.get(0).select("td").select("span");
                tt1.setText(d2.get(0).text());
                tt3.setText(d2.get(1).text());
                tt5.setText(d2.get(2).text());
                tt2.setText(d2.get(3).text());
                tt4.setText(d2.get(4).text());
                tt6.setText(d2.get(5).text());


                //table ket qua mien bac

                //region Set text for all TextView

                Element gdbhi = trsDongTitle.get(1).select("td").first();
                Element gdb1hi = trsDongTitle.get(1).select("td").last();
                gdb.setText(gdbhi.text());
                gdb1.setText(gdb1hi.text());

                g1.setText(trsDongTitle.get(2).select("td").first().text());
                g11.setText(trsDongTitle.get(2).select("td").last().text());

                g2.setText(trsDongTitle.get(3).select("td").first().text());
                Elements tds2 = trsDongTitle.get(3).select("td").select("span");
                g21.setText(tds2.first().text());
                g22.setText(tds2.last().text());

                g3.setText(trsDongTitle.get(4).select("td").first().text());
                Elements tds3 = trsDongTitle.get(4).select("td").select("span");
                g31.setText(tds3.first().text());
                g32.setText(tds3.get(1).text());
                g33.setText(tds3.get(2).text());
                g311.setText(tds3.get(3).text());
                g322.setText(tds3.get(4).text());
                g333.setText(tds3.get(5).text());

                g4.setText(trsDongTitle.get(5).select("td").first().text());
                Elements tds4 = trsDongTitle.get(5).select("td").select("span");
                g41.setText(tds4.first().text());
                g42.setText(tds4.get(1).text());
                g43.setText(tds4.get(2).text());
                g44.setText(tds4.get(3).text());


                g5.setText(trsDongTitle.get(6).select("td").first().text());
                Elements tds5 = trsDongTitle.get(6).select("td").select("span");
                g51.setText(tds5.first().text());
                g52.setText(tds5.get(1).text());
                g52.setText(tds5.get(2).text());
                g511.setText(tds5.get(3).text());
                g522.setText(tds5.get(4).text());
                g533.setText(tds5.get(5).text());

                g6.setText(trsDongTitle.get(7).select("td").first().text());
                Elements tds6 = trsDongTitle.get(7).select("td").select("span");
                g61.setText(tds6.first().text());
                g62.setText(tds6.get(1).text());
                g63.setText(tds6.get(2).text());

                g7.setText(trsDongTitle.get(8).select("td").first().text());
                Elements tds7 = trsDongTitle.get(8).select("td").select("span");
                g71.setText(tds4.first().text());
                g72.setText(tds4.get(1).text());
                g73.setText(tds4.get(2).text());
                g74.setText(tds4.get(3).text());

                //endregion

                for (int i = 1; i < trsDongTitle.size(); i++) {
                    Elements tds = trsDongTitle.get(i).select("td");
                    Element t1 = tds.select("td").first();
                    Element t2 = tds.select("td").select("span").first();
                    Elements t3 = tds.select("td").select("span[class =col-xs-4 number-black-bold div-horizontal]");
//                    Log.d("reHIHIHI1", t2.attr("col-xs-4 number-black-bold div-horizontal") + "");


                    traCuus.add(new TraCuu(t1.text(), t2.text()));
                }

//                traCuuTheoTinhAdapter.notifyDataSetChanged();

                //getloto

                Elements trsDongLotos = lotos.select("tr");

                Element th1 = trsDongLotos.get(0).select("th").first();
                Element th2 = trsDongLotos.get(0).select("th").get(1);
                Element th3 = trsDongLotos.get(0).select("th").get(2);
                Element th4 = trsDongLotos.get(0).select("th").last();
                txtLotoD1.setText(th1.text());
                txtLoToD2.setText(th2.text());
                txtLoToD3.setText(th4.text());
                txtLoToD4.setText(th3.text());

                for (int i = 1; i < trsDongLotos.size(); i++) {
                    Elements ths = trsDongLotos.get(i).select("th");
                    Elements tds = trsDongLotos.get(i).select("td");
                    Element dauDau = ths.select("th").first();
                    Element dauCuoi = ths.select("th").last();
                    Element duoiDau = tds.select("td").first();
                    Element duoiCuoi = tds.select("td").last();

                    loToTraCuus.add(new LoToTraCuu(dauDau.text(), duoiDau.text(), duoiCuoi.text(), dauCuoi.text()));
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
                Elements getListLink = getheader.select("div.site-link").select("a");
//                txtTitle.setText(getTitle.text());
                //list link
                txtListLink.setText(getListLink.text());

                String str = getListLink.get(2).text();
                String [] parts = str.split(" ");
                String part2 = parts[1];
                edtChonNgay.setText(part2);

                Elements tables = getSection.select("div.section-content").get(0).select("table[class = table-result]");
                Elements lotos = getSection.select("div.section-content").get(0).select("table[class = table-loto]");
                Elements trsDongTitle = tables.select("tr");

//                dong dau tien
                Element d1 = trsDongTitle.get(0).select("th").first();
                Element d2 = trsDongTitle.get(0).select("td").first();
                txtListLink.setText(d2.text());

//region setText for TextView
                Element gdbhi = trsDongTitle.get(1).select("td").first();
                Element gdb1hi = trsDongTitle.get(1).select("td").last();
                g8i.setText(gdbhi.text());
                g81i.setText(gdb1hi.text());

                g7i.setText(trsDongTitle.get(2).select("td").first().text());
                g71i.setText(trsDongTitle.get(2).select("td").last().text());

                g6i.setText(trsDongTitle.get(3).select("td").first().text());
                Elements tds6 = trsDongTitle.get(3).select("td").select("span");
                g61i.setText(tds6.first().text());
                g62i.setText(tds6.get(1).text());
                g63i.setText(tds6.get(2).text());

                g5i.setText(trsDongTitle.get(4).select("td").first().text());
                g51i.setText(trsDongTitle.get(4).select("td").last().text());


                g4i.setText(trsDongTitle.get(5).select("td").first().text());
                Elements tds5 = trsDongTitle.get(5).select("td").select("span");
                g41i.setText(tds5.first().text());
                g42i.setText(tds5.get(1).text());
                g43i.setText(tds5.get(2).text());
                g411i.setText(tds5.get(3).text());
                g422i.setText(tds5.get(4).text());
                g433i.setText(tds5.get(5).text());
                g4222i.setText(tds5.get(6).text());


                g3i.setText(trsDongTitle.get(6).select("td").first().text());
                Elements tds3 = trsDongTitle.get(6).select("td").select("span");
                g31i.setText(tds3.first().text());
                g32i.setText(tds3.get(1).text());

                g2i.setText(trsDongTitle.get(7).select("td").first().text());
                g21i.setText(trsDongTitle.get(7).select("td").last().text());

                g1i.setText(trsDongTitle.get(8).select("td").first().text());
                g11i.setText(trsDongTitle.get(8).select("td").last().text());

                gdbi.setText(trsDongTitle.get(9).select("td").first().text());
                gdb1i.setText(trsDongTitle.get(9).select("td").last().text());

//endregion

//                traCuuTheoTinhAdapter.notifyDataSetChanged();
                //get tolo

                Elements trsDongLotos = lotos.select("tr");

                Element th1 = trsDongLotos.get(0).select("th").first();
                Element th2 = trsDongLotos.get(0).select("th").get(1);
                Element th3 = trsDongLotos.get(0).select("th").get(2);
                Element th4 = trsDongLotos.get(0).select("th").last();
                txtLotoD1.setText(th1.text());
                txtLoToD2.setText(th2.text());
                txtLoToD3.setText(th4.text());
                txtLoToD4.setText(th3.text());

                for (int i = 1; i < trsDongLotos.size(); i++) {
                    Elements ths = trsDongLotos.get(i).select("th");
                    Elements tds = trsDongLotos.get(i).select("td");
                    Element dauDau = ths.select("th").first();
                    Element dauCuoi = ths.select("th").last();
                    Element duoiDau = tds.select("td").first();
                    Element duoiCuoi = tds.select("td").last();

                    loToTraCuus.add(new LoToTraCuu(dauDau.text(), duoiDau.text(), duoiCuoi.text(), dauCuoi.text()));
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

    public void Hide(){
        linearLayoutMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });
        scrollViewMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(v);
                return false;
            }
        });
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}