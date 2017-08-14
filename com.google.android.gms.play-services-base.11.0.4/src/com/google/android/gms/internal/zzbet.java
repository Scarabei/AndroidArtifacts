package com.google.android.gms.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;

final class zzbet implements Runnable {
   // $FF: synthetic field
   private Result zzaFh;
   // $FF: synthetic field
   private zzbes zzaFi;

   zzbet(zzbes var1, Result var2) {
      this.zzaFi = var1;
      this.zzaFh = var2;
      super();
   }

   @WorkerThread
   public final void run() {
      boolean var6 = false;

      GoogleApiClient var9;
      label66: {
         try {
            var6 = true;
            zzbbe.zzaBV.set(true);
            PendingResult var1 = zzbes.zzc(this.zzaFi).onSuccess(this.zzaFh);
            zzbes.zzd(this.zzaFi).sendMessage(zzbes.zzd(this.zzaFi).obtainMessage(0, var1));
            var6 = false;
            break label66;
         } catch (RuntimeException var7) {
            zzbes.zzd(this.zzaFi).sendMessage(zzbes.zzd(this.zzaFi).obtainMessage(1, var7));
            var6 = false;
         } finally {
            if (var6) {
               zzbbe.zzaBV.set(false);
               zzbes.zza(this.zzaFi, this.zzaFh);
               GoogleApiClient var3;
               if ((var3 = (GoogleApiClient)zzbes.zze(this.zzaFi).get()) != null) {
                  var3.zzb(this.zzaFi);
               }

            }
         }

         zzbbe.zzaBV.set(false);
         zzbes.zza(this.zzaFi, this.zzaFh);
         if ((var9 = (GoogleApiClient)zzbes.zze(this.zzaFi).get()) != null) {
            var9.zzb(this.zzaFi);
         }

         return;
      }

      zzbbe.zzaBV.set(false);
      zzbes.zza(this.zzaFi, this.zzaFh);
      if ((var9 = (GoogleApiClient)zzbes.zze(this.zzaFi).get()) != null) {
         var9.zzb(this.zzaFi);
      }

   }
}
