package com.example.admissionexamform.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.admissionexamform.fragments.Backlog_Exam_Page1;
import com.example.admissionexamform.fragments.Backlog_Exam_Page2;

public class TabAdapter_BacklogExamForm  extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public TabAdapter_BacklogExamForm(Context context, @NonNull FragmentManager fm, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Backlog_Exam_Page1 backlogexamPage1 = new Backlog_Exam_Page1();
                return backlogexamPage1;
            case 1:
                Backlog_Exam_Page2 backlogexamPage2 = new Backlog_Exam_Page2();
                return backlogexamPage2;
            default:
                return null;
        }
    }

        @Override
        public int getCount() {
            return totalTabs;
        }
}
