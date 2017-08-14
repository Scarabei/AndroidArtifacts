package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public final class zztt {
   private final Object mLock = new Object();
   private zztu zzLD;

   public final zztu zzb(Context var1, zzaje var2) {
      Object var3 = this.mLock;
      synchronized(this.mLock) {
         if (this.zzLD == null) {
            Context var6;
            Context var10003 = (var6 = var1.getApplicationContext()) == null ? var1 : var6;
            zzme var5 = zzmo.zzBX;
            this.zzLD = new zztu(var10003, var2, (String)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var5));
         }

         return this.zzLD;
      }
   }
}
