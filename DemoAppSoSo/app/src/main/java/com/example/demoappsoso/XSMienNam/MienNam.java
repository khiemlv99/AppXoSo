package com.example.demoappsoso.XSMienNam;

public class MienNam {
    private String giai;
    private String dong1;
    private String dong2;
    private String dong3;
    private String dong4;

    public MienNam(String giai, String dong1, String dong2, String dong3) {
        this.giai = giai;
        this.dong1 = dong1;
        this.dong2 = dong2;
        this.dong3 = dong3;
    }

    public MienNam(String giai, String dong1, String dong2, String dong3, String dong4) {
        this.giai = giai;
        this.dong1 = dong1;
        this.dong2 = dong2;
        this.dong3 = dong3;
        this.dong4 = dong4;
    }

    public String getGiai() {
        return giai;
    }

    public void setGiai(String giai) {
        this.giai = giai;
    }

    public String getDong1() {
        return dong1;
    }

    public void setDong1(String dong1) {
        this.dong1 = dong1;
    }

    public String getDong2() {
        return dong2;
    }

    public void setDong2(String dong2) {
        this.dong2 = dong2;
    }

    public String getDong3() {
        return dong3;
    }

    public void setDong3(String dong3) {
        this.dong3 = dong3;
    }

    public String getDong4() {
        return dong4;
    }

    public void setDong4(String dong4) {
        this.dong4 = dong4;
    }
}
