package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.File;

public final class zzu {
   @TargetApi(21)
   public static File getNoBackupFilesDir(Context var0) {
      if (zzq.zzse()) {
         return var0.getNoBackupFilesDir();
      } else {
         ApplicationInfo var1 = var0.getApplicationInfo();
         return zzd(new File(var1.dataDir, "no_backup"));
      }
   }

   private static synchronized File zzd(File var0) {
      if (!var0.exists() && !var0.mkdirs()) {
         if (var0.exists()) {
            return var0;
         } else {
            String var10002 = String.valueOf(var0.getPath());
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "Unable to create no-backup dir ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("Unable to create no-backup dir ");
            }

            Log.w("SupportV4Utils", var10001);
            return null;
         }
      } else {
         return var0;
      }
   }
}
