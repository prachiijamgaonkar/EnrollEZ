package com.example.admissionexamform.fragments;

import static com.example.admissionexamform.classes.AppConstant.isNetworkAvailable;
import static com.example.admissionexamform.classes.AppConstant.showErrorDialog_fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionexamform.Activity_Payment;
import com.example.admissionexamform.MainActivity;
import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.model.modelAdmissionPage3;
import com.example.admissionexamform.model.modelApproved;
import com.example.admissionexamform.model.modelParentSignature;
import com.example.admissionexamform.model.modelStudentSignature;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class Admission_Page3 extends Fragment {

    EditText etphone, etmobilenostu1, etmobilenostu2, etmobilenofat1, etmobilenofat2, etplace;
    TextView tvdate;
    CheckBox cbiagree1, cbiagree2;
    Button btnad_submit3, SelectImage1, UploadImage1, SelectImage2, UploadImage2;
    ImageView Sign1, Sign2;

    DatabaseReference databasePage3;

    private Uri selectedImageUri1, selectedImageUri2;
    FirebaseStorage storage;
    StorageReference storageReference;

    public Admission_Page3() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View AddmissionPage3 =  inflater.inflate(R.layout.fragment_admission__page3, container, false);

        FirebaseApp.initializeApp(getContext());
        databasePage3 = FirebaseDatabase.getInstance().getReference("College_Database");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        etphone = AddmissionPage3.findViewById(R.id.etad_phone);
        etmobilenostu1 = AddmissionPage3.findViewById(R.id.etad_mobilenostu1);
        etmobilenostu2 = AddmissionPage3.findViewById(R.id.etad_mobilenostu2);
        etmobilenofat1 = AddmissionPage3.findViewById(R.id.etad_mobilenofat1);
        etmobilenofat2 = AddmissionPage3.findViewById(R.id.etad_mobilenofat2);
        etplace = AddmissionPage3.findViewById(R.id.etad_place);
        tvdate = AddmissionPage3.findViewById(R.id.tvadpage3_date);
        cbiagree1 = AddmissionPage3.findViewById(R.id.cbad_iagree1);
        cbiagree2 = AddmissionPage3.findViewById(R.id.cbad_iagree2);
        btnad_submit3 = AddmissionPage3.findViewById(R.id.btnad_Submit3);

        SelectImage1 = AddmissionPage3.findViewById(R.id.btnad_SelectImage1);
        UploadImage1 = AddmissionPage3.findViewById(R.id.btnad_UploadImage1);
        SelectImage2 = AddmissionPage3.findViewById(R.id.btnad_SelectImage2);
        UploadImage2 = AddmissionPage3.findViewById(R.id.btnad_UploadImage2);
        Sign1 = AddmissionPage3.findViewById(R.id.ivad_Sign1);
        Sign2 = AddmissionPage3.findViewById(R.id.ivad_Sign2);

        if (isNetworkAvailable(getContext())){
            databasePage3.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Page_3").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelAdmissionPage3 cmodel = snapshot.getValue(modelAdmissionPage3.class);

                                etphone.setText(cmodel.getPhone());
                                etmobilenostu1.setText(cmodel.getMobilenoStu1());
                                etmobilenostu2.setText(cmodel.getMobilenoStu2());
                                etmobilenofat1.setText(cmodel.getMobilenoFat1());
                                etmobilenofat2.setText(cmodel.getMobilenoFat2());
                                etplace.setText(cmodel.getPlace());
                                tvdate.setText(cmodel.getDate());

                                if(cmodel.getIagree1().equals("checked")){
                                    cbiagree1.setChecked(true);
                                }else{
                                    cbiagree1.setChecked(false);
                                }

                                if(cmodel.getIagree2().equals("checked")){
                                    cbiagree2.setChecked(true);
                                }else{
                                    cbiagree2.setChecked(false);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databasePage3.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Student_Signature").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelStudentSignature cmodel = snapshot.getValue(modelStudentSignature.class);
                                if(!cmodel.getStudentSignature().isEmpty() && cmodel.getStudentSignature() != null) {
                                    new DownloadImageFromInternet(Sign1).execute(cmodel.getStudentSignature());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databasePage3.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Parent_Signature").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelParentSignature cmodel = snapshot.getValue(modelParentSignature.class);
                                if(!cmodel.getParentSignature().isEmpty() && cmodel.getParentSignature() != null) {
                                    new DownloadImageFromInternet(Sign2).execute(cmodel.getParentSignature());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databasePage3.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            modelApproved cmodel= snapshot.getValue(modelApproved.class);

                            if(snapshot.exists()) {
                                if (cmodel.getApproval().equals("approve")) {
                                    etphone.setEnabled(false);
                                    etmobilenostu1.setEnabled(false);
                                    etmobilenostu2.setEnabled(false);
                                    etmobilenofat1.setEnabled(false);
                                    etmobilenofat2.setEnabled(false);
                                    etplace.setEnabled(false);
                                    tvdate.setEnabled(false);
                                    cbiagree1.setEnabled(false);
                                    cbiagree2.setEnabled(false);
                                    btnad_submit3.setEnabled(false);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else{
            AppConstant.showErrorDialog_fragment(getContext(),"Admission Page","Internet connection required!");
        }

        tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        SelectImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickImageLauncher1.launch(intent);
            }
        });

        SelectImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickImageLauncher2.launch(intent);
            }
        });

        UploadImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImageUri1 != null && !selectedImageUri1.equals(Uri.EMPTY)){
                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    storageReference.child("Admission_Form").child(AppClass.getUserNo() + "/StudentSign")
                            .putFile(selectedImageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                    downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            modelStudentSignature modelAdd = new modelStudentSignature();
                                            modelAdd.setStudentSignature(uri.toString());

                                            databasePage3.child("Admission_Form")
                                                    .child(AppClass.getUserNo())
                                                    .child("Student_Signature").setValue(modelAdd)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                        }
                                    });
                                }
                            });
                }
            }
        });

        UploadImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedImageUri2 != null && !selectedImageUri2.equals(Uri.EMPTY)){
                    ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setTitle("Uploading...");
                    progressDialog.show();

                    storageReference.child("Admission_Form").child(AppClass.getUserNo() + "/ParentSign")
                            .putFile(selectedImageUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                    downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            modelParentSignature modelAdd = new modelParentSignature();
                                            modelAdd.setParentSignature(uri.toString());

                                            databasePage3.child("Admission_Form")
                                                    .child(AppClass.getUserNo())
                                                    .child("Parent_Signature").setValue(modelAdd)
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            progressDialog.dismiss();
                                                        }
                                                    });
                                        }
                                    });
                                }
                            });
                }
            }
        });

        btnad_submit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){
                    if (isNetworkAvailable(getContext())){
                        modelAdmissionPage3 modeladd = new modelAdmissionPage3();
                        modeladd.setPhone(etphone.getText().toString());
                        modeladd.setMobilenoStu1(etmobilenostu1.getText().toString());
                        modeladd.setMobilenoStu2(etmobilenostu2.getText().toString());
                        modeladd.setMobilenoFat1(etmobilenofat1.getText().toString());
                        modeladd.setMobilenoFat2(etmobilenofat2.getText().toString());
                        modeladd.setPlace(etplace.getText().toString());
                        modeladd.setDate(tvdate.getText().toString());

                        if(cbiagree1.isChecked()){
                            modeladd.setIagree1("checked");
                        }else{
                            modeladd.setIagree1("");
                        }

                        if(cbiagree2.isChecked()){
                            modeladd.setIagree2("checked");
                        }else{
                            modeladd.setIagree2("");
                        }

                        databasePage3.child("Admission_Form")
                                .child(AppClass.getUserNo())
                                .child("Page_3").setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
//                                        etphone.getText().clear();
//                                        etmobilenostu1.getText().clear();
//                                        etmobilenostu2.getText().clear();
//                                        etmobilenofat1.getText().clear();
//                                        etmobilenofat2.getText().clear();
//                                        etplace.getText().clear();
//                                        tvdate.setText("");
//                                        cbiagree1.setChecked(false);
//                                        cbiagree2.setChecked(false);

                                        Intent intent = new Intent(getContext(), Activity_Payment.class);
                                        startActivity(intent);
                                    }
                                });

                        modelApproved cmodel = new modelApproved();
                        cmodel.setApproval(" ");

                        databasePage3.child("Admission_Form")
                                .child(AppClass.getUserNo())
                                .child("Approval").setValue(cmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                    }else{
                        AppConstant.showErrorDialog_fragment(getContext(),"Admission Page","Internet connection required!");
                    }
                }
            }
        });

        return AddmissionPage3;
    }

    private final ActivityResultLauncher<Intent> pickImageLauncher1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        selectedImageUri1 = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri1);
                            Sign1.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    private final ActivityResultLauncher<Intent> pickImageLauncher2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                        selectedImageUri2 = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri2);
                            Sign2.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
    );

    public boolean isValidate(){
        if(!TextUtils.isEmpty(etmobilenostu1.getText())){
            if(etmobilenostu1.getText().length() == 10){
                if(!TextUtils.isEmpty(etmobilenofat1.getText())){
                    if(etmobilenofat1.getText().length() == 10){
                        if(!TextUtils.isEmpty(etplace.getText())){
                            if(!TextUtils.isEmpty(tvdate.getText())){
                                if(cbiagree1.isChecked()){
                                    if(cbiagree2.isChecked()){
                                        return true;
                                    }else{
                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Click I Agree on Declaration to be Signed by Candidates Parent or Guardian");
                                        return false;
                                    }
                                }else{
                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Click I Agree on Declaration by Student");
                                    return false;
                                }
                            }else{
                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Date");
                                return false;
                            }
                        }else{
                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Place");
                            return false;
                        }
                    }else{
                        showErrorDialog_fragment(getContext(), "Admission Form", "Invalid Father / Guardian / Mother Mobile No.!");
                        return false;
                    }
                }else{
                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Father / Guardian / Mother Mobile No.");
                    return false;
                }
            }else{
                showErrorDialog_fragment(getContext(), "Admission Form", "Invalid Student Mobile No.!");
                return false;
            }
        }else{
            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Student Mobile No.");
            return false;
        }
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
            Toast.makeText(getContext(), "Please wait, Loading Signature's...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage= BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }
}