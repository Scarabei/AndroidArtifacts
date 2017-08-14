package com.google.android.gms.internal;

import android.os.RemoteException;
import java.util.Map;

final class zzcvx implements aa {
   // $FF: synthetic field
   private zzcvu zzbIQ;

   private zzcvx(zzcvu var1) {
      this.zzbIQ = var1;
      super();
   }

   public final Object zzd(String var1, Map var2) {
      try {
         return zzcvu.zzb(this.zzbIQ).zzf(var1, var2);
      } catch (RemoteException var4) {
         String var10001 = String.valueOf(var4.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Error calling customEvaluator proxy:".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error calling customEvaluator proxy:");
         }

         zzcvk.e(var10000);
         return null;
      }
   }

   // $FF: synthetic method
   zzcvx(zzcvu var1, zzcvv var2) {
      this(var1);
   }
}
