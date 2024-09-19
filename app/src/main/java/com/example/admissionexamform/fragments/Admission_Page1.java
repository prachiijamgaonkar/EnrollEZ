package com.example.admissionexamform.fragments;

import static com.example.admissionexamform.classes.AppConstant.isNetworkAvailable;
import static com.example.admissionexamform.classes.AppConstant.showErrorDialog;
import static com.example.admissionexamform.classes.AppConstant.showErrorDialog_fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admissionexamform.MainActivity;
import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.model.modelAdmissionPage1;
import com.example.admissionexamform.model.modelApproved;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Admission_Page1 extends Fragment {

    Spinner spiAdmissionYear, spiTypeOfSeat, spiadmissioncategory, spiresidentialstatus, spicandidatebelongs;
    Spinner spiadmissiondetails, spititle, spibloodgropu, spiayearofadmission;
    EditText etWhichAdmitted, etApplicationId, etnameofstudent;
    EditText etfathersname, etmothersname, etplaceofbirth, etNationality, etReligion ;
    EditText etAddharno, etCollegeRegistration;
    TextView tvdateofbirth, tvdateofadmission;
    RadioGroup rdadradioGender;
    RadioButton rbMail, rbFemail;
    Button btnad_submit1;
    TextView one_approvalinfo;

    DatabaseReference databasePage1;

    public Admission_Page1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View AddmissionPage1 = inflater.inflate(R.layout.fragment_admission__page1, container, false);

        FirebaseApp.initializeApp(getContext());
        databasePage1 = FirebaseDatabase.getInstance().getReference("College_Database");

        spiAdmissionYear = AddmissionPage1.findViewById(R.id.spiad_AdmissionYear);
        spiTypeOfSeat = AddmissionPage1.findViewById(R.id.spiad_TypeOfSeat);
        spiadmissioncategory = AddmissionPage1.findViewById(R.id.spiad_admissioncategory);
        spiresidentialstatus  = AddmissionPage1.findViewById(R.id.spiad_residentialstatus);
        spicandidatebelongs = AddmissionPage1.findViewById(R.id.spiad_candidatebelongs);
        spiadmissiondetails = AddmissionPage1.findViewById(R.id.spiad_admissiondetails);
        spititle = AddmissionPage1.findViewById(R.id.spiad_title);
        spibloodgropu = AddmissionPage1.findViewById(R.id.spiad_bloodgropu);
        etWhichAdmitted = AddmissionPage1.findViewById(R.id.etad_WhichAdmitted);
        etApplicationId = AddmissionPage1.findViewById(R.id.etad_ApplicationId);
        spiayearofadmission = AddmissionPage1.findViewById(R.id.spiad_yearofadmission);
        tvdateofadmission = AddmissionPage1.findViewById(R.id.tvad_dateofadmission);
        etnameofstudent = AddmissionPage1.findViewById(R.id.etad_nameofstudent);
        etfathersname = AddmissionPage1.findViewById(R.id.etad_fathersname);
        etmothersname = AddmissionPage1.findViewById(R.id.etad_mothersname);
        etplaceofbirth = AddmissionPage1.findViewById(R.id.etad_placeofbirth);
        etNationality = AddmissionPage1.findViewById(R.id.etad_Nationality);
        etReligion = AddmissionPage1.findViewById(R.id.etad_Religion);
        etAddharno = AddmissionPage1.findViewById(R.id.etad_Addharno);
        etCollegeRegistration = AddmissionPage1.findViewById(R.id.etad_CollegeRegistration);
        tvdateofbirth = AddmissionPage1.findViewById(R.id.tvad_dateofbirth);
        rdadradioGender = AddmissionPage1.findViewById(R.id.rdad_radioGender);
        rbMail = AddmissionPage1.findViewById(R.id.radioMale);
        rbFemail = AddmissionPage1.findViewById(R.id.radioFemale);
        one_approvalinfo = AddmissionPage1.findViewById(R.id.tvadpageone_approvalinfo);
        btnad_submit1 = AddmissionPage1.findViewById(R.id.btnad_Submit1);

        ArrayList<String> arrayListAdmissionYear = new ArrayList<>();
        arrayListAdmissionYear.add("");
        arrayListAdmissionYear.add("Second Year B.E.");
        arrayListAdmissionYear.add("Third Year B.E.");
        arrayListAdmissionYear.add("Final Year B.E.");
        ArrayAdapter<String> arrayAdapterAdmissionYear = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListAdmissionYear);
        arrayAdapterAdmissionYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiAdmissionYear.setAdapter(arrayAdapterAdmissionYear);

        ArrayList<String> arrayListTypeOfSeat = new ArrayList<>();
        arrayListTypeOfSeat.add("");
        arrayListTypeOfSeat.add("Cap");
        arrayListTypeOfSeat.add("IL");
        arrayListTypeOfSeat.add("Minority");
        arrayListTypeOfSeat.add("J&K");
        arrayListTypeOfSeat.add("ACAP");
        arrayListTypeOfSeat.add("TFWS");
        ArrayAdapter<String> arrayAdapterTypeOfSeat = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListTypeOfSeat);
        arrayAdapterTypeOfSeat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiTypeOfSeat.setAdapter(arrayAdapterTypeOfSeat);

        ArrayList<String> arrayListadmissioncategory = new ArrayList<>();
        arrayListadmissioncategory.add("");
        arrayListadmissioncategory.add("Open");
        arrayListadmissioncategory.add("OBC");
        arrayListadmissioncategory.add("SC");
        arrayListadmissioncategory.add("ST");
        arrayListadmissioncategory.add("SBC");
        arrayListadmissioncategory.add("ESBC");
        arrayListadmissioncategory.add("VJ-DT");
        arrayListadmissioncategory.add("NT1");
        arrayListadmissioncategory.add("NT2");
        arrayListadmissioncategory.add("NT3");
        arrayListadmissioncategory.add("EWS");
        ArrayAdapter<String> arrayAdapteradmissioncategory = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListadmissioncategory);
        arrayAdapteradmissioncategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiadmissioncategory.setAdapter(arrayAdapteradmissioncategory);

        ArrayList<String> arrayListresidentialstatus = new ArrayList<>();
        arrayListresidentialstatus.add("");
        arrayListresidentialstatus.add("MS");
        arrayListresidentialstatus.add("OMS");
        arrayListresidentialstatus.add("J&K");
        ArrayAdapter<String> arrayAdapterresidentialstatus = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListresidentialstatus);
        arrayAdapterresidentialstatus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiresidentialstatus.setAdapter(arrayAdapterresidentialstatus);

        ArrayList<String> arrayListcandidatebelongs = new ArrayList<>();
        arrayListcandidatebelongs.add("");
        arrayListcandidatebelongs.add("Urban");
        arrayListcandidatebelongs.add("Rural");
        arrayListcandidatebelongs.add("Tribal");
        ArrayAdapter<String> arrayAdaptercandidatebelongs = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayListcandidatebelongs);
        arrayAdaptercandidatebelongs.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spicandidatebelongs.setAdapter(arrayAdaptercandidatebelongs);

        ArrayList<String> arrayListdmissiondetails = new ArrayList<>();
        arrayListdmissiondetails.add("");
        arrayListdmissiondetails.add("First Year");
        arrayListdmissiondetails.add("Direct Second Year");
        ArrayAdapter<String> arrayAdapterdmissiondetails = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayListdmissiondetails);
        arrayAdapterdmissiondetails.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiadmissiondetails.setAdapter(arrayAdapterdmissiondetails);

        ArrayList<String> arrayListtitle = new ArrayList<>();
        arrayListtitle.add("");
        arrayListtitle.add("Mr.");
        arrayListtitle.add("Ms.");
        arrayListtitle.add("Mrs.");
        ArrayAdapter<String> arrayAdaptertitle = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListtitle);
        arrayAdaptertitle.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spititle.setAdapter(arrayAdaptertitle);

        ArrayList<String> arrayListbloodgropu = new ArrayList<>();
        arrayListbloodgropu.add("");
        arrayListbloodgropu.add("A+");
        arrayListbloodgropu.add("A-");
        arrayListbloodgropu.add("B+");
        arrayListbloodgropu.add("B-");
        arrayListbloodgropu.add("O+");
        arrayListbloodgropu.add("O-");
        arrayListbloodgropu.add("AB+");
        arrayListbloodgropu.add("AB-");
        ArrayAdapter<String> arrayAdapterbloodgropu = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, arrayListbloodgropu);
        arrayAdapterbloodgropu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spibloodgropu.setAdapter(arrayAdapterbloodgropu);

        ArrayList<String> arrayListyearofadmission = new ArrayList<>();
        arrayListyearofadmission.add("");
        arrayListyearofadmission.add("2015-2016");
        arrayListyearofadmission.add("2016-2017");
        arrayListyearofadmission.add("2017-2018");
        arrayListyearofadmission.add("2018-2019");
        arrayListyearofadmission.add("2019-2020");
        arrayListyearofadmission.add("2020-2021");
        arrayListyearofadmission.add("2021-2022");
        arrayListyearofadmission.add("2022-2023");
        arrayListyearofadmission.add("2023-2024");
        ArrayAdapter<String> arrayAdapteryearofadmission = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListyearofadmission);
        arrayAdapteryearofadmission.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiayearofadmission.setAdapter(arrayAdapteryearofadmission);

        tvdateofadmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tvdateofadmission.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        tvdateofbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvdateofbirth.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        if (isNetworkAvailable(getContext())){
            databasePage1.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Page_1").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){
                                modelAdmissionPage1 cmodel = snapshot.getValue(modelAdmissionPage1.class);

                                ArrayAdapter<String> array_spinner0=(ArrayAdapter<String>)spiAdmissionYear.getAdapter();
                                spiAdmissionYear.setSelection(array_spinner0.getPosition(cmodel.getAdmissionYear()));
                                AppClass.setAdmissiomYear(cmodel.getAdmissionYear());

                                ArrayAdapter<String> array_spinner1=(ArrayAdapter<String>)spiTypeOfSeat.getAdapter();
                                spiTypeOfSeat.setSelection(array_spinner1.getPosition(cmodel.getTypeOfSeat()));

                                ArrayAdapter<String> array_spinner2=(ArrayAdapter<String>)spiadmissioncategory.getAdapter();
                                spiadmissioncategory.setSelection(array_spinner2.getPosition(cmodel.getAdmissionCategory()));
                                AppClass.setCategory(cmodel.getAdmissionCategory());

                                ArrayAdapter<String> array_spinner3=(ArrayAdapter<String>)spiresidentialstatus.getAdapter();
                                spiresidentialstatus.setSelection(array_spinner3.getPosition(cmodel.getResidentialStatus()));

                                ArrayAdapter<String> array_spinner4=(ArrayAdapter<String>)spicandidatebelongs.getAdapter();
                                spicandidatebelongs.setSelection(array_spinner4.getPosition(cmodel.getCandidateBelongs()));

                                ArrayAdapter<String> array_spinner5=(ArrayAdapter<String>)spiadmissiondetails.getAdapter();
                                spiadmissiondetails.setSelection(array_spinner5.getPosition(cmodel.getAdmissionDetails()));

                                ArrayAdapter<String> array_spinner6=(ArrayAdapter<String>)spititle.getAdapter();
                                spititle.setSelection(array_spinner6.getPosition(cmodel.getTitle()));

                                ArrayAdapter<String> array_spinner7=(ArrayAdapter<String>)spibloodgropu.getAdapter();
                                spibloodgropu.setSelection(array_spinner7.getPosition(cmodel.getBloodGropu()));

                                ArrayAdapter<String> array_spinner8=(ArrayAdapter<String>)spiayearofadmission.getAdapter();
                                spiayearofadmission.setSelection(array_spinner8.getPosition(cmodel.getYearofAdmission()));

                                etWhichAdmitted.setText(cmodel.getWhichAdmitted());
                                etApplicationId.setText(cmodel.getApplicationId());
                                tvdateofadmission.setText(cmodel.getDateofAdmission());
                                etnameofstudent.setText(cmodel.getNameofStudent());
                                etfathersname.setText(cmodel.getFathersName());
                                etmothersname.setText(cmodel.getMothersName());
                                etplaceofbirth.setText(cmodel.getPlaceofBirth());
                                etNationality.setText(cmodel.getNationality());
                                etReligion.setText(cmodel.getReligion());
                                etAddharno.setText(cmodel.getAddharNo());
                                etCollegeRegistration.setText(cmodel.getCollegeRegistration());
                                tvdateofbirth.setText(cmodel.getDateofBirth());

                                if(cmodel.getGender().equals("Male")){
                                    rbMail.setChecked(true);
                                }else{
                                    rbFemail.setChecked(true);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databasePage1.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            modelApproved cmodel= snapshot.getValue(modelApproved.class);

                            if(snapshot.exists()) {
                                if (cmodel.getApproval().equals("approve")) {
                                    one_approvalinfo.setText("Approved");
//                                    one_approvalinfo.setBackground(Color.RED);
                                    spiAdmissionYear.setEnabled(false);
                                    spiTypeOfSeat.setEnabled(false);
                                    spiadmissioncategory.setEnabled(false);
                                    spiresidentialstatus.setEnabled(false);
                                    spicandidatebelongs.setEnabled(false);
                                    spiadmissiondetails.setEnabled(false);
                                    spititle.setEnabled(false);
                                    spibloodgropu.setEnabled(false);
                                    etWhichAdmitted.setEnabled(false);
                                    etApplicationId.setEnabled(false);
                                    spiayearofadmission.setEnabled(false);
                                    tvdateofadmission.setEnabled(false);
                                    etnameofstudent.setEnabled(false);
                                    etfathersname.setEnabled(false);
                                    etmothersname.setEnabled(false);
                                    etplaceofbirth.setEnabled(false);
                                    etNationality.setEnabled(false);
                                    etReligion.setEnabled(false);
                                    etAddharno.setEnabled(false);
                                    etCollegeRegistration.setEnabled(false);
                                    tvdateofbirth.setEnabled(false);
                                    rdadradioGender.setEnabled(false);
                                    rbMail.setEnabled(false);
                                    rbFemail.setEnabled(false);
                                    btnad_submit1.setEnabled(false);
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

        spiAdmissionYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                AppClass.setAdmissiomYear(spiAdmissionYear.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnad_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databasePage1.child("Admission_Form_Eligibility")
                        .child(etCollegeRegistration.getText().toString()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(!snapshot.exists()){
                                    showErrorDialog_fragment(getContext(),"Admission Page","Your not Eligible for Admission.\n Please! Contact administrative department.");
                                }
                                else{
                                    if(isValidate()){
                                        if (isNetworkAvailable(getContext())){
                                            modelAdmissionPage1 modeladd = new modelAdmissionPage1();
                                            modeladd.setAdmissionYear(spiAdmissionYear.getSelectedItem().toString());
                                            modeladd.setTypeOfSeat(spiTypeOfSeat.getSelectedItem().toString());
                                            modeladd.setAdmissionCategory(spiadmissioncategory.getSelectedItem().toString());
                                            modeladd.setResidentialStatus(spiresidentialstatus.getSelectedItem().toString());
                                            modeladd.setCandidateBelongs(spicandidatebelongs.getSelectedItem().toString());
                                            modeladd.setAdmissionDetails(spiadmissiondetails.getSelectedItem().toString());
                                            modeladd.setTitle(spititle.getSelectedItem().toString());
                                            modeladd.setBloodGropu(spibloodgropu.getSelectedItem().toString());
                                            modeladd.setWhichAdmitted(etWhichAdmitted.getText().toString());
                                            modeladd.setApplicationId(etApplicationId.getText().toString());
                                            modeladd.setYearofAdmission(spiayearofadmission.getSelectedItem().toString());
                                            modeladd.setDateofAdmission(tvdateofadmission.getText().toString());
                                            modeladd.setNameofStudent(etnameofstudent.getText().toString());
                                            modeladd.setFathersName(etfathersname.getText().toString());
                                            modeladd.setMothersName(etmothersname.getText().toString());
                                            modeladd.setPlaceofBirth(etplaceofbirth.getText().toString());
                                            modeladd.setNationality(etNationality.getText().toString());
                                            modeladd.setReligion(etReligion.getText().toString());
                                            modeladd.setAddharNo(etAddharno.getText().toString());
                                            modeladd.setCollegeRegistration(etCollegeRegistration.getText().toString());
                                            modeladd.setDateofBirth(tvdateofbirth.getText().toString());

                                            AppClass.setCategory(spiadmissioncategory.getSelectedItem().toString());
                                            AppClass.setAdmissiomYear(spiadmissioncategory.getSelectedItem().toString());

                                            if(rdadradioGender.getCheckedRadioButtonId()==-1)
                                            {//Do Nothing
                                            }
                                            else
                                            {
                                                int selectedId = rdadradioGender.getCheckedRadioButtonId();
                                                RadioButton selectedRadioButton = (RadioButton) AddmissionPage1.findViewById(selectedId);
                                                modeladd.setGender(selectedRadioButton.getText().toString());
                                            }

                                            databasePage1.child("Admission_Form")
                                                    .child(AppClass.getUserNo())
                                                    .child("Page_1").setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
//                                        spiAdmissionYear.setSelection(0);
//                                        spiTypeOfSeat.setSelection(0);
//                                        spiadmissioncategory.setSelection(0);
//                                        spiresidentialstatus.setSelection(0);
//                                        spicandidatebelongs.setSelection(0);
//                                        spiadmissiondetails.setSelection(0);
//                                        spititle.setSelection(0);
//                                        spibloodgropu.setSelection(0);
//                                        spiayearofadmission.setSelection(0);
//                                        etWhichAdmitted.getText().clear();
//                                        etApplicationId.getText().clear();
//                                        etnameofstudent.getText().clear();
//                                        etfathersname.getText().clear();
//                                        etmothersname.getText().clear();
//                                        etplaceofbirth.getText().clear();
//                                        etNationality.getText().clear();
//                                        etReligion .getText().clear();
//                                        etAddharno.getText().clear();
//                                        etCollegeRegistration.getText().clear();
//                                        tvdateofbirth.setText("");
//                                        tvdateofadmission.setText("");
//                                        rdadradioGender.clearCheck();
                                                            AppConstant.showErrorDialog_fragment(getContext(),"Admission Page","Page 1 successfully submitted. Please fill page 2 and 3.");
                                                        }
                                                    });

                                            modelApproved cmodel = new modelApproved();
                                            cmodel.setApproval(" ");

                                            databasePage1.child("Admission_Form")
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
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
            }
        });

        return AddmissionPage1;
    }

    public boolean isValidate(){
        if(!spiAdmissionYear.getSelectedItem().equals("")){
            if (!spiTypeOfSeat.getSelectedItem().equals("")){
                if(!spiadmissioncategory.getSelectedItem().equals("")){
                    if(!spiresidentialstatus.getSelectedItem().equals("")){
                        if(!spicandidatebelongs.getSelectedItem().equals("")){
                            if(!spiadmissiondetails.getSelectedItem().equals("")){
                                if(!spititle.getSelectedItem().equals("")){
                                    if(!spibloodgropu.getSelectedItem().equals("")){
                                        if(!TextUtils.isEmpty(etWhichAdmitted.getText())){
                                            if(!TextUtils.isEmpty(etApplicationId.getText())){
                                                if(!spiayearofadmission.getSelectedItem().equals("")){
                                                    if(!TextUtils.isEmpty(tvdateofadmission.getText())){
                                                        if(!TextUtils.isEmpty(etnameofstudent.getText())){
                                                            if(!TextUtils.isEmpty(etfathersname.getText())){
                                                                if(!TextUtils.isEmpty(etmothersname.getText())){
                                                                    if(!TextUtils.isEmpty(etplaceofbirth.getText())){
                                                                        if(!TextUtils.isEmpty(etNationality.getText())){
                                                                            if(!TextUtils.isEmpty(etReligion.getText())){
                                                                                if(!TextUtils.isEmpty(etAddharno.getText())){
                                                                                    if(etAddharno.getText().length() != 12){
                                                                                        if(!TextUtils.isEmpty(etCollegeRegistration.getText())){
                                                                                            if(!TextUtils.isEmpty(tvdateofbirth.getText())){
                                                                                                if (rdadradioGender.getCheckedRadioButtonId() != -1)
                                                                                                {
                                                                                                    return true;
                                                                                                }else{
                                                                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Gender.");
                                                                                                    return false;
                                                                                                }
                                                                                            }else{
                                                                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Date of Birth");
                                                                                                return false;
                                                                                            }
                                                                                        }else{
                                                                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter College Registration");
                                                                                            return false;
                                                                                        }
                                                                                    }else{
                                                                                        showErrorDialog_fragment(getContext(), "Admission Form", "Invalid Addhar No.");
                                                                                        return false;
                                                                                    }
                                                                                }else{
                                                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Addhar No.");
                                                                                    return false;
                                                                                }
                                                                            }else{
                                                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Religion");
                                                                                return false;
                                                                            }
                                                                        }else{
                                                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Nationality");
                                                                            return false;
                                                                        }
                                                                    }else{
                                                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Place of Birth");
                                                                        return false;
                                                                    }
                                                                }else{
                                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Mothers Name");
                                                                    return false;
                                                                }
                                                            }else{
                                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Father's Name");
                                                                return false;
                                                            }
                                                        }else{
                                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Name of Student");
                                                            return false;
                                                        }
                                                    }else{
                                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Date of Admission");
                                                        return false;
                                                    }
                                                }else{
                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Year of Admission");
                                                    return false;
                                                }
                                            }else{
                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Application ID");
                                                return false;
                                            }
                                        }else{
                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Which Admitted");
                                            return false;
                                        }
                                    }else{
                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Blood Group");
                                        return false;
                                    }
                                }else{
                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Tital");
                                    return false;
                                }
                            }else{
                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Admission Details");
                                return false;
                            }
                        }else{
                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Candidate Belongs");
                            return false;
                        }
                    }else{
                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Residential Status");
                        return false;
                    }
                }else{
                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Admission Category");
                    return false;
                }
            }else{
                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Type of Seat");
                return false;
            }
        }else{
            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Admission Year");
            return false;
        }
    }
}