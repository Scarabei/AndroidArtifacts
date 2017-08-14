package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzg;

final class zzhw implements zzg {
   // $FF: synthetic field
   private zzhs zzzs;

   zzhw(zzhs var1) {
      this.zzzs = var1;
      super();
   }

   public final void onConnectionFailed(@NonNull ConnectionResult var1) {
      synchronized(zzhs.zzc(this.zzzs)) {
         zzhs.zza(this.zzzs, (zzid)null);
         if (zzhs.zzd(this.zzzs) != null) {
            zzhs.zza(this.zzzs, (zzhz)null);
         }

         zzhs.zzc(this.zzzs).notifyAll();
      }
   }
}
