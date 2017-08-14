package com.google.android.gms.common.util;

import android.content.Context;
import android.os.DropBoxManager;
import android.util.Log;
import com.google.android.gms.common.internal.zzbo;

public final class zzg {
   private static final String[] zzaJD = new String[]{"android.", "com.android.", "dalvik.", "java.", "javax."};
   private static DropBoxManager zzaJE = null;
   private static boolean zzaJF = false;
   private static int zzaJG = -1;
   private static int zzaJH = 0;

   public static boolean zza(Context var0, Throwable var1) {
      return zza(var0, var1, 0);
   }

   private static boolean zza(Context var0, Throwable var1, int var2) {
      try {
         zzbo.zzu(var0);
         zzbo.zzu(var1);
         return false;
      } catch (Exception var4) {
         Log.e("CrashUtils", "Error adding exception to DropBox!", var4);
         return false;
      }
   }
}
