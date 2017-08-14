package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;

public final class zzczc extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(!(var2[0] instanceof ea));
      zzbo.zzaf(!ed.zzm(var2[0]));
      dp var3 = var2[0];
      String var4 = "object";
      if (var3 == dv.zzbLu) {
         var4 = "undefined";
      } else if (var3 instanceof ds) {
         var4 = "boolean";
      } else if (var3 instanceof dt) {
         var4 = "number";
      } else if (var3 instanceof eb) {
         var4 = "string";
      } else if (var3 instanceof du) {
         var4 = "function";
      }

      return new eb(var4);
   }
}
