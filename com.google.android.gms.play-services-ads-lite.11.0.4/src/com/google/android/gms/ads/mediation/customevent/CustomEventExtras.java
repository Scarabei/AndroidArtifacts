package com.google.android.gms.ads.mediation.customevent;

import com.google.ads.mediation.NetworkExtras;
import java.util.HashMap;

/** @deprecated */
@Deprecated
public final class CustomEventExtras implements NetworkExtras {
   private final HashMap zzacT = new HashMap();

   public final Object getExtra(String var1) {
      return this.zzacT.get(var1);
   }

   public final void setExtra(String var1, Object var2) {
      this.zzacT.put(var1, var2);
   }
}
