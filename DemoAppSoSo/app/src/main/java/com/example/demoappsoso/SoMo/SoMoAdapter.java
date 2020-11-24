package com.example.demoappsoso.SoMo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.demoappsoso.R;

import java.io.File;
import java.util.ArrayList;

public class SoMoAdapter extends BaseAdapter implements Filterable {
    Context context;
    ArrayList<SoMo> mSoMoDisplay;
    ArrayList<SoMo> mOriginalValues;


    public SoMoAdapter(Context context, ArrayList<SoMo> soMoArrayList) {
        this.context = context;
        this.mSoMoDisplay = soMoArrayList;
        this.mOriginalValues = soMoArrayList;
    }

    @Override
    public int getCount() {
        return mSoMoDisplay.size();
    }

    @Override
    public Object getItem(int position) {
        return mSoMoDisplay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.dong_somo,null);
        TextView txtTitle = convertView.findViewById(R.id.idSoMoTitle);
        TextView txtValue = convertView.findViewById(R.id.idMotaSoMo);

        SoMo soMo = mSoMoDisplay.get(position);
        txtTitle.setText(soMo.getTitle());
        txtValue.setText("Cặp số tương ứng: "+soMo.getValue());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<SoMo> filterArayList = new ArrayList<SoMo>();
                if(mOriginalValues == null){
                    mOriginalValues  = new ArrayList<>(mSoMoDisplay);
                }
                if(constraint == null || constraint.length() ==0){
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                }
                else {
                    constraint = constraint.toString().toLowerCase();
                    for(int i =0;i<mOriginalValues.size();i++){
                        String data = mOriginalValues.get(i).getTitle();
                        if(data.toLowerCase().startsWith(constraint.toString())){
                            filterArayList.add(new SoMo(mOriginalValues.get(i).getTitle(),mOriginalValues.get(i).getValue()));

                        }
                    }
                    results.count = filterArayList.size();
                    results.values = filterArayList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mSoMoDisplay = (ArrayList<SoMo>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
