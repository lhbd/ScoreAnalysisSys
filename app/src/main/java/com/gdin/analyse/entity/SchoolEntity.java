package com.gdin.analyse.entity;


public class SchoolEntity {
    public int schoolId;
    public String schoolName;

    public SchoolEntity(int schoolId, String schoolName) {

        this.schoolId = schoolId;
        this.schoolName = schoolName;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }
}
