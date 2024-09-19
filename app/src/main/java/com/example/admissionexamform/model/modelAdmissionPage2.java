package com.example.admissionexamform.model;

import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.Exclude;

public class modelAdmissionPage2 {

    String Address, VillageCity, State, StateDomicile, Category, Cast, SubCaste;
    String PreviousYear, ParentProfession, AnnualIncome, BankName, Branch, AccountNo;
    String IFSCode, MICRCode, TenthPercentage, TwelthPercentage, DiplomaPercentage, DegreePercentage;
    String Firstsem_Passatkt, Firstsem_Marksobtained, Firstsem_Totalmarks, Firstsem_Perofmarks, Firstsem_Sgpa, Firstsem_Numofsubjects;
    String Secondsem_Passatkt, Secondsem_Marksobtained, Secondsem_Totalmarks, Secondsem_Perofmarks, Secondsem_Sgpa, Secondsem_Numofsubjects;
    String Thirdsem_Passatkt, Thirdsem_Marksobtained, Thirdsem_Totalmarks, Thirdsem_Perofmarks, Thirdsem_Sgpa, Thirdsem_Numofsubjects;
    String Fourthsem_Passatkt, Fourthsem_Marksobtained, Fourthsem_Totalmarks, Fourthsem_Perofmarks, Fourthsem_Sgpa, Fourthsem_Numofsubjects;
    String Fifthsem_Passatkt, Fifthsem_Marksobtained, Fifthsem_Totalmarks, Fifthsem_Perofmarks, Fifthsem_Sgpa, Fifthsem_Numofsubjects;
    String Sixthsem_Passatkt, Sixthsem_Marksobtained, Sixthsem_Totalmarks, Sixthsem_Perofmarks, Sixthsem_Sgpa, Sixthsem_Numofsubjects;
    String Type, Key;

    public modelAdmissionPage2() {
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getVillageCity() {
        return VillageCity;
    }

    public void setVillageCity(String villageCity) {
        VillageCity = villageCity;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getStateDomicile() {
        return StateDomicile;
    }

    public void setStateDomicile(String stateDomicile) {
        StateDomicile = stateDomicile;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getCast() {
        return Cast;
    }

    public void setCast(String cast) {
        Cast = cast;
    }

    public String getSubCaste() {
        return SubCaste;
    }

    public void setSubCaste(String subCaste) {
        SubCaste = subCaste;
    }

    public String getPreviousYear() {
        return PreviousYear;
    }

    public void setPreviousYear(String previousYear) {
        PreviousYear = previousYear;
    }

    public String getParentProfession() {
        return ParentProfession;
    }

    public void setParentProfession(String parentProfession) {
        ParentProfession = parentProfession;
    }

    public String getAnnualIncome() {
        return AnnualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        AnnualIncome = annualIncome;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getIFSCode() {
        return IFSCode;
    }

    public void setIFSCode(String IFSCode) {
        this.IFSCode = IFSCode;
    }

    public String getMICRCode() {
        return MICRCode;
    }

    public void setMICRCode(String MICRCode) {
        this.MICRCode = MICRCode;
    }

    public String getTenthPercentage() {
        return TenthPercentage;
    }

    public void setTenthPercentage(String tenthPercentage) {
        TenthPercentage = tenthPercentage;
    }

    public String getTwelthPercentage() {
        return TwelthPercentage;
    }

    public void setTwelthPercentage(String twelthPercentage) {
        TwelthPercentage = twelthPercentage;
    }

    public String getDiplomaPercentage() {
        return DiplomaPercentage;
    }

    public void setDiplomaPercentage(String diplomaPercentage) {
        DiplomaPercentage = diplomaPercentage;
    }

    public String getDegreePercentage() {
        return DegreePercentage;
    }

    public void setDegreePercentage(String degreePercentage) {
        DegreePercentage = degreePercentage;
    }

    public String getFirstsem_Passatkt() {
        return Firstsem_Passatkt;
    }

    public void setFirstsem_Passatkt(String firstsem_Passatkt) {
        Firstsem_Passatkt = firstsem_Passatkt;
    }

    public String getFirstsem_Marksobtained() {
        return Firstsem_Marksobtained;
    }

    public void setFirstsem_Marksobtained(String firstsem_Marksobtained) {
        Firstsem_Marksobtained = firstsem_Marksobtained;
    }

    public String getFirstsem_Totalmarks() {
        return Firstsem_Totalmarks;
    }

    public void setFirstsem_Totalmarks(String firstsem_Totalmarks) {
        Firstsem_Totalmarks = firstsem_Totalmarks;
    }

    public String getFirstsem_Perofmarks() {
        return Firstsem_Perofmarks;
    }

    public void setFirstsem_Perofmarks(String firstsem_Perofmarks) {
        Firstsem_Perofmarks = firstsem_Perofmarks;
    }

    public String getFirstsem_Sgpa() {
        return Firstsem_Sgpa;
    }

    public void setFirstsem_Sgpa(String firstsem_Sgpa) {
        Firstsem_Sgpa = firstsem_Sgpa;
    }

    public String getFirstsem_Numofsubjects() {
        return Firstsem_Numofsubjects;
    }

    public void setFirstsem_Numofsubjects(String firstsem_Numofsubjects) {
        Firstsem_Numofsubjects = firstsem_Numofsubjects;
    }

    public String getSecondsem_Passatkt() {
        return Secondsem_Passatkt;
    }

    public void setSecondsem_Passatkt(String secondsem_Passatkt) {
        Secondsem_Passatkt = secondsem_Passatkt;
    }

    public String getSecondsem_Marksobtained() {
        return Secondsem_Marksobtained;
    }

    public void setSecondsem_Marksobtained(String secondsem_Marksobtained) {
        Secondsem_Marksobtained = secondsem_Marksobtained;
    }

    public String getSecondsem_Totalmarks() {
        return Secondsem_Totalmarks;
    }

    public void setSecondsem_Totalmarks(String secondsem_Totalmarks) {
        Secondsem_Totalmarks = secondsem_Totalmarks;
    }

    public String getSecondsem_Perofmarks() {
        return Secondsem_Perofmarks;
    }

    public void setSecondsem_Perofmarks(String secondsem_Perofmarks) {
        Secondsem_Perofmarks = secondsem_Perofmarks;
    }

    public String getSecondsem_Sgpa() {
        return Secondsem_Sgpa;
    }

    public void setSecondsem_Sgpa(String secondsem_Sgpa) {
        Secondsem_Sgpa = secondsem_Sgpa;
    }

    public String getSecondsem_Numofsubjects() {
        return Secondsem_Numofsubjects;
    }

    public void setSecondsem_Numofsubjects(String secondsem_Numofsubjects) {
        Secondsem_Numofsubjects = secondsem_Numofsubjects;
    }

    public String getThirdsem_Passatkt() {
        return Thirdsem_Passatkt;
    }

    public void setThirdsem_Passatkt(String thirdsem_Passatkt) {
        Thirdsem_Passatkt = thirdsem_Passatkt;
    }

    public String getThirdsem_Marksobtained() {
        return Thirdsem_Marksobtained;
    }

    public void setThirdsem_Marksobtained(String thirdsem_Marksobtained) {
        Thirdsem_Marksobtained = thirdsem_Marksobtained;
    }

    public String getThirdsem_Totalmarks() {
        return Thirdsem_Totalmarks;
    }

    public void setThirdsem_Totalmarks(String thirdsem_Totalmarks) {
        Thirdsem_Totalmarks = thirdsem_Totalmarks;
    }

    public String getThirdsem_Perofmarks() {
        return Thirdsem_Perofmarks;
    }

    public void setThirdsem_Perofmarks(String thirdsem_Perofmarks) {
        Thirdsem_Perofmarks = thirdsem_Perofmarks;
    }

    public String getThirdsem_Sgpa() {
        return Thirdsem_Sgpa;
    }

    public void setThirdsem_Sgpa(String thirdsem_Sgpa) {
        Thirdsem_Sgpa = thirdsem_Sgpa;
    }

    public String getThirdsem_Numofsubjects() {
        return Thirdsem_Numofsubjects;
    }

    public void setThirdsem_Numofsubjects(String thirdsem_Numofsubjects) {
        Thirdsem_Numofsubjects = thirdsem_Numofsubjects;
    }

    public String getFourthsem_Passatkt() {
        return Fourthsem_Passatkt;
    }

    public void setFourthsem_Passatkt(String fourthsem_Passatkt) {
        Fourthsem_Passatkt = fourthsem_Passatkt;
    }

    public String getFourthsem_Marksobtained() {
        return Fourthsem_Marksobtained;
    }

    public void setFourthsem_Marksobtained(String fourthsem_Marksobtained) {
        Fourthsem_Marksobtained = fourthsem_Marksobtained;
    }

    public String getFourthsem_Totalmarks() {
        return Fourthsem_Totalmarks;
    }

    public void setFourthsem_Totalmarks(String fourthsem_Totalmarks) {
        Fourthsem_Totalmarks = fourthsem_Totalmarks;
    }

    public String getFourthsem_Perofmarks() {
        return Fourthsem_Perofmarks;
    }

    public void setFourthsem_Perofmarks(String fourthsem_Perofmarks) {
        Fourthsem_Perofmarks = fourthsem_Perofmarks;
    }

    public String getFourthsem_Sgpa() {
        return Fourthsem_Sgpa;
    }

    public void setFourthsem_Sgpa(String fourthsem_Sgpa) {
        Fourthsem_Sgpa = fourthsem_Sgpa;
    }

    public String getFourthsem_Numofsubjects() {
        return Fourthsem_Numofsubjects;
    }

    public void setFourthsem_Numofsubjects(String fourthsem_Numofsubjects) {
        Fourthsem_Numofsubjects = fourthsem_Numofsubjects;
    }

    public String getFifthsem_Passatkt() {
        return Fifthsem_Passatkt;
    }

    public void setFifthsem_Passatkt(String fifthsem_Passatkt) {
        Fifthsem_Passatkt = fifthsem_Passatkt;
    }

    public String getFifthsem_Marksobtained() {
        return Fifthsem_Marksobtained;
    }

    public void setFifthsem_Marksobtained(String fifthsem_Marksobtained) {
        Fifthsem_Marksobtained = fifthsem_Marksobtained;
    }

    public String getFifthsem_Totalmarks() {
        return Fifthsem_Totalmarks;
    }

    public void setFifthsem_Totalmarks(String fifthsem_Totalmarks) {
        Fifthsem_Totalmarks = fifthsem_Totalmarks;
    }

    public String getFifthsem_Perofmarks() {
        return Fifthsem_Perofmarks;
    }

    public void setFifthsem_Perofmarks(String fifthsem_Perofmarks) {
        Fifthsem_Perofmarks = fifthsem_Perofmarks;
    }

    public String getFifthsem_Sgpa() {
        return Fifthsem_Sgpa;
    }

    public void setFifthsem_Sgpa(String fifthsem_Sgpa) {
        Fifthsem_Sgpa = fifthsem_Sgpa;
    }

    public String getFifthsem_Numofsubjects() {
        return Fifthsem_Numofsubjects;
    }

    public void setFifthsem_Numofsubjects(String fifthsem_Numofsubjects) {
        Fifthsem_Numofsubjects = fifthsem_Numofsubjects;
    }

    public String getSixthsem_Passatkt() {
        return Sixthsem_Passatkt;
    }

    public void setSixthsem_Passatkt(String sixthsem_Passatkt) {
        Sixthsem_Passatkt = sixthsem_Passatkt;
    }

    public String getSixthsem_Marksobtained() {
        return Sixthsem_Marksobtained;
    }

    public void setSixthsem_Marksobtained(String sixthsem_Marksobtained) {
        Sixthsem_Marksobtained = sixthsem_Marksobtained;
    }

    public String getSixthsem_Totalmarks() {
        return Sixthsem_Totalmarks;
    }

    public void setSixthsem_Totalmarks(String sixthsem_Totalmarks) {
        Sixthsem_Totalmarks = sixthsem_Totalmarks;
    }

    public String getSixthsem_Perofmarks() {
        return Sixthsem_Perofmarks;
    }

    public void setSixthsem_Perofmarks(String sixthsem_Perofmarks) {
        Sixthsem_Perofmarks = sixthsem_Perofmarks;
    }

    public String getSixthsem_Sgpa() {
        return Sixthsem_Sgpa;
    }

    public void setSixthsem_Sgpa(String sixthsem_Sgpa) {
        Sixthsem_Sgpa = sixthsem_Sgpa;
    }

    public String getSixthsem_Numofsubjects() {
        return Sixthsem_Numofsubjects;
    }

    public void setSixthsem_Numofsubjects(String sixthsem_Numofsubjects) {
        Sixthsem_Numofsubjects = sixthsem_Numofsubjects;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    @Exclude
    public String getKey() {
        return Key;
    }

    @Exclude
    public void setKey(String key) {
        Key = key;
    }
}
