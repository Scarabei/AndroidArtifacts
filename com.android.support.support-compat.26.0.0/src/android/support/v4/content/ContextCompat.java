package android.support.v4.content;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.os.Build.VERSION;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import java.io.File;

public class ContextCompat {
   private static final String TAG = "ContextCompat";
   private static final Object sLock = new Object();
   private static TypedValue sTempValue;

   public static boolean startActivities(Context context, Intent[] intents) {
      return startActivities(context, intents, (Bundle)null);
   }

   public static boolean startActivities(Context context, Intent[] intents, Bundle options) {
      if (VERSION.SDK_INT >= 16) {
         context.startActivities(intents, options);
      } else {
         context.startActivities(intents);
      }

      return true;
   }

   public static void startActivity(Context context, Intent intent, @Nullable Bundle options) {
      if (VERSION.SDK_INT >= 16) {
         context.startActivity(intent, options);
      } else {
         context.startActivity(intent);
      }

   }

   public static File getDataDir(Context context) {
      if (VERSION.SDK_INT >= 24) {
         return context.getDataDir();
      } else {
         String dataDir = context.getApplicationInfo().dataDir;
         return dataDir != null ? new File(dataDir) : null;
      }
   }

   public static File[] getObbDirs(Context context) {
      return VERSION.SDK_INT >= 19 ? context.getObbDirs() : new File[]{context.getObbDir()};
   }

   public static File[] getExternalFilesDirs(Context context, String type) {
      return VERSION.SDK_INT >= 19 ? context.getExternalFilesDirs(type) : new File[]{context.getExternalFilesDir(type)};
   }

   public static File[] getExternalCacheDirs(Context context) {
      return VERSION.SDK_INT >= 19 ? context.getExternalCacheDirs() : new File[]{context.getExternalCacheDir()};
   }

   private static File buildPath(File base, String... segments) {
      File cur = base;
      String[] var3 = segments;
      int var4 = segments.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         String segment = var3[var5];
         if (cur == null) {
            cur = new File(segment);
         } else if (segment != null) {
            cur = new File(cur, segment);
         }
      }

      return cur;
   }

   public static final Drawable getDrawable(Context context, @DrawableRes int id) {
      if (VERSION.SDK_INT >= 21) {
         return context.getDrawable(id);
      } else if (VERSION.SDK_INT >= 16) {
         return context.getResources().getDrawable(id);
      } else {
         Object var3 = sLock;
         int resolvedId;
         synchronized(sLock) {
            if (sTempValue == null) {
               sTempValue = new TypedValue();
            }

            context.getResources().getValue(id, sTempValue, true);
            resolvedId = sTempValue.resourceId;
         }

         return context.getResources().getDrawable(resolvedId);
      }
   }

   public static final ColorStateList getColorStateList(Context context, @ColorRes int id) {
      return VERSION.SDK_INT >= 23 ? context.getColorStateList(id) : context.getResources().getColorStateList(id);
   }

   @ColorInt
   public static final int getColor(Context context, @ColorRes int id) {
      return VERSION.SDK_INT >= 23 ? context.getColor(id) : context.getResources().getColor(id);
   }

   public static int checkSelfPermission(@NonNull Context context, @NonNull String permission) {
      if (permission == null) {
         throw new IllegalArgumentException("permission is null");
      } else {
         return context.checkPermission(permission, Process.myPid(), Process.myUid());
      }
   }

   public static final File getNoBackupFilesDir(Context context) {
      if (VERSION.SDK_INT >= 21) {
         return context.getNoBackupFilesDir();
      } else {
         ApplicationInfo appInfo = context.getApplicationInfo();
         return createFilesDir(new File(appInfo.dataDir, "no_backup"));
      }
   }

   public static File getCodeCacheDir(Context context) {
      if (VERSION.SDK_INT >= 21) {
         return context.getCodeCacheDir();
      } else {
         ApplicationInfo appInfo = context.getApplicationInfo();
         return createFilesDir(new File(appInfo.dataDir, "code_cache"));
      }
   }

   private static synchronized File createFilesDir(File file) {
      if (!file.exists() && !file.mkdirs()) {
         if (file.exists()) {
            return file;
         } else {
            Log.w("ContextCompat", "Unable to create files subdir " + file.getPath());
            return null;
         }
      } else {
         return file;
      }
   }

   public static Context createDeviceProtectedStorageContext(Context context) {
      return VERSION.SDK_INT >= 24 ? context.createDeviceProtectedStorageContext() : null;
   }

   public static boolean isDeviceProtectedStorage(Context context) {
      return VERSION.SDK_INT >= 24 ? context.isDeviceProtectedStorage() : false;
   }

   public static void startForegroundService(Context context, Intent intent) {
      if (VERSION.SDK_INT >= 26) {
         context.startForegroundService(intent);
      } else {
         context.startService(intent);
      }

   }
}
