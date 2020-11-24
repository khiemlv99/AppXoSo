package com.example.demoappsoso.KetQuaDai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class DMTAdapter extends BaseAdapter {

    ArrayList<DMT> arrayList;
    Context context;

    public DMTAdapter(ArrayList<DMT> arrayList, Context context) {
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
        convertView = inflater.inflate(R.layout.dong_dai_mientrung,null);

        TextView txtDongMot = convertView.findViewById(R.id.textDaiMienTrung);

        DMT dmt = arrayList.get(position);
        txtDongMot.setText("Xổ số "+dmt.getDong1());
        return convertView;
    }
}
