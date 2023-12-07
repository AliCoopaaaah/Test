package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

public class NasaObject implements Parcelable {

    private String hdURL;
    private String date;

    //constructor
    public NasaObject(String date, String hdURL) {
        this.hdURL = hdURL;
        this.date = date;
    }

    // implementing parcelable interface
    protected NasaObject(Parcel in) {
        hdURL = in.readString();
        date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hdURL);
        dest.writeString(date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NasaObject> CREATOR = new Creator<NasaObject>() {
        @Override
        public NasaObject createFromParcel(Parcel in) {
            return new NasaObject(in);
        }

        @Override
        public NasaObject[] newArray(int size) {
            return new NasaObject[size];
        }
    };

    // Getter and Setter methods
    public String getHdURL() {
        return hdURL;
    }

    public void setHdURL(String hdURL) {
        this.hdURL = hdURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}