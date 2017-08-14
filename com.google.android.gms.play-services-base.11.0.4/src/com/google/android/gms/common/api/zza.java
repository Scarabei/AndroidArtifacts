package com.google.android.gms.common.api;

import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbat;
import java.util.ArrayList;
import java.util.Iterator;

public final class zza extends Exception {
   private final ArrayMap zzaAB;

   public zza(ArrayMap var1) {
      this.zzaAB = var1;
   }

   public final ConnectionResult zza(GoogleApi var1) {
      zzbat var2 = var1.zzph();
      zzbo.zzb(this.zzaAB.get(var2) != null, "The given API was not part of the availability request.");
      return (ConnectionResult)this.zzaAB.get(var2);
   }

   public final ArrayMap zzpf() {
      return this.zzaAB;
   }

   public final String getMessage() {
      boolean var1 = true;
      ArrayList var2 = new ArrayList();
      Iterator var3 = this.zzaAB.keySet().iterator();

      while(var3.hasNext()) {
         zzbat var4 = (zzbat)var3.next();
         ConnectionResult var5;
         if ((var5 = (ConnectionResult)this.zzaAB.get(var4)).isSuccess()) {
            var1 = false;
         }

         String var6 = String.valueOf(var4.zzpr());
         String var7 = String.valueOf(var5);
         var2.add((new StringBuilder(2 + String.valueOf(var6).length() + String.valueOf(var7).length())).append(var6).append(": ").append(var7).toString());
      }

      StringBuilder var8 = new StringBuilder();
      if (var1) {
         var8.append("None of the queried APIs are available. ");
      } else {
         var8.append("Some of the queried APIs are unavailable. ");
      }

      var8.append(TextUtils.join("; ", var2));
      return var8.toString();
   }
}
