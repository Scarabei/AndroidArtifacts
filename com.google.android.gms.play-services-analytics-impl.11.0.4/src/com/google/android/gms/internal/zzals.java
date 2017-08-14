package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzals extends zzj {
   public String zzafa;
   public boolean zzafb;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("description", this.zzafa);
      var1.put("fatal", this.zzafb);
      return zzh(var1);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzals var3 = (zzals)var1;
      if (!TextUtils.isEmpty(this.zzafa)) {
         String var4 = this.zzafa;
         var3.zzafa = this.zzafa;
      }

      if (this.zzafb) {
         boolean var5 = this.zzafb;
         var3.zzafb = this.zzafb;
      }

   }
}
