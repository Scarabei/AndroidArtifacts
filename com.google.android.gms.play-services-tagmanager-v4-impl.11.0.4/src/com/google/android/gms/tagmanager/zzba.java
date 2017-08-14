package com.google.android.gms.tagmanager;

import android.util.Log;

public final class zzba implements zzdk {
   private int zzagX = 5;

   public final void e(String var1) {
      if (this.zzagX <= 6) {
         Log.e("GoogleTagManager", var1);
      }

   }

   public final void zzb(String var1, Throwable var2) {
      if (this.zzagX <= 6) {
         Log.e("GoogleTagManager", var1, var2);
      }

   }

   public final void zzaT(String var1) {
      if (this.zzagX <= 5) {
         Log.w("GoogleTagManager", var1);
      }

   }

   public final void zzc(String var1, Throwable var2) {
      if (this.zzagX <= 5) {
         Log.w("GoogleTagManager", var1, var2);
      }

   }

   public final void zzaS(String var1) {
      if (this.zzagX <= 4) {
         Log.i("GoogleTagManager", var1);
      }

   }

   public final void zzaC(String var1) {
      if (this.zzagX <= 3) {
         Log.d("GoogleTagManager", var1);
      }

   }

   public final void v(String var1) {
      if (this.zzagX <= 2) {
         Log.v("GoogleTagManager", var1);
      }

   }

   public final void setLogLevel(int var1) {
      this.zzagX = var1;
   }
}
