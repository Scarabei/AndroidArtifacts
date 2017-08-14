package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;

public final class zzg implements zzo {
   private static final Uri zzadO;
   private final LogPrinter zzadP = new LogPrinter(4, "GA/LogCatTransport");

   public final Uri zzjl() {
      return zzadO;
   }

   public final void zzb(zzi var1) {
      ArrayList var2;
      Collections.sort(var2 = new ArrayList(var1.zzjq()), new zzh(this));
      StringBuilder var3 = new StringBuilder();
      ArrayList var5;
      int var6 = (var5 = (ArrayList)var2).size();
      int var7 = 0;

      while(var7 < var6) {
         Object var10000 = var5.get(var7);
         ++var7;
         String var4;
         if (!TextUtils.isEmpty(var4 = ((zzj)var10000).toString())) {
            if (var3.length() != 0) {
               var3.append(", ");
            }

            var3.append(var4);
         }
      }

      this.zzadP.println(var3.toString());
   }

   static {
      Builder var0;
      (var0 = new Builder()).scheme("uri");
      var0.authority("local");
      zzadO = var0.build();
   }
}
