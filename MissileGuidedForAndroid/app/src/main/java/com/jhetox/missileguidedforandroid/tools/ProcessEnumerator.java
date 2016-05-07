package com.jhetox.missileguidedforandroid.tools;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jheto Xekri on 5/05/16.
 */
public final class ProcessEnumerator {

    private ProcessEnumerator(){}

    private static String getPS(){
        try{
            java.lang.Process p = Runtime.getRuntime().exec("ps");
            InputStream is = p.getInputStream();
            byte[] buffer = new byte[1024];
            int c;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while((c = is.read(buffer))!=-1){
                baos.write(buffer, 0, c);
            }
            is.close();
            String s = new String(baos.toByteArray());
            return s.substring(s.indexOf("\n")+1);
        }catch (Exception e){}
        return null;
    }

    private static List<Process> getProcess(){
        List<Process> processes = new ArrayList<>();
        try{
            String ps = getPS();
            for(String line:ps.split("\n")){
                String packageName = line.substring(line.lastIndexOf(" ")+1);
                line = line.replace("  ", " ").replace("  ", " ").replace("  ", " ");
                line = line.substring(line.indexOf(" ") + 1);
                line = line.substring(0, line.indexOf(" "));
                if(packageName.contains("."))
                    processes.add(new Process(packageName, Integer.parseInt(line)));
            }
        }catch(Exception e){}
        return processes;
    }

    public static List<Process> enumerate(Context context){
        List<Process> processes = null;
        try{
            ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                processes = getProcess();
            }
            else{
                processes = new ArrayList<>();
                for(ActivityManager.RunningAppProcessInfo process : am.getRunningAppProcesses()) {
                    if(process.processName.contains("."))
                        processes.add(new Process(process.processName, process.pid));
                }
            }
        }catch(Exception e){
            Log.e(ProcessEnumerator.class.getSimpleName(), e.toString());
        }
        return processes;
    }

    public static class Process {

        private String packageName;
        private int pid;

        public Process(String packageName, int pid){
            this.packageName = packageName;
            this.pid = pid;
        }

        public String getPackageName() {
            return packageName;
        }

        public int getPid() {
            return pid;
        }

        @Override
        public String toString() {
            return packageName + ":" + pid;
        }
    }

}
