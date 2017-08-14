package com.google.android.gms.internal;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.os.StrictMode.ThreadPolicy.Builder;
import java.util.concurrent.Callable;

@zzzn
public final class zzait {
   public static Object zzb(Callable var0) {
      ThreadPolicy var1 = StrictMode.getThreadPolicy();

      try {
         StrictMode.setThreadPolicy((new Builder(var1)).permitDiskReads().permitDiskWrites().build());
         Object var3 = var0.call();
         return var3;
      } catch (Throwable var7) {
         zzafr.zzb("Unexpected exception.", var7);
         com.google.android.gms.ads.internal.zzbs.zzbD().zza(var7, "StrictModeUtil.runWithLaxStrictMode");
      } finally {
         StrictMode.setThreadPolicy(var1);
      }

      return null;
   }

   public static Object zzc(Callable var0) throws Exception {
      ThreadPolicy var1 = StrictMode.getThreadPolicy();

      Object var2;
      try {
         StrictMode.setThreadPolicy((new Builder(var1)).permitDiskReads().permitDiskWrites().build());
         var2 = var0.call();
      } finally {
         StrictMode.setThreadPolicy(var1);
      }

      return var2;
   }
}
