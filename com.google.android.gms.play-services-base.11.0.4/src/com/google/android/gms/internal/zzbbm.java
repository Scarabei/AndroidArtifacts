package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;

final class zzbbm implements zzbdq {
   // $FF: synthetic field
   private zzbbk zzaCx;

   private zzbbm(zzbbk var1) {
      this.zzaCx = var1;
      super();
   }

   public final void zzm(@Nullable Bundle var1) {
      zzbbk.zza(this.zzaCx).lock();

      try {
         zzbbk.zza(this.zzaCx, var1);
         zzbbk.zza(this.zzaCx, ConnectionResult.zzazX);
         zzbbk.zzb(this.zzaCx);
      } finally {
         zzbbk.zza(this.zzaCx).unlock();
      }

   }

   public final void zzc(@NonNull ConnectionResult var1) {
      zzbbk.zza(this.zzaCx).lock();

      try {
         zzbbk.zza(this.zzaCx, var1);
         zzbbk.zzb(this.zzaCx);
      } finally {
         zzbbk.zza(this.zzaCx).unlock();
      }

   }

   public final void zze(int var1, boolean var2) {
      zzbbk.zza(this.zzaCx).lock();

      try {
         if (zzbbk.zzc(this.zzaCx) || zzbbk.zzd(this.zzaCx) == null || !zzbbk.zzd(this.zzaCx).isSuccess()) {
            zzbbk.zza(this.zzaCx, false);
            zzbbk.zza(this.zzaCx, var1, var2);
            return;
         }

         zzbbk.zza(this.zzaCx, true);
         zzbbk.zze(this.zzaCx).onConnectionSuspended(var1);
      } finally {
         zzbbk.zza(this.zzaCx).unlock();
      }

   }

   // $FF: synthetic method
   zzbbm(zzbbk var1, zzbbl var2) {
      this(var1);
   }
}
