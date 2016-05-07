package com.jhetox.missileguidedforandroid.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jheto Xekri on 5/05/16.
 */
public final class ServiceEnumerator {

    private ServiceEnumerator(){}

    public static List<Service> enumerate(Context context){
        List<Service> list = new ArrayList<>();
        try{
            ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            for(ActivityManager.RunningServiceInfo service : am.getRunningServices(Integer.MAX_VALUE)){
                String packageName = service.service.getPackageName();
                String className = service.service.getClassName();
                int uid = service.uid;
                list.add(new Service(packageName, className, uid));
            }
        }catch(Exception e){
            Log.e(ServiceEnumerator.class.getSimpleName(), e.toString());
        }
        return list;
    }

    public static class Service {

        private String packageName;
        private String className;
        private int uid;

        public Service(String packageName, String className, int uid){
            this.packageName = packageName;
            this.className = className;
            this.uid = uid;
        }

        public String getPackageName() {
            return packageName;
        }

        public String getClassName() {
            return className;
        }

        public int getUid() {
            return uid;
        }

        @Override
        public String toString() {
            return packageName + "/" + className + ":" + uid;
        }
    }
}
