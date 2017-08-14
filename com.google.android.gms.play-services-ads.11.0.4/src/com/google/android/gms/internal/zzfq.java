package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import org.json.JSONObject;

@zzzn
public final class zzfq implements zzgd {
   private final zzfi zzxn;
   private final zzai zzxo;
   private final zzrd zzxp = new zzfr(this);
   private final zzrd zzxq = new zzfs(this);
   private final zzrd zzxr = new zzft(this);

   public zzfq(zzfi var1, zzai var2) {
      this.zzxn = var1;
      this.zzxo = var2;
      zzai var4 = this.zzxo;
      var4.zza("/updateActiveView", this.zzxp);
      var4.zza("/untrackActiveViewUnit", this.zzxq);
      var4.zza("/visibilityChanged", this.zzxr);
      String var10001 = String.valueOf(this.zzxn.zzwQ.zzcm());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Custom JS tracking ad unit: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Custom JS tracking ad unit: ");
      }

      zzafr.zzaC(var10000);
   }

   public final void zzb(JSONObject var1, boolean var2) {
      if (!var2) {
         this.zzxo.zza("AFMA_updateActiveView", var1);
      } else {
         this.zzxn.zzb((zzgd)this);
      }
   }

   public final boolean zzcy() {
      return true;
   }

   public final void zzcz() {
      zzai var2 = this.zzxo;
      var2.zzb("/visibilityChanged", this.zzxr);
      var2.zzb("/untrackActiveViewUnit", this.zzxq);
      var2.zzb("/updateActiveView", this.zzxp);
   }

   // $FF: synthetic method
   static zzfi zza(zzfq var0) {
      return var0.zzxn;
   }
}
