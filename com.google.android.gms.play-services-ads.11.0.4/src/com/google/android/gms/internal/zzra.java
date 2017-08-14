package com.google.android.gms.internal;

import java.util.Map;

final class zzra implements zzrd {
   public final void zza(zzaka var1, Map var2) {
      String var3;
      if ((var3 = (String)var2.get("u")) == null) {
         zzafr.zzaT("URL missing from httpTrack GMSG.");
      } else {
         (new zzaiq(var1.getContext(), var1.zziz().zzaP, var3)).zzhL();
      }
   }
}
