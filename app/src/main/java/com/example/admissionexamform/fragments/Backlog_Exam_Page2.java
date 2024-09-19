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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admissionexamform.Activity_Payment;
import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.model.modelApproved;
import com.example.admissionexamform.model.modelBacklogExamPage2;
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
import java.util.ArrayList;
import java.util.Calendar;

public class Backlog_Exam_Page2 extends Fragment {

    EditText one_Examination, one_CreditEarned, one_SGPA;
    EditText two_Examination, two_CreditEarned, two_SGPA;
    EditText three_Examination, three_CreditEarned, three_SGPA;
    EditText four_Examination, four_CreditEarned, four_SGPA;
    EditText five_Examination, five_CreditEarned, five_SGPA;
    EditText six_Examination, six_CreditEarned, six_SGPA;
    EditText seven_Examination, seven_CreditEarned, seven_SGPA;
    Spinner spione_Result, spitwo_Result, spithree_Result,spifour_Result, spifive_Result,spisix_Result, spiseven_Result;
    TextView page2_date;
    CheckBox iagree1;
    Button Submit2, SelectImage1, UploadImage1, SelectImage2, UploadImage2;
    ImageView Sign1, Sign2;

    DatabaseReference databaseBKPage2;

    private Uri selectedImageUri1, selectedImageUri2;
    FirebaseStorage storage;
    StorageReference storageReference;

    public Backlog_Exam_Page2() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ExamPage2 =  inflater.inflate(R.layout.fragment_backlog__exam__page2, container, false);

        FirebaseApp.initializeApp(getContext());
        databaseBKPage2 = FirebaseDatabase.getInstance().getReference("College_Database");

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        one_Examination = ExamPage2.findViewById(R.id.etbkex_one_Examination);
        one_CreditEarned = ExamPage2.findViewById(R.id.etbkex_one_CreditEarned);
        one_SGPA = ExamPage2.findViewById(R.id.etbkex_one_SGPA);
        spione_Result = ExamPage2.findViewById(R.id.spibkex_one_Result);
        two_Examination = ExamPage2.findViewById(R.id.etbkex_two_Examination);
        two_CreditEarned = ExamPage2.findViewById(R.id.etbkex_two_CreditEarned);
        two_SGPA = ExamPage2.findViewById(R.id.etbkex_two_SGPA);
        spitwo_Result = ExamPage2.findViewById(R.id.spibkex_two_Result);
        three_Examination = ExamPage2.findViewById(R.id.etbkex_three_Examination);
        three_CreditEarned = ExamPage2.findViewById(R.id.etbkex_three_CreditEarned);
        three_SGPA = ExamPage2.findViewById(R.id.etbkex_three_SGPA);
        spithree_Result = ExamPage2.findViewById(R.id.spibkex_three_Result);
        four_Examination = ExamPage2.findViewById(R.id.etbkex_four_Examination);
        four_CreditEarned = ExamPage2.findViewById(R.id.etbkex_four_CreditEarned);
        four_SGPA = ExamPage2.findViewById(R.id.etbkex_four_SGPA);
        spifour_Result = ExamPage2.findViewById(R.id.spibkex_four_Result);
        five_Examination = ExamPage2.findViewById(R.id.etbkex_five_Examination);
        five_CreditEarned = ExamPage2.findViewById(R.id.etbkex_five_CreditEarned);
        five_SGPA = ExamPage2.findViewById(R.id.etbkex_five_SGPA);
        spifive_Result = ExamPage2.findViewById(R.id.spibkex_five_Result);
        six_Examination = ExamPage2.findViewById(R.id.etbkex_six_Examination);
        six_CreditEarned = ExamPage2.findViewById(R.id.etbkex_six_CreditEarned);
        six_SGPA = ExamPage2.findViewById(R.id.etbkex_six_SGPA);
        spisix_Result = ExamPage2.findViewById(R.id.spibkex_six_Result);
        seven_Examination = ExamPage2.findViewById(R.id.etbkex_seven_Examination);
        seven_CreditEarned = ExamPage2.findViewById(R.id.etbkex_seven_CreditEarned);
        seven_SGPA = ExamPage2.findViewById(R.id.etbkex_seven_SGPA);
        spiseven_Result = ExamPage2.findViewById(R.id.spibkex_seven_Result);
        page2_date = ExamPage2.findViewById(R.id.tvexpage2_date);
        iagree1 = ExamPage2.findViewById(R.id.cbbkex_iagree1);
        Submit2 = ExamPage2.findViewById(R.id.btnbkex_Submit2);
        SelectImage1 = ExamPage2.findViewById(R.id.btnbkex_SelectImage1);
        UploadImage1 = ExamPage2.findViewById(R.id.btnbkex_UploadImage1);
        SelectImage2 = ExamPage2.findViewById(R.id.btnbkex_SelectImage2);
        UploadImage2 = ExamPage2.findViewById(R.id.btnbkex_UploadImage2);
        Sign1 = ExamPage2.findViewById(R.id.ivbkex_Sign1);
        Sign2 = ExamPage2.findViewById(R.id.ivbkex_Sign2);

        ArrayList<String> arrayListNameResult = new ArrayList<>();
        arrayListNameResult.add("");
        arrayListNameResult.add("Sucessful");
        arrayListNameResult.add("Unsucessful");
        ArrayAdapter<String> arrayAdapterResult = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListNameResult);
        arrayAdapterResult.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spione_Result.setAdapter(arrayAdapterResult);
        spitwo_Result.setAdapter(arrayAdapterResult);
        spithree_Result.setAdapter(arrayAdapterResult);
        spifour_Result.setAdapter(arrayAdapterResult);
        spifive_Result.setAdapter(arrayAdapterResult);
        spisix_Result.setAdapter(arrayAdapterResult);
        spiseven_Result.setAdapter(arrayAdapterResult);

        page2_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        page2_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        if (isNetworkAvailable(getContext())) {
            databaseBKPage2.child("Backlog_Exam_Form")
                    .child(AppClass.getUserNo())
                    .child("Page_2").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                modelBacklogExamPage2 cmodel = snapshot.getValue(modelBacklogExamPage2.class);

                                ArrayAdapter<String> array_spinner0=(ArrayAdapter<String>)spione_Result.getAdapter();
                                spione_Result.setSelection(array_spinner0.getPosition(cmodel.getSpione_Result()));

                                ArrayAdapter<String> array_spinner1=(ArrayAdapter<String>)spitwo_Result.getAdapter();
                                spitwo_Result.setSelection(array_spinner1.getPosition(cmodel.getSpitwo_Result()));

                                ArrayAdapter<String> array_spinner2=(ArrayAdapter<String>)spithree_Result.getAdapter();
                                spithree_Result.setSelection(array_spinner2.getPosition(cmodel.getSpithree_Result()));

                                ArrayAdapter<String> array_spinner3=(ArrayAdapter<String>)spifour_Result.getAdapter();
                                spifour_Result.setSelection(array_spinner3.getPosition(cmodel.getSpifour_Result()));

                                ArrayAdapter<String> array_spinner4=(ArrayAdapter<String>)spifive_Result.getAdapter();
                                spifive_Result.setSelection(array_spinner4.getPosition(cmodel.getSpifive_Result()));

                                ArrayAdapter<String> array_spinner5=(ArrayAdapter<String>)spisix_Result.getAdapter();
                                spisix_Result.setSelection(array_spinner5.getPosition(cmodel.getSpisix_Result()));

                                ArrayAdapter<String> array_spinner6=(ArrayAdapter<String>)spiseven_Result.getAdapter();
                                spiseven_Result.setSelection(array_spinner6.getPosition(cmodel.getSpiseven_Result()));

                                one_Examination.setText(cmodel.getOne_Examination());
                                one_CreditEarned.setText(cmodel.getOne_CreditEarned());
                                one_SGPA.setText(cmodel.getOne_SGPA());
                                two_Examination.setText(cmodel.getTwo_Examination());
                                two_CreditEarned.setText(cmodel.getTwo_CreditEarned());
                                two_SGPA.setText(cmodel.getTwo_SGPA());
                                three_Examination.setText(cmodel.getThree_Examination());
                                three_CreditEarned.setText(cmodel.getThree_CreditEarned());
                                three_SGPA.setText(cmodel.getThree_SGPA());
                                three_Examination.setText(cmodel.getThree_Examination());
                                three_CreditEarned.setText(cmodel.getThree_CreditEarned());
                                three_SGPA.setText(cmodel.getThree_SGPA());
                                four_Examination.setText(cmodel.getFour_Examination());
                                four_CreditEarned.setText(cmodel.getFour_CreditEarned());
                                four_SGPA.setText(cmodel.getFour_SGPA());
                                five_Examination.setText(cmodel.getFive_Examination());
                                five_CreditEarned.setText(cmodel.getFive_CreditEarned());
                                five_SGPA.setText(cmodel.getFive_SGPA());
                                six_Examination.setText(cmodel.getSix_Examination());
                                six_CreditEarned.setText(cmodel.getSix_CreditEarned());
                                six_SGPA.setText(cmodel.getSix_SGPA());
                                seven_Examination.setText(cmodel.getSeven_Examination());
                                seven_CreditEarned.setText(cmodel.getSeven_CreditEarned());
                                seven_SGPA.setText(cmodel.getSeven_SGPA());
                                page2_date.setText(cmodel.getPage2_date());

                                if(cmodel.getIagree1().equals("checked")){
                                    iagree1.setChecked(true);
                                }else{
                                    iagree1.setChecked(false);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databaseBKPage2.child("Backlog_Exam_Form")
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

            databaseBKPage2.child("Backlog_Exam_Form")
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

            databaseBKPage2.child("Backlog_Exam_Form")
                    .child(AppClass.getUserNo())
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            modelApproved cmodel= snapshot.getValue(modelApproved.class);

                            if(snapshot.exists()) {
                                if (cmodel.getApproval().equals("approve")) {
                                    one_Examination.setEnabled(false);
                                    one_CreditEarned.setEnabled(false);
                                    one_SGPA.setEnabled(false);
                                    spione_Result.setEnabled(false);
                                    two_Examination.setEnabled(false);
                                    two_CreditEarned.setEnabled(false);
                                    two_SGPA.setEnabled(false);
                                    spitwo_Result.setEnabled(false);
                                    three_Examination.setEnabled(false);
                                    three_CreditEarned.setEnabled(false);
                                    three_SGPA.setEnabled(false);
                                    spithree_Result.setEnabled(false);
                                    four_Examination.setEnabled(false);
                                    four_CreditEarned.setEnabled(false);
                                    four_SGPA.setEnabled(false);
                                    spifour_Result.setEnabled(false);
                                    five_Examination.setEnabled(false);
                                    five_CreditEarned.setEnabled(false);
                                    five_SGPA.setEnabled(false);
                                    spifive_Result.setEnabled(false);
                                    six_Examination.setEnabled(false);
                                    six_CreditEarned.setEnabled(false);
                                    six_SGPA.setEnabled(false);
                                    spisix_Result.setEnabled(false);
                                    seven_Examination.setEnabled(false);
                                    seven_CreditEarned.setEnabled(false);
                                    seven_SGPA.setEnabled(false);
                                    spiseven_Result.setEnabled(false);
                                    page2_date.setEnabled(false);
                                    iagree1.setEnabled(false);
                                    Submit2.setEnabled(false);
                                    SelectImage1.setEnabled(false);
                                    SelectImage2.setEnabled(false);
                                    UploadImage1.setEnabled(false);
                                    UploadImage2.setEnabled(false);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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

                        storageReference.child("Backlog_Exam_Form").child(AppClass.getUserNo() + "/StudentSign")
                                .putFile(selectedImageUri1).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                        downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                modelStudentSignature modelAdd = new modelStudentSignature();
                                                modelAdd.setStudentSignature(uri.toString());

                                                databaseBKPage2.child("Backlog_Exam_Form")
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

                        storageReference.child("Backlog_Exam_Form").child(AppClass.getUserNo() + "/ParentSign")
                                .putFile(selectedImageUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Task<Uri> downloadUri = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                                        downloadUri.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                            @Override
                                            public void onSuccess(Uri uri) {
                                                modelParentSignature modelAdd = new modelParentSignature();
                                                modelAdd.setParentSignature(uri.toString());

                                                databaseBKPage2.child("Backlog_Exam_Form")
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

            Submit2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isValidate()) {
                        if (isNetworkAvailable(getContext())) {
                            modelBacklogExamPage2 modelAdd = new modelBacklogExamPage2();
                            modelAdd.setOne_Examination(one_Examination.getText().toString());
                            modelAdd.setOne_CreditEarned(one_CreditEarned.getText().toString());
                            modelAdd.setOne_SGPA(one_SGPA.getText().toString());
                            modelAdd.setSpione_Result(spione_Result.getSelectedItem().toString());
                            modelAdd.setTwo_Examination(two_Examination.getText().toString());
                            modelAdd.setTwo_CreditEarned(two_CreditEarned.getText().toString());
                            modelAdd.setTwo_SGPA(two_SGPA.getText().toString());
                            modelAdd.setSpitwo_Result(spitwo_Result.getSelectedItem().toString());
                            modelAdd.setThree_Examination(three_Examination.getText().toString());
                            modelAdd.setThree_CreditEarned(three_CreditEarned.getText().toString());
                            modelAdd.setThree_SGPA(three_SGPA.getText().toString());
                            modelAdd.setSpithree_Result(spithree_Result.getSelectedItem().toString());
                            modelAdd.setFour_Examination(four_Examination.getText().toString());
                            modelAdd.setFour_CreditEarned(four_CreditEarned.getText().toString());
                            modelAdd.setFour_SGPA(four_SGPA.getText().toString());
                            modelAdd.setSpifour_Result(spifour_Result.getSelectedItem().toString());
                            modelAdd.setFive_Examination(five_Examination.getText().toString());
                            modelAdd.setFive_CreditEarned(five_CreditEarned.getText().toString());
                            modelAdd.setFive_SGPA(five_SGPA.getText().toString());
                            modelAdd.setSpifive_Result(spifive_Result.getSelectedItem().toString());
                            modelAdd.setSix_Examination(six_Examination.getText().toString());
                            modelAdd.setSix_CreditEarned(six_CreditEarned.getText().toString());
                            modelAdd.setSix_SGPA(six_SGPA.getText().toString());
                            modelAdd.setSpisix_Result(spisix_Result.getSelectedItem().toString());
                            modelAdd.setSeven_Examination(seven_Examination.getText().toString());
                            modelAdd.setSeven_CreditEarned(seven_CreditEarned.getText().toString());
                            modelAdd.setSeven_SGPA(seven_SGPA.getText().toString());
                            modelAdd.setSpiseven_Result(spiseven_Result.getSelectedItem().toString());
                            modelAdd.setPage2_date(page2_date.getText().toString());

                            if(iagree1.isChecked()){
                                modelAdd.setIagree1("checked");
                            }else{
                                modelAdd.setIagree1("");
                            }

                            databaseBKPage2.child("Backlog_Exam_Form")
                                    .child(AppClass.getUserNo())
                                    .child("Page_2").setValue(modelAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
//                                            one_Examination.getText().clear();
//                                            one_CreditEarned.getText().clear();
//                                            one_SGPA.getText().clear();
//                                            spione_Result.setSelection(0);
//                                            two_Examination.getText().clear();
//                                            two_CreditEarned.getText().clear();
//                                            two_SGPA.getText().clear();
//                                            spitwo_Result.setSelection(0);
//                                            three_Examination.getText().clear();
//                                            three_CreditEarned.getText().clear();
//                                            three_SGPA.getText().clear();
//                                            spithree_Result.setSelection(0);
//                                            four_Examination.getText().clear();
//                                            four_CreditEarned.getText().clear();
//                                            four_SGPA.getText().clear();
//                                            spifour_Result.setSelection(0);
//                                            five_Examination.getText().clear();
//                                            five_CreditEarned.getText().clear();
//                                            five_SGPA.getText().clear();
//                                            spifive_Result.setSelection(0);
//                                            six_Examination.getText().clear();
//                                            six_CreditEarned.getText().clear();
//                                            six_SGPA.getText().clear();
//                                            spisix_Result.setSelection(0);
//                                            seven_Examination.getText().clear();
//                                            seven_CreditEarned.getText().clear();
//                                            seven_SGPA.getText().clear();
//                                            spiseven_Result.setSelection(0);
//                                            page2_date.setText("");
//                                            iagree1.setChecked(false);

                                            modelApproved cmodel = new modelApproved();
                                            cmodel.setApproval(" ");

                                            databaseBKPage2.child("Backlog_Exam_Form")
                                                    .child(AppClass.getUserNo())
                                                    .child("Approval").setValue(cmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });

                                            Intent intent = new Intent(getContext(), Activity_Payment.class);
                                            startActivity(intent);
                                        }
                                    });
                        }
                    }else{
                        AppConstant.showErrorDialog_fragment(getContext(),"Backlog Exam Page","Internet connection required!");
                    }
                }
            });

        }
        
        return ExamPage2;
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
        if(iagree1.isChecked()){
            if(!TextUtils.isEmpty(page2_date.getText())){
//                if(selectedImageUri1 != null && !selectedImageUri1.equals(Uri.EMPTY)){
//                    if(selectedImageUri2 != null && !selectedImageUri2.equals(Uri.EMPTY)){
                        return true;
//                    }else{
//                        showErrorDialog_fragment(getContext(), "Exam Form", "Please! Select Parent Signature");
//                        return false;
//                    }
//                }else{
//                    showErrorDialog_fragment(getContext(), "Exam Form", "Please! Select Student Signature");
//                    return false;
//                }
            }else{
                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Date");
                return false;
            }
        }else{
            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Click I Agree on Undertaking");
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