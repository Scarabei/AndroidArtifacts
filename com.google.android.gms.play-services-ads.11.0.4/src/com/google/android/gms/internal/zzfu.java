package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.js.zzai;
import com.google.android.gms.ads.internal.js.zzl;
import com.google.android.gms.ads.internal.js.zzy;
import org.json.JSONObject;

@zzzn
public final class zzfu implements zzgd {
   private final zzfi zzxn;
   private final Context mContext;
   private zzy zzxt;
   private boolean zzxu;
   private final zzrd zzxp = new zzfz(this);
   private final zzrd zzxq = new zzga(this);
   private final zzrd zzxr = new zzgb(this);
   private final zzrd zzxv = new zzgc(this);

   public zzfu(zzfi var1, zzl var2, Context var3) {
      this.zzxn = var1;
      this.mContext = var3;
      this.zzxt = var2.zzb((zzcu)null);
      this.zzxt.zza(new zzfv(this), new zzfw(this));
      String var10001 = String.valueOf(this.zzxn.zzwQ.zzcm());
      String var10000;
      if (var10001.length() != 0) {
         var10000 = "Core JS tracking ad unit: ".concat(var10001);
      } else {
         String var10002 = new String;
         var10000 = var10002;
         var10002.<init>("Core JS tracking ad unit: ");
      }

      zzafr.zzaC(var10000);
   }

   public final void zzb(JSONObject var1, boolean var2) {
      this.zzxt.zza(new zzfx(this, var1), new zzajr());
   }

   public final boolean zzcy() {
      return this.zzxu;
   }

   public final void zzcz() {
      this.zzxt.zza(new zzfy(this), new zzajr());
      this.zzxt.release();
   }

   final void zzb(zzai var1) {
      var1.zza("/updateActiveView", this.zzxp);
      var1.zza("/untrackActiveViewUnit", this.zzxq);
      var1.zza("/visibilityChanged", this.zzxr);
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(this.mContext)) {
         var1.zza("/logScionEvent", this.zzxv);
      }

   }

   final void zzc(zzai var1) {
      var1.zzb("/visibilityChanged", this.zzxr);
      var1.zzb("/untrackActiveViewUnit", this.zzxq);
      var1.zzb("/updateActiveView", this.zzxp);
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(this.mContext)) {
         var1.zzb("/logScionEvent", this.zzxv);
      }

   }

   // $FF: synthetic method
   static boolean zza(zzfu var0, boolean var1) {
      return var0.zzxu = true;
   }

   // $FF: synthetic method
   static zzfi zza(zzfu var0) {
      return var0.zzxn;
   }
}
