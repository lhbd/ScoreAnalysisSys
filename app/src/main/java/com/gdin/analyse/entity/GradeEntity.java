package com.gdin.analyse.entity;

public class GradeEntity {

    public int loginGradeId;
    public String gradeName;

    public GradeEntity(int loginGradeId, String gradeName) {
        this.loginGradeId = loginGradeId;
        this.gradeName = gradeName;
    }

    public void setLoginGradeId(int loginGradeId) {
        this.loginGradeId = loginGradeId;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public int getLoginGradeId() {
        return loginGradeId;
    }

    public String getGradeName() {
        return gradeName;
    }

    @Override
    public String toString() {
        return "GradeEntity{" +
                "loginGradeId=" + loginGradeId +
                ", gradeName='" + gradeName + '\'' +
                '}';
    }
}
