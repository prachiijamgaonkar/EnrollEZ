package com.example.admissionexamform.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.admissionexamform.Activity_Exam_Form;
import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.classes.SessionManager;
import com.example.admissionexamform.model.modelApproved;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyService extends Service {

    private String TAG = "MyService";
    public static boolean isServiceRunning;
    private String CHANNEL_ID = "NOTIFICATION_CHANNEL";

    DatabaseReference databseNoti;
    SessionManager task_sessionManager;

    public MyService() {
        Log.d(TAG, "constructor called");
        isServiceRunning = false;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate called");
        android.os.Debug.waitForDebugger();
        task_sessionManager = new SessionManager(getBaseContext());
        createNotificationChannel();
        isServiceRunning = true;
        databseNoti = FirebaseDatabase.getInstance().getReference("College_Database");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (AppConstant.isNetworkAvailable(getBaseContext())) {

            String Login_Name = task_sessionManager.isLoggedIn();

            databseNoti.child("Admission_Form")
                    .child(Login_Name)
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelApproved cmodel= snapshot.getValue(modelApproved.class);
                                if (cmodel.getApproval().equals("approve")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJIT")
                                            .setContentText("Admission Form Approved")
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                                else if (cmodel.getApproval().equals("unapprove")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJIT")
                                            .setContentText("Admission Form Unapproved, Reason : " + cmodel.getReason())
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databseNoti.child("Exam_Form")
                    .child(Login_Name)
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelApproved cmodel= snapshot.getValue(modelApproved.class);
                                if (cmodel.getApproval().equals("approve")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJIT")
                                            .setContentText("Exam Form Approved")
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                                else if (cmodel.getApproval().equals("unapprove")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJIT")
                                            .setContentText("Exam Form Unapproved, Reason : " + cmodel.getReason())
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databseNoti.child("Backlog_Exam_Form")
                    .child(Login_Name)
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelApproved cmodel= snapshot.getValue(modelApproved.class);
                                if (cmodel.getApproval().equals("approve")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJT")
                                            .setContentText("Backlog Exam Form Approved")
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                                else if (cmodel.getApproval().equals("unapprove")) {
                                    Intent notificationIntent = new Intent(getBaseContext(), Activity_Exam_Form.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                                            0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
                                    Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                                            .setContentTitle("SBJIT")
                                            .setContentText("Backlog Exam Form Unapproved, Reason : " + cmodel.getReason())
                                            .setSmallIcon(R.drawable.noti_icon)
                                            .setContentIntent(pendingIntent)
                                            .setColor(getResources().getColor(R.color.colorAccent))
                                            .build();

                                    startForeground(1, notification);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else{
            Log.e("Scheduler","Internet connection required!");
        }


        return START_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String appName = getString(R.string.app_name);
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    appName,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy called");
        isServiceRunning = false;
        stopForeground(true);

        // call MyReceiver which will restart this service via a worker
        Intent broadcastIntent = new Intent(this, MyReceiver.class);
        sendBroadcast(broadcastIntent);

        super.onDestroy();
    }
}
