package com.google.android.gms.internal;

import org.json.JSONObject;

final class zzrg implements Runnable {
   // $FF: synthetic field
   private JSONObject zzJr;
   // $FF: synthetic field
   private zzrf zzJs;

   zzrg(zzrf var1, JSONObject var2) {
      this.zzJs = var1;
      this.zzJr = var2;
      super();
   }

   public final void run() {
      this.zzJs.zzJp.zzb("fetchHttpRequestCompleted", this.zzJr);
      zzafr.zzaC("Dispatched http response.");
   }
}
