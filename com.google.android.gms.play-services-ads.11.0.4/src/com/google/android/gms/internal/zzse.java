package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

final class zzse implements Runnable {
   // $FF: synthetic field
   private String zzsD;
   // $FF: synthetic field
   private String zzJR;
   // $FF: synthetic field
   private String zzJW;
   // $FF: synthetic field
   private String val$message;
   // $FF: synthetic field
   private zzsb zzJV;

   zzse(zzsb var1, String var2, String var3, String var4, String var5) {
      this.zzJV = var1;
      this.zzsD = var2;
      this.zzJR = var3;
      this.zzJW = var4;
      this.val$message = var5;
      super();
   }

   public final void run() {
      HashMap var1;
      (var1 = new HashMap()).put("event", "precacheCanceled");
      var1.put("src", this.zzsD);
      if (!TextUtils.isEmpty(this.zzJR)) {
         var1.put("cachedSrc", this.zzJR);
      }

      var1.put("type", zzsb.zza(this.zzJV, this.zzJW));
      var1.put("reason", this.zzJW);
      if (!TextUtils.isEmpty(this.val$message)) {
         var1.put("message", this.val$message);
      }

      zzsb.zza(this.zzJV, "onPrecacheEvent", var1);
   }
}
