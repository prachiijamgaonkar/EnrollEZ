package com.example.admissionexamform;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.example.admissionexamform.adapters.TabAdapter_AdmissionForm;
import com.example.admissionexamform.fragments.Admission_Page1;
import com.example.admissionexamform.fragments.Admission_Page2;
import com.google.android.material.tabs.TabLayout;

public class Activity_Admission_Form extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admission_form);

        tabLayout = findViewById(R.id.tabLayout_admission);
        viewPager = findViewById(R.id.viewPager_admission);
        tabLayout.addTab(tabLayout.newTab().setText("Page No. 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Page No. 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Page No. 3"));
//        tabLayout.addTab(tabLayout.newTab().setText("Page No. 4"));
//        tabLayout.addTab(tabLayout.newTab().setText("Page No. 5"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final TabAdapter_AdmissionForm adapter = new TabAdapter_AdmissionForm(this,getSupportFragmentManager(), tabLayout.getTabCount());
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