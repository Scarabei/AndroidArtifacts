package com.google.android.gms.internal;

import java.util.Map;

final class zzakt implements Runnable {
   // $FF: synthetic field
   private Map zzacB;
   // $FF: synthetic field
   private zzaks zzacC;

   zzakt(zzaks var1, Map var2) {
      this.zzacC = var1;
      this.zzacB = var2;
      super();
   }

   public final void run() {
      zzaks.zzb(this.zzacC).zza("pubVideoCmd", this.zzacB);
   }
}
