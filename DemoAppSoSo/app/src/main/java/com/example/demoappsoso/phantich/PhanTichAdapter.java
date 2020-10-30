package com.example.demoappsoso.phantich;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.demoappsoso.R;

import java.util.ArrayList;

public class PhanTichAdapter extends BaseAdapter {
    Context context;
    ArrayList<PhanTich> arrayList;

    public PhanTichAdapter(Context context, ArrayList<PhanTich> arrayList) {
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
        convertView = inflater.inflate(R.layout.dong_phan_tich,null);
        ImageView imageView = convertView.findViewById(R.id.Image2222);
        TextView textView = convertView.findViewById(R.id.TextView2);
        PhanTich phanTich = arrayList.get(position);
        Glide.with(context).load("https:" +phanTich.getDong1() ).into(imageView);
        textView.setText(phanTich.getDong2());

        return convertView;
    }
}
