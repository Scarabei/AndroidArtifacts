package com.google.android.gms.common.api;

final class zzb implements PendingResult.zza {
   // $FF: synthetic field
   private Batch zzaAG;

   zzb(Batch var1) {
      this.zzaAG = var1;
      super();
   }

   public final void zzo(Status var1) {
      synchronized(Batch.zza(this.zzaAG)) {
         if (!this.zzaAG.isCanceled()) {
            if (var1.isCanceled()) {
               Batch.zza(this.zzaAG, true);
            } else if (!var1.isSuccess()) {
               Batch.zzb(this.zzaAG, true);
            }

            Batch.zzb(this.zzaAG);
            if (Batch.zzc(this.zzaAG) == 0) {
               if (Batch.zzd(this.zzaAG)) {
                  Batch.zze(this.zzaAG);
               } else {
                  Status var3;
                  if (Batch.zzf(this.zzaAG)) {
                     var3 = new Status(13);
                  } else {
                     var3 = Status.zzaBm;
                  }

                  this.zzaAG.setResult(new BatchResult(var3, Batch.zzg(this.zzaAG)));
               }
            }

         }
      }
   }
}
