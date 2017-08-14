package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

/** @deprecated */
@Deprecated
public final class zzaob {
   private static volatile Logger zzaim = new zzanl();

   public static void zzf(String var0, Object var1) {
      zzaoc var2;
      if ((var2 = zzaoc.zzlM()) != null) {
         var2.zze(var0, var1);
      } else if (zzz(3)) {
         String var10000;
         if (var1 != null) {
            String var4 = String.valueOf(var1);
            var10000 = (new StringBuilder(1 + String.valueOf(var0).length() + String.valueOf(var4).length())).append(var0).append(":").append(var4).toString();
         } else {
            var10000 = var0;
         }

         String var3 = var10000;
         Log.e((String)zzans.zzahg.get(), var3);
      }

      Logger var5 = zzaim;
      if (zzaim != null) {
         var5.error(var0);
      }

   }

   public static void v(String var0) {
      zzaoc var1;
      if ((var1 = zzaoc.zzlM()) != null) {
         var1.zzbo(var0);
      } else if (zzz(0)) {
         Log.v((String)zzans.zzahg.get(), var0);
      }

      Logger var2 = zzaim;
      if (zzaim != null) {
         var2.verbose(var0);
      }

   }

   public static void zzaT(String var0) {
      zzaoc var1;
      if ((var1 = zzaoc.zzlM()) != null) {
         var1.zzbr(var0);
      } else if (zzz(2)) {
         Log.w((String)zzans.zzahg.get(), var0);
      }

      Logger var2 = zzaim;
      if (zzaim != null) {
         var2.warn(var0);
      }

   }

   private static boolean zzz(int var0) {
      if (zzaim != null) {
         return zzaim.getLogLevel() <= var0;
      } else {
         return false;
      }
   }

   public static void setLogger(Logger var0) {
      zzaim = var0;
   }

   public static Logger getLogger() {
      return zzaim;
   }
}
