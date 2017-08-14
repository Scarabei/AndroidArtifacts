package com.google.android.gms.tasks;

final class zzh implements Runnable {
   // $FF: synthetic field
   private Task zzbLT;
   // $FF: synthetic field
   private zzg zzbLZ;

   zzh(zzg var1, Task var2) {
      this.zzbLZ = var1;
      this.zzbLT = var2;
      super();
   }

   public final void run() {
      synchronized(zzg.zza(this.zzbLZ)) {
         if (zzg.zzb(this.zzbLZ) != null) {
            zzg.zzb(this.zzbLZ).onFailure(this.zzbLT.getException());
         }

      }
   }
}
