package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zzns;
import com.google.android.gms.internal.zzos;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzat implements zzakf {
   // $FF: synthetic field
   private zzns zzuB;
   // $FF: synthetic field
   private String zzuz;
   // $FF: synthetic field
   private zzaka zzuA;

   zzat(zzns var1, String var2, zzaka var3) {
      this.zzuB = var1;
      this.zzuz = var2;
      this.zzuA = var3;
      super();
   }

   public final void zza(zzaka var1, boolean var2) {
      try {
         JSONObject var3;
         (var3 = new JSONObject()).put("headline", this.zzuB.getHeadline());
         var3.put("body", this.zzuB.getBody());
         var3.put("call_to_action", this.zzuB.getCallToAction());
         var3.put("advertiser", this.zzuB.getAdvertiser());
         var3.put("logo", zzar.zza(this.zzuB.zzem()));
         JSONArray var4 = new JSONArray();
         List var5;
         if ((var5 = this.zzuB.getImages()) != null) {
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
               zzos var7 = zzar.zze(var6.next());
               var4.put(zzar.zza(var7));
            }
         }

         var3.put("images", var4);
         var3.put("extras", zzar.zzb(this.zzuB.getExtras(), this.zzuz));
         JSONObject var9;
         (var9 = new JSONObject()).put("assets", var3);
         var9.put("template_id", "1");
         this.zzuA.zza("google.afma.nativeExpressAds.loadAssets", var9);
      } catch (JSONException var8) {
         zzafr.zzc("Exception occurred when loading assets", var8);
      }
   }
}
