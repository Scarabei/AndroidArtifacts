package com.google.android.gms.internal;

import org.json.JSONException;
import org.json.JSONObject;

@zzzn
public final class zzwq {
   private final boolean zzNR;
   private final boolean zzNS;
   private final boolean zzNT;
   private final boolean zzNU;
   private final boolean zzNV;

   private zzwq(zzws var1) {
      this.zzNR = zzws.zza(var1);
      this.zzNS = zzws.zzb(var1);
      this.zzNT = zzws.zzc(var1);
      this.zzNU = zzws.zzd(var1);
      this.zzNV = zzws.zze(var1);
   }

   public final JSONObject toJson() {
      try {
         return (new JSONObject()).put("sms", this.zzNR).put("tel", this.zzNS).put("calendar", this.zzNT).put("storePicture", this.zzNU).put("inlineVideo", this.zzNV);
      } catch (JSONException var2) {
         zzafr.zzb("Error occured while obtaining the MRAID capabilities.", var2);
         return null;
      }
   }

   // $FF: synthetic method
   zzwq(zzws var1, zzwr var2) {
      this(var1);
   }
}
