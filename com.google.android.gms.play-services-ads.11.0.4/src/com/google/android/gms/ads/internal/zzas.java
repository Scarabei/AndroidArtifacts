package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzaka;
import com.google.android.gms.internal.zzakf;
import com.google.android.gms.internal.zznq;
import com.google.android.gms.internal.zzos;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzas implements zzakf {
   // $FF: synthetic field
   private zznq zzuy;
   // $FF: synthetic field
   private String zzuz;
   // $FF: synthetic field
   private zzaka zzuA;

   zzas(zznq var1, String var2, zzaka var3) {
      this.zzuy = var1;
      this.zzuz = var2;
      this.zzuA = var3;
      super();
   }

   public final void zza(zzaka var1, boolean var2) {
      try {
         JSONObject var3;
         (var3 = new JSONObject()).put("headline", this.zzuy.getHeadline());
         var3.put("body", this.zzuy.getBody());
         var3.put("call_to_action", this.zzuy.getCallToAction());
         var3.put("price", this.zzuy.getPrice());
         var3.put("star_rating", String.valueOf(this.zzuy.getStarRating()));
         var3.put("store", this.zzuy.getStore());
         var3.put("icon", zzar.zza(this.zzuy.zzeh()));
         JSONArray var4 = new JSONArray();
         List var5;
         if ((var5 = this.zzuy.getImages()) != null) {
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
               zzos var7 = zzar.zze(var6.next());
               var4.put(zzar.zza(var7));
            }
         }

         var3.put("images", var4);
         var3.put("extras", zzar.zzb(this.zzuy.getExtras(), this.zzuz));
         JSONObject var9;
         (var9 = new JSONObject()).put("assets", var3);
         var9.put("template_id", "2");
         this.zzuA.zza("google.afma.nativeExpressAds.loadAssets", var9);
      } catch (JSONException var8) {
         zzafr.zzc("Exception occurred when loading assets", var8);
      }
   }
}
