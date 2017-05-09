package com.gdin.analyse.model.teacher;

import android.os.Parcel;
import android.os.Parcelable;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class ClassResultScoreEntity implements Parcelable, MultiItemEntity {

    public final static int TITLE = 0;
    public final static int TWO_TV = 1;
    public final static int FOUR_TV = 2;

    private int type;
    private String title;
    private int max;
    private int avg;


    public ClassResultScoreEntity(int type, String title) {
        this.type = type;
        this.title = title;
    }

    public ClassResultScoreEntity(int type, int max, int avg) {
        this.type = type;
        this.max = max;
        this.avg = avg;
    }

    protected ClassResultScoreEntity(Parcel in) {
        type = in.readInt();
        title = in.readString();
        max = in.readInt();
        avg = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(type);
        dest.writeString(title);
        dest.writeInt(max);
        dest.writeInt(avg);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ClassResultScoreEntity> CREATOR = new Creator<ClassResultScoreEntity>() {
        @Override
        public ClassResultScoreEntity createFromParcel(Parcel in) {
            return new ClassResultScoreEntity(in);
        }

        @Override
        public ClassResultScoreEntity[] newArray(int size) {
            return new ClassResultScoreEntity[size];
        }
    };

    @Override
    public int getItemType() {
        return type;
    }
}
