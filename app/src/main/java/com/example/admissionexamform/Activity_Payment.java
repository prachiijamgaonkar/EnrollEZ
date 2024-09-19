package com.example.admissionexamform;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.model.modelAdmissionDetails;
import com.example.admissionexamform.model.modelBacklogExamDetails;
import com.example.admissionexamform.model.modelExamDetails;
import com.example.admissionexamform.model.modelPayment;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Activity_Payment extends AppCompatActivity {

    TextView tvpaymenthistory;
    EditText etbanktransactionID, etqrtransactionID;
    Button btnbankdetails, btnqrcode, btncashpaid;

    DatabaseReference databasePayment;

    String fees = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tvpaymenthistory = (TextView) findViewById(R.id.tvPaymentHistory);
        etbanktransactionID = (EditText) findViewById(R.id.etBankTransactionID);
        etqrtransactionID = (EditText) findViewById(R.id.etQRTransactionID);
        btnbankdetails = (Button) findViewById(R.id.btnBankDetails);
        btnqrcode = (Button) findViewById(R.id.btnQRCode);
        btncashpaid = (Button) findViewById(R.id.btnCashPaid);

        FirebaseApp.initializeApp(Activity_Payment.this);
        databasePayment = FirebaseDatabase.getInstance().getReference("College_Database");

        databasePayment.child(AppClass.getFormName())
                .child(AppClass.getUserNo())
                .child("Payment_Details").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        modelPayment cmodel = snapshot.getValue(modelPayment.class);

                        if(snapshot.exists()) {
                            String text = "Payment Done\n Mode of Payment:- " + cmodel.getModeofPayment() + "\n";
                                text = text + " Total Payment:- " + cmodel.getAmount() + " Rs.\n";
                                if(!cmodel.getTransactionID().equals("")) {
                                 text = text + " Transaction ID:- " + cmodel.getTransactionID() + "\n";
                                }
                                text = text + "Payment Date:- " + cmodel.getPaymentDate();
                            tvpaymenthistory.setText(text);

                            etbanktransactionID.setEnabled(false);
                            etqrtransactionID.setEnabled(false);
                            btnbankdetails.setEnabled(false);
                            btnqrcode.setEnabled(false);
                            btncashpaid.setEnabled(false);
                        }
                        else{
                            if(AppClass.getFormName().equals("Admission_Form")){
                                databasePayment.child("Admission_Details").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        modelAdmissionDetails cmodel = snapshot.getValue(modelAdmissionDetails.class);
                                        if(AppClass.getCategory().equals("Open")){
                                            fees = cmodel.getOpen();
                                        }else if(AppClass.getCategory().equals("OBC")){
                                            fees = cmodel.getOBC();
                                        }else if(AppClass.getCategory().equals("SC")){
                                            fees = cmodel.getSC();
                                        }else if(AppClass.getCategory().equals("ST")){
                                            fees = cmodel.getST();
                                        }else if(AppClass.getCategory().equals("SBC")){
                                            fees = cmodel.getSBC();
                                        }else if(AppClass.getCategory().equals("ESBC")){
                                            fees = cmodel.getESBC();
                                        }else if(AppClass.getCategory().equals("VJ_DT")){
                                            fees = cmodel.getVJ_DT();
                                        }else if(AppClass.getCategory().equals("NT1")){
                                            fees = cmodel.getNT1();
                                        }else if(AppClass.getCategory().equals("NT2")){
                                            fees = cmodel.getNT2();
                                        }else if(AppClass.getCategory().equals("NT3")){
                                            fees = cmodel.getNT3();
                                        }else if(AppClass.getCategory().equals("EWS")){
                                            fees = cmodel.getEWS();
                                        }

                                        tvpaymenthistory.setText("Admission Date:-" + cmodel.getAdmissionDate() +
                                                "\nAdmission Fees:-" + fees);
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else if(AppClass.getFormName().equals("Exam_Form")){

                                databasePayment.child("Exam_Details").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        modelExamDetails cmodel = snapshot.getValue(modelExamDetails.class);
                                        String examdate = cmodel.getExamDate();

                                        Date c = Calendar.getInstance().getTime();
                                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                        String currentdate = df.format(c);

                                        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), currentdate, examdate);
                                        if(dateDifference < 0) {
                                            int latecharges = (dateDifference * -1) * 100;
                                            int totalfees = Integer.parseInt(cmodel.getExamFees()) + latecharges;
                                            tvpaymenthistory.setText("Last Date:-" + examdate + "\n Exam Fees:-" + cmodel.getExamFees()
                                                    + "\n Late Charges:-" + cmodel.getExamFees() + "\n(Rs. 100/- Per Day)\n"
                                                    + "Total Fees:-" + totalfees );

                                            fees = String.valueOf(totalfees);
                                        }
                                        else{
                                            tvpaymenthistory.setText("Last Date:-" + examdate + "\n Exam Fees:-" + cmodel.getExamFees());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                            else if(AppClass.getFormName().equals("Backlog_Exam_Form")){

                                databasePayment.child("Backlog_Exam_Details").addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        modelBacklogExamDetails cmodel = snapshot.getValue(modelBacklogExamDetails.class);
                                        String examdate = cmodel.getExamDate();

                                        Date c = Calendar.getInstance().getTime();
                                        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                        String currentdate = df.format(c);

                                        int dateDifference = (int) getDateDiff(new SimpleDateFormat("dd/MM/yyyy"), currentdate, examdate);
                                        if(dateDifference < 0) {
                                            int latecharges = (dateDifference * -1) * 100;
                                            int totalfees = Integer.parseInt(cmodel.getExamFees()) + latecharges;
                                            tvpaymenthistory.setText("Last Date:-" + examdate + "\n Exam Fees:-" + cmodel.getExamFees()
                                                    + "\n Late Charges:-" + cmodel.getExamFees() + "\n(Rs. 100/- Per Day)\n"
                                                    + "Total Fees:-" + totalfees );

                                            fees = String.valueOf(totalfees);
                                        }
                                        else{
                                            tvpaymenthistory.setText("Last Date:-" + examdate + "\n Exam Fees:-" + cmodel.getExamFees());
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        btnbankdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etbanktransactionID.getText())){
                    modelPayment modeladd = new modelPayment();

                    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    modeladd.setModeofPayment("Bank");
                    modeladd.setTransactionID(etbanktransactionID.getText().toString());
                    modeladd.setPaymentDate(date);
                    modeladd.setAmount(fees);

                    databasePayment.child(AppClass.getFormName())
                            .child(AppClass.getUserNo())
                            .child("Payment_Details")
                            .setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Payment.this);
                                    builder.setMessage("Form Submitted \n Payment Successful!")
                                            .setPositiveButton("OK", null).show();
                                }
                            });
                }
                else{
                    Toast.makeText(Activity_Payment.this,"Please! Enter Bank Transaction ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnqrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(etqrtransactionID.getText())){
                    modelPayment modeladd = new modelPayment();

                    String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                    modeladd.setModeofPayment("QR Code");
                    modeladd.setTransactionID(etqrtransactionID.getText().toString());
                    modeladd.setPaymentDate(date);
                    modeladd.setAmount(fees);

                    databasePayment.child(AppClass.getFormName())
                            .child(AppClass.getUserNo())
                            .child("Payment_Details")
                            .setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Payment.this);
                                    builder.setMessage("Form Submitted \n Payment Successful!")
                                            .setPositiveButton("OK", null).show();
                                }
                            });
                }
                else{
                    Toast.makeText(Activity_Payment.this,"Please! Enter Bank Transaction ID", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btncashpaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelPayment modeladd = new modelPayment();

                String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                modeladd.setModeofPayment("Cash");
                modeladd.setTransactionID("");
                modeladd.setPaymentDate(date);
                modeladd.setAmount(fees);

                databasePayment.child(AppClass.getFormName())
                        .child(AppClass.getUserNo())
                        .child("Payment_Details")
                        .setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                                AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Payment.this);
                                builder.setMessage("Form Submission on Hold till the cash paid on cash counter.")
                                        .setPositiveButton("OK", null).show();
                            }
                        });
            }
        });
    }

    public static long getDateDiff(SimpleDateFormat format, String oldDate, String newDate) {
        try {
            return TimeUnit.DAYS.convert(format.parse(newDate).getTime() - format.parse(oldDate).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}