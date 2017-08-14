package com.google.android.gms.internal;

import com.google.android.gms.analytics.zzj;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class zzalm extends zzj {
   private Map zzaeR = new HashMap(4);

   public final String toString() {
      HashMap var1 = new HashMap();
      Iterator var2 = this.zzaeR.entrySet().iterator();

      while(var2.hasNext()) {
         Entry var3;
         String var5 = String.valueOf((var3 = (Entry)var2.next()).getKey());
         String var4 = (new StringBuilder(9 + String.valueOf(var5).length())).append("dimension").append(var5).toString();
         var1.put(var4, var3.getValue());
      }

      return zzh(var1);
   }

   public final Map zzjP() {
      return Collections.unmodifiableMap(this.zzaeR);
   }

   // $FF: synthetic method
   public final void zzb(zzj var1) {
      zzalm var3 = (zzalm)var1;
      var3.zzaeR.putAll(this.zzaeR);
   }
}
