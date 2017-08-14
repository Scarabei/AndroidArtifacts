package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat.EditorCompat;

public class AppLaunchChecker {
   private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";
   private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";

   public static boolean hasStartedFromLauncher(Context context) {
      return context.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
   }

   public static void onActivityCreate(Activity activity) {
      SharedPreferences sp = activity.getSharedPreferences("android.support.AppLaunchChecker", 0);
      if (!sp.getBoolean("startedFromLauncher", false)) {
         Intent launchIntent = activity.getIntent();
         if (launchIntent != null) {
            if ("android.intent.action.MAIN".equals(launchIntent.getAction()) && (launchIntent.hasCategory("android.intent.category.LAUNCHER") || launchIntent.hasCategory("android.intent.category.LEANBACK_LAUNCHER"))) {
               EditorCompat.getInstance().apply(sp.edit().putBoolean("startedFromLauncher", true));
            }

         }
      }
   }
}
