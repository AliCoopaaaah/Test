package com.example.test;

import android.os.Parcel;
import android.os.Parcelable;

public class NasaObject implements Parcelable {

    private String hdURL;
    private String name;
    private String date;

    //constructor
    public NasaObject(String date, String name, String hdURL) {
        this.date = date;
        this.name = name;
        this.hdURL = hdURL;
    }

    // implementing parcelable interface
    protected NasaObject(Parcel in) {
        hdURL = in.readString();
        date = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hdURL);
        dest.writeString(date);
        dest.writeString(name);
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
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
}