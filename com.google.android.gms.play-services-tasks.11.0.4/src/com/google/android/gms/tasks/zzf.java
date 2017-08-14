package com.google.android.gms.tasks;

final class zzf implements Runnable {
   // $FF: synthetic field
   private Task zzbLT;
   // $FF: synthetic field
   private zze zzbLX;

   zzf(zze var1, Task var2) {
      this.zzbLX = var1;
      this.zzbLT = var2;
      super();
   }

   public final void run() {
      synchronized(zze.zza(this.zzbLX)) {
         if (zze.zzb(this.zzbLX) != null) {
            zze.zzb(this.zzbLX).onComplete(this.zzbLT);
         }

      }
   }
}
