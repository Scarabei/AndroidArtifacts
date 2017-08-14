package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONObject;

final class zzrf implements Runnable {
   // $FF: synthetic field
   private Map zzIl;
   // $FF: synthetic field
   final zzaka zzJp;
   // $FF: synthetic field
   private zzre zzJq;

   zzrf(zzre var1, Map var2, zzaka var3) {
      this.zzJq = var1;
      this.zzIl = var2;
      this.zzJp = var3;
      super();
   }

   public final void run() {
      zzafr.zzaC("Received Http request.");
      String var1 = (String)this.zzIl.get("http_request");
      JSONObject var2;
      if ((var2 = this.zzJq.zzR(var1)) == null) {
         zzafr.e("Response should not be null.");
      } else {
         zzagz.zzZr.post(new zzrg(this, var2));
      }
   }
}
