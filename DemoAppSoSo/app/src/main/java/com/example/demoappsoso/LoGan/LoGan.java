package com.example.demoappsoso.LoGan;

import com.android.volley.toolbox.StringRequest;

public class LoGan {
    private String title;
    private String value;
    private String data;

    public LoGan(String title, String value, String data) {
        this.title = title;
        this.value = value;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
