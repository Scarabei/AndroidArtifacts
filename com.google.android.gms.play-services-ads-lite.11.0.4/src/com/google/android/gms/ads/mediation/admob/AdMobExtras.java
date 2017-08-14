package com.google.android.gms.ads.mediation.admob;

import android.os.Bundle;
import com.google.ads.mediation.NetworkExtras;

/** @deprecated */
@Deprecated
public final class AdMobExtras implements NetworkExtras {
   private final Bundle mExtras;

   public AdMobExtras(Bundle var1) {
      this.mExtras = var1 != null ? new Bundle(var1) : null;
   }

   public final Bundle getExtras() {
      return this.mExtras;
   }
}
