package com.google.android.gms.internal;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class zzab {
   private static String TAG = "Volley";
   public static boolean DEBUG = Log.isLoggable("Volley", 2);

   public static void zza(String var0, Object... var1) {
      if (DEBUG) {
         Log.v(TAG, zzd(var0, var1));
      }

   }

   public static void zzb(String var0, Object... var1) {
      Log.d(TAG, zzd(var0, var1));
   }

   public static void zzc(String var0, Object... var1) {
      Log.e(TAG, zzd(var0, var1));
   }

   public static void zza(Throwable var0, String var1, Object... var2) {
      Log.e(TAG, zzd(var1, var2), var0);
   }

   private static String zzd(String var0, Object... var1) {
      String var2 = var1 == null ? var0 : String.format(Locale.US, var0, var1);
      StackTraceElement[] var3 = (new Throwable()).fillInStackTrace().getStackTrace();
      String var4 = "<unknown>";

      for(int var5 = 2; var5 < var3.length; ++var5) {
         if (!var3[var5].getClass().equals(zzab.class)) {
            String var10000 = var3[var5].getClassName();
            var10000 = var10000.substring(var10000.lastIndexOf(46) + 1);
            String var7 = var10000.substring(var10000.lastIndexOf(36) + 1);
            String var8 = String.valueOf(var3[var5].getMethodName());
            var4 = (new StringBuilder(1 + String.valueOf(var7).length() + String.valueOf(var8).length())).append(var7).append(".").append(var8).toString();
            break;
         }
      }

      return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), var4, var2);
   }

   static class zza {
      public static final boolean zzai;
      private final List zzaj = new ArrayList();
      private boolean zzak = false;

      public final synchronized void zza(String var1, long var2) {
         if (this.zzak) {
            throw new IllegalStateException("Marker added to finished log");
         } else {
            this.zzaj.add(new zzac(var1, var2, SystemClock.elapsedRealtime()));
         }
      }

      public final synchronized void zzc(String var1) {
         this.zzak = true;
         long var10000;
         if (this.zzaj.size() == 0) {
            var10000 = 0L;
         } else {
            long var11 = ((zzac)this.zzaj.get(0)).time;
            var10000 = ((zzac)this.zzaj.get(this.zzaj.size() - 1)).time - var11;
         }

         long var2 = var10000;
         if (var10000 > 0L) {
            long var4 = ((zzac)this.zzaj.get(0)).time;
            zzab.zzb("(%-4d ms) %s", var2, var1);

            long var8;
            for(Iterator var6 = this.zzaj.iterator(); var6.hasNext(); var4 = var8) {
               zzac var7;
               var8 = (var7 = (zzac)var6.next()).time;
               zzab.zzb("(+%-4d) [%2d] %s", var8 - var4, var7.zzal, var7.name);
            }

         }
      }

      protected final void finalize() throws Throwable {
         if (!this.zzak) {
            this.zzc("Request on the loose");
            zzab.zzc("Marker log finalized without finish() - uncaught exit point for request");
         }

      }

      static {
         zzai = zzab.DEBUG;
      }
   }
}
