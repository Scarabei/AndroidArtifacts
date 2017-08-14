package com.google.android.gms.internal;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.util.Map;

final class zzqx implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3;
      if ((var3 = (String)var2.get("u")) == null) {
         zzafr.zzaT("URL missing from click GMSG.");
      } else {
         Uri var4 = Uri.parse(var3);

         try {
            zzcu var5;
            if ((var5 = var1.zziy()) != null && var5.zzc(var4)) {
               var4 = var5.zza(var4, var1.getContext(), var1.getView());
            }
         } catch (zzcv var10) {
            String var10001 = String.valueOf(var3);
            String var10000;
            if (var10001.length() != 0) {
               var10000 = "Unable to append parameter to URL: ".concat(var10001);
            } else {
               String var10002 = new String;
               var10000 = var10002;
               var10002.<init>("Unable to append parameter to URL: ");
            }

            zzafr.zzaT(var10000);
         }

         zzaew var13 = com.google.android.gms.ads.internal.zzbs.zzbY();
         Context var7 = var1.getContext();
         zzaew var6 = var13;
         zzme var8 = zzmo.zzDy;
         if (((Boolean)com.google.android.gms.ads.internal.zzbs.zzbL().zzd(var8)).booleanValue() && var6.zzp(var7) && TextUtils.isEmpty(var4.getQueryParameter("fbs_aeid"))) {
            String var11 = com.google.android.gms.ads.internal.zzbs.zzbY().zzw(var1.getContext());
            com.google.android.gms.ads.internal.zzbs.zzbz();
            String var12 = "fbs_aeid";
            var4 = zzagz.zzb(var4.toString(), var12, var11);
            com.google.android.gms.ads.internal.zzbs.zzbY().zzf(var1.getContext(), var11);
         }

         var3 = var4.toString();
         (new zzaiq(var1.getContext(), var1.zziz().zzaP, var3)).zzhL();
      }
   }
}
