package com.example.admissionexamform;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.services.MyService;
import com.example.admissionexamform.services.MyWorker;

import java.util.concurrent.TimeUnit;

public class Activity_User extends AppCompatActivity {

    Button btnadmission, btnexam, btnbacklock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        btnadmission = (Button) findViewById(R.id.btnAdmission);
        btnexam = (Button) findViewById(R.id.btnExam);
        btnbacklock = (Button) findViewById(R.id.btnBacklock);

        startServiceViaWorker();
        startService();

        btnadmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClass.setFormName("Admission_Form");
                Intent intent = new Intent(Activity_User.this, Activity_Admission_Form.class);
                startActivity(intent);
            }
        });

        btnexam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClass.setFormName("Exam_Form");
                Intent intent = new Intent(Activity_User.this, Activity_Exam_Form.class);
                startActivity(intent);
            }
        });

        btnbacklock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppClass.setFormName("Backlog_Exam_Form");
                Intent intent = new Intent(Activity_User.this, Activity_Backlog_Exam_Form.class);
                startActivity(intent);
            }
        });
    }

    public void startService() {
        Log.d("TAG", "startService called");
        if (!MyService.isServiceRunning) {
            Intent serviceIntent = new Intent(this, MyService.class);
            ContextCompat.startForegroundService(this, serviceIntent);
        }
    }

    public void stopService() {
        Log.d("TAG", "stopService called");
        if (MyService.isServiceRunning) {
            Intent serviceIntent = new Intent(this, MyService.class);
            stopService(serviceIntent);
        }
    }

    public void startServiceViaWorker() {
        Log.d("TAG", "startServiceViaWorker called");
        String UNIQUE_WORK_NAME = "StartMyServiceViaWorker";
        WorkManager workManager = WorkManager.getInstance(this);

        // As per Documentation: The minimum repeat interval that can be defined is 15 minutes
        // (same as the JobScheduler API), but in practice 15 doesn't work. Using 16 here
        PeriodicWorkRequest request =
                new PeriodicWorkRequest.Builder(MyWorker.class, 16, TimeUnit.MINUTES).build();

        // to schedule a unique work, no matter how many times app is opened i.e. startServiceViaWorker gets called
        // do check for AutoStart permission
        workManager.enqueueUniquePeriodicWork(UNIQUE_WORK_NAME, ExistingPeriodicWorkPolicy.KEEP, request);

    }
}