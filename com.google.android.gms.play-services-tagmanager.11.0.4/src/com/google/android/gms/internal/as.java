package com.google.android.gms.internal;

import android.os.Build;
import com.google.android.gms.common.internal.zzbo;

public final class as implements zzcxo {
   private final String zzbKc;
   private final String zzbKd;

   public as() {
      this.zzbKc = Build.MANUFACTURER;
      this.zzbKd = Build.MODEL;
   }

   public final dp zzb(zzcwa var1, dp... var2) {
      zzbo.zzaf(var2 != null);
      zzbo.zzaf(var2.length == 0);
      String var3 = this.zzbKc;
      String var4 = this.zzbKd;
      if (!this.zzbKd.startsWith(var3) && !var3.equals("unknown")) {
         var4 = (new StringBuilder(1 + String.valueOf(var3).length() + String.valueOf(var4).length())).append(var3).append(" ").append(var4).toString();
      }

      return new eb(var4);
   }
}
