package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public final class bv extends zzcxq {
   private final zzcux zzbKi;
   private static final Set zzbKj = new HashSet(Arrays.asList("GET", "HEAD", "POST", "PUT"));

   public bv(zzcux var1) {
      this.zzbKi = var1;
   }

   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length == 1);
      zzbo.zzaf(var2[0] instanceof dz);
      dp var3;
      zzbo.zzaf((var3 = var2[0].zzfZ("url")) instanceof eb);
      String var4 = (String)((eb)var3).value();
      Object var5;
      if ((var5 = var2[0].zzfZ("method")) == dv.zzbLu) {
         var5 = new eb("GET");
      }

      zzbo.zzaf(var5 instanceof eb);
      String var6 = (String)((eb)var5).value();
      zzbo.zzaf(zzbKj.contains(var6));
      dp var7;
      zzbo.zzaf((var7 = var2[0].zzfZ("uniqueId")) == dv.zzbLu || var7 == dv.zzbLt || var7 instanceof eb);
      String var8 = var7 != dv.zzbLu && var7 != dv.zzbLt ? (String)((eb)var7).value() : null;
      dp var9;
      zzbo.zzaf((var9 = var2[0].zzfZ("headers")) == dv.zzbLu || var9 instanceof dz);
      HashMap var10 = new HashMap();
      if (var9 == dv.zzbLu) {
         var10 = null;
      } else {
         Iterator var11 = ((Map)((dz)var9).zzDt()).entrySet().iterator();

         while(var11.hasNext()) {
            Entry var12;
            String var13 = (String)(var12 = (Entry)var11.next()).getKey();
            dp var14;
            if (!((var14 = (dp)var12.getValue()) instanceof eb)) {
               zzcvk.zzaT(String.format("Ignore the non-string value of header key %s.", var13));
            } else {
               var10.put(var13, (String)((eb)var14).value());
            }
         }
      }

      dp var15;
      zzbo.zzaf((var15 = var2[0].zzfZ("body")) == dv.zzbLu || var15 instanceof eb);
      String var16 = var15 == dv.zzbLu ? null : (String)((eb)var15).value();
      if ((var6.equals("GET") || var6.equals("HEAD")) && var16 != null) {
         zzcvk.zzaT(String.format("Body of %s hit will be ignored: %s.", var6, var16));
      }

      this.zzbKi.zza(var4, var6, var8, var10, var16);
      zzcvk.v(String.format("QueueRequest:\n  url = %s,\n  method = %s,\n  uniqueId = %s,\n  headers = %s,\n  body = %s", var4, var6, var8, var10, var16));
      return dv.zzbLu;
   }
}
