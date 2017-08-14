package com.google.android.gms.ads.internal.js;

import org.json.JSONObject;

final class zzf implements Runnable {
   // $FF: synthetic field
   private String zzKU;
   // $FF: synthetic field
   private JSONObject zzKV;
   // $FF: synthetic field
   private zze zzKW;

   zzf(zze var1, String var2, JSONObject var3) {
      this.zzKW = var1;
      this.zzKU = var2;
      this.zzKV = var3;
      super();
   }

   public final void run() {
      zze.zza(this.zzKW).zza(this.zzKU, this.zzKV);
   }
}
