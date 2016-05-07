package com.jhetox.missileguidedforandroid.bombs;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Jheto Xekri on 5/05/16.
 */
public class MissileP implements Parcelable {

    static {
        Log.e(MissileP.class.getSimpleName(), "Init Static");
    }

    private String title;

    public MissileP(){
        title = "!!! Boom !!!";
        Log.e(MissileP.class.getSimpleName(), "Init Constructor 1");
    }

    protected MissileP(Parcel in) {
        title = in.readString();
        Log.e(MissileP.class.getSimpleName(), "Init Constructor 2");
    }

    public String getTitle() {
        return title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Log.e(MissileP.class.getSimpleName(), "Init Write To Parcel");
        dest.writeString(title);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<MissileP> CREATOR = new Parcelable.Creator<MissileP>() {
        @Override
        public MissileP createFromParcel(Parcel in) {
            Log.e(MissileP.class.getSimpleName(), "Init Create From Parcel");
            return new MissileP(in);
        }
        @Override
        public MissileP[] newArray(int size) {
            Log.e(MissileP.class.getSimpleName(), "Init New Array");
            return new MissileP[size];
        }
    };

    @Override
    protected void finalize() throws Throwable {
        Log.e(MissileP.class.getSimpleName(), "Init finalize");
        super.finalize();
    }

    @Override
    public String toString() {
        Log.e(MissileP.class.getSimpleName(), "Init toString");
        return MissileP.class.getSimpleName();
    }
}
