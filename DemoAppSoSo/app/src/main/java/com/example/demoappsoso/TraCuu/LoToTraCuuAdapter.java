package com.example.demoappsoso.TraCuu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class LoToTraCuuAdapter extends BaseAdapter {
    Context context;
    ArrayList<LoToTraCuu> arrayList;

    public LoToTraCuuAdapter(Context context, ArrayList<LoToTraCuu> arrayList) {
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
        convertView = inflater.inflate(R.layout.dong_tracuu_loto_theotinh,null );
        TextView txtDong1 = convertView.findViewById(R.id.loToTraCuuDong1);
        TextView txtDong2 = convertView.findViewById(R.id.loToTraCuuDong2);
        TextView txtDong3 = convertView.findViewById(R.id.loToTraCuuDong3);
        TextView txtDong4 = convertView.findViewById(R.id.loToTraCuuDong4);

        LoToTraCuu loToTraCuu = arrayList.get(position);
        txtDong1.setText(loToTraCuu.getDong1());
        txtDong2.setText(loToTraCuu.getDong2());
        txtDong3.setText(loToTraCuu.getDong3());
        txtDong4.setText(loToTraCuu.getDong4());

        return convertView;
    }
}
