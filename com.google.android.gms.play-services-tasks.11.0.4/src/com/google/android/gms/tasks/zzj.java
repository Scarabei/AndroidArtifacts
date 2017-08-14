package com.google.android.gms.tasks;

final class zzj implements Runnable {
   // $FF: synthetic field
   private Task zzbLT;
   // $FF: synthetic field
   private zzi zzbMb;

   zzj(zzi var1, Task var2) {
      this.zzbMb = var1;
      this.zzbLT = var2;
      super();
   }

   public final void run() {
      synchronized(zzi.zza(this.zzbMb)) {
         if (zzi.zzb(this.zzbMb) != null) {
            zzi.zzb(this.zzbMb).onSuccess(this.zzbLT.getResult());
         }

      }
   }
}
