package android.support.v4.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;

public final class NavUtils {
   private static final String TAG = "NavUtils";
   public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";

   public static boolean shouldUpRecreateTask(Activity sourceActivity, Intent targetIntent) {
      if (VERSION.SDK_INT >= 16) {
         return sourceActivity.shouldUpRecreateTask(targetIntent);
      } else {
         String action = sourceActivity.getIntent().getAction();
         return action != null && !action.equals("android.intent.action.MAIN");
      }
   }

   public static void navigateUpFromSameTask(Activity sourceActivity) {
      Intent upIntent = getParentActivityIntent(sourceActivity);
      if (upIntent == null) {
         throw new IllegalArgumentException("Activity " + sourceActivity.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)");
      } else {
         navigateUpTo(sourceActivity, upIntent);
      }
   }

   public static void navigateUpTo(Activity sourceActivity, Intent upIntent) {
      if (VERSION.SDK_INT >= 16) {
         sourceActivity.navigateUpTo(upIntent);
      } else {
         upIntent.addFlags(67108864);
         sourceActivity.startActivity(upIntent);
         sourceActivity.finish();
      }

   }

   public static Intent getParentActivityIntent(Activity sourceActivity) {
      if (VERSION.SDK_INT >= 16) {
         Intent result = sourceActivity.getParentActivityIntent();
         if (result != null) {
            return result;
         }
      }

      String parentName = getParentActivityName(sourceActivity);
      if (parentName == null) {
         return null;
      } else {
         ComponentName target = new ComponentName(sourceActivity, parentName);

         try {
            String grandparent = getParentActivityName(sourceActivity, target);
            return grandparent == null ? Intent.makeMainActivity(target) : (new Intent()).setComponent(target);
         } catch (NameNotFoundException var4) {
            Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + parentName + "' in manifest");
            return null;
         }
      }
   }

   public static Intent getParentActivityIntent(Context context, Class sourceActivityClass) throws NameNotFoundException {
      String parentActivity = getParentActivityName(context, new ComponentName(context, sourceActivityClass));
      if (parentActivity == null) {
         return null;
      } else {
         ComponentName target = new ComponentName(context, parentActivity);
         String grandparent = getParentActivityName(context, target);
         Intent parentIntent = grandparent == null ? Intent.makeMainActivity(target) : (new Intent()).setComponent(target);
         return parentIntent;
      }
   }

   public static Intent getParentActivityIntent(Context context, ComponentName componentName) throws NameNotFoundException {
      String parentActivity = getParentActivityName(context, componentName);
      if (parentActivity == null) {
         return null;
      } else {
         ComponentName target = new ComponentName(componentName.getPackageName(), parentActivity);
         String grandparent = getParentActivityName(context, target);
         Intent parentIntent = grandparent == null ? Intent.makeMainActivity(target) : (new Intent()).setComponent(target);
         return parentIntent;
      }
   }

   @Nullable
   public static String getParentActivityName(Activity sourceActivity) {
      try {
         return getParentActivityName(sourceActivity, sourceActivity.getComponentName());
      } catch (NameNotFoundException var2) {
         throw new IllegalArgumentException(var2);
      }
   }

   @Nullable
   public static String getParentActivityName(Context context, ComponentName componentName) throws NameNotFoundException {
      PackageManager pm = context.getPackageManager();
      ActivityInfo info = pm.getActivityInfo(componentName, 128);
      String parentActivity;
      if (VERSION.SDK_INT >= 16) {
         parentActivity = info.parentActivityName;
         if (parentActivity != null) {
            return parentActivity;
         }
      }

      if (info.metaData == null) {
         return null;
      } else {
         parentActivity = info.metaData.getString("android.support.PARENT_ACTIVITY");
         if (parentActivity == null) {
            return null;
         } else {
            if (parentActivity.charAt(0) == '.') {
               parentActivity = context.getPackageName() + parentActivity;
            }

            return parentActivity;
         }
      }
   }
}
