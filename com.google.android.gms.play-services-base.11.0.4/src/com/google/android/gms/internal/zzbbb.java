package com.google.android.gms.internal;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbo;

final class zzbbb {
   private final int zzaBP;
   private final ConnectionResult zzaBQ;

   zzbbb(ConnectionResult var1, int var2) {
      zzbo.zzu(var1);
      this.zzaBQ = var1;
      this.zzaBP = var2;
   }

   final int zzpy() {
      return this.zzaBP;
   }

   final ConnectionResult zzpz() {
      return this.zzaBQ;
   }
}
