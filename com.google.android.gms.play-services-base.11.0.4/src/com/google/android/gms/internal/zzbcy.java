package com.google.android.gms.internal;

abstract class zzbcy {
   private final zzbcw zzaDZ;

   protected zzbcy(zzbcw var1) {
      this.zzaDZ = var1;
   }

   public final void zzc(zzbcx var1) {
      zzbcx.zza(var1).lock();

      try {
         if (zzbcx.zzb(var1) == this.zzaDZ) {
            this.zzpV();
            return;
         }
      } finally {
         zzbcx.zza(var1).unlock();
      }

   }

   protected abstract void zzpV();
}
