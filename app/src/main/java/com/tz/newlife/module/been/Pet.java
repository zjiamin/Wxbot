package com.tz.newlife.module.been;

import android.os.Parcel;
import android.os.Parcelable;


public class Pet implements Parcelable {
    private String type;
    private int age;

    protected Pet(Parcel in) {
        type = in.readString();
        age = in.readInt();
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(age);
    }
}
