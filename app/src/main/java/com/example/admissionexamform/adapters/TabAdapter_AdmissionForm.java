package com.example.admissionexamform.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.admissionexamform.fragments.Admission_Page1;
import com.example.admissionexamform.fragments.Admission_Page2;
import com.example.admissionexamform.fragments.Admission_Page3;
import com.example.admissionexamform.fragments.Admission_Page4;
import com.example.admissionexamform.fragments.Admission_Page5;

public class TabAdapter_AdmissionForm extends FragmentPagerAdapter {

    Context context;
    int totalTabs;


    public TabAdapter_AdmissionForm(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Admission_Page1 admissionPage1 = new Admission_Page1();
                return admissionPage1;
            case 1:
                Admission_Page2 admissionPage2 = new Admission_Page2();
                return admissionPage2;
            case 2:
                Admission_Page3 admissionPage3 = new Admission_Page3();
                return admissionPage3;
//            case 3:
//                Admission_Page4 admissionPage4 = new Admission_Page4();
//                return admissionPage4;
//            case 4:
//                Admission_Page5 admissionPage5 = new Admission_Page5();
//                return admissionPage5;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
