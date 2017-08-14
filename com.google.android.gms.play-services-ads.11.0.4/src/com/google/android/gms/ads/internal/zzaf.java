package com.google.android.gms.ads.internal;

import android.text.TextUtils;
import com.google.android.gms.internal.zzafr;
import com.google.android.gms.internal.zzajs;
import org.json.JSONObject;

final class zzaf implements zzajs {
   // $FF: synthetic field
   private zzae zztG;

   zzaf(zzae var1) {
      this.zztG = var1;
      super();
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      com.google.android.gms.ads.internal.js.zzai var3 = (com.google.android.gms.ads.internal.js.zzai)var1;
      zzaf var2 = this;
      var3.zza("/appSettingsFetched", this.zztG.zztB);

      try {
         JSONObject var4 = new JSONObject();
         if (!TextUtils.isEmpty(var2.zztG.zztC)) {
            var4.put("app_id", var2.zztG.zztC);
         } else if (!TextUtils.isEmpty(var2.zztG.zztD)) {
            var4.put("ad_unit_id", var2.zztG.zztD);
         }

         var4.put("is_init", var2.zztG.zztE);
         var4.put("pn", var2.zztG.zztF.getPackageName());
         var3.zza("AFMA_fetchAppSettings", var4);
      } catch (Exception var5) {
         var3.zzb("/appSettingsFetched", this.zztG.zztB);
         zzafr.zzb("Error requesting application settings", var5);
      }
   }
}
