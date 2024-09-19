package com.example.admissionexamform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.classes.SessionManager;
import com.example.admissionexamform.model.modelNewUserList;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Activity_New_User extends AppCompatActivity {

    EditText etnew_user, etnew_number, etnew_password;
    Button btnnew_submit;

    SessionManager login_sessionManager;

    ProgressDialog loading = null;

    DatabaseReference databasenewuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        etnew_user = (EditText) findViewById(R.id.etNew_User);
        etnew_number = (EditText) findViewById(R.id.etNew_Number);
        etnew_password = (EditText) findViewById(R.id.etNew_Password);
        btnnew_submit = (Button) findViewById(R.id.btnNew_Submit);

        login_sessionManager = new SessionManager(Activity_New_User.this);

        FirebaseApp.initializeApp(this);
        databasenewuser = FirebaseDatabase.getInstance().getReference("College_Database");

        btnnew_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppConstant.isNetworkAvailable(Activity_New_User.this)) {

                    boolean isSbjit = etnew_number.getText().toString().contains("@sbjit.edu.in");

                    if(isSbjit){
                        if (!TextUtils.isEmpty(etnew_user.getText().toString())){
                            if(!TextUtils.isEmpty(etnew_number.getText().toString())){
                                if(!TextUtils.isEmpty(etnew_password.getText().toString())){

                                    modelNewUserList modeladd = new modelNewUserList();
                                    modeladd.setUserName(etnew_user.getText().toString());
                                    modeladd.setUserNo(etnew_number.getText().toString());
                                    modeladd.setUserPassword(etnew_password.getText().toString());

                                    databasenewuser.child("Users").child(etnew_password.getText().toString()).setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            AppClass.setUserNo(etnew_password.getText().toString());
                                            login_sessionManager.createLoginSession(etnew_password.getText().toString());
                                            Intent intent = new Intent(Activity_New_User.this, Activity_User.class);
                                            startActivity(intent);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            AppConstant.showErrorDialog(Activity_New_User.this,"Add New User","Unable to add new user.");
                                        }
                                    });

                                }
                                else{
                                    AppConstant.showErrorDialog(Activity_New_User.this,"Add New User","Please! Enter Password.");
                                    return;
                                }
                            }
                            else{
                                AppConstant.showErrorDialog(Activity_New_User.this,"Add New User","Please! Enter Number.");
                                return;
                            }
                        }
                        else{
                            AppConstant.showErrorDialog(Activity_New_User.this,"Add New User","Please! Enter Name.");
                            return;
                        }
                    }else{
                        AppConstant.showErrorDialog(Activity_New_User.this,"Add New User", "Invalid E-mail ID.");
                    }
                }else{
                    AppConstant.showErrorDialog(Activity_New_User.this,"Add New User","Internet connection required!");
                }
            }
        });
    }
}