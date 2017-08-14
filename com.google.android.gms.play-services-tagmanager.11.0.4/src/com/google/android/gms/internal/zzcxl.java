package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzcxl {
   private static final Map zzbJO;

   public static ea zza(String var0, Map var1, zzcwa var2) {
      if (!zzbJO.containsKey(var0)) {
         throw new RuntimeException((new StringBuilder(47 + String.valueOf(var0).length())).append("Fail to convert ").append(var0).append(" to the internal representation").toString());
      } else {
         zzcxm var3;
         List var4 = zza((var3 = (zzcxm)zzbJO.get(var0)).zzCH(), var1);
         ArrayList var5;
         (var5 = new ArrayList()).add(new eb("gtmUtils"));
         ea var6 = new ea("15", var5);
         ArrayList var7;
         (var7 = new ArrayList()).add(var6);
         var7.add(new eb("mobile"));
         ea var8 = new ea("17", var7);
         ArrayList var9;
         (var9 = new ArrayList()).add(var8);
         var9.add(new eb(var3.zzCG()));
         var9.add(new dw(var4));
         return new ea("2", var9);
      }
   }

   private static List zza(String[] var0, Map var1) {
      ArrayList var2 = new ArrayList();

      for(int var3 = 0; var3 < var0.length; ++var3) {
         if (!var1.containsKey(var0[var3])) {
            var2.add(dv.zzbLu);
         } else {
            var2.add((dp)var1.get(var0[var3]));
         }
      }

      return var2;
   }

   public static String zzfM(String var0) {
      return zzbJO.containsKey(var0) ? ((zzcxm)zzbJO.get(var0)).zzCG() : null;
   }

   public static String zza(zzbf var0) {
      return zzfM(var0.toString());
   }

   static {
      HashMap var0;
      (var0 = new HashMap()).put(zzbf.zzey.toString(), new zzcxm("contains"));
      var0.put(zzbf.zzex.toString(), new zzcxm("endsWith"));
      var0.put(zzbf.zzez.toString(), new zzcxm("equals"));
      var0.put(zzbf.zzeD.toString(), new zzcxm("greaterEquals"));
      var0.put(zzbf.zzeC.toString(), new zzcxm("greaterThan"));
      var0.put(zzbf.zzeB.toString(), new zzcxm("lessEquals"));
      var0.put(zzbf.zzeA.toString(), new zzcxm("lessThan"));
      var0.put(zzbf.zzev.toString(), new zzcxm("regex", new String[]{zzbg.zzfR.toString(), zzbg.zzfS.toString(), zzbg.zzhP.toString()}));
      var0.put(zzbf.zzew.toString(), new zzcxm("startsWith"));
      zzbJO = var0;
   }
}
