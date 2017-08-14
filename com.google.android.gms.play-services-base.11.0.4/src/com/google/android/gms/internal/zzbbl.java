package com.google.android.gms.internal;

final class zzbbl implements Runnable {
   // $FF: synthetic field
   private zzbbk zzaCx;

   zzbbl(zzbbk var1) {
      this.zzaCx = var1;
      super();
   }

   public final void run() {
      zzbbk.zza(this.zzaCx).lock();

      try {
         zzbbk.zzb(this.zzaCx);
      } finally {
         zzbbk.zza(this.zzaCx).unlock();
      }

   }
}
