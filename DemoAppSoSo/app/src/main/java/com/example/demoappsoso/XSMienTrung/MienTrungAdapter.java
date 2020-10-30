package com.example.demoappsoso.XSMienTrung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class MienTrungAdapter extends BaseAdapter {
    ArrayList<MienTrung> arrayList;
    Context context;

    public MienTrungAdapter(ArrayList<MienTrung> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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

        convertView = inflater.inflate(R.layout.dong_mien_trung, null);

        TextView txtdong1 = convertView.findViewById(R.id.dong1PhanTich);
        TextView txtdong2 = convertView.findViewById(R.id.dong2PhanTich);
        TextView txtdong3 = convertView.findViewById(R.id.dong3PhanTich);
        MienTrung mienTrung = (MienTrung) getItem(position);
        txtdong1.setText(mienTrung.getDong1());
        txtdong2.setText(mienTrung.getDong2());
        txtdong3.setText(mienTrung.getDong3());

        String d1 = arrayList.get(0).getDong1();
        String ddb = arrayList.get(arrayList.size()-1).getDong1();
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(position).getDong1().equals(d1) ||arrayList.get(position).getDong1().equals(ddb)){
                txtdong2.setTextColor(-65536);
                txtdong3.setTextColor(-65536);
            }
        }

        return convertView;
    }
}
