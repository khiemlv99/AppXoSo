package com.example.demoappsoso.KetQuaDai;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.demoappsoso.R;

import java.util.ArrayList;

public class DaiMienTrungAdapter extends PagerAdapter {

    ArrayList<KetQuaDMT> ketQuaDMTArrayList;
    ArrayList<FullAll> fullAlls;
    private LayoutInflater inflater;

    public DaiMienTrungAdapter(ArrayList<FullAll> fullAlls, Context context) {
        this.fullAlls = fullAlls;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fullAlls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.dong_dai_ketqua_mientrung, container, false);

        //region Anh Xa
        TextView gtitle = view.findViewById(R.id.idXoSoTitle);
        TextView gdbi = view.findViewById(R.id.gdbi);
        TextView gdb1i = view.findViewById(R.id.gdb1i);
        TextView g1i = view.findViewById(R.id.g1i);
        TextView g11i = view.findViewById(R.id.g11i);
        TextView g2i = view.findViewById(R.id.g2i);
        TextView g21i = view.findViewById(R.id.g21i);
        TextView g3i = view.findViewById(R.id.g3i);
        TextView g31i = view.findViewById(R.id.g31i);
        TextView g32i = view.findViewById(R.id.g32i);
        TextView g4i = view.findViewById(R.id.g4i);
        TextView g41i = view.findViewById(R.id.g41i);
        TextView g411i = view.findViewById(R.id.g411i);
        TextView g42i = view.findViewById(R.id.g42i);
        TextView g422i = view.findViewById(R.id.g422i);
        TextView g4222i = view.findViewById(R.id.g4222i);
        TextView g43i = view.findViewById(R.id.g43i);
        TextView g433i = view.findViewById(R.id.g433i);
        TextView g5i = view.findViewById(R.id.g5i);
        TextView g51i = view.findViewById(R.id.g51i);
        TextView g6i = view.findViewById(R.id.g6i);
        TextView g61i = view.findViewById(R.id.g61i);
        TextView g62i = view.findViewById(R.id.g62i);
        TextView g63i = view.findViewById(R.id.g63i);
        TextView g7i = view.findViewById(R.id.g7i);
        TextView g71i = view.findViewById(R.id.g71i);
        TextView g8i = view.findViewById(R.id.g8i);
        TextView g81i = view.findViewById(R.id.g81i);

        TextView dau = view.findViewById(R.id.dau);
        TextView dau0 = view.findViewById(R.id.dau0);
        TextView dau1 = view.findViewById(R.id.dau1);
        TextView dau2 = view.findViewById(R.id.dau2);
        TextView dau3 = view.findViewById(R.id.dau3);
        TextView dau4 = view.findViewById(R.id.dau4);
        TextView daudau = view.findViewById(R.id.daudau);
        TextView dau5 = view.findViewById(R.id.dau5);
        TextView dau6 = view.findViewById(R.id.dau6);
        TextView dau7 = view.findViewById(R.id.dau7);
        TextView dau8 = view.findViewById(R.id.dau8);
        TextView dau9 = view.findViewById(R.id.dau9);
        TextView duoi = view.findViewById(R.id.duoi);
        TextView duoi0 = view.findViewById(R.id.duoi0);
        TextView duoi1 = view.findViewById(R.id.duoi1);
        TextView duoi2 = view.findViewById(R.id.duoi2);
        TextView duoi3 = view.findViewById(R.id.duoi3);
        TextView duoi4 = view.findViewById(R.id.duoi4);
        TextView duoiduoi = view.findViewById(R.id.duoiduoi);
        TextView duoi5 = view.findViewById(R.id.duoi5);
        TextView duoi6 = view.findViewById(R.id.duoi6);
        TextView duoi7 = view.findViewById(R.id.duoi7);
        TextView duoi8 = view.findViewById(R.id.duoi8);
        TextView duoi9 = view.findViewById(R.id.duoi9);

        //endregion

        FullAll fullAll = fullAlls.get(position);
        for (int i = 0; i < fullAll.getKetQuaDMTArrayList().size(); i++) {
            String[] part = fullAll.getKetQuaDMTArrayList().get(i).gtitle.split(" ");
            gtitle.setText(part[part.length - 5] + " " + part[part.length - 4] + " ,ngày " + part[part.length - 1]);
            g8i.setText(fullAll.getKetQuaDMTArrayList().get(i).g8i);
            g81i.setText(fullAll.getKetQuaDMTArrayList().get(i).g81i);
            g7i.setText(fullAll.getKetQuaDMTArrayList().get(i).g7i);
            g71i.setText(fullAll.getKetQuaDMTArrayList().get(i).g71i);
            g6i.setText(fullAll.getKetQuaDMTArrayList().get(i).g6i);
            g61i.setText(fullAll.getKetQuaDMTArrayList().get(i).g61i);
            g62i.setText(fullAll.getKetQuaDMTArrayList().get(i).g62i);
            g63i.setText(fullAll.getKetQuaDMTArrayList().get(i).g63i);
            g5i.setText(fullAll.getKetQuaDMTArrayList().get(i).g5i);
            g51i.setText(fullAll.getKetQuaDMTArrayList().get(i).g51i);
            g4i.setText(fullAll.getKetQuaDMTArrayList().get(i).g4i);
            g41i.setText(fullAll.getKetQuaDMTArrayList().get(i).g41i);
            g42i.setText(fullAll.getKetQuaDMTArrayList().get(i).g42i);
            g43i.setText(fullAll.getKetQuaDMTArrayList().get(i).g43i);
            g411i.setText(fullAll.getKetQuaDMTArrayList().get(i).g411i);
            g422i.setText(fullAll.getKetQuaDMTArrayList().get(i).g422i);
            g433i.setText(fullAll.getKetQuaDMTArrayList().get(i).g433i);
            g4222i.setText(fullAll.getKetQuaDMTArrayList().get(i).g4222i);
            g3i.setText(fullAll.getKetQuaDMTArrayList().get(i).g3i);
            g31i.setText(fullAll.getKetQuaDMTArrayList().get(i).g31i);
            g32i.setText(fullAll.getKetQuaDMTArrayList().get(i).g32i);
            g1i.setText(fullAll.getKetQuaDMTArrayList().get(i).g1i);
            g11i.setText(fullAll.getKetQuaDMTArrayList().get(i).g11i);
            g2i.setText(fullAll.getKetQuaDMTArrayList().get(i).g2i);
            g21i.setText(fullAll.getKetQuaDMTArrayList().get(i).g21i);
            gdbi.setText(fullAll.getKetQuaDMTArrayList().get(i).gdbi);
            gdb1i.setText(fullAll.getKetQuaDMTArrayList().get(i).gdb1i);

        }

        for(int j =0;j<fullAll.getLoToDaiMienTrungs().size();j++){
            dau.setText(fullAll.getLoToDaiMienTrungs().get(j).dau);
            dau0.setText(fullAll.getLoToDaiMienTrungs().get(j).dau0);
            dau1.setText(fullAll.getLoToDaiMienTrungs().get(j).dau1);
            dau2.setText(fullAll.getLoToDaiMienTrungs().get(j).dau2);
            dau3.setText(fullAll.getLoToDaiMienTrungs().get(j).dau3);
            dau4.setText(fullAll.getLoToDaiMienTrungs().get(j).dau4);
            daudau.setText(fullAll.getLoToDaiMienTrungs().get(j).daudau);
            dau5.setText(fullAll.getLoToDaiMienTrungs().get(j).dau5);
            dau6.setText(fullAll.getLoToDaiMienTrungs().get(j).dau6);
            dau7.setText(fullAll.getLoToDaiMienTrungs().get(j).dau7);
            dau8.setText(fullAll.getLoToDaiMienTrungs().get(j).dau8);
            dau9.setText(fullAll.getLoToDaiMienTrungs().get(j).dau9);

            duoi.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi);
            duoi0.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi0);
            duoi1.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi1);
            duoi2.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi2);
            duoi3.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi3);
            duoi4.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi4);
            duoiduoi.setText(fullAll.getLoToDaiMienTrungs().get(j).duoiduoi);
            duoi5.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi5);
            duoi6.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi6);
            duoi7.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi7);
            duoi8.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi8);
            duoi9.setText(fullAll.getLoToDaiMienTrungs().get(j).duoi9);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
