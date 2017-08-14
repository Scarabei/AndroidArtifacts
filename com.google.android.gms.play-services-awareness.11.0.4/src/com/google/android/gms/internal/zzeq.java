package com.google.android.gms.internal;

import android.util.Log;

public final class zzeq {
   private static boolean zzdd = false;

   public static String zzd(String var0, String var1) {
      if (zzd(6)) {
         String var2 = zza(var0, var1);
         Log.e("ctxmgr", var2);
         return var2;
      } else {
         return "";
      }
   }

   public static String zza(String var0, String var1, Object var2) {
      if (zzd(6)) {
         String var3 = zza(var0, var1, var2);
         Log.e("ctxmgr", var3);
         return var3;
      } else {
         return "";
      }
   }

   public static String zza(String var0, String var1, Throwable var2) {
      if (zzd(6)) {
         String var3 = zza(var0, var1);
         Log.e("ctxmgr", var3, var2);
         return var3;
      } else {
         return "";
      }
   }

   private static String zza(String var0, String var1, Object... var2) {
      if (var2.length == 0) {
         return String.format("[%s]%s", var0, var1);
      } else {
         String var10000 = String.valueOf(String.format("[%s]", var0));
         String var10001 = String.valueOf(String.format(var1, var2));
         return var10001.length() != 0 ? var10000.concat(var10001) : new String(var10000);
      }
   }

   private static boolean zzd(int var0) {
      return Log.isLoggable("ctxmgr", 6);
   }
}
