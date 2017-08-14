package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.js.zzai;
import org.json.JSONObject;

final class zzod extends zzym {
   // $FF: synthetic field
   private JSONObject zzIg;

   zzod(zzoc var1, JSONObject var2) {
      this.zzIg = var2;
      super();
   }

   public final void zzd(zzai var1) {
      var1.zza("google.afma.nativeAds.handleClickGmsg", this.zzIg);
   }
}
