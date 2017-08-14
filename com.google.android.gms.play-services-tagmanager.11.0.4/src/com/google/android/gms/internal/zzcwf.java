package com.google.android.gms.internal;

final class zzcwf implements Runnable {
   // $FF: synthetic field
   private zzcwd zzbJa;

   zzcwf(zzcwd var1) {
      this.zzbJa = var1;
      super();
   }

   public final void run() {
      zzcwd.zza(this.zzbJa, false);
      zzcwd.zze(this.zzbJa).dispatch();
   }
}
