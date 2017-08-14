package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import java.util.Set;

public final class zzm implements zzj {
   // $FF: synthetic field
   private zzd zzaHe;

   public zzm(zzd var1) {
      this.zzaHe = var1;
      super();
   }

   public final void zzf(@NonNull ConnectionResult var1) {
      if (var1.isSuccess()) {
         this.zzaHe.zza((zzal)null, (Set)this.zzaHe.zzrh());
      } else {
         if (zzd.zzg(this.zzaHe) != null) {
            zzd.zzg(this.zzaHe).onConnectionFailed(var1);
         }

      }
   }
}
