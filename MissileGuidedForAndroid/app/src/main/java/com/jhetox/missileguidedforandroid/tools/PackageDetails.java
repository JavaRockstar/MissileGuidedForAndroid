package com.jhetox.missileguidedforandroid.tools;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.jhetox.missileguidedforandroid.R;

/**
 * Created by Jheto Xekri on 7/05/16.
 */
public class PackageDetails {

    private PackageDetails(){}

    public static String getLabel(Context context, String packageName) {
        String label = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            label = (applicationInfo != null) ? packageManager.getApplicationLabel(applicationInfo).toString() : "Unknown";
        } catch (Exception e) {}
        label = (label == null || label.isEmpty())? "Unknown":label;
        return label;
    }

    public static Drawable getIcon(Context context, String packageName){
        Drawable icon = null;
        try {
            PackageManager packageManager = context.getPackageManager();
            icon = packageManager.getApplicationIcon(packageName);
        } catch (Exception e) {}

        if(icon == null){
            try {
                PackageManager packageManager = context.getPackageManager();
                icon = packageManager.getApplicationIcon(context.getPackageName());
            } catch (Exception e) {}
        }

        return icon;
    }

}
