package com.example.demoappsoso.LoKet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class SoKetAdapter extends BaseAdapter {
    Context context;
    ArrayList<SoKet> soKetArrayList;

    public SoKetAdapter(Context context, ArrayList<SoKet> soKetArrayList) {
        this.context = context;
        this.soKetArrayList = soKetArrayList;
    }

    @Override
    public int getCount() {
        return soKetArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return soKetArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder{
        TextView txtDong1,txtDong2,txtDong3;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_so_ket,null);
            viewHolder.txtDong1 = convertView.findViewById(R.id.idSoKetDong1);
            viewHolder.txtDong2 = convertView.findViewById(R.id.idSoKetDong2);
            viewHolder.txtDong3 = convertView.findViewById(R.id.idSoKetDong3);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        SoKet soKet = soKetArrayList.get(position);
        viewHolder.txtDong1.setText(soKet.getDong1());
        viewHolder.txtDong2.setText(soKet.getDong2());
        viewHolder.txtDong3.setText(soKet.getDong3());
        return convertView;
    }
}
