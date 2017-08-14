package com.google.android.gms.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import com.google.android.gms.common.internal.zzbo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class bx extends zzcxq {
   private final zzcux zzbKi;

   public bx(zzcux var1) {
      this.zzbKi = var1;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);
      dp var3;
      zzbo.zzaf(!((var3 = var2[0]) instanceof dv));
      Object var4;
      zzbo.zzaf((var4 = var2.length > 1 ? var2[1] : dv.zzbLu) == dv.zzbLu || var4 instanceof dw);
      Object var5;
      zzbo.zzaf((var5 = var2.length > 2 ? var2[2] : dv.zzbLu) == dv.zzbLu || !(var5 instanceof dv));
      Builder var6 = Uri.parse(zzcxp.zzd(var3)).buildUpon();
      if (var4 != dv.zzbLu) {
         Iterator var8 = ((List)((dw)var4).zzDs()).iterator();

         while(var8.hasNext()) {
            dp var9;
            zzbo.zzaf((var9 = (dp)var8.next()) instanceof dz);
            Iterator var10 = ((Map)((dz)var9).zzDt()).entrySet().iterator();

            while(var10.hasNext()) {
               Entry var11;
               String var12 = ((String)(var11 = (Entry)var10.next()).getKey()).toString();
               String var13 = zzcxp.zzd(ed.zza(var1, (dp)var11.getValue()));
               var6.appendQueryParameter(var12, var13);
            }
         }
      }

      String var7 = var6.build().toString();
      if (var5 == dv.zzbLu) {
         this.zzbKi.zzfh(var7);
         String var10001 = String.valueOf(var7);
         String var10000;
         if (var10001.length() != 0) {
            var10000 = "SendPixel: url = ".concat(var10001);
         } else {
            String var10002 = new String;
            var10000 = var10002;
            var10002.<init>("SendPixel: url = ");
         }

         zzcvk.v(var10000);
      } else {
         String var14 = zzcxp.zzd((dp)var5);
         this.zzbKi.zzW(var7, var14);
         zzcvk.v((new StringBuilder(30 + String.valueOf(var7).length() + String.valueOf(var14).length())).append("SendPixel: url = ").append(var7).append(", uniqueId = ").append(var14).toString());
      }

      return dv.zzbLu;
   }
}
