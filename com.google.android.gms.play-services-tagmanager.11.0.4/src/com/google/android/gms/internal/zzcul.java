package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

final class zzcul implements ce, Runnable {
   // $FF: synthetic field
   private zzcuf zzbHS;

   private zzcul(zzcuf var1) {
      this.zzbHS = var1;
      super();
   }

   @WorkerThread
   public final void run() {
      zzbo.zzae(zzcuf.zza(this.zzbHS) == 2);
      if (!zzcvs.zzCw().zzfG(zzcuf.zzd(this.zzbHS))) {
         String var1 = zzcuf.zzd(this.zzbHS);
         zzcvk.v((new StringBuilder(24 + String.valueOf(var1).length())).append("Refreshing container ").append(var1).append("...").toString());
         ArrayList var2;
         (var2 = new ArrayList()).add(Integer.valueOf(0));
         zzcuf.zzh(this.zzbHS).zza(zzcuf.zzd(this.zzbHS), zzcuf.zze(this.zzbHS), zzcuf.zzf(this.zzbHS), var2, this, zzcuf.zzg(this.zzbHS));
      }
   }

   public final void zza(cm var1) {
      if (var1.getStatus() == Status.zzaBm) {
         String var2 = zzcuf.zzd(this.zzbHS);
         zzcvk.v((new StringBuilder(47 + String.valueOf(var2).length())).append("Refreshed container ").append(var2).append(". Reinitializing runtime...").toString());
         zzcuf.zzc(this.zzbHS).execute(new zzcum(this.zzbHS, var1));
      } else {
         zzcuf.zza(this.zzbHS, zzcuf.zzg(this.zzbHS).zzAT());
      }
   }

   // $FF: synthetic method
   zzcul(zzcuf var1, zzcug var2) {
      this(var1);
   }
}
