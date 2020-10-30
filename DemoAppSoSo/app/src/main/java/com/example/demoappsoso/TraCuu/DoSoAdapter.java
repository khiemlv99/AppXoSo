package com.example.demoappsoso.TraCuu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class DoSoAdapter extends BaseAdapter {
    Context context;
    ArrayList<DoSo> doSoArrayList;

    public DoSoAdapter(Context context, ArrayList<DoSo> doSoArrayList) {
        this.context = context;
        this.doSoArrayList = doSoArrayList;
    }

    @Override
    public int getCount() {
        return doSoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return doSoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.dong_do_so,null);
        TextView txtDong1 = convertView.findViewById(R.id.idDoSoDong1);
        TextView txtDong2 = convertView.findViewById(R.id.idDoSoDong2);
        TextView txtDong3 = convertView.findViewById(R.id.idDoSoDong3);

        DoSo doSo = doSoArrayList.get(position);
        txtDong1.setText(doSo.getDong1());
        txtDong2.setText(doSo.getDong2());
        txtDong3.setText(doSo.getDong3());
        return convertView;
    }

}
