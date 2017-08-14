package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import java.util.Collections;
import java.util.List;

public final class zza {
   private static final Object zzaHL = new Object();
   private static volatile zza zzaJa;
   private final List zzaJb;
   private final List zzaJc;
   private final List zzaJd;
   private final List zzaJe;

   public static zza zzrU() {
      if (zzaJa == null) {
         Object var0 = zzaHL;
         synchronized(zzaHL) {
            if (zzaJa == null) {
               zzaJa = new zza();
            }
         }
      }

      return zzaJa;
   }

   private zza() {
      this.zzaJb = Collections.EMPTY_LIST;
      this.zzaJc = Collections.EMPTY_LIST;
      this.zzaJd = Collections.EMPTY_LIST;
      this.zzaJe = Collections.EMPTY_LIST;
   }

   @SuppressLint({"UntrackedBindService"})
   public static boolean zza(Context var0, String var1, Intent var2, ServiceConnection var3, int var4) {
      ComponentName var7;
      if ((var7 = var2.getComponent()) == null ? false : com.google.android.gms.common.util.zzd.zzC(var0, var7.getPackageName())) {
         Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
         return false;
      } else {
         return var0.bindService(var2, var3, var4);
      }
   }

   public final boolean zza(Context var1, Intent var2, ServiceConnection var3, int var4) {
      return zza(var1, var1.getClass().getName(), var2, var3, var4);
   }
}
