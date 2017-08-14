package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzj;
import java.util.HashMap;

public final class zzalv extends zzj {
   public String zzafq;
   public String zzaeX;
   public String zzafr;

   public final String toString() {
      HashMap var1;
      (var1 = new HashMap()).put("network", this.zzafq);
      var1.put("action", this.zzaeX);
      var1.put("target", this.zzafr);
      return zzh(var1);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalv var3 = (zzalv)var1;
      String var4;
      if (!TextUtils.isEmpty(this.zzafq)) {
         var4 = this.zzafq;
         var3.zzafq = this.zzafq;
      }

      if (!TextUtils.isEmpty(this.zzaeX)) {
         var4 = this.zzaeX;
         var3.zzaeX = this.zzaeX;
      }

      if (!TextUtils.isEmpty(this.zzafr)) {
         var4 = this.zzafr;
         var3.zzafr = this.zzafr;
      }

   }
}
