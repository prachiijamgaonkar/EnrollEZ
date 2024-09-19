package com.example.admissionexamform.fragments;

import static com.example.admissionexamform.classes.AppConstant.isNetworkAvailable;
import static com.example.admissionexamform.classes.AppConstant.showErrorDialog_fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
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
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.admissionexamform.Activity_Payment;
import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.model.modelApproved;
import com.example.admissionexamform.model.modelBacklogExamPage1;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Backlog_Exam_Page1 extends Fragment {
    Spinner spiNameExamination, spiProgramme, spiSemester;
    EditText etProgrammeDetail, etStudentRegNo, etName, etStudentABC, etMotherName;
    EditText etAddress, etMobileNoParent, etMobileNoStudent, etEnrolmentNo;
    EditText etone_CourseCode, etone_CourseName, etone_Credit;
    Spinner spione_TheroyPractical;
    EditText ettwo_CourseCode, ettwo_CourseName, ettwo_Credit;
    Spinner spitwo_TheroyPractical;
    EditText etthree_CourseCode, etthree_CourseName, etthree_Credit;
    Spinner spithree_TheroyPractical;
    EditText etfour_CourseCode, etfour_CourseName, etfour_Credit;
    Spinner spifour_TheroyPractical;
    EditText etfive_CourseCode, etfive_CourseName, etfive_Credit;
    Spinner spifive_TheroyPractical;
    EditText etsix_CourseCode, etsix_CourseName, etsix_Credit;
    Spinner spisix_TheroyPractical;
    EditText etseven_CourseCode, etseven_CourseName, etseven_Credit;
    Spinner spiseven_TheroyPractical;
    EditText eteight_CourseCode, eteight_CourseName, eteight_Credit;
    Spinner spieight_TheroyPractical;
    EditText etnine_CourseCode, etnine_CourseName, etnine_Credit;
    Spinner spinine_TheroyPractical;
    EditText etten_CourseCode, etten_CourseName, etten_Credit;
    Spinner spiten_TheroyPractical;
    TextView tvdateofbirth;
    RadioGroup radiostudent;
    RadioButton radioRegulerStudent, radioExStudent;
//    Spinner spiOpenElective;
//    EditText ProgramElective1, ProgramElective2, ProgramElective3, ProgramElective4;
    Button btnSubmit1;
    LinearLayout llbkex_SrNo1, llbkex_SrNo2, llbkex_SrNo3, llbkex_SrNo4, llbkex_SrNo5, llbkex_SrNo6, llbkex_SrNo7, llbkex_SrNo8, llbkex_SrNo9, llbkex_SrNo10;

    DatabaseReference databaseBkPage1;

    int spiIndex = -1;

    public Backlog_Exam_Page1() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ExamPage1 =  inflater.inflate(R.layout.fragment_backlog__exam__page1, container, false);

        FirebaseApp.initializeApp(getContext());
        databaseBkPage1 = FirebaseDatabase.getInstance().getReference("College_Database");

        spiNameExamination = ExamPage1.findViewById(R.id.spibkex_NameExamination);
        spiProgramme = ExamPage1.findViewById(R.id.spibkex_Programme);
        etProgrammeDetail = ExamPage1.findViewById(R.id.etbkex_ProgrammeDetail);
        spiSemester = ExamPage1.findViewById(R.id.spibkex_Semester);
        etStudentRegNo = ExamPage1.findViewById(R.id.etbkex_StudentRegNo);
        etName = ExamPage1.findViewById(R.id.etbkex_Name);
        etStudentABC = ExamPage1.findViewById(R.id.etbkex_StudentABC);
        etMotherName = ExamPage1.findViewById(R.id.etbkex_MotherName);
        etAddress = ExamPage1.findViewById(R.id.etbkex_Address);
        etMobileNoParent = ExamPage1.findViewById(R.id.etbkex_MobileNoParent);
        etMobileNoStudent = ExamPage1.findViewById(R.id.etbkex_MobileNoStudent);
        etEnrolmentNo = ExamPage1.findViewById(R.id.etbkex_EnrolmentNo);
        etone_CourseCode = ExamPage1.findViewById(R.id.etbkex_one_CourseCode);
        etone_CourseName = ExamPage1.findViewById(R.id.etbkex_one_CourseName);
        etone_Credit = ExamPage1.findViewById(R.id.etbkex_one_Credit);
        spione_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_one_TheroyPractical);
        ettwo_CourseCode = ExamPage1.findViewById(R.id.etbkex_two_CourseCode);
        ettwo_CourseName = ExamPage1.findViewById(R.id.etbkex_two_CourseName);
        ettwo_Credit = ExamPage1.findViewById(R.id.etbkex_two_Credit);
        spitwo_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_two_TheroyPractical);
        etthree_CourseCode = ExamPage1.findViewById(R.id.etbkex_three_CourseCode);
        etthree_CourseName = ExamPage1.findViewById(R.id.etbkex_three_CourseName);
        etthree_Credit = ExamPage1.findViewById(R.id.etbkex_three_Credit);
        spithree_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_three_TheroyPractical);
        etfour_CourseCode = ExamPage1.findViewById(R.id.etbkex_four_CourseCode);
        etfour_CourseName = ExamPage1.findViewById(R.id.etbkex_four_CourseName);
        etfour_Credit = ExamPage1.findViewById(R.id.etbkex_four_Credit);
        spifour_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_four_TheroyPractical);
        etfive_CourseCode = ExamPage1.findViewById(R.id.etbkex_five_CourseCode);
        etfive_CourseName = ExamPage1.findViewById(R.id.etbkex_five_CourseName);
        etfive_Credit = ExamPage1.findViewById(R.id.etbkex_five_Credit);
        spifive_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_five_TheroyPractical);
        etsix_CourseCode = ExamPage1.findViewById(R.id.etbkex_six_CourseCode);
        etsix_CourseName = ExamPage1.findViewById(R.id.etbkex_six_CourseName);
        etsix_Credit = ExamPage1.findViewById(R.id.etbkex_six_Credit);
        spisix_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_six_TheroyPractical);
        etseven_CourseCode = ExamPage1.findViewById(R.id.etbkex_seven_CourseCode);
        etseven_CourseName = ExamPage1.findViewById(R.id.etbkex_seven_CourseName);
        etseven_Credit = ExamPage1.findViewById(R.id.etbkex_seven_Credit);
        spiseven_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_seven_TheroyPractical);
        eteight_CourseCode = ExamPage1.findViewById(R.id.etbkex_eight_CourseCode);
        eteight_CourseName = ExamPage1.findViewById(R.id.etbkex_eight_CourseName);
        eteight_Credit = ExamPage1.findViewById(R.id.etbkex_eight_Credit);
        spieight_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_eight_TheroyPractical);
        etnine_CourseCode = ExamPage1.findViewById(R.id.etbkex_nine_CourseCode);
        etnine_CourseName = ExamPage1.findViewById(R.id.etbkex_nine_CourseName);
        etnine_Credit = ExamPage1.findViewById(R.id.etbkex_nine_Credit);
        spinine_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_nine_TheroyPractical);
        etten_CourseCode = ExamPage1.findViewById(R.id.etbkex_ten_CourseCode);
        etten_CourseName = ExamPage1.findViewById(R.id.etbkex_ten_CourseName);
        etten_Credit = ExamPage1.findViewById(R.id.etbkex_ten_Credit);
        spiten_TheroyPractical = ExamPage1.findViewById(R.id.spibkex_ten_TheroyPractical);
        tvdateofbirth = ExamPage1.findViewById(R.id.tvbkex_dateofbirth);
        radiostudent = ExamPage1.findViewById(R.id.rdbkex_radiostudent);
        radioRegulerStudent = ExamPage1.findViewById(R.id.radioRegulerStudent);
        radioExStudent = ExamPage1.findViewById(R.id.radioExStudent);
//        spiOpenElective = ExamPage1.findViewById(R.id.spibkex_OpenElective);
//        ProgramElective1 = ExamPage1.findViewById(R.id.etbkex_ProgramElective1);
//        ProgramElective2 = ExamPage1.findViewById(R.id.etbkex_ProgramElective2);
//        ProgramElective3 = ExamPage1.findViewById(R.id.etbkex_ProgramElective3);
//        ProgramElective4 = ExamPage1.findViewById(R.id.etbkex_ProgramElective4);
        btnSubmit1 = ExamPage1.findViewById(R.id.btnbkex_Submit1);

        llbkex_SrNo1 = ExamPage1.findViewById(R.id.llbkex_SrNo1);
        llbkex_SrNo2 = ExamPage1.findViewById(R.id.llbkex_SrNo2);
        llbkex_SrNo3 = ExamPage1.findViewById(R.id.llbkex_SrNo3);
        llbkex_SrNo4 = ExamPage1.findViewById(R.id.llbkex_SrNo4);
        llbkex_SrNo5 = ExamPage1.findViewById(R.id.llbkex_SrNo5);
        llbkex_SrNo6 = ExamPage1.findViewById(R.id.llbkex_SrNo6);
        llbkex_SrNo7 = ExamPage1.findViewById(R.id.llbkex_SrNo7);
        llbkex_SrNo8 = ExamPage1.findViewById(R.id.llbkex_SrNo8);
        llbkex_SrNo9 = ExamPage1.findViewById(R.id.llbkex_SrNo9);
        llbkex_SrNo10 = ExamPage1.findViewById(R.id.llbkex_SrNo10);

        ArrayList<String> arrayListNameExamination = new ArrayList<>();
        arrayListNameExamination.add("");
        arrayListNameExamination.add("ESE");
        arrayListNameExamination.add("Re-Sit");
        arrayListNameExamination.add("Improvement");
        ArrayAdapter<String> arrayAdapterNameExamination = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListNameExamination);
        arrayAdapterNameExamination.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiNameExamination.setAdapter(arrayAdapterNameExamination);

        ArrayList<String> arrayListProgramme = new ArrayList<>();
        arrayListProgramme.add("");
        arrayListProgramme.add("UG:B.Tech");
        ArrayAdapter<String> arrayAdapterProgramme = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListProgramme);
        arrayAdapterProgramme.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiProgramme.setAdapter(arrayAdapterProgramme);

        ArrayList<String> arrayListSemester = new ArrayList<>();
        arrayListSemester.add("");
        arrayListSemester.add("1st");
        arrayListSemester.add("2nd");
        arrayListSemester.add("3rd");
        arrayListSemester.add("4th");
        arrayListSemester.add("5th");
        arrayListSemester.add("6th");
        arrayListSemester.add("7th");
        arrayListSemester.add("8th");
        ArrayAdapter<String> arrayAdapterSemester = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListSemester);
        arrayAdapterSemester.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiSemester.setAdapter(arrayAdapterSemester);

        ArrayList<String> arrayone_TheroyPractical = new ArrayList<>();
        arrayone_TheroyPractical.add("");
        arrayone_TheroyPractical.add("Theroy");
        arrayone_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterone_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayone_TheroyPractical);
        arrayAdapterone_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spione_TheroyPractical.setAdapter(arrayAdapterone_TheroyPractical);

        ArrayList<String> arraytwo_TheroyPractical = new ArrayList<>();
        arraytwo_TheroyPractical.add("");
        arraytwo_TheroyPractical.add("Theroy");
        arraytwo_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdaptertwo_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arraytwo_TheroyPractical);
        arrayAdaptertwo_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spitwo_TheroyPractical.setAdapter(arrayAdaptertwo_TheroyPractical);

        ArrayList<String> arraythree_TheroyPractical = new ArrayList<>();
        arraythree_TheroyPractical.add("");
        arraythree_TheroyPractical.add("Theroy");
        arraythree_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterthree_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arraythree_TheroyPractical);
        arrayAdapterthree_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spithree_TheroyPractical.setAdapter(arrayAdapterthree_TheroyPractical);

        ArrayList<String> arrayfour_TheroyPractical = new ArrayList<>();
        arrayfour_TheroyPractical.add("");
        arrayfour_TheroyPractical.add("Theroy");
        arrayfour_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterfour_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayfour_TheroyPractical);
        arrayAdapterfour_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spifour_TheroyPractical.setAdapter(arrayAdapterfour_TheroyPractical);

        ArrayList<String> arrayfive_TheroyPractical = new ArrayList<>();
        arrayfive_TheroyPractical.add("");
        arrayfive_TheroyPractical.add("Theroy");
        arrayfive_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterfive_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayfive_TheroyPractical);
        arrayAdapterfive_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spifive_TheroyPractical.setAdapter(arrayAdapterfive_TheroyPractical);

        ArrayList<String> arraysix_TheroyPractical = new ArrayList<>();
        arraysix_TheroyPractical.add("");
        arraysix_TheroyPractical.add("Theroy");
        arraysix_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdaptersix_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arraysix_TheroyPractical);
        arrayAdaptersix_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spisix_TheroyPractical.setAdapter(arrayAdaptersix_TheroyPractical);

        ArrayList<String> arrayseven_TheroyPractical = new ArrayList<>();
        arrayseven_TheroyPractical.add("");
        arrayseven_TheroyPractical.add("Theroy");
        arrayseven_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterseven_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayseven_TheroyPractical);
        arrayAdapterseven_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiseven_TheroyPractical.setAdapter(arrayAdapterseven_TheroyPractical);

        ArrayList<String> arrayeight_TheroyPractical = new ArrayList<>();
        arrayeight_TheroyPractical.add("");
        arrayeight_TheroyPractical.add("Theroy");
        arrayeight_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdaptereight_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayeight_TheroyPractical);
        arrayAdaptereight_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spieight_TheroyPractical.setAdapter(arrayAdaptereight_TheroyPractical);

        ArrayList<String> arraynine_TheroyPractical = new ArrayList<>();
        arraynine_TheroyPractical.add("");
        arraynine_TheroyPractical.add("Theroy");
        arraynine_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapternine_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arraynine_TheroyPractical);
        arrayAdapternine_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinine_TheroyPractical.setAdapter(arrayAdapternine_TheroyPractical);

        ArrayList<String> arrayten_TheroyPractical = new ArrayList<>();
        arrayten_TheroyPractical.add("");
        arrayten_TheroyPractical.add("Theroy");
        arrayten_TheroyPractical.add("Practical");
        ArrayAdapter<String> arrayAdapterten_TheroyPractical = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayten_TheroyPractical);
        arrayAdapterten_TheroyPractical.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiten_TheroyPractical.setAdapter(arrayAdapterten_TheroyPractical);

//        ArrayList<String> arrayOpenElective = new ArrayList<>();
//        arrayOpenElective.add("");
//        arrayOpenElective.add("Sensor");
//        arrayOpenElective.add("Automobile");
//        arrayOpenElective.add("Mobile");
//        arrayOpenElective.add("CCN");
//        ArrayAdapter<String> arrayAdaptertenOpenElective = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElective);
//        arrayAdaptertenOpenElective.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spiOpenElective.setAdapter(arrayAdaptertenOpenElective);

        spiSemester.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spiSemester.getSelectedItem().equals("1st")){
//                    etone_CourseCode.setText("BSC101T");
//                    etone_CourseName.setText("Engineering Mathematics-1");
//                    etone_Credit.setText("4");
//                    ettwo_CourseCode.setText("BSC105T");
//                    ettwo_CourseName.setText("Modern Physics");
//                    ettwo_Credit.setText("4");
//                    etthree_CourseCode.setText("BSC105P");
//                    etthree_CourseName.setText("Modern Physics Lab");
//                    etthree_Credit.setText("1");
//                    etfour_CourseCode.setText("ESC101T");
//                    etfour_CourseName.setText("Electrical Engineering");
//                    etfour_Credit.setText("4");
//                    etfive_CourseCode.setText("ESC101P");
//                    etfive_CourseName.setText("Electrical Engineering Lab");
//                    etfive_Credit.setText("1");
//                    etsix_CourseCode.setText("ESC102P");
//                    etsix_CourseName.setText("Workshop Practices");
//                    etsix_Credit.setText("2");
//                    etseven_CourseCode.setText("ESC1O5T");
//                    etseven_CourseName.setText("Programming for Problem Solving");
//                    etseven_Credit.setText("3");
//                    eteight_CourseCode.setText("ESC105P");
//                    eteight_CourseName.setText("Programming for Problem Solving Lab");
//                    eteight_Credit.setText("2");
//                    etnine_CourseCode.setText("MC101");
//                    etnine_CourseName.setText("Environment Studies");
//                    etnine_Credit.setText("0");
//                    etten_CourseCode.setText("MC102");
//                    etten_CourseName.setText("Indian Constitution");
//                    etten_Credit.setText("0");

                    ArrayList<String> arrayOpenElectiv1 = new ArrayList<>();
                    arrayOpenElectiv1.add(" ");
                    ArrayAdapter<String> arrayAdaptertenOpenElective1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv1);
                    arrayAdaptertenOpenElective1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective1);
//                    spiOpenElective.setSelection(0);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.VISIBLE);
                }
                if(spiSemester.getSelectedItem().equals("2nd")){
//                    etone_CourseCode.setText("BSC104T");
//                    etone_CourseName.setText("Engineering Mathematics-II");
//                    etone_Credit.setText("4");
//                    ettwo_CourseCode.setText("BSC103T");
//                    ettwo_CourseName.setText("Engineering Chemistry");
//                    ettwo_Credit.setText("4");
//                    etthree_CourseCode.setText("BSC103P");
//                    etthree_CourseName.setText("Engineering Chemistry Lab");
//                    etthree_Credit.setText("1");
//                    etfour_CourseCode.setText("ESC104T");
//                    etfour_CourseName.setText("Engineering Graphics and Design");
//                    etfour_Credit.setText("1");
//                    etfive_CourseCode.setText("ESC104P");
//                    etfive_CourseName.setText("Engineering Graphics and Design Lab");
//                    etfive_Credit.setText("2");
//                    etsix_CourseCode.setText("ESC106P");
//                    etsix_CourseName.setText("Computer Workshop Lab");
//                    etsix_Credit.setText("2");
//                    etseven_CourseCode.setText("ESC107P");
//                    etseven_CourseName.setText("Web Development Lab");
//                    etseven_Credit.setText("2");
//                    eteight_CourseCode.setText("HSMC101T");
//                    eteight_CourseName.setText("Communicative English");
//                    eteight_Credit.setText("2");
//                    etnine_CourseCode.setText("HSMC101P");
//                    etnine_CourseName.setText("Communicative English Lab");
//                    etnine_Credit.setText("1");
//                    etten_CourseCode.setText("");
//                    etten_CourseName.setText("");
//                    etten_Credit.setText("");

                    ArrayList<String> arrayOpenElectiv2 = new ArrayList<>();
                    arrayOpenElectiv2.add(" ");
                    ArrayAdapter<String> arrayAdaptertenOpenElective2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv2);
                    arrayAdaptertenOpenElective2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective2);
//                    spiOpenElective.setSelection(0);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.GONE);
                }
                if(spiSemester.getSelectedItem().equals("3rd")){
//                    etone_CourseCode.setText("BSCET301T");
//                    etone_CourseName.setText("Engineering Mathematics-III");
//                    etone_Credit.setText("4");
//                    ettwo_CourseCode.setText("PCCET301T");
//                    ettwo_CourseName.setText("Electronic Devices & Circuits");
//                    ettwo_Credit.setText("4");
//                    etthree_CourseCode.setText("PCCET301P");
//                    etthree_CourseName.setText("Electronic Devices & Circuits Lab");
//                    etthree_Credit.setText("4");
//                    etfour_CourseCode.setText("PCCET302T");
//                    etfour_CourseName.setText("Digital Electronics");
//                    etfour_Credit.setText("4");
//                    etfive_CourseCode.setText("PCCET302P");
//                    etfive_CourseName.setText("Digital Electronics Lab");
//                    etfive_Credit.setText("1");
//                    etsix_CourseCode.setText("PCCET303T");
//                    etsix_CourseName.setText("Network Theory");
//                    etsix_Credit.setText("4");
//                    etseven_CourseCode.setText("PCCET304T");
//                    etseven_CourseName.setText("Object Oriented Programming and Data Structures");
//                    etseven_Credit.setText("3");
//                    eteight_CourseCode.setText("PCCET304P");
//                    eteight_CourseName.setText("Object Oriented Programming and Data Structure Lab");
//                    eteight_Credit.setText("1");
//                    etnine_CourseCode.setText("PROJET301");
//                    etnine_CourseName.setText("Practice School-I (Internship of 1-2 weeks)");
//                    etnine_Credit.setText("1");
//                    etten_CourseCode.setText("");
//                    etten_CourseName.setText("");
//                    etten_Credit.setText("");

                    ArrayList<String> arrayOpenElectiv3 = new ArrayList<>();
                    arrayOpenElectiv3.add(" ");
                    ArrayAdapter<String> arrayAdaptertenOpenElective3 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv3);
                    arrayAdaptertenOpenElective3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective3);
//                    spiOpenElective.setSelection(0);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.GONE);
                }
                if(spiSemester.getSelectedItem().equals("4th")){
//                    etone_CourseCode.setText("BSCET401T");
//                    etone_CourseName.setText("Engineering Mathematics-IV");
//                    etone_Credit.setText("4");
//                    ettwo_CourseCode.setText("РССЕТ401Т");
//                    ettwo_CourseName.setText("Electromagnetic Fields");
//                    ettwo_Credit.setText("4");
//                    etthree_CourseCode.setText("PCCET402T");
//                    etthree_CourseName.setText("Signal Processing");
//                    etthree_Credit.setText("4");
//                    etfour_CourseCode.setText("PCCET402P");
//                    etfour_CourseName.setText("Signal Processing Lab");
//                    etfour_Credit.setText("1");
//                    etfive_CourseCode.setText("PCCET403T");
//                    etfive_CourseName.setText("Python Programming");
//                    etfive_Credit.setText("3");
//                    etsix_CourseCode.setText("PCCET403P");
//                    etsix_CourseName.setText("Python Programming Lab");
//                    etsix_Credit.setText("1");
//                    etseven_CourseCode.setText("HSMCET401P");
//                    etseven_CourseName.setText("Soft Skills-I");
//                    etseven_Credit.setText("1");
//                    eteight_CourseCode.setText("OEC");
//                    eteight_CourseName.setText("Open Elective-I");
//                    eteight_Credit.setText("3");
//                    etnine_CourseCode.setText("MCET401");
//                    etnine_CourseName.setText("Essence of Indian Traditional Knowledge");
//                    etnine_Credit.setText("0");
//                    etten_CourseCode.setText("");
//                    etten_CourseName.setText("");
//                    etten_Credit.setText("");

                    ArrayList<String> arrayOpenElectiv4 = new ArrayList<>();
                    arrayOpenElectiv4.add("");
                    arrayOpenElectiv4.add("(OECCS401T) Digital Marketing");
                    arrayOpenElectiv4.add("(OFCEE401T) Electric Utilities and Fundamental");
                    arrayOpenElectiv4.add("(OBOME401T) Energy System and Technology");
                    ArrayAdapter<String> arrayAdaptertenOpenElective4 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv4);
                    arrayAdaptertenOpenElective4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective4);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.GONE);
                }
                if(spiSemester.getSelectedItem().equals("5th")){
//                    etone_CourseCode.setText("PCCET501P");
//                    etone_CourseName.setText("JAVA Programming Lab");
//                    etone_Credit.setText("2");
//                    ettwo_CourseCode.setText("PEC");
//                    ettwo_CourseName.setText("Program Elective-I");
//                    ettwo_Credit.setText("3");
//                    etthree_CourseCode.setText("PCCET502T");
//                    etthree_CourseName.setText("Microprocessor and Microcontroller");
//                    etthree_Credit.setText("4");
//                    etfour_CourseCode.setText("PCCET502P");
//                    etfour_CourseName.setText("Microprocessor and Microcontroller Lab");
//                    etfour_Credit.setText("1");
//                    etfive_CourseCode.setText("PCCET503T");
//                    etfive_CourseName.setText("Analog Communication");
//                    etfive_Credit.setText("4");
//                    etsix_CourseCode.setText("PCCET503P");
//                    etsix_CourseName.setText("Analog Communication Lab");
//                    etsix_Credit.setText("1");
//                    etseven_CourseCode.setText("HSMCET501T");
//                    etseven_CourseName.setText("Economics and Finance for Engineers");
//                    etseven_Credit.setText("2");
//                    eteight_CourseCode.setText("OEC");
//                    eteight_CourseName.setText("Open Elective-II");
//                    eteight_Credit.setText("3");
//                    etnine_CourseCode.setText("PROJET501");
//                    etnine_CourseName.setText("Practice School-II (Internship of 2-3 weeks)");
//                    etnine_Credit.setText("1");
//                    etten_CourseCode.setText("PROJET502");
//                    etten_CourseName.setText("Mini Project");
//                    etten_Credit.setText("2");

                    ArrayList<String> arrayOpenElectiv5 = new ArrayList<>();
                    arrayOpenElectiv5.add("");
                    arrayOpenElectiv5.add("(OECCS501T) Java Script Programming");
                    arrayOpenElectiv5.add("(OECEE501T) Electrical Energy Condensation & Audit");
                    arrayOpenElectiv5.add("(OECAD501T) Web Development Using Python");
                    arrayOpenElectiv5.add("(OECHE501T) Optimization Techniques");
                    arrayOpenElectiv5.add("(OECAM501T) Game Development Wing Python");
                    ArrayAdapter<String> arrayAdaptertenOpenElective5 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv5);
                    arrayAdaptertenOpenElective5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective5);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.VISIBLE);
                }
                if(spiSemester.getSelectedItem().equals("6th")){
//                    etone_CourseCode.setText("РССЕТ601Т");
//                    etone_CourseName.setText("Digital Communication");
//                    etone_Credit.setText("4");
//                    ettwo_CourseCode.setText("PCCET602T");
//                    ettwo_CourseName.setText("Control System Engineering");
//                    ettwo_Credit.setText("4");
//                    etthree_CourseCode.setText("PCCET603T");
//                    etthree_CourseName.setText("Computer Communication Networks");
//                    etthree_Credit.setText("3");
//                    etfour_CourseCode.setText("PCCET603P");
//                    etfour_CourseName.setText("Computer Communication Networks Lab");
//                    etfour_Credit.setText("1");
//                    etfive_CourseCode.setText("PCCET604T");
//                    etfive_CourseName.setText("Digital System Design");
//                    etfive_Credit.setText("3");
//                    etsix_CourseCode.setText("PCCET604P");
//                    etsix_CourseName.setText("Digital System Design Lab");
//                    etsix_Credit.setText("1");
//                    etseven_CourseCode.setText("PCCET605P");
//                    etseven_CourseName.setText("Software Workshop Lab");
//                    etseven_Credit.setText("2");
//                    eteight_CourseCode.setText("HSMCET601P");
//                    eteight_CourseName.setText("Soft Skills-III");
//                    eteight_Credit.setText("1");
//                    etnine_CourseCode.setText("PEC");
//                    etnine_CourseName.setText("Program Elective-II");
//                    etnine_Credit.setText("3");
//                    etten_CourseCode.setText("OEC");
//                    etten_CourseName.setText("Open Elective-III");
//                    etten_Credit.setText("3");

                    ArrayList<String> arrayOpenElectiv6 = new ArrayList<>();
                    arrayOpenElectiv6.add("");
                    arrayOpenElectiv6.add("(OECAD601T) Data visualization");
                    arrayOpenElectiv6.add("(OECME601T) Smart Manufacturing System");
                    arrayOpenElectiv6.add("(OECEE601T) Solar Photovoltaic System");
                    arrayOpenElectiv6.add("(OECES601T) Basics of Computer Graphics");
                    ArrayAdapter<String> arrayAdaptertenOpenElective6 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv6);
                    arrayAdaptertenOpenElective6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective6);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.VISIBLE);
                    llbkex_SrNo8.setVisibility(View.VISIBLE);
                    llbkex_SrNo9.setVisibility(View.VISIBLE);
                    llbkex_SrNo10.setVisibility(View.VISIBLE);
                }
                if(spiSemester.getSelectedItem().equals("7th")){
//                    etone_CourseCode.setText("PEC");
//                    etone_CourseName.setText("Program Elective-III");
//                    etone_Credit.setText("3");
//                    ettwo_CourseCode.setText("PEC");
//                    ettwo_CourseName.setText("Program Elective-IV");
//                    ettwo_Credit.setText("3");
//                    etthree_CourseCode.setText("PEC");
//                    etthree_CourseName.setText("Program Elective-V");
//                    etthree_Credit.setText("3");
//                    etfour_CourseCode.setText("PEC");
//                    etfour_CourseName.setText("Program Elective-VI");
//                    etfour_Credit.setText("3");
//                    etfive_CourseCode.setText("PROJET701");
//                    etfive_CourseName.setText("Project-1");
//                    etfive_Credit.setText("6");
//                    etsix_CourseCode.setText("OEC");
//                    etsix_CourseName.setText("Open Elective-IV");
//                    etsix_Credit.setText("3");
//                    etseven_CourseCode.setText("");
//                    etseven_CourseName.setText("");
//                    etseven_Credit.setText("");
//                    eteight_CourseCode.setText("");
//                    eteight_CourseName.setText("");
//                    eteight_Credit.setText("");
//                    etnine_CourseCode.setText("");
//                    etnine_CourseName.setText("");
//                    etnine_Credit.setText("");
//                    etten_CourseCode.setText("");
//                    etten_CourseName.setText("");
//                    etten_Credit.setText("");

                    ArrayList<String> arrayOpenElectiv7 = new ArrayList<>();
                    arrayOpenElectiv7.add("");
                    arrayOpenElectiv7.add("(OECCS701T) Mobile Allocation and Development");
                    arrayOpenElectiv7.add("(OECEE701T) Electric Vehicles");
                    arrayOpenElectiv7.add("(OECME701T) Automobile Engineering");
                    ArrayAdapter<String> arrayAdaptertenOpenElective7 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv7);
                    arrayAdaptertenOpenElective7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective7);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.VISIBLE);
                    llbkex_SrNo3.setVisibility(View.VISIBLE);
                    llbkex_SrNo4.setVisibility(View.VISIBLE);
                    llbkex_SrNo5.setVisibility(View.VISIBLE);
                    llbkex_SrNo6.setVisibility(View.VISIBLE);
                    llbkex_SrNo7.setVisibility(View.GONE);
                    llbkex_SrNo8.setVisibility(View.GONE);
                    llbkex_SrNo9.setVisibility(View.GONE);
                    llbkex_SrNo10.setVisibility(View.GONE);
                }
                if(spiSemester.getSelectedItem().equals("8th")){
//                    etone_CourseCode.setText("PROJET801");
//                    etone_CourseName.setText("Project-II/Industry Internship");
//                    etone_Credit.setText("6");
//                    ettwo_CourseCode.setText("");
//                    ettwo_CourseName.setText("");
//                    ettwo_Credit.setText("");
//                    etthree_CourseCode.setText("");
//                    etthree_CourseName.setText("");
//                    etthree_Credit.setText("");
//                    etfour_CourseCode.setText("");
//                    etfour_CourseName.setText("");
//                    etfour_Credit.setText("");
//                    etfive_CourseCode.setText("");
//                    etfive_CourseName.setText("");
//                    etfive_Credit.setText("");
//                    etsix_CourseCode.setText("");
//                    etsix_CourseName.setText("");
//                    etsix_Credit.setText("");
//                    etseven_CourseCode.setText("");
//                    etseven_CourseName.setText("");
//                    etseven_Credit.setText("");
//                    eteight_CourseCode.setText("");
//                    eteight_CourseName.setText("");
//                    eteight_Credit.setText("");
//                    etnine_CourseCode.setText("");
//                    etnine_CourseName.setText("");
//                    etnine_Credit.setText("");
//                    etten_CourseCode.setText("");
//                    etten_CourseName.setText("");
//                    etten_Credit.setText("");

                    ArrayList<String> arrayOpenElectiv8 = new ArrayList<>();
                    arrayOpenElectiv8.add(" ");
                    ArrayAdapter<String> arrayAdaptertenOpenElective8 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv8);
                    arrayAdaptertenOpenElective8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective8);
//                    spiOpenElective.setSelection(0);
//
//                    if(spiIndex > -1){
//                        spiOpenElective.setSelection(spiIndex);
//                    }

                    llbkex_SrNo1.setVisibility(View.VISIBLE);
                    llbkex_SrNo2.setVisibility(View.GONE);
                    llbkex_SrNo3.setVisibility(View.GONE);
                    llbkex_SrNo4.setVisibility(View.GONE);
                    llbkex_SrNo5.setVisibility(View.GONE);
                    llbkex_SrNo6.setVisibility(View.GONE);
                    llbkex_SrNo7.setVisibility(View.GONE);
                    llbkex_SrNo8.setVisibility(View.GONE);
                    llbkex_SrNo9.setVisibility(View.GONE);
                    llbkex_SrNo10.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            databaseBkPage1.child("Backlog_Exam_Form")
                    .child(AppClass.getUserNo())
                    .child("Page_1").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                modelBacklogExamPage1 cmodel = snapshot.getValue(modelBacklogExamPage1.class);

                                if(cmodel.getSemester().equals("1st")){
//                                    etone_CourseCode.setText("BSC101T");
//                                    etone_CourseName.setText("Engineering Mathematics-1");
//                                    etone_Credit.setText("4");
//                                    ettwo_CourseCode.setText("BSC105T");
//                                    ettwo_CourseName.setText("Modern Physics");
//                                    ettwo_Credit.setText("4");
//                                    etthree_CourseCode.setText("BSC105P");
//                                    etthree_CourseName.setText("Modern Physics Lab");
//                                    etthree_Credit.setText("1");
//                                    etfour_CourseCode.setText("ESC101T");
//                                    etfour_CourseName.setText("Electrical Engineering");
//                                    etfour_Credit.setText("4");
//                                    etfive_CourseCode.setText("ESC101P");
//                                    etfive_CourseName.setText("Electrical Engineering Lab");
//                                    etfive_Credit.setText("1");
//                                    etsix_CourseCode.setText("ESC102P");
//                                    etsix_CourseName.setText("Workshop Practices");
//                                    etsix_Credit.setText("2");
//                                    etseven_CourseCode.setText("ESC1O5T");
//                                    etseven_CourseName.setText("Programming for Problem Solving");
//                                    etseven_Credit.setText("3");
//                                    eteight_CourseCode.setText("ESC105P");
//                                    eteight_CourseName.setText("Programming for Problem Solving Lab");
//                                    eteight_Credit.setText("2");
//                                    etnine_CourseCode.setText("MC101");
//                                    etnine_CourseName.setText("Environment Studies");
//                                    etnine_Credit.setText("0");
//                                    etten_CourseCode.setText("MC102");
//                                    etten_CourseName.setText("Indian Constitution");
//                                    etten_Credit.setText("0");

                                    ArrayList<String> arrayOpenElectiv1 = new ArrayList<>();
                                    arrayOpenElectiv1.add(" ");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective1 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv1);
                                    arrayAdaptertenOpenElective1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective1);
//                                    spiOpenElective.setSelection(0);

                                    spiIndex = arrayAdaptertenOpenElective1.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("2nd")){
//                                    etone_CourseCode.setText("BSC104T");
//                                    etone_CourseName.setText("Engineering Mathematics-II");
//                                    etone_Credit.setText("4");
//                                    ettwo_CourseCode.setText("BSC103T");
//                                    ettwo_CourseName.setText("Engineering Chemistry");
//                                    ettwo_Credit.setText("4");
//                                    etthree_CourseCode.setText("BSC103P");
//                                    etthree_CourseName.setText("Engineering Chemistry Lab");
//                                    etthree_Credit.setText("1");
//                                    etfour_CourseCode.setText("ESC104T");
//                                    etfour_CourseName.setText("Engineering Graphics and Design");
//                                    etfour_Credit.setText("1");
//                                    etfive_CourseCode.setText("ESC104P");
//                                    etfive_CourseName.setText("Engineering Graphics and Design Lab");
//                                    etfive_Credit.setText("2");
//                                    etsix_CourseCode.setText("ESC106P");
//                                    etsix_CourseName.setText("Computer Workshop Lab");
//                                    etsix_Credit.setText("2");
//                                    etseven_CourseCode.setText("ESC107P");
//                                    etseven_CourseName.setText("Web Development Lab");
//                                    etseven_Credit.setText("2");
//                                    eteight_CourseCode.setText("HSMC101T");
//                                    eteight_CourseName.setText("Communicative English");
//                                    eteight_Credit.setText("2");
//                                    etnine_CourseCode.setText("HSMC101P");
//                                    etnine_CourseName.setText("Communicative English Lab");
//                                    etnine_Credit.setText("1");
//                                    etten_CourseCode.setText("");
//                                    etten_CourseName.setText("");
//                                    etten_Credit.setText("");

                                    ArrayList<String> arrayOpenElectiv2 = new ArrayList<>();
                                    arrayOpenElectiv2.add(" ");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective2 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv2);
                                    arrayAdaptertenOpenElective2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective2);
//                                    spiOpenElective.setSelection(0);

                                    spiIndex = arrayAdaptertenOpenElective2.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("3rd")){
//                                    etone_CourseCode.setText("BSCET301T");
//                                    etone_CourseName.setText("Engineering Mathematics-III");
//                                    etone_Credit.setText("4");
//                                    ettwo_CourseCode.setText("PCCET301T");
//                                    ettwo_CourseName.setText("Electronic Devices & Circuits");
//                                    ettwo_Credit.setText("4");
//                                    etthree_CourseCode.setText("PCCET301P");
//                                    etthree_CourseName.setText("Electronic Devices & Circuits Lab");
//                                    etthree_Credit.setText("4");
//                                    etfour_CourseCode.setText("PCCET302T");
//                                    etfour_CourseName.setText("Digital Electronics");
//                                    etfour_Credit.setText("4");
//                                    etfive_CourseCode.setText("PCCET302P");
//                                    etfive_CourseName.setText("Digital Electronics Lab");
//                                    etfive_Credit.setText("1");
//                                    etsix_CourseCode.setText("PCCET303T");
//                                    etsix_CourseName.setText("Network Theory");
//                                    etsix_Credit.setText("4");
//                                    etseven_CourseCode.setText("PCCET304T");
//                                    etseven_CourseName.setText("Object Oriented Programming and Data Structures");
//                                    etseven_Credit.setText("3");
//                                    eteight_CourseCode.setText("PCCET304P");
//                                    eteight_CourseName.setText("Object Oriented Programming and Data Structure Lab");
//                                    eteight_Credit.setText("1");
//                                    etnine_CourseCode.setText("PROJET301");
//                                    etnine_CourseName.setText("Practice School-I (Internship of 1-2 weeks)");
//                                    etnine_Credit.setText("1");
//                                    etten_CourseCode.setText("");
//                                    etten_CourseName.setText("");
//                                    etten_Credit.setText("");

                                    ArrayList<String> arrayOpenElectiv3 = new ArrayList<>();
                                    arrayOpenElectiv3.add(" ");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective3 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv3);
                                    arrayAdaptertenOpenElective3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective3);
//                                    spiOpenElective.setSelection(0);

                                    spiIndex = arrayAdaptertenOpenElective3.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("4th")){
//                                    etone_CourseCode.setText("BSCET401T");
//                                    etone_CourseName.setText("Engineering Mathematics-IV");
//                                    etone_Credit.setText("4");
//                                    ettwo_CourseCode.setText("РССЕТ401Т");
//                                    ettwo_CourseName.setText("Electromagnetic Fields");
//                                    ettwo_Credit.setText("4");
//                                    etthree_CourseCode.setText("PCCET402T");
//                                    etthree_CourseName.setText("Signal Processing");
//                                    etthree_Credit.setText("4");
//                                    etfour_CourseCode.setText("PCCET402P");
//                                    etfour_CourseName.setText("Signal Processing Lab");
//                                    etfour_Credit.setText("1");
//                                    etfive_CourseCode.setText("PCCET403T");
//                                    etfive_CourseName.setText("Python Programming");
//                                    etfive_Credit.setText("3");
//                                    etsix_CourseCode.setText("PCCET403P");
//                                    etsix_CourseName.setText("Python Programming Lab");
//                                    etsix_Credit.setText("1");
//                                    etseven_CourseCode.setText("HSMCET401P");
//                                    etseven_CourseName.setText("Soft Skills-I");
//                                    etseven_Credit.setText("1");
//                                    eteight_CourseCode.setText("OEC");
//                                    eteight_CourseName.setText("Open Elective-I");
//                                    eteight_Credit.setText("3");
//                                    etnine_CourseCode.setText("MCET401");
//                                    etnine_CourseName.setText("Essence of Indian Traditional Knowledge");
//                                    etnine_Credit.setText("0");
//                                    etten_CourseCode.setText("");
//                                    etten_CourseName.setText("");
//                                    etten_Credit.setText("");

                                    ArrayList<String> arrayOpenElectiv4 = new ArrayList<>();
                                    arrayOpenElectiv4.add("");
                                    arrayOpenElectiv4.add("(OECCS401T) Digital Marketing");
                                    arrayOpenElectiv4.add("(OFCEE401T) Electric Utilities and Fundamental");
                                    arrayOpenElectiv4.add("(OBOME401T) Energy System and Technology");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective4 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv4);
                                    arrayAdaptertenOpenElective4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective4);

                                    spiIndex = arrayAdaptertenOpenElective4.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("5th")){
//                                    etone_CourseCode.setText("PCCET501P");
//                                    etone_CourseName.setText("JAVA Programming Lab");
//                                    etone_Credit.setText("2");
//                                    ettwo_CourseCode.setText("PEC");
//                                    ettwo_CourseName.setText("Program Elective-I");
//                                    ettwo_Credit.setText("3");
//                                    etthree_CourseCode.setText("PCCET502T");
//                                    etthree_CourseName.setText("Microprocessor and Microcontroller");
//                                    etthree_Credit.setText("4");
//                                    etfour_CourseCode.setText("PCCET502P");
//                                    etfour_CourseName.setText("Microprocessor and Microcontroller Lab");
//                                    etfour_Credit.setText("1");
//                                    etfive_CourseCode.setText("PCCET503T");
//                                    etfive_CourseName.setText("Analog Communication");
//                                    etfive_Credit.setText("4");
//                                    etsix_CourseCode.setText("PCCET503P");
//                                    etsix_CourseName.setText("Analog Communication Lab");
//                                    etsix_Credit.setText("1");
//                                    etseven_CourseCode.setText("HSMCET501T");
//                                    etseven_CourseName.setText("Economics and Finance for Engineers");
//                                    etseven_Credit.setText("2");
//                                    eteight_CourseCode.setText("OEC");
//                                    eteight_CourseName.setText("Open Elective-II");
//                                    eteight_Credit.setText("3");
//                                    etnine_CourseCode.setText("PROJET501");
//                                    etnine_CourseName.setText("Practice School-II (Internship of 2-3 weeks)");
//                                    etnine_Credit.setText("1");
//                                    etten_CourseCode.setText("PROJET502");
//                                    etten_CourseName.setText("Mini Project");
//                                    etten_Credit.setText("2");

                                    ArrayList<String> arrayOpenElectiv5 = new ArrayList<>();
                                    arrayOpenElectiv5.add("");
                                    arrayOpenElectiv5.add("(OECCS501T) Java Script Programming");
                                    arrayOpenElectiv5.add("(OECEE501T) Electrical Energy Condensation & Audit");
                                    arrayOpenElectiv5.add("(OECAD501T) Web Development Using Python");
                                    arrayOpenElectiv5.add("(OECHE501T) Optimization Techniques");
                                    arrayOpenElectiv5.add("(OECAM501T) Game Development Wing Python");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective5 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv5);
                                    arrayAdaptertenOpenElective5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective5);

                                    spiIndex = arrayAdaptertenOpenElective5.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("6th")){
//                                    etone_CourseCode.setText("РССЕТ601Т");
//                                    etone_CourseName.setText("Digital Communication");
//                                    etone_Credit.setText("4");
//                                    ettwo_CourseCode.setText("PCCET602T");
//                                    ettwo_CourseName.setText("Control System Engineering");
//                                    ettwo_Credit.setText("4");
//                                    etthree_CourseCode.setText("PCCET603T");
//                                    etthree_CourseName.setText("Computer Communication Networks");
//                                    etthree_Credit.setText("3");
//                                    etfour_CourseCode.setText("PCCET603P");
//                                    etfour_CourseName.setText("Computer Communication Networks Lab");
//                                    etfour_Credit.setText("1");
//                                    etfive_CourseCode.setText("PCCET604T");
//                                    etfive_CourseName.setText("Digital System Design");
//                                    etfive_Credit.setText("3");
//                                    etsix_CourseCode.setText("PCCET604P");
//                                    etsix_CourseName.setText("Digital System Design Lab");
//                                    etsix_Credit.setText("1");
//                                    etseven_CourseCode.setText("PCCET605P");
//                                    etseven_CourseName.setText("Software Workshop Lab");
//                                    etseven_Credit.setText("2");
//                                    eteight_CourseCode.setText("HSMCET601P");
//                                    eteight_CourseName.setText("Soft Skills-III");
//                                    eteight_Credit.setText("1");
//                                    etnine_CourseCode.setText("PEC");
//                                    etnine_CourseName.setText("Program Elective-II");
//                                    etnine_Credit.setText("3");
//                                    etten_CourseCode.setText("OEC");
//                                    etten_CourseName.setText("Open Elective-III");
//                                    etten_Credit.setText("3");

                                    ArrayList<String> arrayOpenElectiv6 = new ArrayList<>();
                                    arrayOpenElectiv6.add("");
                                    arrayOpenElectiv6.add("(OECAD601T) Data visualization");
                                    arrayOpenElectiv6.add("(OECME601T) Smart Manufacturing System");
                                    arrayOpenElectiv6.add("(OECEE601T) Solar Photovoltaic System");
                                    arrayOpenElectiv6.add("(OECES601T) Basics of Computer Graphics");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective6 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv6);
                                    arrayAdaptertenOpenElective6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective6);

                                    spiIndex = arrayAdaptertenOpenElective6.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("7th")){
//                                    etone_CourseCode.setText("PEC");
//                                    etone_CourseName.setText("Program Elective-III");
//                                    etone_Credit.setText("3");
//                                    ettwo_CourseCode.setText("PEC");
//                                    ettwo_CourseName.setText("Program Elective-IV");
//                                    ettwo_Credit.setText("3");
//                                    etthree_CourseCode.setText("PEC");
//                                    etthree_CourseName.setText("Program Elective-V");
//                                    etthree_Credit.setText("3");
//                                    etfour_CourseCode.setText("PEC");
//                                    etfour_CourseName.setText("Program Elective-VI");
//                                    etfour_Credit.setText("3");
//                                    etfive_CourseCode.setText("PROJET701");
//                                    etfive_CourseName.setText("Project-1");
//                                    etfive_Credit.setText("6");
//                                    etsix_CourseCode.setText("OEC");
//                                    etsix_CourseName.setText("Open Elective-IV");
//                                    etsix_Credit.setText("3");
//                                    etseven_CourseCode.setText("");
//                                    etseven_CourseName.setText("");
//                                    etseven_Credit.setText("");
//                                    eteight_CourseCode.setText("");
//                                    eteight_CourseName.setText("");
//                                    eteight_Credit.setText("");
//                                    etnine_CourseCode.setText("");
//                                    etnine_CourseName.setText("");
//                                    etnine_Credit.setText("");
//                                    etten_CourseCode.setText("");
//                                    etten_CourseName.setText("");
//                                    etten_Credit.setText("");

                                    ArrayList<String> arrayOpenElectiv7 = new ArrayList<>();
                                    arrayOpenElectiv7.add("");
                                    arrayOpenElectiv7.add("(OECCS701T) Mobile Allocation and Development");
                                    arrayOpenElectiv7.add("(OECEE701T) Electric Vehicles");
                                    arrayOpenElectiv7.add("(OECME701T) Automobile Engineering");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective7 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv7);
                                    arrayAdaptertenOpenElective7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective7);

                                    spiIndex = arrayAdaptertenOpenElective7.getPosition(cmodel.getOpenElective());
                                }
                                if(cmodel.getSemester().equals("8th")){
//                                    etone_CourseCode.setText("PROJET801");
//                                    etone_CourseName.setText("Project-II/Industry Internship");
//                                    etone_Credit.setText("6");
//                                    ettwo_CourseCode.setText("");
//                                    ettwo_CourseName.setText("");
//                                    ettwo_Credit.setText("");
//                                    etthree_CourseCode.setText("");
//                                    etthree_CourseName.setText("");
//                                    etthree_Credit.setText("");
//                                    etfour_CourseCode.setText("");
//                                    etfour_CourseName.setText("");
//                                    etfour_Credit.setText("");
//                                    etfive_CourseCode.setText("");
//                                    etfive_CourseName.setText("");
//                                    etfive_Credit.setText("");
//                                    etsix_CourseCode.setText("");
//                                    etsix_CourseName.setText("");
//                                    etsix_Credit.setText("");
//                                    etseven_CourseCode.setText("");
//                                    etseven_CourseName.setText("");
//                                    etseven_Credit.setText("");
//                                    eteight_CourseCode.setText("");
//                                    eteight_CourseName.setText("");
//                                    eteight_Credit.setText("");
//                                    etnine_CourseCode.setText("");
//                                    etnine_CourseName.setText("");
//                                    etnine_Credit.setText("");
//                                    etten_CourseCode.setText("");
//                                    etten_CourseName.setText("");
//                                    etten_Credit.setText("");

                                    ArrayList<String> arrayOpenElectiv8 = new ArrayList<>();
                                    arrayOpenElectiv8.add(" ");
                                    ArrayAdapter<String> arrayAdaptertenOpenElective8 = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayOpenElectiv8);
                                    arrayAdaptertenOpenElective8.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                                    spiOpenElective.setAdapter(arrayAdaptertenOpenElective8);
//                                    spiOpenElective.setSelection(0);

                                    spiIndex = arrayAdaptertenOpenElective8.getPosition(cmodel.getOpenElective());
                                }

                                ArrayAdapter<String> array_spinner0=(ArrayAdapter<String>)spiSemester.getAdapter();
                                spiSemester.setSelection(array_spinner0.getPosition(cmodel.getSemester()));

                                ArrayAdapter<String> array_spinner1=(ArrayAdapter<String>)spiNameExamination.getAdapter();
                                spiNameExamination.setSelection(array_spinner1.getPosition(cmodel.getNameExamination()));

                                ArrayAdapter<String> array_spinner2=(ArrayAdapter<String>)spiProgramme.getAdapter();
                                spiProgramme.setSelection(array_spinner2.getPosition(cmodel.getProgramme()));

                                ArrayAdapter<String> array_spinner3=(ArrayAdapter<String>)spione_TheroyPractical.getAdapter();
                                spione_TheroyPractical.setSelection(array_spinner3.getPosition(cmodel.getOne_TheroyPractical()));

                                ArrayAdapter<String> array_spinner4=(ArrayAdapter<String>)spitwo_TheroyPractical.getAdapter();
                                spitwo_TheroyPractical.setSelection(array_spinner4.getPosition(cmodel.getTwo_TheroyPractical()));

                                ArrayAdapter<String> array_spinner5=(ArrayAdapter<String>)spithree_TheroyPractical.getAdapter();
                                spithree_TheroyPractical.setSelection(array_spinner5.getPosition(cmodel.getThree_TheroyPractical()));

                                ArrayAdapter<String> array_spinner6=(ArrayAdapter<String>)spifour_TheroyPractical.getAdapter();
                                spifour_TheroyPractical.setSelection(array_spinner6.getPosition(cmodel.getFour_TheroyPractical()));

                                ArrayAdapter<String> array_spinner7=(ArrayAdapter<String>)spifive_TheroyPractical.getAdapter();
                                spifive_TheroyPractical.setSelection(array_spinner7.getPosition(cmodel.getFive_TheroyPractical()));

                                ArrayAdapter<String> array_spinner8=(ArrayAdapter<String>)spisix_TheroyPractical.getAdapter();
                                spisix_TheroyPractical.setSelection(array_spinner8.getPosition(cmodel.getSix_TheroyPractical()));

                                ArrayAdapter<String> array_spinner9=(ArrayAdapter<String>)spiseven_TheroyPractical.getAdapter();
                                spiseven_TheroyPractical.setSelection(array_spinner9.getPosition(cmodel.getSeven_TheroyPractical()));

                                ArrayAdapter<String> array_spinner10=(ArrayAdapter<String>)spieight_TheroyPractical.getAdapter();
                                spieight_TheroyPractical.setSelection(array_spinner10.getPosition(cmodel.getEight_TheroyPractical()));

                                ArrayAdapter<String> array_spinner11=(ArrayAdapter<String>)spinine_TheroyPractical.getAdapter();
                                spinine_TheroyPractical.setSelection(array_spinner11.getPosition(cmodel.getNine_TheroyPractical()));

                                ArrayAdapter<String> array_spinner12=(ArrayAdapter<String>)spiten_TheroyPractical.getAdapter();
                                spiten_TheroyPractical.setSelection(array_spinner12.getPosition(cmodel.getTen_TheroyPractical()));

//                                ArrayAdapter<String> array_spinner13=(ArrayAdapter<String>)spiOpenElective.getAdapter();
//                                spiOpenElective.setSelection(array_spinner13.getPosition(cmodel.getOpenElective()));

                                etProgrammeDetail.setText(cmodel.getProgrammeDetail());
                                etStudentRegNo.setText(cmodel.getStudentRegNo());
                                etName.setText(cmodel.getName());
                                etStudentABC.setText(cmodel.getStudentABC());
                                etMotherName.setText(cmodel.getMotherName());
                                etAddress.setText(cmodel.getAddress());
                                etMobileNoParent.setText(cmodel.getMobileNoParent());
                                etMobileNoStudent.setText(cmodel.getMobileNoStudent());
                                etEnrolmentNo.setText(cmodel.getEnrolmentNo());
                                etone_CourseCode.setText(cmodel.getOne_CourseCode());
                                etone_CourseName.setText(cmodel.getOne_CourseName());
                                etone_Credit.setText(cmodel.getOne_Credit());
                                ettwo_CourseCode.setText(cmodel.getTwo_CourseCode());
                                ettwo_CourseName.setText(cmodel.getTwo_CourseName());
                                ettwo_Credit.setText(cmodel.getTwo_Credit());
                                etthree_CourseCode.setText(cmodel.getThree_CourseCode());
                                etthree_CourseName.setText(cmodel.getThree_CourseCode());
                                etthree_Credit.setText(cmodel.getThree_Credit());
                                etfour_CourseCode.setText(cmodel.getFour_CourseCode());
                                etfour_CourseName.setText(cmodel.getFour_CourseName());
                                etfour_Credit.setText(cmodel.getFour_Credit());
                                etfive_CourseCode.setText(cmodel.getFive_CourseCode());
                                etfive_CourseName.setText(cmodel.getFive_CourseName());
                                etfive_Credit.setText(cmodel.getFive_Credit());
                                etsix_CourseCode.setText(cmodel.getSix_CourseCode());
                                etsix_CourseName.setText(cmodel.getSix_CourseName());
                                etsix_Credit.setText(cmodel.getSix_Credit());
                                etseven_CourseCode.setText(cmodel.getSeven_CourseCode());
                                etseven_CourseName.setText(cmodel.getSeven_CourseName());
                                etseven_Credit.setText(cmodel.getSeven_Credit());
                                eteight_CourseCode.setText(cmodel.getEight_CourseCode());
                                eteight_CourseName.setText(cmodel.getEight_CourseName());
                                eteight_Credit.setText(cmodel.getEight_Credit());
                                etnine_CourseCode.setText(cmodel.getNine_CourseCode());
                                etnine_CourseName.setText(cmodel.getNine_CourseName());
                                etnine_Credit.setText(cmodel.getNine_Credit());
                                etten_CourseCode.setText(cmodel.getTen_CourseCode());
                                etten_CourseName.setText(cmodel.getTen_CourseName());
                                etten_Credit.setText(cmodel.getTen_Credit());
                                tvdateofbirth.setText(cmodel.getDateofBirth());
//                                ProgramElective1.setText(cmodel.getProgramElective1());
//                                ProgramElective2.setText(cmodel.getProgramElective2());
//                                ProgramElective3.setText(cmodel.getProgramElective3());
//                                ProgramElective4.setText(cmodel.getProgramElective4());

                                if(cmodel.getStudentType().equals("Reguler Student")){
                                    radioRegulerStudent.setChecked(true);
                                }else{
                                    radioExStudent.setChecked(true);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databaseBkPage1.child("Backlog_Exam_Form")
                    .child(AppClass.getUserNo())
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            modelApproved cmodel= snapshot.getValue(modelApproved.class);

                            if(snapshot.exists()) {
                                if (cmodel.getApproval().equals("approve")) {
                                    spiNameExamination.setEnabled(false);
                                    spiProgramme.setEnabled(false);
                                    etProgrammeDetail.setEnabled(false);
                                    spiSemester.setEnabled(false);
                                    etStudentRegNo.setEnabled(false);
                                    etName.setEnabled(false);
                                    etStudentABC.setEnabled(false);
                                    etMotherName.setEnabled(false);
                                    etAddress.setEnabled(false);
                                    etMobileNoParent.setEnabled(false);
                                    etMobileNoStudent.setEnabled(false);
                                    etEnrolmentNo.setEnabled(false);
                                    etone_CourseCode.setEnabled(false);
                                    etone_CourseName.setEnabled(false);
                                    etone_Credit.setEnabled(false);
                                    spione_TheroyPractical.setEnabled(false);
                                    ettwo_CourseCode.setEnabled(false);
                                    ettwo_CourseName.setEnabled(false);
                                    ettwo_Credit.setEnabled(false);
                                    spitwo_TheroyPractical.setEnabled(false);
                                    etthree_CourseCode.setEnabled(false);
                                    etthree_CourseName.setEnabled(false);
                                    etthree_Credit.setEnabled(false);
                                    spithree_TheroyPractical.setEnabled(false);
                                    etfour_CourseCode.setEnabled(false);
                                    etfour_CourseName.setEnabled(false);
                                    etfour_Credit.setEnabled(false);
                                    spifour_TheroyPractical.setEnabled(false);
                                    etfive_CourseCode.setEnabled(false);
                                    etfive_CourseName.setEnabled(false);
                                    etfive_Credit.setEnabled(false);
                                    spifive_TheroyPractical.setEnabled(false);
                                    etsix_CourseCode.setEnabled(false);
                                    etsix_CourseName.setEnabled(false);
                                    etsix_Credit.setEnabled(false);
                                    spisix_TheroyPractical.setEnabled(false);
                                    etseven_CourseCode.setEnabled(false);
                                    etseven_CourseName.setEnabled(false);
                                    etseven_Credit.setEnabled(false);
                                    spiseven_TheroyPractical.setEnabled(false);
                                    eteight_CourseCode.setEnabled(false);
                                    eteight_CourseName.setEnabled(false);
                                    eteight_Credit.setEnabled(false);
                                    spieight_TheroyPractical.setEnabled(false);
                                    etnine_CourseCode.setEnabled(false);
                                    etnine_CourseName.setEnabled(false);
                                    etnine_Credit.setEnabled(false);
                                    spinine_TheroyPractical.setEnabled(false);
                                    etten_CourseCode.setEnabled(false);
                                    etten_CourseName.setEnabled(false);
                                    etten_Credit.setEnabled(false);
                                    spiten_TheroyPractical.setEnabled(false);
                                    tvdateofbirth.setEnabled(false);
                                    radiostudent.setEnabled(false);
                                    radioRegulerStudent.setEnabled(false);
                                    radioExStudent.setEnabled(false);
//                                    spiOpenElective.setEnabled(false);
//                                    ProgramElective1.setEnabled(false);
//                                    ProgramElective2.setEnabled(false);
//                                    ProgramElective3.setEnabled(false);
//                                    ProgramElective4.setEnabled(false);
                                    btnSubmit1.setEnabled(false);
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else{
            AppConstant.showErrorDialog_fragment(getContext(),"Backlog Exam Form","Internet connection required!");
        }

        btnSubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){
                    if (isNetworkAvailable(getContext())){
                        modelBacklogExamPage1 modelAdd = new modelBacklogExamPage1();
                        modelAdd.setNameExamination(spiNameExamination.getSelectedItem().toString());
                        modelAdd.setProgramme(spiProgramme.getSelectedItem().toString());
                        modelAdd.setProgrammeDetail(etProgrammeDetail.getText().toString());
                        modelAdd.setSemester(spiSemester.getSelectedItem().toString());
                        modelAdd.setStudentRegNo(etStudentRegNo.getText().toString());
                        modelAdd.setName(etName.getText().toString());
                        modelAdd.setStudentABC(etStudentABC.getText().toString());
                        modelAdd.setMotherName(etMotherName.getText().toString());
                        modelAdd.setAddress(etAddress.getText().toString());
                        modelAdd.setMobileNoParent(etMobileNoParent.getText().toString());
                        modelAdd.setMobileNoStudent(etMobileNoStudent.getText().toString());
                        modelAdd.setEnrolmentNo(etEnrolmentNo.getText().toString());
                        modelAdd.setOne_CourseCode(etone_CourseCode.getText().toString());
                        modelAdd.setOne_CourseName(etone_CourseName.getText().toString());
                        modelAdd.setOne_Credit(etone_Credit.getText().toString());
                        modelAdd.setOne_TheroyPractical(spione_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setTwo_CourseCode(ettwo_CourseCode.getText().toString());
                        modelAdd.setTwo_CourseName(ettwo_CourseName.getText().toString());
                        modelAdd.setTwo_Credit(ettwo_Credit.getText().toString());
                        modelAdd.setTwo_TheroyPractical(spitwo_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setThree_CourseCode(etthree_CourseCode.getText().toString());
                        modelAdd.setThree_CourseName(etthree_CourseName.getText().toString());
                        modelAdd.setThree_Credit(etthree_Credit.getText().toString());
                        modelAdd.setThree_TheroyPractical(spithree_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setFour_CourseCode(etfour_CourseCode.getText().toString());
                        modelAdd.setFour_CourseName(etfour_CourseName.getText().toString());
                        modelAdd.setFour_Credit(etfour_Credit.getText().toString());
                        modelAdd.setFour_TheroyPractical(spifour_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setFive_CourseCode(etfive_CourseCode.getText().toString());
                        modelAdd.setFive_CourseName(etfive_CourseName.getText().toString());
                        modelAdd.setFive_Credit(etfive_Credit.getText().toString());
                        modelAdd.setFive_TheroyPractical(spifive_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setSix_CourseCode(etsix_CourseCode.getText().toString());
                        modelAdd.setSix_CourseName(etsix_CourseName.getText().toString());
                        modelAdd.setSix_Credit(etsix_Credit.getText().toString());
                        modelAdd.setSix_TheroyPractical(spisix_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setSeven_CourseCode(etseven_CourseCode.getText().toString());
                        modelAdd.setSeven_CourseName(etseven_CourseName.getText().toString());
                        modelAdd.setSeven_Credit(etseven_Credit.getText().toString());
                        modelAdd.setSeven_TheroyPractical(spiseven_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setEight_CourseCode(eteight_CourseCode.getText().toString());
                        modelAdd.setEight_CourseName(eteight_CourseName.getText().toString());
                        modelAdd.setEight_Credit(eteight_Credit.getText().toString());
                        modelAdd.setEight_TheroyPractical(spieight_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setNine_CourseCode(etnine_CourseCode.getText().toString());
                        modelAdd.setNine_CourseName(etnine_CourseName.getText().toString());
                        modelAdd.setNine_Credit(etnine_Credit.getText().toString());
                        modelAdd.setNine_TheroyPractical(spinine_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setTen_CourseCode(etten_CourseCode.getText().toString());
                        modelAdd.setTen_CourseName(etten_CourseName.getText().toString());
                        modelAdd.setTen_Credit(etten_Credit.getText().toString());
                        modelAdd.setTen_TheroyPractical(spiten_TheroyPractical.getSelectedItem().toString());
                        modelAdd.setDateofBirth(tvdateofbirth.getText().toString());
//                        modelAdd.setOpenElective(spiOpenElective.getSelectedItem().toString());
//                        modelAdd.setProgramElective1(ProgramElective1.getText().toString());
//                        modelAdd.setProgramElective2(ProgramElective2.getText().toString());
//                        modelAdd.setProgramElective3(ProgramElective3.getText().toString());
//                        modelAdd.setProgramElective4(ProgramElective4.getText().toString());

                        if(radiostudent.getCheckedRadioButtonId()==-1)
                        {//Do Nothing
                        }
                        else
                        {
                            int selectedId = radiostudent.getCheckedRadioButtonId();
                            RadioButton selectedRadioButton = (RadioButton) ExamPage1.findViewById(selectedId);
                            modelAdd.setStudentType(selectedRadioButton.getText().toString());
                        }

                        databaseBkPage1.child("Backlog_Exam_Form")
                                .child(AppClass.getUserNo())
                                .child("Page_1").setValue(modelAdd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
//                                        spiNameExamination.setSelection(0);
//                                        spiProgramme.setSelection(0);
//                                        etProgrammeDetail.getText().clear();
//                                        spiSemester.setSelection(0);
//                                        etStudentRegNo.getText().clear();
//                                        etName.getText().clear();
//                                        etStudentABC.getText().clear();
//                                        etMotherName.getText().clear();
//                                        etAddress.getText().clear();
//                                        etMobileNoParent.getText().clear();
//                                        etMobileNoStudent.getText().clear();
//                                        etEnrolmentNo.getText().clear();
//                                        etone_CourseCode.getText().clear();
//                                        etone_CourseName.getText().clear();
//                                        etone_Credit.getText().clear();
//                                        spione_TheroyPractical.setSelection(0);
//                                        ettwo_CourseCode.getText().clear();
//                                        ettwo_CourseName.getText().clear();
//                                        ettwo_Credit.getText().clear();
//                                        spitwo_TheroyPractical.setSelection(0);
//                                        etthree_CourseCode.getText().clear();
//                                        etthree_CourseName.getText().clear();
//                                        etthree_Credit.getText().clear();
//                                        spithree_TheroyPractical.setSelection(0);
//                                        etfour_CourseCode.getText().clear();
//                                        etfour_CourseName.getText().clear();
//                                        etfour_Credit.getText().clear();
//                                        spifour_TheroyPractical.setSelection(0);
//                                        etfive_CourseCode.getText().clear();
//                                        etfive_CourseName.getText().clear();
//                                        etfive_Credit.getText().clear();
//                                        spifive_TheroyPractical.setSelection(0);
//                                        etsix_CourseCode.getText().clear();
//                                        etsix_CourseName.getText().clear();
//                                        etsix_Credit.getText().clear();
//                                        spisix_TheroyPractical.setSelection(0);
//                                        etseven_CourseCode.getText().clear();
//                                        etseven_CourseName.getText().clear();
//                                        etseven_Credit.getText().clear();
//                                        spiseven_TheroyPractical.setSelection(0);
//                                        eteight_CourseCode.getText().clear();
//                                        eteight_CourseName.getText().clear();
//                                        eteight_Credit.getText().clear();
//                                        spieight_TheroyPractical.setSelection(0);
//                                        etnine_CourseCode.getText().clear();
//                                        etnine_CourseName.getText().clear();
//                                        etnine_Credit.getText().clear();
//                                        spinine_TheroyPractical.setSelection(0);
//                                        etten_CourseCode.getText().clear();
//                                        etten_CourseName.getText().clear();
//                                        etten_Credit.getText().clear();
//                                        spiten_TheroyPractical.setSelection(0);
//                                        tvdateofbirth.setText("");

                                        modelApproved cmodel = new modelApproved();
                                        cmodel.setApproval(" ");

                                        databaseBkPage1.child("Backlog_Exam_Form")
                                                .child(AppClass.getUserNo())
                                                .child("Approval").setValue(cmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {

                                                    }
                                                });

                                        AppConstant.showErrorDialog_fragment(getContext(),"Backlog Exam Form","Page 1 successfully submitted. Please fill page 2.");
                                    }
                                });

                        modelApproved cmodel = new modelApproved();
                        cmodel.setApproval("unapprove");

                        databaseBkPage1.child("Backlog_Exam_Form")
                                .child(AppClass.getUserNo())
                                .child("Approval").setValue(cmodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                    }else{
                        AppConstant.showErrorDialog_fragment(getContext(),"Backlog Exam Form","Internet connection required!");
                    }
                }
            }
        });

        return ExamPage1;
    }

    public boolean isValidate(){
        if(!spiNameExamination.getSelectedItem().equals("")){
            if(!spiProgramme.getSelectedItem().equals("")){
                if(!TextUtils.isEmpty(etProgrammeDetail.getText())){
                    if(!spiSemester.getSelectedItem().equals("")){
                        if(!TextUtils.isEmpty(etStudentRegNo.getText())){
                            if(!TextUtils.isEmpty(etName.getText())){
                                if(!TextUtils.isEmpty(etStudentABC.getText())){
                                    if(!TextUtils.isEmpty(etMotherName.getText())){
                                        if(!TextUtils.isEmpty(etAddress.getText())){
                                            if(!TextUtils.isEmpty(etMobileNoParent.getText())){
                                                if(etMobileNoParent.getText().length() == 10){
                                                    if(!TextUtils.isEmpty(etMobileNoStudent.getText())){
                                                        if(etMobileNoStudent.getText().length() == 10){
                                                            if(!TextUtils.isEmpty(etEnrolmentNo.getText())){
                                                                if(!TextUtils.isEmpty(tvdateofbirth.getText())){
                                                                    if (radiostudent.getCheckedRadioButtonId() != -1) {
                                                                        return true;
//                                                                        if(!spiOpenElective.getSelectedItem().equals("")){
//                                                                            if(!TextUtils.isEmpty(ProgramElective.getText())){
//
//                                                                            }else{
//                                                                                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Program Elective");
//                                                                                return false;
//                                                                            }
//                                                                        }else{
//                                                                            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Open Elective");
//                                                                            return false;
//                                                                        }
                                                                    }else{
                                                                        showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Student Type");
                                                                        return false;
                                                                    }
                                                                }else{
                                                                    showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Date of Birth");
                                                                    return false;
                                                                }
                                                            }else{
                                                                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Enrolment No");
                                                                return false;
                                                            }
                                                        }else{
                                                            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Invalid Mobile No of Student!");
                                                            return false;
                                                        }
                                                    }else{
                                                        showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Mobile No Student");
                                                        return false;
                                                    }
                                                }else{
                                                    showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Invalid Mobile No. of Parent!");
                                                    return false;
                                                }
                                            }else{
                                                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Mobile No. Parent");
                                                return false;
                                            }
                                        }else{
                                            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Address");
                                            return false;
                                        }
                                    }else{
                                        showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Mother Name");
                                        return false;
                                    }
                                }else{
                                    showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Student ABC\n (Academic Bank of Credits)");
                                    return false;
                                }
                            }else{
                                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Student Name");
                                return false;
                            }
                        }else{
                            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Student Registration No");
                            return false;
                        }
                    }else{
                        showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Semester");
                        return false;
                    }
                }else{
                    showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Enter Programme Detail");
                    return false;
                }
            }else{
                showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Programme");
                return false;
            }
        }else{
            showErrorDialog_fragment(getContext(), "Backlog Exam Form", "Please! Select Name of Examination");
            return false;
        }
    }
}