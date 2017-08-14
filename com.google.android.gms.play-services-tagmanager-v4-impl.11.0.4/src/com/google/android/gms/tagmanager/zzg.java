package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import java.util.Map;

final class zzg implements DataLayer.zzb {
   private final Context zzqD;

   public zzg(Context var1) {
      this.zzqD = var1;
   }

   public final void zzp(Map var1) {
      Object var2;
      Object var3;
      if ((var2 = var1.get("gtm.url")) == null && (var3 = var1.get("gtm")) != null && var3 instanceof Map) {
         var2 = ((Map)var3).get("url");
      }

      if (var2 != null && var2 instanceof String) {
         String var4;
         if ((var4 = Uri.parse((String)var2).getQueryParameter("referrer")) != null) {
            zzcx.zzM(this.zzqD, var4);
         }

      }
   }
}
