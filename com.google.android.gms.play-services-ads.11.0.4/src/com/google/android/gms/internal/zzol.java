package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class zzol implements zzrd {
   // $FF: synthetic field
   private zzai zzIj;
   // $FF: synthetic field
   private zzog zzIk;

   zzol(zzog var1, zzai var2) {
      this.zzIk = var1;
      this.zzIj = var2;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      JSONObject var3 = new JSONObject();

      try {
         Iterator var4 = var2.keySet().iterator();

         while(var4.hasNext()) {
            String var5 = (String)var4.next();
            var3.put(var5, var2.get(var5));
         }

         var3.put("id", zzog.zzb(this.zzIk));
         this.zzIj.zzb("sendMessageToNativeJs", var3);
      } catch (JSONException var6) {
         zzafr.zzb("Unable to dispatch sendMessageToNativeJs event", var6);
      }
   }
}
