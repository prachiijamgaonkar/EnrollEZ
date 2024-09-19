package com.example.admissionexamform.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.admissionexamform.fragments.Exam_Page1;
import com.example.admissionexamform.fragments.Exam_Page2;

public class TabAdapter_ExamForm extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public TabAdapter_ExamForm(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Exam_Page1 examPage1 = new Exam_Page1();
                return examPage1;
            case 1:
                Exam_Page2 examPage2 = new Exam_Page2();
                return examPage2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
