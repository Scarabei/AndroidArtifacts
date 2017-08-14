package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.js.zzai;
import java.util.Map;

final class zzoh implements zzrd {
   // $FF: synthetic field
   final zzai zzIj;
   // $FF: synthetic field
   final zzog zzIk;

   zzoh(zzog var1, zzai var2) {
      this.zzIk = var1;
      this.zzIj = var2;
   }

   public final void zza(zzaka var1, Map var2) {
      zzaka var3;
      if ((var3 = (zzaka)zzog.zza(this.zzIk).get()) == null) {
         this.zzIj.zzb("/loadHtml", (zzrd)this);
      } else {
         var3.zziw().zza((zzakf)(new zzoi(this, var2)));
         String var4 = (String)var2.get("overlayHtml");
         String var5;
         if (TextUtils.isEmpty(var5 = (String)var2.get("baseUrl"))) {
            var3.loadData(var4, "text/html", "UTF-8");
         } else {
            var3.loadDataWithBaseURL(var5, var4, "text/html", "UTF-8", (String)null);
         }
      }
   }
}
