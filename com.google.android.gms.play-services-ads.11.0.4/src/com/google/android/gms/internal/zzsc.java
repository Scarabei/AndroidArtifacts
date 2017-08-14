package com.google.android.gms.internal;

import java.util.HashMap;

final class zzsc implements Runnable {
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private String zzJR;
   // $FF: synthetic field
   private int zzJS;
   // $FF: synthetic field
   private int zzJT;
   // $FF: synthetic field
   private boolean zzJU;
   // $FF: synthetic field
   private zzsb zzJV;

   zzsc(zzsb var1, String var2, String var3, int var4, int var5, boolean var6) {
      this.zzJV = var1;
      this.zzsD = var2;
      this.zzJR = var3;
      this.zzJS = var4;
      this.zzJT = var5;
      this.zzJU = false;
      super();
   }

   public final void run() {
      HashMap var1;
      (var1 = new HashMap()).put("event", "precacheProgress");
      var1.put("src", this.zzsD);
      var1.put("cachedSrc", this.zzJR);
      var1.put("bytesLoaded", Integer.toString(this.zzJS));
      var1.put("totalBytes", Integer.toString(this.zzJT));
      var1.put("cacheReady", this.zzJU ? "1" : "0");
      zzsb.zza(this.zzJV, "onPrecacheEvent", var1);
   }
}
