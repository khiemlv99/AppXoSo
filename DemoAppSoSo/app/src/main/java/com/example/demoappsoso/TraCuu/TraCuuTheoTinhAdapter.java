package com.example.demoappsoso.TraCuu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class TraCuuTheoTinhAdapter extends BaseAdapter {
    Context context;
    ArrayList<TraCuu> traCuus;

    public TraCuuTheoTinhAdapter(Context context, ArrayList<TraCuu> traCuus) {
        this.context = context;
        this.traCuus = traCuus;
    }

    @Override
    public int getCount() {
        return traCuus.size();
    }

    @Override
    public Object getItem(int position) {
        return traCuus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.dong_tracuu_theotinh,null);
        TextView txtDong1 = convertView.findViewById(R.id.ketQuaTraCuuTheoTinhDong1);
        TextView txtDong2 = convertView.findViewById(R.id.ketQuaTraCuuTheoTinhDong2);

        TraCuu traCuu = traCuus.get(position);
        txtDong1.setText(traCuu.getDong1());
        txtDong2.setText(traCuu.getDong2());

        String d1 = traCuus.get(0).getDong1();
        String ddb = traCuus.get(traCuus.size()-1).getDong1();
        for(int i=0;i<traCuus.size();i++){
            if(traCuus.get(position).getDong1().equals(d1) ||traCuus.get(position).getDong1().equals(ddb)){
                txtDong1.setTextColor(-65536);
                txtDong2.setTextColor(-65536);
            }
        }



        return convertView;
    }
}
