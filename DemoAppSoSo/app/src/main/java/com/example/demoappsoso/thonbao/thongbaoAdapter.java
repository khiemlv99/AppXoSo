package com.example.demoappsoso.thonbao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.util.List;

public class thongbaoAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private List<item> list;

    public thongbaoAdapter(Context context, int layout, List<item> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(layout,null);

        TextView txtView = convertView.findViewById(R.id.textViewthongbao);
        item item = list.get(position);
        txtView.setText(item.getDong());
        return convertView;
    }
}
