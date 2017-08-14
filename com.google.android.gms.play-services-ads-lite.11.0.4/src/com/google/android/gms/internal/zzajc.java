package com.google.android.gms.internal;

import android.util.Log;

@zzzn
public class zzajc {
   public static void zzaC(String var0) {
      if (zzz(3)) {
         Log.d("Ads", var0);
      }

   }

   public static void zza(String var0, Throwable var1) {
      if (zzz(3)) {
         Log.d("Ads", var0, var1);
      }

   }

   public static void e(String var0) {
      if (zzz(6)) {
         Log.e("Ads", var0);
      }

   }

   public static void zzb(String var0, Throwable var1) {
      if (zzz(6)) {
         Log.e("Ads", var0, var1);
      }

   }

   public static void zzaS(String var0) {
      if (zzz(4)) {
         Log.i("Ads", var0);
      }

   }

   public static void zzaT(String var0) {
      if (zzz(5)) {
         Log.w("Ads", var0);
      }

   }

   public static void zzc(String var0, Throwable var1) {
      if (zzz(5)) {
         Log.w("Ads", var0, var1);
      }

   }

   public static boolean zzz(int var0) {
      return var0 >= 5 || Log.isLoggable("Ads", var0);
   }
}
