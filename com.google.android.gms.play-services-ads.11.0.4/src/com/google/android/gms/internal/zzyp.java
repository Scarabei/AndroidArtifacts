package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.js.zzai;
import com.google.android.gms.common.internal.zzbo;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzyp implements zzrd {
   // $FF: synthetic field
   private zzai zzIj;
   // $FF: synthetic field
   private zzyo zzRG;

   zzyp(zzyo var1, zzai var2) {
      this.zzRG = var1;
      this.zzIj = var2;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      try {
         String var3 = (String)var2.get("success");
         String var4;
         JSONObject var5;
         boolean var6;
         if (!TextUtils.isEmpty(var4 = (String)var2.get("failure"))) {
            var5 = new JSONObject(var4);
            var6 = false;
         } else {
            var5 = new JSONObject(var3);
            var6 = true;
         }

         if (!this.zzRG.zzRC.equals(var5.optString("ads_id", ""))) {
            return;
         }

         this.zzIj.zzb("/nativeAdPreProcess", this.zzRG.zzRD.zzRW);
         if (var6) {
            JSONArray var7;
            if ((var7 = var5.optJSONArray("ads")) != null && var7.length() > 0) {
               this.zzRG.zzRE.zzg(var7.getJSONObject(0));
               return;
            }

            this.zzRG.zzRF.zzt(3);
            this.zzRG.zzRE.zzg((Object)null);
            return;
         }
      } catch (JSONException var8) {
         zzafr.zzb("Malformed native JSON response.", var8);
      }

      this.zzRG.zzRF.zzt(0);
      zzbo.zza(this.zzRG.zzRF.zzgx(), "Unable to set the ad state error!");
      this.zzRG.zzRE.zzg((Object)null);
   }
}
