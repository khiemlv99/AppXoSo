package com.example.demoappsoso.ketqua;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class XoSoAdapter extends BaseAdapter {

    ArrayList<XoSo> arrayList;
    Context context;
    private int layout;

    public XoSoAdapter(ArrayList<XoSo> arrayList, Context context, int layout) {
        this.arrayList = arrayList;
        this.context = context;
        this.layout = layout;
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
        convertView = inflater.inflate(layout,null);
        TextView txtdong1 = convertView.findViewById(R.id.statusid);
        TextView txtdong2  = convertView.findViewById(R.id.ketquaid);
        XoSo xoSo = (XoSo) getItem(position);
        txtdong1.setText(xoSo.getDong1());
        txtdong2.setText(xoSo.getDong2());

        String ii = arrayList.get(0).getDong2();
        String iii = arrayList.get(arrayList.size()-1).getDong2();

        for(int i=0;i<arrayList.size();i++)
        {
            if(arrayList.get(position).getDong2().equals(ii) || arrayList.get(position).getDong2().equals(iii) )
            {
                txtdong2.setTextColor(-65536);
            }
        }
        Log.d("aaa",convertView.toString());
        return convertView;
    }
}
