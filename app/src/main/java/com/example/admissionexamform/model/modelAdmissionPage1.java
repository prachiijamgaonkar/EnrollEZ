package com.example.admissionexamform.model;

import com.google.firebase.database.Exclude;

public class modelAdmissionPage1 {

    String AdmissionYear, TypeOfSeat, AdmissionCategory, ResidentialStatus, CandidateBelongs;
    String AdmissionDetails, Title, BloodGropu;
    String WhichAdmitted, ApplicationId, YearofAdmission, DateofAdmission, NameofStudent;
    String FathersName, MothersName, PlaceofBirth, Nationality, Religion ;
    String AddharNo, CollegeRegistration;
    String DateofBirth;
    String Gender;

    String Key;

    public modelAdmissionPage1() {
    }

    public String getAdmissionYear() {
        return AdmissionYear;
    }

    public void setAdmissionYear(String admissionYear) {
        AdmissionYear = admissionYear;
    }

    public String getTypeOfSeat() {
        return TypeOfSeat;
    }

    public void setTypeOfSeat(String typeOfSeat) {
        TypeOfSeat = typeOfSeat;
    }

    public String getAdmissionCategory() {
        return AdmissionCategory;
    }

    public void setAdmissionCategory(String admissionCategory) {
        AdmissionCategory = admissionCategory;
    }

    public String getResidentialStatus() {
        return ResidentialStatus;
    }

    public void setResidentialStatus(String residentialStatus) {
        ResidentialStatus = residentialStatus;
    }

    public String getCandidateBelongs() {
        return CandidateBelongs;
    }

    public void setCandidateBelongs(String candidateBelongs) {
        CandidateBelongs = candidateBelongs;
    }

    public String getAdmissionDetails() {
        return AdmissionDetails;
    }

    public void setAdmissionDetails(String admissionDetails) {
        AdmissionDetails = admissionDetails;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getBloodGropu() {
        return BloodGropu;
    }

    public void setBloodGropu(String bloodGropu) {
        BloodGropu = bloodGropu;
    }

    public String getWhichAdmitted() {
        return WhichAdmitted;
    }

    public void setWhichAdmitted(String whichAdmitted) {
        WhichAdmitted = whichAdmitted;
    }

    public String getApplicationId() {
        return ApplicationId;
    }

    public void setApplicationId(String applicationId) {
        ApplicationId = applicationId;
    }

    public String getYearofAdmission() {
        return YearofAdmission;
    }

    public void setYearofAdmission(String yearofAdmission) {
        YearofAdmission = yearofAdmission;
    }

    public String getDateofAdmission() {
        return DateofAdmission;
    }

    public void setDateofAdmission(String dateofAdmission) {
        DateofAdmission = dateofAdmission;
    }

    public String getNameofStudent() {
        return NameofStudent;
    }

    public void setNameofStudent(String nameofStudent) {
        NameofStudent = nameofStudent;
    }

    public String getFathersName() {
        return FathersName;
    }

    public void setFathersName(String fathersName) {
        FathersName = fathersName;
    }

    public String getMothersName() {
        return MothersName;
    }

    public void setMothersName(String mothersName) {
        MothersName = mothersName;
    }

    public String getPlaceofBirth() {
        return PlaceofBirth;
    }

    public void setPlaceofBirth(String placeofBirth) {
        PlaceofBirth = placeofBirth;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getReligion() {
        return Religion;
    }

    public void setReligion(String religion) {
        Religion = religion;
    }

    public String getAddharNo() {
        return AddharNo;
    }

    public void setAddharNo(String addharNo) {
        AddharNo = addharNo;
    }

    public String getCollegeRegistration() {
        return CollegeRegistration;
    }

    public void setCollegeRegistration(String collegeRegistration) {
        CollegeRegistration = collegeRegistration;
    }

    public String getDateofBirth() {
        return DateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        DateofBirth = dateofBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
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
