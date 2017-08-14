package com.google.android.gms.internal;

import java.util.Map;

@zzzn
public final class zzqj implements zzrd {
   private final zzqk zzIT;

   public zzqj(zzqk var1) {
      this.zzIT = var1;
   }

   public final void zza(zzaka var1, Map var2) {
      String var3;
      if ((var3 = (String)var2.get("name")) == null) {
         zzafr.zzaT("App event with no name parameter.");
      } else {
         this.zzIT.onAppEvent(var3, (String)var2.get("info"));
      }
   }
}
