package com.google.android.gms.common.stats;

import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.util.zzk;
import java.util.List;

public final class zze {
   private static zze zzaJA = new zze();
   private static Boolean zzaJB;

   public static zze zzrX() {
      return zzaJA;
   }

   public static void zza(Context var0, String var1, int var2, String var3, String var4, String var5, int var6, List var7) {
      zza(var0, var1, 8, var3, var4, var5, var6, var7, 0L);
   }

   public static void zza(Context var0, String var1, int var2, String var3, String var4, String var5, int var6, List var7, long var8) {
      if (zzaJB == null) {
         zzaJB = false;
      }

      if (zzaJB.booleanValue()) {
         if (TextUtils.isEmpty(var1)) {
            String var10002 = String.valueOf(var1);
            String var10001;
            if (var10002.length() != 0) {
               var10001 = "missing wakeLock key. ".concat(var10002);
            } else {
               String var10003 = new String;
               var10001 = var10003;
               var10003.<init>("missing wakeLock key. ");
            }

            Log.e("WakeLockTracker", var10001);
         } else {
            long var10 = System.currentTimeMillis();
            if (7 == var2 || 8 == var2 || 10 == var2 || 11 == var2) {
               WakeLockEvent var10000 = new WakeLockEvent;
               List var15 = var7;
               if (var7 != null && var7.size() == 1) {
                  var15 = "com.google.android.gms".equals(var7.get(0)) ? null : var7;
               }

               long var10008 = SystemClock.elapsedRealtime();
               int var10009 = zzk.zzaK(var0);
               String var14 = var0.getPackageName();
               var10000.<init>(var10, var2, var3, var6, var15, var1, var10008, var10009, var4, "com.google.android.gms".equals(var14) ? null : var14, zzk.zzaL(var0), var8, var5);
               WakeLockEvent var12 = var10000;

               try {
                  var0.startService((new Intent()).setComponent(zzb.zzaJf).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", var12));
                  return;
               } catch (Exception var16) {
                  Log.wtf("WakeLockTracker", var16);
               }
            }

         }
      }
   }
}
