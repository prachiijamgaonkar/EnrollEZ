package com.example.admissionexamform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.admissionexamform.adapters.TabAdapter_BacklogExamForm;
import com.google.android.material.tabs.TabLayout;

public class Activity_Backlog_Exam_Form extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backlog_exam_form);

        tabLayout = findViewById(R.id.tabLayout_BKExam);
        viewPager = findViewById(R.id.viewPager_BKExam);
        tabLayout.addTab(tabLayout.newTab().setText("Page No. 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Page No. 2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter_BacklogExamForm adapter = new TabAdapter_BacklogExamForm(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}