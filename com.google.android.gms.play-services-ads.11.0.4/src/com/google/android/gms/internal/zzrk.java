package com.google.android.gms.internal;

import java.util.List;

@zzzn
final class zzrk {
   private final String zzJt;
   private final int zzJA;
   private final List zzJB;
   private final String zzHD;

   public zzrk(String var1, int var2, List var3, String var4) {
      this.zzJt = var1;
      this.zzJA = var2;
      this.zzJB = var3;
      this.zzHD = var4;
   }

   public final String zzey() {
      return this.zzJt;
   }

   public final int getResponseCode() {
      return this.zzJA;
   }

   public final Iterable zzeD() {
      return this.zzJB;
   }

   public final String getBody() {
      return this.zzHD;
   }
}
