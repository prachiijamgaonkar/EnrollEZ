package com.example.admissionexamform.fragments;

import static com.example.admissionexamform.classes.AppConstant.isNetworkAvailable;
import static com.example.admissionexamform.classes.AppConstant.showErrorDialog_fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admissionexamform.R;
import com.example.admissionexamform.classes.AppClass;
import com.example.admissionexamform.classes.AppConstant;
import com.example.admissionexamform.model.modelAdmissionPage2;
import com.example.admissionexamform.model.modelApproved;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Admission_Page2 extends Fragment {

    EditText etAddress, etVillageCity, etState, etStateDomicile, etCategory, etCast, etSubCaste;
    EditText etPreviousYear,etParentProfession, etAnnualIncome, etBankName, etBranch, etAccountNo;
    EditText etifsccode, etmicrcode, et10thpercentage, et12thpercentage, etdiplomapercentage, etdegreepercentage;
    EditText etfirstsem_marksobtained, etfirstsem_totalmarks, etfirstsem_perofmarks, etfirstsem_sgpa, etfirstsem_numofsubjects;
    EditText etsecondsem_marksobtained, etsecondsem_totalmarks, etsecondsem_perofmarks, etsecondsem_sgpa, etsecondsem_numofsubjects;
    EditText etthirdsem_marksobtained, etthirdsem_totalmarks, etthirdsem_perofmarks, etthirdsem_sgpa, etthirdsem_numofsubjects;
    EditText etfourthsem_marksobtained, etfourthsem_totalmarks, etfourthsem_perofmarks, etfourthsem_sgpa, etfourthsem_numofsubjects;
    EditText etfifthsem_marksobtained, etfifthsem_totalmarks, etfifthsem_perofmarks, etfifthsem_sgpa, etfifthsem_numofsubjects;
    EditText etsixthsem_marksobtained, etsixthsem_totalmarks, etsixthsem_perofmarks, etsixthsem_sgpa, etsixthsem_numofsubjects;
    Spinner spitype, spiCategory, spifirstsem_passatkt, spisecondsem_passatkt, spithirdsem_passatkt, spifourthsem_passatkt, spififthsem_passatkt, spisixthsem_passatkt;
    LinearLayout FirstSemester, SecondSemester, ThirdSemester, FourthSemester, FifthSemester, SixthSemester;
    Button btnad_submit2;
    
    DatabaseReference databasePage2;

    Handler handler;

    public Admission_Page2() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View AddmissionPage2 = inflater.inflate(R.layout.fragment_admission__page2, container, false);

        FirebaseApp.initializeApp(getContext());
        databasePage2 = FirebaseDatabase.getInstance().getReference("College_Database");

        handler = new Handler();

        etAddress = AddmissionPage2.findViewById(R.id.etad_Address);
        etVillageCity = AddmissionPage2.findViewById(R.id.etad_VillageCity);
        etState = AddmissionPage2.findViewById(R.id.etad_State);
        etStateDomicile = AddmissionPage2.findViewById(R.id.etad_StateDomicile);
        spiCategory = AddmissionPage2.findViewById(R.id.spiad_Category);
        etCast = AddmissionPage2.findViewById(R.id.etad_Cast);
        etSubCaste = AddmissionPage2.findViewById(R.id.etad_SubCaste);
        etPreviousYear = AddmissionPage2.findViewById(R.id.etad_PreviousYear);
        etParentProfession = AddmissionPage2.findViewById(R.id.etad_ParentProfession);
        etAnnualIncome = AddmissionPage2.findViewById(R.id.etad_AnnualIncome);
        etBankName = AddmissionPage2.findViewById(R.id.etad_BankName);
        etBranch = AddmissionPage2.findViewById(R.id.etad_Branch);
        etAccountNo = AddmissionPage2.findViewById(R.id.etad_AccountNo);
        etifsccode = AddmissionPage2.findViewById(R.id.etad_ifsccode);
        etmicrcode = AddmissionPage2.findViewById(R.id.etad_micrcode);
        et10thpercentage = AddmissionPage2.findViewById(R.id.etad_10thpercentage);
        et12thpercentage = AddmissionPage2.findViewById(R.id.etad_12thpercentage);
        etdiplomapercentage = AddmissionPage2.findViewById(R.id.etad_diplomapercentage);
        etdegreepercentage = AddmissionPage2.findViewById(R.id.etad_degreepercentage);
        spifirstsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_firstsem_passatkt);
        etfirstsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_firstsem_marksobtained);
        etfirstsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_firstsem_totalmarks);
        etfirstsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_firstsem_perofmarks);
        etfirstsem_sgpa = AddmissionPage2.findViewById(R.id.etad_firstsem_sgpa);
        etfirstsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_firstsem_numofsubjects);
        spisecondsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_secondsem_passatkt);
        etsecondsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_secondsem_marksobtained);
        etsecondsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_secondsem_totalmarks);
        etsecondsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_secondsem_perofmarks);
        etsecondsem_sgpa = AddmissionPage2.findViewById(R.id.etad_secondsem_sgpa);
        etsecondsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_secondsem_numofsubjects);
        spithirdsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_thirdsem_passatkt);
        etthirdsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_thirdsem_marksobtained);
        etthirdsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_thirdsem_totalmarks);
        etthirdsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_thirdsem_perofmarks);
        etthirdsem_sgpa = AddmissionPage2.findViewById(R.id.etad_thirdsem_sgpa);
        etthirdsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_thirdsem_numofsubjects);
        spifourthsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_fourthsem_passatkt);
        etfourthsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_fourthsem_marksobtained);
        etfourthsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_fourthsem_totalmarks);
        etfourthsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_fourthsem_perofmarks);
        etfourthsem_sgpa = AddmissionPage2.findViewById(R.id.etad_fourthsem_sgpa);
        etfourthsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_fourthsem_numofsubjects);
        spififthsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_fifthsem_passatkt);
        etfifthsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_fifthsem_marksobtained);
        etfifthsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_fifthsem_totalmarks);
        etfifthsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_fifthsem_perofmarks);
        etfifthsem_sgpa = AddmissionPage2.findViewById(R.id.etad_fifthsem_sgpa);
        etfifthsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_fifthsem_numofsubjects);
        spisixthsem_passatkt = AddmissionPage2.findViewById(R.id.spiad_sixthsem_passatkt);
        etsixthsem_marksobtained = AddmissionPage2.findViewById(R.id.etad_sixthsem_marksobtained);
        etsixthsem_totalmarks = AddmissionPage2.findViewById(R.id.etad_sixthsem_totalmarks);
        etsixthsem_perofmarks = AddmissionPage2.findViewById(R.id.etad_sixthsem_perofmarks);
        etsixthsem_sgpa = AddmissionPage2.findViewById(R.id.etad_sixthsem_sgpa);
        etsixthsem_numofsubjects = AddmissionPage2.findViewById(R.id.etad_sixthsem_numofsubjects);
        spitype = AddmissionPage2.findViewById(R.id.spiad_type);
        FirstSemester = AddmissionPage2.findViewById(R.id.rlad_FirstSemester);
        SecondSemester = AddmissionPage2.findViewById(R.id.rlad_SecondSemester);
        ThirdSemester = AddmissionPage2.findViewById(R.id.rlad_ThirdSemester);
        FourthSemester = AddmissionPage2.findViewById(R.id.rlad_FourthSemester);
        FifthSemester = AddmissionPage2.findViewById(R.id.rlad_FifthSemester);
        SixthSemester = AddmissionPage2.findViewById(R.id.rlad_SixthSemester);
        btnad_submit2 = AddmissionPage2.findViewById(R.id.btnad_Submit2);

        ArrayList<String> arrayCategory = new ArrayList<>();
        arrayCategory.add("");
        arrayCategory.add("Open");
        arrayCategory.add("OBC");
        arrayCategory.add("SC");
        arrayCategory.add("ST");
        arrayCategory.add("SBC");
        arrayCategory.add("ESBC");
        arrayCategory.add("VJ-DT");
        arrayCategory.add("NT1");
        arrayCategory.add("NT2");
        arrayCategory.add("NT3");
        arrayCategory.add("EWS");
        ArrayAdapter<String> arrayAdapterCategory = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayCategory);
        arrayAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiCategory.setAdapter(arrayAdapterCategory);

        ArrayList<String> arrayListtype = new ArrayList<>();
        arrayListtype.add("");
        arrayListtype.add("Scholarship");
        arrayListtype.add("Freeship");
        arrayListtype.add("Minority");
        arrayListtype.add("EBC");
        ArrayAdapter<String> arrayAdaptertype = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListtype);
        arrayAdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spitype.setAdapter(arrayAdaptertype);

        ArrayList<String> arrayListfirstsem = new ArrayList<>();
        arrayListfirstsem.add("");
        arrayListfirstsem.add("Pass");
        arrayListfirstsem.add("ATKT");
        ArrayAdapter<String> arrayAdapterfirstsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListfirstsem);
        arrayAdapterfirstsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spifirstsem_passatkt.setAdapter(arrayAdapterfirstsem);

        ArrayList<String> arrayListsecondsem = new ArrayList<>();
        arrayListsecondsem.add("");
        arrayListsecondsem.add("Pass");
        arrayListsecondsem.add("ATKT");
        ArrayAdapter<String> arrayAdaptersecondsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListsecondsem);
        arrayAdaptersecondsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spisecondsem_passatkt.setAdapter(arrayAdaptersecondsem);

        ArrayList<String> arrayListthirdsem = new ArrayList<>();
        arrayListthirdsem.add("");
        arrayListthirdsem.add("Pass");
        arrayListthirdsem.add("ATKT");
        ArrayAdapter<String> arrayAdapterthirdsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListthirdsem);
        arrayAdapterthirdsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spithirdsem_passatkt.setAdapter(arrayAdapterthirdsem);

        ArrayList<String> arrayListfourthsem = new ArrayList<>();
        arrayListfourthsem.add("");
        arrayListfourthsem.add("Pass");
        arrayListfourthsem.add("ATKT");
        ArrayAdapter<String> arrayAdapterfourthsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListfourthsem);
        arrayAdapterfourthsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spifourthsem_passatkt.setAdapter(arrayAdapterfourthsem);

        ArrayList<String> arrayListfifthsem = new ArrayList<>();
        arrayListfifthsem.add("");
        arrayListfifthsem.add("Pass");
        arrayListfifthsem.add("ATKT");
        ArrayAdapter<String> arrayAdapterfifthsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListfifthsem);
        arrayAdapterfifthsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spififthsem_passatkt.setAdapter(arrayAdapterfifthsem);

        ArrayList<String> arrayListsixthsem = new ArrayList<>();
        arrayListsixthsem.add("");
        arrayListsixthsem.add("Pass");
        arrayListsixthsem.add("ATKT");
        ArrayAdapter<String> arrayAdaptersixthsem = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_item, arrayListsixthsem);
        arrayAdaptersixthsem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spisixthsem_passatkt.setAdapter(arrayAdaptersixthsem);

        handler.post( new Runnable() {
            @Override
            public void run()
            {
                if(AppClass.getAdmissiomYear() != null && !AppClass.getAdmissiomYear().isEmpty()){
                    if(AppClass.getAdmissiomYear().equals("Second Year B.E.")){
                        FirstSemester.setVisibility(View.VISIBLE);
                        SecondSemester.setVisibility(View.VISIBLE);
                        ThirdSemester.setVisibility(View.GONE);
                        FourthSemester.setVisibility(View.GONE);
                        FifthSemester.setVisibility(View.GONE);
                        SixthSemester.setVisibility(View.GONE);
                    }else if(AppClass.getAdmissiomYear().equals("Third Year B.E.")){
                        FirstSemester.setVisibility(View.VISIBLE);
                        SecondSemester.setVisibility(View.VISIBLE);
                        ThirdSemester.setVisibility(View.VISIBLE);
                        FourthSemester.setVisibility(View.VISIBLE);
                        FifthSemester.setVisibility(View.GONE);
                        SixthSemester.setVisibility(View.GONE);
                    }else if(AppClass.getAdmissiomYear().equals("Final Year B.E.")){
                        FirstSemester.setVisibility(View.VISIBLE);
                        SecondSemester.setVisibility(View.VISIBLE);
                        ThirdSemester.setVisibility(View.VISIBLE);
                        FourthSemester.setVisibility(View.VISIBLE);
                        FifthSemester.setVisibility(View.VISIBLE);
                        SixthSemester.setVisibility(View.VISIBLE);
                    }else{
                        FirstSemester.setVisibility(View.GONE);
                        SecondSemester.setVisibility(View.GONE);
                        ThirdSemester.setVisibility(View.GONE);
                        FourthSemester.setVisibility(View.GONE);
                        FifthSemester.setVisibility(View.GONE);
                        SixthSemester.setVisibility(View.GONE);
                    }
                }
            handler.postDelayed(this, 15000);
        }
    });

        if (isNetworkAvailable(getContext())){
            databasePage2.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Page_2").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.exists()){
                                modelAdmissionPage2 cmodel = snapshot.getValue(modelAdmissionPage2.class);

                                ArrayAdapter<String> array_spinner1=(ArrayAdapter<String>)spiCategory.getAdapter();
                                spiCategory.setSelection(array_spinner1.getPosition(cmodel.getCategory()));

                                ArrayAdapter<String> array_spinner2=(ArrayAdapter<String>)spifirstsem_passatkt.getAdapter();
                                spifirstsem_passatkt.setSelection(array_spinner2.getPosition(cmodel.getFirstsem_Passatkt()));

                                ArrayAdapter<String> array_spinner3=(ArrayAdapter<String>)spisecondsem_passatkt.getAdapter();
                                spisecondsem_passatkt.setSelection(array_spinner3.getPosition(cmodel.getSecondsem_Passatkt()));

                                ArrayAdapter<String> array_spinner4=(ArrayAdapter<String>)spithirdsem_passatkt.getAdapter();
                                spithirdsem_passatkt.setSelection(array_spinner4.getPosition(cmodel.getThirdsem_Passatkt()));

                                ArrayAdapter<String> array_spinner5=(ArrayAdapter<String>)spifourthsem_passatkt.getAdapter();
                                spifourthsem_passatkt.setSelection(array_spinner5.getPosition(cmodel.getFourthsem_Passatkt()));

                                ArrayAdapter<String> array_spinner6=(ArrayAdapter<String>)spififthsem_passatkt.getAdapter();
                                spififthsem_passatkt.setSelection(array_spinner6.getPosition(cmodel.getFifthsem_Passatkt()));

                                ArrayAdapter<String> array_spinner7=(ArrayAdapter<String>)spisixthsem_passatkt.getAdapter();
                                spisixthsem_passatkt.setSelection(array_spinner7.getPosition(cmodel.getSixthsem_Passatkt()));

                                ArrayAdapter<String> array_spinner8=(ArrayAdapter<String>)spitype.getAdapter();
                                spitype.setSelection(array_spinner8.getPosition(cmodel.getType()));

                                etAddress.setText(cmodel.getAddress());
                                etVillageCity.setText(cmodel.getVillageCity());
                                etState.setText(cmodel.getState());
                                etStateDomicile.setText(cmodel.getStateDomicile());
                                etCast.setText(cmodel.getCast());
                                etSubCaste.setText(cmodel.getSubCaste());
                                etPreviousYear.setText(cmodel.getPreviousYear());
                                etParentProfession.setText(cmodel.getParentProfession());
                                etAnnualIncome.setText(cmodel.getAnnualIncome());
                                etBankName.setText(cmodel.getBankName());
                                etBranch.setText(cmodel.getBranch());
                                etAccountNo.setText(cmodel.getAccountNo());
                                etifsccode.setText(cmodel.getIFSCode());
                                etmicrcode.setText(cmodel.getMICRCode());
                                et10thpercentage.setText(cmodel.getTenthPercentage());
                                et12thpercentage.setText(cmodel.getTwelthPercentage());
                                etdiplomapercentage.setText(cmodel.getDiplomaPercentage());
                                etdegreepercentage.setText(cmodel.getDegreePercentage());
                                etfirstsem_marksobtained.setText(cmodel.getFirstsem_Marksobtained());
                                etfirstsem_totalmarks.setText(cmodel.getFirstsem_Totalmarks());
                                etfirstsem_perofmarks.setText(cmodel.getFirstsem_Perofmarks());
                                etfirstsem_sgpa.setText(cmodel.getFirstsem_Sgpa());
                                etfirstsem_numofsubjects.setText(cmodel.getFirstsem_Numofsubjects());
                                etsecondsem_marksobtained.setText(cmodel.getSecondsem_Marksobtained());
                                etsecondsem_totalmarks.setText(cmodel.getSecondsem_Totalmarks());
                                etsecondsem_perofmarks.setText(cmodel.getSecondsem_Perofmarks());
                                etsecondsem_sgpa.setText(cmodel.getSecondsem_Sgpa());
                                etsecondsem_numofsubjects.setText(cmodel.getSecondsem_Numofsubjects());
                                etthirdsem_marksobtained.setText(cmodel.getThirdsem_Marksobtained());
                                etthirdsem_totalmarks.setText(cmodel.getThirdsem_Totalmarks());
                                etthirdsem_perofmarks.setText(cmodel.getThirdsem_Perofmarks());
                                etthirdsem_sgpa.setText(cmodel.getThirdsem_Sgpa());
                                etthirdsem_numofsubjects.setText(cmodel.getThirdsem_Numofsubjects());
                                etfourthsem_marksobtained.setText(cmodel.getFourthsem_Marksobtained());
                                etfourthsem_totalmarks.setText(cmodel.getFourthsem_Totalmarks());
                                etfourthsem_perofmarks.setText(cmodel.getFourthsem_Perofmarks());
                                etfourthsem_sgpa.setText(cmodel.getFourthsem_Sgpa());
                                etfourthsem_numofsubjects.setText(cmodel.getFourthsem_Numofsubjects());
                                etfifthsem_marksobtained.setText(cmodel.getFifthsem_Marksobtained());
                                etfifthsem_totalmarks.setText(cmodel.getFifthsem_Totalmarks());
                                etfifthsem_perofmarks.setText(cmodel.getFifthsem_Perofmarks());
                                etfifthsem_sgpa.setText(cmodel.getFifthsem_Sgpa());
                                etfifthsem_numofsubjects.setText(cmodel.getFifthsem_Numofsubjects());
                                etsixthsem_marksobtained.setText(cmodel.getSixthsem_Marksobtained());
                                etsixthsem_totalmarks.setText(cmodel.getSixthsem_Totalmarks());
                                etsixthsem_perofmarks.setText(cmodel.getSixthsem_Perofmarks());
                                etsixthsem_sgpa.setText(cmodel.getSixthsem_Sgpa());
                                etsixthsem_numofsubjects.setText(cmodel.getSixthsem_Numofsubjects());
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

            databasePage2.child("Admission_Form")
                    .child(AppClass.getUserNo())
                    .child("Approval").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            modelApproved cmodel= snapshot.getValue(modelApproved.class);

                            if(snapshot.exists()) {
                                if (cmodel.getApproval().equals("approve")) {
                                    etAddress.setEnabled(false);
                                    etVillageCity.setEnabled(false);
                                    etState.setEnabled(false);
                                    etStateDomicile.setEnabled(false);
                                    spiCategory.setEnabled(false);
                                    etCast.setEnabled(false);
                                    etSubCaste.setEnabled(false);
                                    etPreviousYear.setEnabled(false);
                                    etParentProfession.setEnabled(false);
                                    etAnnualIncome.setEnabled(false);
                                    etBankName.setEnabled(false);
                                    etBranch.setEnabled(false);
                                    etAccountNo.setEnabled(false);
                                    etifsccode.setEnabled(false);
                                    etmicrcode.setEnabled(false);
                                    et10thpercentage.setEnabled(false);
                                    et12thpercentage.setEnabled(false);
                                    etdiplomapercentage.setEnabled(false);
                                    etdegreepercentage.setEnabled(false);
                                    spifirstsem_passatkt.setEnabled(false);
                                    etfirstsem_marksobtained.setEnabled(false);
                                    etfirstsem_totalmarks.setEnabled(false);
                                    etfirstsem_perofmarks.setEnabled(false);
                                    etfirstsem_sgpa.setEnabled(false);
                                    etfirstsem_numofsubjects.setEnabled(false);
                                    spisecondsem_passatkt.setEnabled(false);
                                    etsecondsem_marksobtained.setEnabled(false);
                                    etsecondsem_totalmarks.setEnabled(false);
                                    etsecondsem_perofmarks.setEnabled(false);
                                    etsecondsem_sgpa.setEnabled(false);
                                    etsecondsem_numofsubjects.setEnabled(false);
                                    spithirdsem_passatkt.setEnabled(false);
                                    etthirdsem_marksobtained.setEnabled(false);
                                    etthirdsem_totalmarks.setEnabled(false);
                                    etthirdsem_perofmarks.setEnabled(false);
                                    etthirdsem_sgpa.setEnabled(false);
                                    etthirdsem_numofsubjects.setEnabled(false);
                                    spifourthsem_passatkt.setEnabled(false);
                                    etfourthsem_marksobtained.setEnabled(false);
                                    etfourthsem_totalmarks.setEnabled(false);
                                    etfourthsem_perofmarks.setEnabled(false);
                                    etfourthsem_sgpa.setEnabled(false);
                                    etfourthsem_numofsubjects.setEnabled(false);
                                    spififthsem_passatkt.setEnabled(false);
                                    etfifthsem_marksobtained.setEnabled(false);
                                    etfifthsem_totalmarks.setEnabled(false);
                                    etfifthsem_perofmarks.setEnabled(false);
                                    etfifthsem_sgpa.setEnabled(false);
                                    etfifthsem_numofsubjects.setEnabled(false);
                                    spisixthsem_passatkt.setEnabled(false);
                                    etsixthsem_marksobtained.setEnabled(false);
                                    etsixthsem_totalmarks.setEnabled(false);
                                    etsixthsem_perofmarks.setEnabled(false);
                                    etsixthsem_sgpa.setEnabled(false);
                                    etsixthsem_numofsubjects.setEnabled(false);
                                    spitype.setEnabled(false);
                                    btnad_submit2.setEnabled(false);
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

        etfirstsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etfirstsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etfirstsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etfirstsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etfirstsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etfirstsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etfirstsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });

        etsecondsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etsecondsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etsecondsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etsecondsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etsecondsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etsecondsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etsecondsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });

        etthirdsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etthirdsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etthirdsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etthirdsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etthirdsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etthirdsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etthirdsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });

        etfourthsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etfourthsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etfourthsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etfourthsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etfourthsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etfourthsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etfourthsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });

        etfifthsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etfifthsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etfifthsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etfifthsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etfifthsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etfifthsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etfifthsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });

        etsixthsem_totalmarks.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if(!TextUtils.isEmpty(etsixthsem_marksobtained.getText())){
                        if(!TextUtils.isEmpty(etsixthsem_totalmarks.getText())){
                            DecimalFormat df = new DecimalFormat("##.##");
                            float obtainedvalue = Float.valueOf(etsixthsem_marksobtained.getText().toString());
                            float totalmarks = Integer.valueOf(etsixthsem_totalmarks.getText().toString());
                            float percent = (obtainedvalue / totalmarks) * 100;
                            etsixthsem_perofmarks.setText(String.valueOf(df.format(percent)) + "%");
                            float sgpa = (percent / (float)9.5);
                            etsixthsem_sgpa.setText(String.valueOf(df.format(sgpa)));
                        }
                    }
                }
            }
        });
        
        btnad_submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidate()){
                    if (isNetworkAvailable(getContext())){
                        modelAdmissionPage2 modeladd = new modelAdmissionPage2();

                        modeladd.setAddress(etAddress.getText().toString());
                        modeladd.setVillageCity(etVillageCity.getText().toString());
                        modeladd.setState(etState.getText().toString());
                        modeladd.setStateDomicile(etStateDomicile.getText().toString());
                        modeladd.setCategory(spiCategory.getSelectedItem().toString());
                        modeladd.setCast(etCast.getText().toString());
                        modeladd.setSubCaste(etSubCaste.getText().toString());
                        modeladd.setPreviousYear(etPreviousYear.getText().toString());
                        modeladd.setParentProfession(etParentProfession.getText().toString());
                        modeladd.setAnnualIncome(etAnnualIncome.getText().toString());
                        modeladd.setBankName(etBankName.getText().toString());
                        modeladd.setBranch(etBranch.getText().toString());
                        modeladd.setAccountNo(etAccountNo.getText().toString());
                        modeladd.setIFSCode(etifsccode.getText().toString());
                        modeladd.setMICRCode(etmicrcode.getText().toString());
                        modeladd.setTenthPercentage(et10thpercentage.getText().toString());
                        modeladd.setTwelthPercentage(et12thpercentage.getText().toString());
                        modeladd.setDiplomaPercentage(etdiplomapercentage.getText().toString());
                        modeladd.setDegreePercentage(etdegreepercentage.getText().toString());
                        modeladd.setFirstsem_Passatkt(spifirstsem_passatkt.getSelectedItem().toString());
                        modeladd.setFirstsem_Marksobtained(etfirstsem_marksobtained.getText().toString());
                        modeladd.setFirstsem_Totalmarks(etfirstsem_totalmarks.getText().toString());
                        modeladd.setFirstsem_Perofmarks(etfirstsem_perofmarks.getText().toString());
                        modeladd.setFirstsem_Sgpa(etfirstsem_sgpa.getText().toString());
                        modeladd.setFirstsem_Numofsubjects(etfirstsem_numofsubjects.getText().toString());
                        modeladd.setSecondsem_Passatkt(spisecondsem_passatkt.getSelectedItem().toString());
                        modeladd.setSecondsem_Marksobtained(etsecondsem_marksobtained.getText().toString());
                        modeladd.setSecondsem_Totalmarks(etsecondsem_totalmarks.getText().toString());
                        modeladd.setSecondsem_Perofmarks(etsecondsem_perofmarks.getText().toString());
                        modeladd.setSecondsem_Sgpa(etsecondsem_sgpa.getText().toString());
                        modeladd.setSecondsem_Numofsubjects(etsecondsem_numofsubjects.getText().toString());
                        modeladd.setThirdsem_Passatkt(spithirdsem_passatkt.getSelectedItem().toString());
                        modeladd.setThirdsem_Marksobtained(etthirdsem_marksobtained.getText().toString());
                        modeladd.setThirdsem_Totalmarks(etthirdsem_totalmarks.getText().toString());
                        modeladd.setThirdsem_Perofmarks(etthirdsem_perofmarks.getText().toString());
                        modeladd.setThirdsem_Sgpa(etthirdsem_sgpa.getText().toString());
                        modeladd.setThirdsem_Numofsubjects(etthirdsem_numofsubjects.getText().toString());
                        modeladd.setFourthsem_Passatkt(spifourthsem_passatkt.getSelectedItem().toString());
                        modeladd.setFourthsem_Marksobtained(etfourthsem_marksobtained.getText().toString());
                        modeladd.setFourthsem_Totalmarks(etfourthsem_totalmarks.getText().toString());
                        modeladd.setFourthsem_Perofmarks(etfourthsem_perofmarks.getText().toString());
                        modeladd.setFourthsem_Sgpa(etfourthsem_sgpa.getText().toString());
                        modeladd.setFourthsem_Numofsubjects(etfourthsem_numofsubjects.getText().toString());
                        modeladd.setFifthsem_Passatkt(spififthsem_passatkt.getSelectedItem().toString());
                        modeladd.setFifthsem_Marksobtained(etfifthsem_marksobtained.getText().toString());
                        modeladd.setFifthsem_Totalmarks(etfifthsem_totalmarks.getText().toString());
                        modeladd.setFifthsem_Perofmarks(etfifthsem_perofmarks.getText().toString());
                        modeladd.setFifthsem_Sgpa(etfifthsem_sgpa.getText().toString());
                        modeladd.setFifthsem_Numofsubjects(etfifthsem_numofsubjects.getText().toString());
                        modeladd.setSixthsem_Passatkt(spisixthsem_passatkt.getSelectedItem().toString());
                        modeladd.setSixthsem_Marksobtained(etsixthsem_marksobtained.getText().toString());
                        modeladd.setSixthsem_Totalmarks(etsixthsem_totalmarks.getText().toString());
                        modeladd.setSixthsem_Perofmarks(etsixthsem_perofmarks.getText().toString());
                        modeladd.setSixthsem_Sgpa(etsixthsem_sgpa.getText().toString());
                        modeladd.setSixthsem_Numofsubjects(etsixthsem_numofsubjects.getText().toString());
                        modeladd.setType(spitype.getSelectedItem().toString());

                        databasePage2.child("Admission_Form")
                                .child(AppClass.getUserNo())
                                .child("Page_2").setValue(modeladd).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        AppConstant.showErrorDialog_fragment(getContext(),"Admission Page","Page 2 successfully submitted. Please fill page 3.");
                                    }
                                });

                        modelApproved cmodel = new modelApproved();
                        cmodel.setApproval(" ");

                        databasePage2.child("Admission_Form")
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

        return  AddmissionPage2;
    }

    public boolean isValidate(){
        if(!TextUtils.isEmpty(etAddress.getText())){
            if(!TextUtils.isEmpty(etVillageCity.getText())){
                if(!TextUtils.isEmpty(etState.getText())){
                    if(!TextUtils.isEmpty(etStateDomicile.getText())){
                        if(!spiCategory.getSelectedItem().equals("")){
                            if(!TextUtils.isEmpty(etCast.getText())){
                                if(!TextUtils.isEmpty(etSubCaste.getText())){
                                    if(!TextUtils.isEmpty(etPreviousYear.getText())){
                                        if(!TextUtils.isEmpty(etParentProfession.getText())){
                                            if(!TextUtils.isEmpty(etAnnualIncome.getText())){
                                                if(!TextUtils.isEmpty(etBankName.getText())){
                                                    if(!TextUtils.isEmpty(etBranch.getText())){
                                                        if(!TextUtils.isEmpty(etAccountNo.getText())){
                                                            if(!TextUtils.isEmpty(etifsccode.getText())){
                                                                if(!TextUtils.isEmpty(etmicrcode.getText())){
                                                                    return true;
                                                                }else{
                                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter MICR Code");
                                                                    return false;
                                                                }
                                                            }else{
                                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter IFSC Code");
                                                                return false;
                                                            }
                                                        }else{
                                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Account No");
                                                            return false;
                                                        }
                                                    }else{
                                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Branch");
                                                        return false;
                                                    }
                                                }else{
                                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Bank Name");
                                                    return false;
                                                }
                                            }else{
                                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Annual Income");
                                                return false;
                                            }
                                        }else{
                                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Parent Profession");
                                            return false;
                                        }
                                    }else{
                                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Previous Year");
                                        return false;
                                    }
                                }else{
                                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Sub Caste");
                                    return false;
                                }
                            }else{
                                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Cast");
                                return false;
                            }
                        }else{
                            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Select Category");
                            return false;
                        }
                    }else{
                        showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter State Domicile");
                        return false;
                    }
                }else{
                    showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter State");
                    return false;
                }
            }else{
                showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Village City");
                return false;
            }
        }else{
            showErrorDialog_fragment(getContext(), "Admission Form", "Please! Enter Address");
            return false;
        }
    }
}