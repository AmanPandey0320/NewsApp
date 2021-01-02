package com.dark_phoenix09.khabarilal.bookmarkDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bookmark")
public class model {

    @NonNull
    @PrimaryKey
    public String u;

    @ColumnInfo(name = "i")
    public  String i;
     @ColumnInfo(name = "t")
    public String t;
     @ColumnInfo(name = "d")
    public String d;
     @ColumnInfo(name = "s")
    public String s;
     @ColumnInfo(name = "a")
    public String a;

    @NonNull
    public String getU() {
        return u;
    }

    public void setU(@NonNull String u) {
        this.u = u;
    }

    public String getI() {
        return i;
    }

    public void setI(String i) {
        this.i = i;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
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

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }
}
