package com.google.android.gms.internal;

import android.util.Log;

public final class zzcuq implements zzcvl {
   private boolean zzbHX = true;
   private int zzagX = 5;

   public final void e(String var1) {
      if (this.zzz(6)) {
         Log.e("GoogleTagManager", var1);
      }

   }

   public final void zzb(String var1, Throwable var2) {
      if (this.zzz(6)) {
         Log.e("GoogleTagManager", var1, var2);
      }

   }

   public final void zzaT(String var1) {
      if (this.zzz(5)) {
         Log.w("GoogleTagManager", var1);
      }

   }

   public final void zzc(String var1, Throwable var2) {
      if (this.zzz(5)) {
         Log.w("GoogleTagManager", var1, var2);
      }

   }

   public final void zzaS(String var1) {
      if (this.zzz(4)) {
         Log.i("GoogleTagManager", var1);
      }

   }

   public final void v(String var1) {
      if (this.zzz(2)) {
         Log.v("GoogleTagManager", var1);
      }

   }

   private final boolean zzz(int var1) {
      return this.zzbHX && Log.isLoggable("GoogleTagManager", var1) || !this.zzbHX && this.zzagX <= var1;
   }
}
