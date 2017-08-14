package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.js.zzai;
import java.lang.ref.WeakReference;
import java.util.Map;

final class zzof extends zzym implements zzrd {
   private final WeakReference zztX;
   private final String zzIe;

   public zzof(zznz var1, String var2) {
      this.zztX = new WeakReference(var1);
      this.zzIe = var2;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3;
      if (!TextUtils.isEmpty(var3 = (String)var2.get("ads_id")) && this.zzIe.equals(var3)) {
         int var4 = -1;

         try {
            var4 = Integer.parseInt((String)var2.get("eventType"));
         } catch (Exception var6) {
            zzafr.zzb("Parse Scion log event type error", var6);
         }

         zznz var5;
         if (1 == var4) {
            if ((var5 = (zznz)this.zztX.get()) != null) {
               var5.zzat();
            }

         } else if (var4 == 0) {
            if ((var5 = (zznz)this.zztX.get()) != null) {
               var5.zzau();
            }

         }
      }
   }

   public final void zzd(zzai var1) {
      var1.zza("/logScionEvent", (zzrd)this);
   }
}
