package com.example.admissionexamform.model;

public class modelExamEligibility {

    String USNNo, StudentName, CollegeId, Semester;

    public modelExamEligibility() {
    }

    public String getUSNNo() {
        return USNNo;
    }

    public void setUSNNo(String USNNo) {
        this.USNNo = USNNo;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getCollegeId() {
        return CollegeId;
    }

    public void setCollegeId(String collegeId) {
        CollegeId = collegeId;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
