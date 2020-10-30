package com.example.demoappsoso.ketqua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class lotoAdapter extends BaseAdapter {
    ArrayList<loto> arrayList;
    Context context;

    public lotoAdapter(ArrayList<loto> arrayList, Context context) {
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

        convertView = inflater.inflate(R.layout.dong_xo_so,null);

        TextView txtdong1 = convertView.findViewById(R.id.statusid);
        TextView txtdong2  = convertView.findViewById(R.id.ketquaid);
        loto loto = (loto) getItem(position);

        txtdong1.setText(loto.getDong1());
        txtdong2.setText(loto.getDong2());

        return convertView;
    }
}
