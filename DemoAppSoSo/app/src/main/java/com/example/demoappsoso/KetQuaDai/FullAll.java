package com.example.demoappsoso.KetQuaDai;

import java.util.ArrayList;

public class FullAll {
    private ArrayList<KetQuaDMT> ketQuaDMTArrayList;
    private ArrayList<LoToDaiMienTrung> loToDaiMienTrungs;

    public FullAll(ArrayList<KetQuaDMT> ketQuaDMTArrayList, ArrayList<LoToDaiMienTrung> loToDaiMienTrungs) {
        this.ketQuaDMTArrayList = ketQuaDMTArrayList;
        this.loToDaiMienTrungs = loToDaiMienTrungs;
    }

    public ArrayList<KetQuaDMT> getKetQuaDMTArrayList() {
        return ketQuaDMTArrayList;
    }

    public void setKetQuaDMTArrayList(ArrayList<KetQuaDMT> ketQuaDMTArrayList) {
        this.ketQuaDMTArrayList = ketQuaDMTArrayList;
    }

    public ArrayList<LoToDaiMienTrung> getLoToDaiMienTrungs() {
        return loToDaiMienTrungs;
    }

    public void setLoToDaiMienTrungs(ArrayList<LoToDaiMienTrung> loToDaiMienTrungs) {
        this.loToDaiMienTrungs = loToDaiMienTrungs;
    }
}
