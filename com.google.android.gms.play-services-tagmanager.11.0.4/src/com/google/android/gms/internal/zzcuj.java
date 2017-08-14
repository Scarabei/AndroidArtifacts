package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbo;
import java.util.ArrayList;

final class zzcuj implements ce, Runnable {
   // $FF: synthetic field
   private zzcuf zzbHS;

   private zzcuj(zzcuf var1) {
      this.zzbHS = var1;
      super();
   }

   @WorkerThread
   public final void run() {
      zzbo.zzae(zzcuf.zza(this.zzbHS) == 1);
      ArrayList var1 = new ArrayList();
      zzcuf.zza(this.zzbHS, false);
      if (zzcvs.zzCw().zzfG(zzcuf.zzd(this.zzbHS))) {
         var1.add(Integer.valueOf(0));
      } else {
         zzcuf.zza(this.zzbHS, zzcuf.zzg(this.zzbHS).zzCj());
         if (!zzcuf.zzl(this.zzbHS)) {
            var1.add(Integer.valueOf(0));
            var1.add(Integer.valueOf(1));
         } else {
            var1.add(Integer.valueOf(1));
            var1.add(Integer.valueOf(0));
         }

         var1.add(Integer.valueOf(2));
      }

      zzcuf.zzh(this.zzbHS).zza(zzcuf.zzd(this.zzbHS), zzcuf.zze(this.zzbHS), zzcuf.zzf(this.zzbHS), var1, this, zzcuf.zzg(this.zzbHS));
   }

   public final void zza(cm var1) {
      if (var1.getStatus() == Status.zzaBm) {
         zzcuf.zzc(this.zzbHS).execute(new zzcum(this.zzbHS, var1));
      } else {
         zzcuf.zzc(this.zzbHS).execute(new zzcui(this.zzbHS, (zzcug)null));
      }
   }

   // $FF: synthetic method
   zzcuj(zzcuf var1, zzcug var2) {
      this(var1);
   }
}
