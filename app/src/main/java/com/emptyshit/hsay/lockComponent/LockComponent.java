package com.emptyshit.hsay.lockComponent;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import com.emptyshit.hsay.frontsGames.WindowEnterWordGame;

import java.util.Collections;
import java.util.List;

/**
 * Created by tungu on 09/04/2017.
 */

public class LockComponent extends Application implements LockComponentInterface {

    private List apps;
    private Context mContext;
    private String whatsapp;
    private Intent lockIntent;
    private boolean active;

    // public void getApplications(){
    public void lockApp() {
        PackageManager packageManager = getPackageManager();           // Class for retrieving various kinds of information related to the application packages that are currently installed on the device. You can find this class through getPackageManager().
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);        // getting all installed apllications
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        List<PackageInfo> packs = packageManager.getInstalledPackages(0);                       // Return a List of all packages that are installed on the device. 0 = int flag
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo a = p.applicationInfo;
            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }
            if (p.equals("com.whatsapp")) {                                    // checken ob whatsapp vorhanden ist
                whatsapp = p.packageName;
            }
            apps.add(p.packageName);                                         // List of applications
        }
        getRunningApps();
    }


    // To find the current foreground application:
/*
        public void getRunningApps() {
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE);   // Class Kontetxt Use with getSystemService(Class) to retrieve a AccountManager for receiving intents at a time of your choosing.
        List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
        ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
        String activityOnTop = ar.topActivity.getClassName();                  //The activity component at the top of the history stack of the task.
        if (RunningTask.contains(this.whatsapp)) {
            lockAppIntent();
        }
    }
*/
    public boolean getRunningApps() {
        ActivityManager mActivityManager = (ActivityManager) mContext.getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningProcesses = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
            if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                for (String activeProcess : processInfo.pkgList) {
                    if (activeProcess.startsWith("com.whatsapp")) {
                        lockAppIntent();
                        return true;
                    }
                }
            }
        }


        return false;
    }


    public void lockAppIntent() {
        lockIntent = new Intent(mContext, WindowEnterWordGame.class);         // An intent is an abstract description of an operation to be performed.
        lockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);                 // If set, this activity will become the start of a new task on this history stack.
        mContext.startActivity(lockIntent);                                 // launch Activity
        //while(this.active == true){
        //    getRunningApps();
        //}
    }

    public void releaseLock(){
        mContext.stopService(lockIntent);
        //this.active = false;
    }

/*
    public void lockWhatsApp(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");
        startActivity(sendIntent);

        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.whatsapp");
        startActivity(launchIntent);
    }
*/
}
