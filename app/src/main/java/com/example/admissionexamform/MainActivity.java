package com.example.admissionexamform;

import static com.example.admissionexamform.classes.AppConstant.isNetworkAvailable;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.classes.SessionManager;
import com.example.admissionexamform.model.modelAdmissionDetails;
import com.example.admissionexamform.model.modelBacklogExamDetails;
import com.example.admissionexamform.model.modelExamDetails;
import com.example.admissionexamform.model.modelNewUserList;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUsername_lg;
    EditText etPassword;
    Button btnLogin;
    Button btnCreateAccount;

    private ProgressDialog mainProgressBar;
    DatabaseReference databaseLogin;

    SessionManager login_sessionManager;

    String username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername_lg = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCreateAccount = (Button) findViewById(R.id.btnCreateAccount);

        mainProgressBar = new ProgressDialog(MainActivity.this);
        login_sessionManager = new SessionManager(MainActivity.this);

        FirebaseApp.initializeApp(this);
        databaseLogin = FirebaseDatabase.getInstance().getReference("College_Database");

        etUsername_lg.setText("upasanam.etc20@sbjit.edu.in");
        etPassword.setText("ET20084");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkAvailable(MainActivity.this)){
                    username = etUsername_lg.getText().toString();
                    password = etPassword.getText().toString();

                    boolean isSbjit = username.contains("@sbjit.edu.in");

                    if(isSbjit){
                        if(!TextUtils.isEmpty(username)){
                            if(!TextUtils.isEmpty(password)){
                                mainProgressBar.setTitle("Login Account");
                                mainProgressBar.setMessage("Please wait, while we are checking the credentials.");
                                mainProgressBar.setCanceledOnTouchOutside(false);
                                mainProgressBar.show();

                                AllowAccessToAccount(username, password);

                            }else{
                                Toast.makeText(MainActivity.this,"Please! Enter password.",Toast.LENGTH_LONG).show();
                            }
                        }else{
                            Toast.makeText(MainActivity.this, "Please! Enter username.",Toast.LENGTH_LONG).show();
                        }
                    }else{
                        AppConstant.showErrorDialog(MainActivity.this,"Login", "Invalid E-mail ID.");
                    }
                }else{
                    AppConstant.showErrorDialog(MainActivity.this,"Login","Internet connection required!");
                }
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_New_User.class);
                startActivity(intent);
            }
        });
    }

    private void AllowAccessToAccount(final String phoneno, final String password)
    {
//        modelAdmissionDetails modeladd = new modelAdmissionDetails();
//        modeladd.setAdmissionDate("05/12/2023");
//        modeladd.setESBC("23174");
//        modeladd.setEWS("67737");
//        modeladd.setNT1("23174");
//        modeladd.setNT2("23174");
//        modeladd.setNT3("23174");
//        modeladd.setOBC("67737");
//        modeladd.setSBC("23174");
//        modeladd.setSC("12300");
//        modeladd.setST("12300");
//        modeladd.setVJ_DT("23174");
//        modeladd.setOpen("112300");
//
//        databaseLogin.child("Admission_Details").setValue(modeladd)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void unused) {
//
//            }
//        });

        databaseLogin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.child("Users").child(password).exists())
                {
                    modelNewUserList usersData = dataSnapshot.child("Users").child(password).getValue(modelNewUserList.class);

                    if(usersData.getUserNo().equals(phoneno))
                    {
                        if(usersData.getUserPassword().equals(password))
                        {
                            AppClass.setUserID(username);
                            AppClass.setUserPassword(password);
                            AppClass.setUserNo(usersData.getUserPassword());

                            login_sessionManager.createLoginSession(password);

                            mainProgressBar.dismiss();
                            Intent intent = new Intent(MainActivity.this, Activity_User.class);
                            startActivity(intent);
                        }
                        else
                        {
                            mainProgressBar.dismiss();
                            Toast.makeText(MainActivity.this, "Password is incorrect.", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Account with " + phoneno + " do not exists.", Toast.LENGTH_SHORT).show();
                    mainProgressBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}