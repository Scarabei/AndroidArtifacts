package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.Map;

final class zzakp implements zzrd {
   // $FF: synthetic field
   private zzako zzacr;

   zzakp(zzako var1) {
      this.zzacr = var1;
      super();
   }

   public final void zza(zzaka var1, Map var2) {
      String var3;
      if (var2 != null && !TextUtils.isEmpty(var3 = (String)var2.get("height"))) {
         try {
            int var4 = Integer.parseInt(var3);
            synchronized(zzako.zza(this.zzacr)) {
               if (zzako.zzb(this.zzacr) != var4) {
                  zzako.zza(this.zzacr, var4);
                  this.zzacr.requestLayout();
               }

               return;
            }
         } catch (Exception var8) {
            zzafr.zzc("Exception occurred while getting webview content height", var8);
         }
      }

   }
}
