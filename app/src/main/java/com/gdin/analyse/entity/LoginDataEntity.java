package com.gdin.analyse.entity;

import android.os.Parcel;
import android.os.Parcelable;


public class LoginDataEntity implements Parcelable{

    private int loginSchoolId;
    private int loginGradeId;
    private int loginClassId;
    private String loginType;
    private String loginUser;
    private String loginPwd;

    @Override
    public String toString() {
        return "LoginDataEntity{" +
                "loginSchoolId=" + loginSchoolId +
                ", loginGradeId=" + loginGradeId +
                ", loginClassId=" + loginClassId +
                ", loginType='" + loginType + '\'' +
                ", loginUser='" + loginUser + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                '}';
    }

    public LoginDataEntity(int loginSchoolId, int loginGradeId, int loginClassId, String loginType, String loginUser, String loginPwd) {
        this.loginSchoolId = loginSchoolId;
        this.loginGradeId = loginGradeId;
        this.loginClassId = loginClassId;
        this.loginType = loginType;
        this.loginUser = loginUser;
        this.loginPwd = loginPwd;
    }

    protected LoginDataEntity(Parcel in) {
        loginSchoolId = in.readInt();
        loginGradeId = in.readInt();
        loginClassId = in.readInt();
        loginType = in.readString();
        loginUser = in.readString();
        loginPwd = in.readString();
    }

    public static final Creator<LoginDataEntity> CREATOR = new Creator<LoginDataEntity>() {
        @Override
        public LoginDataEntity createFromParcel(Parcel in) {
            return new LoginDataEntity(in);
        }

        @Override
        public LoginDataEntity[] newArray(int size) {
            return new LoginDataEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(loginSchoolId);
        dest.writeInt(loginGradeId);
        dest.writeInt(loginClassId);
        dest.writeString(loginType);
        dest.writeString(loginUser);
        dest.writeString(loginPwd);
    }

    public int getLoginClassId() {
        return loginClassId;
    }

    public int getLoginGradeId() {
        return loginGradeId;
    }

    public int getLoginSchoolId() {
        return loginSchoolId;
    }

    public String getLoginType() {
        return loginType;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginClassId(int loginClassId) {
        this.loginClassId = loginClassId;
    }

    public void setLoginGradeId(int loginGradeId) {
        this.loginGradeId = loginGradeId;
    }

    public void setLoginSchoolId(int loginSchoolId) {
        this.loginSchoolId = loginSchoolId;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }


}
