package com.gdin.analyse.entity;


import android.os.Parcel;
import android.os.Parcelable;

public class ClassScoreEntity implements Parcelable{
    private String maxString;
    private String maxValue;
    private String avgString;
    private String avgValue;

    @Override
    public String toString() {
        return "ClassScoreEntity{" +
                "maxString='" + maxString + '\'' +
                ", maxValue='" + maxValue + '\'' +
                ", avgString='" + avgString + '\'' +
                ", avgValue='" + avgValue + '\'' +
                '}';
    }

    public ClassScoreEntity(String maxString, String maxValue, String avgString, String avgValue) {
        this.maxString = maxString;
        this.maxValue = maxValue;
        this.avgString = avgString;
        this.avgValue = avgValue;
    }

    protected ClassScoreEntity(Parcel in) {
        maxString = in.readString();
        maxValue = in.readString();
        avgString = in.readString();
        avgValue = in.readString();
    }

    public static final Creator<ClassScoreEntity> CREATOR = new Creator<ClassScoreEntity>() {
        @Override
        public ClassScoreEntity createFromParcel(Parcel in) {
            return new ClassScoreEntity(in);
        }

        @Override
        public ClassScoreEntity[] newArray(int size) {
            return new ClassScoreEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(maxString);
        dest.writeString(maxValue);
        dest.writeString(avgString);
        dest.writeString(avgValue);
    }

    public String getMaxString() {
        return maxString;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public String getAvgString() {
        return avgString;
    }

    public String getAvgValue() {
        return avgValue;
    }

    public void setMaxString(String maxString) {
        this.maxString = maxString;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public void setAvgString(String avgString) {
        this.avgString = avgString;
    }

    public void setAvgValue(String avgValue) {
        this.avgValue = avgValue;
    }
}
