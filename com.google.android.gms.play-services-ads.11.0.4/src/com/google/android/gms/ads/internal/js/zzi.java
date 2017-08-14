package com.google.android.gms.ads.internal.js;

final class zzi implements Runnable {
   // $FF: synthetic field
   private String zzKY;
   // $FF: synthetic field
   private zze zzKW;

   zzi(zze var1, String var2) {
      this.zzKW = var1;
      this.zzKY = var2;
      super();
   }

   public final void run() {
      zze.zza(this.zzKW).loadData(this.zzKY, "text/html", "UTF-8");
   }
}
