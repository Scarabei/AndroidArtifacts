package com.google.android.gms.internal;

final class zze implements Runnable {
   // $FF: synthetic field
   private zzp zzl;
   // $FF: synthetic field
   private zzd zzm;

   zze(zzd var1, zzp var2) {
      this.zzm = var1;
      this.zzl = var2;
      super();
   }

   public final void run() {
      try {
         zzd.zza(this.zzm).put(this.zzl);
      } catch (InterruptedException var1) {
         ;
      }
   }
}
