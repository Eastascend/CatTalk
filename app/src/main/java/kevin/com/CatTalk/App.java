package kevin.com.CatTalk;

/**
 * Created by Kevin Wong on 2017/4/30 0030.
 */

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import io.rong.imkit.RongIM;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);


    }


    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


}