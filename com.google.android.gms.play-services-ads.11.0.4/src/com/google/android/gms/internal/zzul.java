package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzul implements Callable {
   // $FF: synthetic field
   private zzue zzMT;
   // $FF: synthetic field
   private zzuk zzMU;

   zzul(zzuk var1, zzue var2) {
      this.zzMU = var1;
      this.zzMT = var2;
      super();
   }

   private final zzuh zzfp() throws Exception {
      synchronized(zzuk.zza(this.zzMU)) {
         if (zzuk.zzb(this.zzMU)) {
            return null;
         }
      }

      return this.zzMT.zza(zzuk.zzc(this.zzMU), zzuk.zzd(this.zzMU));
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzfp();
   }
}
