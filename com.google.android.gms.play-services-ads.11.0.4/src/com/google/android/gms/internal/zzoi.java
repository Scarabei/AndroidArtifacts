package com.google.android.gms.internal;

import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

final class zzoi implements zzakf {
   // $FF: synthetic field
   private Map zzIl;
   // $FF: synthetic field
   private zzoh zzIm;

   zzoi(zzoh var1, Map var2) {
      this.zzIm = var1;
      this.zzIl = var2;
      super();
   }

   public final void zza(zzaka var1, boolean var2) {
      zzog.zza(this.zzIm.zzIk, (String)this.zzIl.get("id"));
      JSONObject var3 = new JSONObject();

      try {
         var3.put("messageType", "htmlLoaded");
         var3.put("id", zzog.zzb(this.zzIm.zzIk));
         this.zzIm.zzIj.zzb("sendMessageToNativeJs", var3);
      } catch (JSONException var5) {
         zzafr.zzb("Unable to dispatch sendMessageToNativeJs event", var5);
      }
   }
}
