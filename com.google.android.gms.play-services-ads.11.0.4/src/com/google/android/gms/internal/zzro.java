package com.google.android.gms.internal;

import java.util.Map;

@zzzn
public final class zzro implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      if (com.google.android.gms.ads.internal.zzbs.zzbY().zzp(var1.getContext())) {
         int var3 = -1;

         try {
            var3 = Integer.parseInt((String)var2.get("eventType"));
         } catch (Exception var5) {
            zzafr.zzb("Parse Scion log event type error", var5);
         }

         String var4 = (String)var2.get("eventId");
         switch(var3) {
         case 0:
            com.google.android.gms.ads.internal.zzbs.zzbY().zzf(var1.getContext(), var4);
            return;
         case 1:
            com.google.android.gms.ads.internal.zzbs.zzbY().zzg(var1.getContext(), var4);
            return;
         case 2:
            com.google.android.gms.ads.internal.zzbs.zzbY().zzi(var1.getContext(), var4);
         default:
         }
      }
   }
}
