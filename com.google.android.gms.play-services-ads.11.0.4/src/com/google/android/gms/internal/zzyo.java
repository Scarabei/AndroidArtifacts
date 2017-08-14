package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import org.json.JSONException;
import org.json.JSONObject;

final class zzyo extends zzym {
   // $FF: synthetic field
   final String zzRC;
   // $FF: synthetic field
   final zzyw zzRD;
   // $FF: synthetic field
   final zzajg zzRE;
   // $FF: synthetic field
   final zzyn zzRF;

   zzyo(zzyn var1, String var2, zzyw var3, zzajg var4) {
      this.zzRF = var1;
      this.zzRC = var2;
      this.zzRD = var3;
      this.zzRE = var4;
   }

   public final void zzd(zzai var1) {
      zzyp var2 = new zzyp(this, var1);
      this.zzRD.zzRW = var2;
      var1.zza("/nativeAdPreProcess", (zzrd)var2);

      try {
         JSONObject var3;
         (var3 = new JSONObject(zzyn.zza(this.zzRF).zzXY.body)).put("ads_id", this.zzRC);
         var1.zza("google.afma.nativeAds.preProcessJsonGmsg", var3);
      } catch (JSONException var4) {
         zzafr.zzc("Exception occurred while invoking javascript", var4);
         this.zzRE.zzg((Object)null);
      }
   }

   public final void zzgv() {
      this.zzRE.zzg((Object)null);
   }
}
