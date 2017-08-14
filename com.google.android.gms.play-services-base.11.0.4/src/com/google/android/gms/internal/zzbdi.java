package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzal;
import java.util.Collections;

final class zzbdi implements Runnable {
   // $FF: synthetic field
   private ConnectionResult zzaEw;
   // $FF: synthetic field
   private zzbdh zzaEy;

   zzbdi(zzbdh var1, ConnectionResult var2) {
      this.zzaEy = var1;
      this.zzaEw = var2;
      super();
   }

   public final void run() {
      if (this.zzaEw.isSuccess()) {
         zzbdh.zza(this.zzaEy, true);
         if (zzbdh.zza(this.zzaEy).zzmv()) {
            zzbdh.zzb(this.zzaEy);
         } else {
            zzbdh.zza(this.zzaEy).zza((zzal)null, Collections.emptySet());
         }
      } else {
         ((zzbdd)zzbdb.zzj(this.zzaEy.zzaEm).get(zzbdh.zzc(this.zzaEy))).onConnectionFailed(this.zzaEw);
      }
   }
}
