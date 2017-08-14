package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzj;
import com.google.android.gms.common.internal.zzbo;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzalo extends zzj {
   private final Map zzHa = new HashMap();

   public final String toString() {
      return zzh(this.zzHa);
   }

   public final void set(String var1, String var2) {
      String var3 = var1;
      zzbo.zzcF(var1);
      if (var1 != null && var1.startsWith("&")) {
         var3 = var1.substring(1);
      }

      zzbo.zzh(var3, "Name can not be empty or \"&\"");
      this.zzHa.put(var3, var2);
   }

   public final Map zzjR() {
      return Collections.unmodifiableMap(this.zzHa);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalo var3 = (zzalo)var1;
      zzbo.zzu(var3);
      var3.zzHa.putAll(this.zzHa);
   }
}
