package com.google.android.gms.internal;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

final class zzqv implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3;
      if (TextUtils.isEmpty(var3 = (String)var2.get("urls"))) {
         zzafr.zzaT("URLs missing in canOpenURLs GMSG.");
      } else {
         String[] var4 = var3.split(",");
         HashMap var5 = new HashMap();
         PackageManager var6 = var1.getContext().getPackageManager();
         String[] var7 = var4;
         int var8 = var4.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            String var10;
            String[] var11;
            String var12 = (var11 = (var10 = var7[var9]).split(";", 2))[0].trim();
            String var13 = var11.length > 1 ? var11[1].trim() : "android.intent.action.VIEW";
            Uri var14 = Uri.parse(var12);
            Intent var15 = new Intent(var13, var14);
            ResolveInfo var16 = var6.resolveActivity(var15, 65536);
            var5.put(var10, var16 != null);
         }

         var1.zza("openableURLs", (Map)var5);
      }
   }
}
