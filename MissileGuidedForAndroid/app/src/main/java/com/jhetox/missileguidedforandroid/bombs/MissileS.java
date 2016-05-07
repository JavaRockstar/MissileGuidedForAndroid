package com.jhetox.missileguidedforandroid.bombs;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Jheto Xekri on 5/05/16.
 */
public final class MissileS implements Serializable {

    static {
        Log.e(MissileS.class.getSimpleName(), "Init Static");
    }

    private String title;

    public MissileS(){
        title = "!!! Boom !!!";
        Log.e(MissileS.class.getSimpleName(), "Init Constructor 1");
    }

    public MissileS(String title){
        this.title = title;
        Log.e(MissileS.class.getSimpleName(), "Init Constructor 2");
    }

    public String getTitle() {
        return title;
    }

    @Override
    protected void finalize() throws Throwable {
        Log.e(MissileS.class.getSimpleName(), "Init finalize");
        super.finalize();
    }

    @Override
    public String toString() {
        Log.e(MissileS.class.getSimpleName(), "Init toString");
        return MissileS.class.getSimpleName();
    }
}
