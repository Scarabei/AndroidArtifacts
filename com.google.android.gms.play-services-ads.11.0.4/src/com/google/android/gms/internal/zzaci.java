package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import org.json.JSONObject;

final class zzaci implements zzajs {
   // $FF: synthetic field
   private zzrd zzWk;
   // $FF: synthetic field
   private JSONObject zzWl;
   // $FF: synthetic field
   private zzabu zzUA;
   // $FF: synthetic field
   private zzacg zzWj;

   zzaci(zzacg var1, zzrd var2, JSONObject var3, zzabu var4) {
      this.zzWj = var1;
      this.zzWk = var2;
      this.zzWl = var3;
      this.zzUA = var4;
      super();
   }

   // $FF: synthetic method
   public final void zzc(Object var1) {
      zzai var3 = (zzai)var1;
      zzaci var2 = this;
      var3.zza("/loadSdkConstants", this.zzWk);

      try {
         var3.zza("AFMA_getSdkConstants", var2.zzWl);
      } catch (Exception var5) {
         zzafr.zzb("Error requesting an ad url", var5);
         zzacg.zze(this.zzUA);
      }
   }
}
