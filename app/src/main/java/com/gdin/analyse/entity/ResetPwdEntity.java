package com.gdin.analyse.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ResetPwdEntity implements Parcelable{

    private int tokenId;
    private String newPwd;
    private String oldPwd;

    public int getTokenId() {
        return tokenId;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setTokenId(int tokenId) {
        this.tokenId = tokenId;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    @Override
    public String toString() {
        return "ResetPwdEntity{" +
                "tokenId=" + tokenId +
                ", newPwd='" + newPwd + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                '}';
    }

    public ResetPwdEntity(int tokenId, String newPwd, String oldPwd) {
        this.tokenId = tokenId;
        this.newPwd = newPwd;
        this.oldPwd = oldPwd;
    }

    protected ResetPwdEntity(Parcel in) {
        tokenId = in.readInt();
        newPwd = in.readString();
        oldPwd = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(tokenId);
        dest.writeString(newPwd);
        dest.writeString(oldPwd);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ResetPwdEntity> CREATOR = new Creator<ResetPwdEntity>() {
        @Override
        public ResetPwdEntity createFromParcel(Parcel in) {
            return new ResetPwdEntity(in);
        }

        @Override
        public ResetPwdEntity[] newArray(int size) {
            return new ResetPwdEntity[size];
        }
    };
}
