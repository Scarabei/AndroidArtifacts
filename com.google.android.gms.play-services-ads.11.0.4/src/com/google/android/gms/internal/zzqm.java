package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

@zzzn
public final class zzqm implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3 = (String)var2.get("action");
      String var7;
      String var8;
      if ("tick".equals(var3)) {
         var7 = (String)var2.get("label");
         var8 = (String)var2.get("start_label");
         String var19 = (String)var2.get("timestamp");
         if (TextUtils.isEmpty(var7)) {
            zzafr.zzaT("No label given for CSI tick.");
         } else if (TextUtils.isEmpty(var19)) {
            zzafr.zzaT("No timestamp given for CSI tick.");
         } else {
            long var10;
            try {
               long var13 = Long.parseLong(var19);
               long var15 = com.google.android.gms.ads.internal.zzbs.zzbF().currentTimeMillis();
               var10 = com.google.android.gms.ads.internal.zzbs.zzbF().elapsedRealtime() + (var13 - var15);
            } catch (NumberFormatException var17) {
               zzafr.zzc("Malformed timestamp for CSI tick.", var17);
               return;
            }

            var8 = TextUtils.isEmpty(var8) ? "native:view_load" : var8;
            var1.zziG().zza(var7, var8, var10);
         }
      } else if ("experiment".equals(var3)) {
         if (TextUtils.isEmpty(var7 = (String)var2.get("value"))) {
            zzafr.zzaT("No value given for CSI experiment.");
         } else {
            zznb var18;
            if ((var18 = var1.zziG().zzdR()) == null) {
               zzafr.zzaT("No ticker for WebView, dropping experiment ID.");
            } else {
               var18.zzh("e", var7);
            }
         }
      } else {
         if ("extra".equals(var3)) {
            var7 = (String)var2.get("name");
            if (TextUtils.isEmpty(var8 = (String)var2.get("value"))) {
               zzafr.zzaT("No value given for CSI extra.");
               return;
            }

            if (TextUtils.isEmpty(var7)) {
               zzafr.zzaT("No name given for CSI extra.");
               return;
            }

            zznb var9;
            if ((var9 = var1.zziG().zzdR()) == null) {
               zzafr.zzaT("No ticker for WebView, dropping extra parameter.");
               return;
            }

            var9.zzh(var7, var8);
         }

      }
   }
}
