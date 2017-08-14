package com.google.android.gms.internal;

import android.util.Log;
import com.google.android.gms.fitness.result.DataReadResult;

final class zzbyj extends zzbvw {
   private final zzbaz zzaIz;
   private int zzaVQ;
   private DataReadResult zzaVR;

   private zzbyj(zzbaz var1) {
      this.zzaVQ = 0;
      this.zzaVR = null;
      this.zzaIz = var1;
   }

   public final void zza(DataReadResult var1) {
      synchronized(this) {
         if (Log.isLoggable("Fitness", 2)) {
            int var3 = this.zzaVQ;
            Log.v("Fitness", (new StringBuilder(33)).append("Received batch result ").append(var3).toString());
         }

         if (this.zzaVR == null) {
            this.zzaVR = var1;
         } else {
            this.zzaVR.zzb(var1);
         }

         ++this.zzaVQ;
         if (this.zzaVQ == this.zzaVR.zztX()) {
            this.zzaIz.setResult(this.zzaVR);
         }

      }
   }

   // $FF: synthetic method
   zzbyj(zzbaz var1, zzbyb var2) {
      this(var1);
   }
}
