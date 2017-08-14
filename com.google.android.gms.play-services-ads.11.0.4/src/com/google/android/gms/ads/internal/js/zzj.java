package com.google.android.gms.ads.internal.js;

final class zzj implements Runnable {
   // $FF: synthetic field
   private String zzKS;
   // $FF: synthetic field
   private zze zzKW;

   zzj(zze var1, String var2) {
      this.zzKW = var1;
      this.zzKS = var2;
      super();
   }

   public final void run() {
      zze.zza(this.zzKW).loadUrl(this.zzKS);
   }
}
