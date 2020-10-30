package com.example.demoappsoso.TraCuu;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DoSoLoto_Fragment extends Fragment {
    String urlTinh ="https://xoso.com.vn/do-so.html";
    String url = "https://xoso.com.vn/ThongKeAjax/DoSo?lotteryId=0&lotteryName=Mi%E1%BB%81n%20B%E1%BA%AFc&lotos=32&dayfr=23%2F09%2F2020&dayto=23%2F10%2F2020";
    Spinner spinnerTinh;
    ArrayList<String> arrayTinh;
    ArrayList<String> arrValueOfTinh;
    ArrayList<DoSo> doSoArrayList;
    DoSoAdapter doSoAdapter;
    LinearLayout linearLayout;
    ListView lstDoSo;
    Button btnTraCuu;
    EditText edtNgay1,edtNgay2,edtInputLoto;


    public DoSoLoto_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AnhXa();
        LoadNgayHienTai();
        arrayTinh = new ArrayList<>();
        arrValueOfTinh = new ArrayList<>();
        doSoArrayList = new ArrayList<>();
        doSoAdapter = new DoSoAdapter(getContext(),doSoArrayList);
        lstDoSo.setAdapter(doSoAdapter);
        linearLayout.setVisibility(View.GONE);
        getTinhTP();
    }
    private void LoadNgayHienTai() {
        final int MONTH = -30;
        final Calendar calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        Date date = new Date();
        edtNgay1.setText(getPastDate(date,MONTH));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        edtNgay2.setText(simpleDateFormat.format(calendar.getTime()));
        calendar.set(ngay,thang,nam);
    }

    private void AnhXa() {
        edtNgay1 = getView().findViewById(R.id.EditTraCuuDoLotoNgay1);
        edtNgay2 = getView().findViewById(R.id.EditTraCuuDoLotoNgay2);
        spinnerTinh = getView().findViewById(R.id.SpinnerDoSoLoto);
        linearLayout = getView().findViewById(R.id.LinearTitleTH);
        lstDoSo  = getView().findViewById(R.id.ListViewDoSo);
        btnTraCuu  = getView().findViewById(R.id.ButtonDoSoLotoTraCuu);
        edtInputLoto = getView().findViewById(R.id.editTextLoToDoSo);
    }
    public static String getPastDate(Date day, int interval)
    {
        try{

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            Calendar c = Calendar.getInstance();
            c.setTime(day);

            c.add(Calendar.DATE,interval);  //not sure

            return dateFormat.format(c.getTime());


        }catch (Exception exp)
        {
            return null;
        }
    }

    public void getTinhTP(){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlTinh, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);
                Elements getSelect = document.select("select[id = ddLotteries]");

                for(Element tinh : getSelect){
                    Elements hihi = tinh.select("option");
                    for(Element h : hihi){
                        arrayTinh.add(h.text());
                        arrValueOfTinh.add(h.attr("value"));
                    }
                }
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayTinh);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTinh.setAdapter(adapter);

                btnTraCuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String loto = String.valueOf(edtInputLoto.getText());
                        final String ngay1 = String.valueOf(edtNgay1.getText());
                        final String ngay2 = String.valueOf(edtNgay2.getText());
                        if(loto.length()==0){
                            Toast.makeText(getContext(), "Bạn hãy nhập dãy số!", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            linearLayout.setVisibility(View.VISIBLE);

                            for(int i =0;i<arrValueOfTinh.size();i++){
                                final int j = i;
                                if(i == spinnerTinh.getSelectedItemId()){
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            doSoArrayList.clear();
                                            doSoAdapter.notifyDataSetChanged();
                                            PostValue(arrValueOfTinh.get(j),loto,ngay1,ngay2);
                                        }
                                    });
                                }
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

    public void PostValue(final String lotteryId, final String lotos, final String dayfr, final String dayto){
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Document document = Jsoup.parse(response);

                Elements getTable = document.select("table[class=table text-center]").get(0).select("tbody");
                Elements getTr = getTable.select("tr");
                for(Element element : getTr){
                    Elements tds = element.select("td");
                    Element t1 = tds.select("td").first();
                    Element t2 = tds.select("td").get(1);
                    Element t3 = tds.select("td").last();
                    doSoArrayList.add(new DoSo(t1.text(),t2.text(),t3.text()));
                }

                doSoAdapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> hashMap = new HashMap<String, String>();
                hashMap.put("lotteryId",lotteryId);
                hashMap.put("lotos",lotos);
                hashMap.put("dayfr",dayfr);
                hashMap.put("dayto",dayto);
                return  hashMap;
            }
        };
        requestQueue.add(stringRequest);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_do_so_loto_, container, false);
    }
}