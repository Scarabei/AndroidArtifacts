package com.google.android.gms.internal;

import java.util.Map;

@zzzn
public final class zzwm {
   private final zzaka zzJH;
   private final boolean zzNM;
   private final String zzNN;

   public zzwm(zzaka var1, Map var2) {
      this.zzJH = var1;
      this.zzNN = (String)var2.get("forceOrientation");
      if (var2.containsKey("allowOrientationChange")) {
         this.zzNM = Boolean.parseBoolean((String)var2.get("allowOrientationChange"));
      } else {
         this.zzNM = true;
      }
   }

   public final void execute() {
      if (this.zzJH == null) {
         zzafr.zzaT("AdWebView is null");
      } else {
         int var1;
         if ("portrait".equalsIgnoreCase(this.zzNN)) {
            var1 = com.google.android.gms.ads.internal.zzbs.zzbB().zzhU();
         } else if ("landscape".equalsIgnoreCase(this.zzNN)) {
            var1 = com.google.android.gms.ads.internal.zzbs.zzbB().zzhT();
         } else if (this.zzNM) {
            var1 = -1;
         } else {
            var1 = com.google.android.gms.ads.internal.zzbs.zzbB().zzhV();
         }

         this.zzJH.setRequestedOrientation(var1);
      }
   }
}
