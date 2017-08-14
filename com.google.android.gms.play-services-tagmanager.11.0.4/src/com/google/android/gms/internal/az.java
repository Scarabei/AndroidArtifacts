package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbo;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class az extends zzcxq {
   protected final dp zza(zzcwa var1, dp... var2) {
      zzbo.zzaf(true);
      zzbo.zzaf(var2.length > 0);
      dp var3 = var2[0];
      Object var4 = var2.length > 1 ? var2[1] : dv.zzbLu;
      String var5 = "";
      if (var2.length > 2) {
         var5 = var2[2] == dv.zzbLu ? "" : zzcxp.zzd(var2[2]);
      }

      String var6 = "=";
      if (var2.length > 3) {
         var6 = var2[3] == dv.zzbLu ? "=" : zzcxp.zzd(var2[3]);
      }

      byte var7 = 0;
      HashSet var8 = null;
      if (var4 != dv.zzbLu) {
         zzbo.zzaf(var4 instanceof eb);
         if ("url".equals(((dp)var4).zzDl())) {
            var7 = 1;
         } else {
            if (!"backslash".equals(((dp)var4).zzDl())) {
               return new eb("");
            }

            var7 = 2;
            zza((Set)(var8 = new HashSet()), (String)var5);
            zza((Set)var8, (String)var6);
            var8.remove('\\');
         }
      }

      StringBuilder var9 = new StringBuilder();
      if (var3 instanceof dw) {
         boolean var10 = true;
         Iterator var11 = ((List)((dw)var3).zzDs()).iterator();

         while(var11.hasNext()) {
            dp var12 = (dp)var11.next();
            if (!var10) {
               var9.append(var5);
            }

            var10 = false;
            zzb(var9, zzcxp.zzd(var12), var7, var8);
         }
      } else if (var3 instanceof dz) {
         Map var15 = (Map)((dz)var3).zzDt();
         boolean var16 = true;
         Iterator var17 = var15.keySet().iterator();

         while(var17.hasNext()) {
            String var13 = (String)var17.next();
            if (!var16) {
               var9.append(var5);
            }

            var16 = false;
            String var14 = zzcxp.zzd((dp)var15.get(var13));
            zzb(var9, var13, var7, var8);
            var9.append(var6);
            zzb(var9, var14, var7, var8);
         }
      } else {
         zzb(var9, zzcxp.zzd(var3), var7, var8);
      }

      return new eb(var9.toString());
   }

   private static void zza(Set var0, String var1) {
      for(int var2 = 0; var2 < var1.length(); ++var2) {
         var0.add(var1.charAt(var2));
      }

   }

   private static void zzb(StringBuilder var0, String var1, int var2, Set var3) {
      var0.append(zzb(var1, var2, var3));
   }

   private static String zzb(String var0, int var1, Set var2) {
      switch(var1) {
      case 1:
         try {
            return URLEncoder.encode(var0, "UTF-8").replaceAll("\\+", "%20");
         } catch (UnsupportedEncodingException var5) {
            return var0;
         }
      case 2:
         var0 = var0.replace("\\", "\\\\");

         String var10002;
         String var4;
         for(Iterator var3 = var2.iterator(); var3.hasNext(); var0 = var0.replace(var4, var10002)) {
            var4 = ((Character)var3.next()).toString();
            String var10003 = String.valueOf(var4);
            if (var10003.length() != 0) {
               var10002 = "\\".concat(var10003);
            } else {
               String var10004 = new String;
               var10002 = var10004;
               var10004.<init>("\\");
            }
         }

         return var0;
      default:
         return var0;
      }
   }
}
