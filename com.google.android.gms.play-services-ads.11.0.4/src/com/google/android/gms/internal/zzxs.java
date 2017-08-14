package com.google.android.gms.internal;

final class zzxs implements Runnable {
   // $FF: synthetic field
   private zzxr zzQV;

   zzxs(zzxr var1) {
      this.zzQV = var1;
      super();
   }

   public final void run() {
      if (zzxr.zza(this.zzQV).get()) {
         zzafr.e("Timed out waiting for WebView to finish loading.");
         this.zzQV.cancel();
      }
   }
}
