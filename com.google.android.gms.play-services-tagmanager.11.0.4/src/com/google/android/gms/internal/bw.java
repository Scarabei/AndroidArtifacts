package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.tagmanager.zzcn;
import java.util.Map;

public final class bw extends zzcxq {
   private final zzcn zzbKk;
   private final zzcvy zzbIP;

   public bw(zzcn var1, zzcvy var2) {
      this.zzbKk = var1;
      this.zzbIP = var2;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1 || var2.length == 2);
      zzbo.zzaf(var2[0] instanceof eb);
      Object var3;
      zzbo.zzaf((var3 = var2.length > 1 ? var2[1] : dv.zzbLu) == dv.zzbLu || var3 instanceof dz);
      zzcut var4 = this.zzbIP.zzCy();
      String var5 = (String)((eb)var2[0]).value();
      Bundle var6 = null;
      if (var3 != dv.zzbLu) {
         var6 = ed.zzy((Map)((dz)var3).zzDt());
      }

      try {
         this.zzbKk.logEventInternalNoInterceptor(var4.zzCm(), var5, var6, var4.currentTimeMillis());
      } catch (RemoteException var8) {
         String var10001 = String.valueOf(var8.getMessage());
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "Error calling measurement proxy:".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("Error calling measurement proxy:");
         }

         zzcvk.e(var10000);
      }

      return dv.zzbLu;
   }
}
