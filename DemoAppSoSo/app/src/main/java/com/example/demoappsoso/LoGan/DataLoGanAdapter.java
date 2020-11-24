package com.example.demoappsoso.LoGan;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class DataLoGanAdapter extends BaseAdapter {
    Context context;
    ArrayList<DataLoGan> arrayList;

    public DataLoGanAdapter(Context context, ArrayList<DataLoGan> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.layout_data_logan,null);

        TextView txtDong1 = convertView.findViewById(R.id.idDong1);
        TextView txtDong2 = convertView.findViewById(R.id.idDong2);

        DataLoGan dataLoGan = arrayList.get(position);
        txtDong1.setText(dataLoGan.getDong1());
        txtDong2.setText(dataLoGan.getDong2());
        String d1 = arrayList.get(0).getDong1();
        String d2 = arrayList.get(11).getDong1();
        for(int i =0;i<arrayList.size();i++){
            if(arrayList.get(position).getDong1().equals(d1) || arrayList.get(position).getDong1().equals(d2)){
                txtDong1.setTextColor(Color.RED);
                txtDong2.setTextColor(Color.RED);
                txtDong1.setTextSize(20);
                txtDong2.setTextSize(20);
            }
        }
        if(position %2==0){
            convertView.setBackgroundColor(Color.rgb(231, 231, 231));
        }
        return convertView;
    }
}
