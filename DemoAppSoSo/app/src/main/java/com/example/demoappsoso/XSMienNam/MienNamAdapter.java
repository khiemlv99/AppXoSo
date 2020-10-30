package com.example.demoappsoso.XSMienNam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class MienNamAdapter extends BaseAdapter {
    Context context;
    ArrayList<MienNam> arrayList;

    public MienNamAdapter(Context context, ArrayList<MienNam> arrayList) {
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
        convertView = inflater.inflate(R.layout.dong_mien_nam,null);
        TextView txtGiai = convertView.findViewById(R.id.miennamgiai);
        TextView txtdong1 = convertView.findViewById(R.id.miennamdong1);
        TextView txtdong2 = convertView.findViewById(R.id.miennamdong2);
        TextView txtdong3 = convertView.findViewById(R.id.miennamdong3);
        TextView txtdong4 = convertView.findViewById(R.id.miennamdong4);

        MienNam mienNam = arrayList.get(position);
        txtGiai.setText(mienNam.getGiai());
        txtdong1.setText(mienNam.getDong1());
        txtdong2.setText(mienNam.getDong2());
        txtdong3.setText(mienNam.getDong3());
        txtdong4.setText(mienNam.getDong4());

        String d1 = arrayList.get(0).getGiai();
        String ddb = arrayList.get(arrayList.size()-1).getGiai();
        for(int i=0;i<arrayList.size();i++){
            if(arrayList.get(position).getGiai().equals(d1) ||arrayList.get(position).getGiai().equals(ddb)){
                txtdong1.setTextColor(-65536);
                txtdong4.setTextColor(-65536);
                txtdong2.setTextColor(-65536);
                txtdong3.setTextColor(-65536);
            }
        }
        return convertView;
    }
}
