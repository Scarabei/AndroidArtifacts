package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.ConditionVariable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.zzo;

@zzzn
public final class zzmm {
   private final Object mLock = new Object();
   private final ConditionVariable zzBS = new ConditionVariable();
   private volatile boolean zzuH = false;
   @Nullable
   private SharedPreferences zzBT = null;

   public final void initialize(Context var1) {
      if (!this.zzuH) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (!this.zzuH) {
               try {
                  Context var3;
                  if ((var3 = zzo.getRemoteContext(var1)) == null && var1 != null) {
                     Context var4;
                     var3 = (var4 = var1.getApplicationContext()) == null ? var1 : var4;
                  }

                  if (var3 != null) {
                     com.google.android.gms.ads.internal.zzbs.zzbJ();
                     this.zzBT = var3.getSharedPreferences("google_ads_flags", 0);
                     this.zzuH = true;
                     return;
                  }
               } finally {
                  this.zzBS.open();
               }

            }
         }
      }
   }

   public final Object zzd(zzme var1) {
      if (!this.zzBS.block(5000L)) {
         throw new IllegalStateException("Flags.initialize() was not called!");
      } else {
         if (!this.zzuH || this.zzBT == null) {
            Object var2 = this.mLock;
            synchronized(this.mLock) {
               if (!this.zzuH || this.zzBT == null) {
                  return var1.zzdI();
               }
            }
         }

         return zzait.zzb(new zzmn(this, var1));
      }
   }

   // $FF: synthetic method
   static SharedPreferences zza(zzmm var0) {
      return var0.zzBT;
   }
}
