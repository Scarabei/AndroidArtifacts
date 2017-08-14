package com.google.android.gms.internal;

import android.content.Context;

@zzzn
public final class zzaev implements zzgi {
   private final Context mContext;
   private final String zztV;
   private final Object mLock;
   private boolean zzXy;

   public zzaev(Context var1, String var2) {
      this.mContext = var1;
      this.zztV = var2;
      this.zzXy = false;
      this.mLock = new Object();
   }

   public final void zzu(boolean var1) {
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(this.mContext)) {
         Object var2 = this.mLock;
         synchronized(this.mLock) {
            if (this.zzXy != var1) {
               this.zzXy = var1;
               if (this.zzXy) {
                  com.google.android.gms.ads.internal.zzbs.zzbY().zzc(this.mContext, this.zztV);
               } else {
                  com.google.android.gms.ads.internal.zzbs.zzbY().zzd(this.mContext, this.zztV);
               }

            }
         }
      }
   }

   public final void zza(zzgh var1) {
      this.zzu(var1.zzxS);
   }
}
