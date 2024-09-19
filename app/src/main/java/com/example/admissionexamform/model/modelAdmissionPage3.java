package com.example.admissionexamform.model;

import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.Exclude;

public class modelAdmissionPage3 {

    String Phone, MobilenoStu1, MobilenoStu2, MobilenoFat1, MobilenoFat2, Place, Date, Iagree1, Iagree2, key;

    public modelAdmissionPage3() {
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getMobilenoStu1() {
        return MobilenoStu1;
    }

    public void setMobilenoStu1(String mobilenoStu1) {
        MobilenoStu1 = mobilenoStu1;
    }

    public String getMobilenoStu2() {
        return MobilenoStu2;
    }

    public void setMobilenoStu2(String mobilenoStu2) {
        MobilenoStu2 = mobilenoStu2;
    }

    public String getMobilenoFat1() {
        return MobilenoFat1;
    }

    public void setMobilenoFat1(String mobilenoFat1) {
        MobilenoFat1 = mobilenoFat1;
    }

    public String getMobilenoFat2() {
        return MobilenoFat2;
    }

    public void setMobilenoFat2(String mobilenoFat2) {
        MobilenoFat2 = mobilenoFat2;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getIagree1() {
        return Iagree1;
    }

    public void setIagree1(String iagree1) {
        Iagree1 = iagree1;
    }

    public String getIagree2() {
        return Iagree2;
    }

    public void setIagree2(String iagree2) {
        Iagree2 = iagree2;
    }

    @Exclude
    public String getKey() {
        return key;
    }

    @Exclude
    public void setKey(String key) {
        this.key = key;
    }
}
