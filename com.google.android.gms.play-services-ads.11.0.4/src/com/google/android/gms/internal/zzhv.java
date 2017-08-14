package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzf;

final class zzhv implements zzf {
   // $FF: synthetic field
   private zzhs zzzs;

   zzhv(zzhs var1) {
      this.zzzs = var1;
      super();
   }

   public final void onConnected(@Nullable Bundle var1) {
      synchronized(zzhs.zzc(this.zzzs)) {
         try {
            if (zzhs.zzd(this.zzzs) != null) {
               zzhs.zza(this.zzzs, zzhs.zzd(this.zzzs).zzdd());
            }
         } catch (DeadObjectException var5) {
            zzafr.zzb("Unable to obtain a cache service instance.", var5);
            zzhs.zza(this.zzzs);
         }

         zzhs.zzc(this.zzzs).notifyAll();
      }
   }

   public final void onConnectionSuspended(int var1) {
      synchronized(zzhs.zzc(this.zzzs)) {
         zzhs.zza(this.zzzs, (zzid)null);
         zzhs.zzc(this.zzzs).notifyAll();
      }
   }
}
