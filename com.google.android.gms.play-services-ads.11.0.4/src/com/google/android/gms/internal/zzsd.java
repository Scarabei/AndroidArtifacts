package com.google.android.gms.internal;

import java.util.HashMap;

final class zzsd implements Runnable {
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private String zzJR;
   // $FF: synthetic field
   private int zzJT;
   // $FF: synthetic field
   private zzsb zzJV;

   zzsd(zzsb var1, String var2, String var3, int var4) {
      this.zzJV = var1;
      this.zzsD = var2;
      this.zzJR = var3;
      this.zzJT = var4;
      super();
   }

   public final void run() {
      HashMap var1;
      (var1 = new HashMap()).put("event", "precacheComplete");
      var1.put("src", this.zzsD);
      var1.put("cachedSrc", this.zzJR);
      var1.put("totalBytes", Integer.toString(this.zzJT));
      zzsb.zza(this.zzJV, "onPrecacheEvent", var1);
   }
}
