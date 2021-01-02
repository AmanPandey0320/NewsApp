package com.dark_phoenix09.khabarilal;

public class dataModel {

    String t;//title
    String i;//image
    String d;//desc
    String s;//source
    String u;//url
    String a;//author

    public dataModel(){}  //needed

    public dataModel(String t, String i, String d, String s, String u, String a) {
        this.t = t;
        this.i = i;
        this.d = d;
        this.s = s;
        this.u = u;
        this.a = a;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
