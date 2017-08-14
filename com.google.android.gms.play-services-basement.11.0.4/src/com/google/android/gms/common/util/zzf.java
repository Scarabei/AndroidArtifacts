package com.google.android.gms.common.util;

import android.support.v4.util.ArrayMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzf {
   private static Set zzf(int var0, boolean var1) {
      return (Set)(var0 <= 256 ? new zza(var0) : new HashSet(var0, 1.0F));
   }

   public static Set zza(Object var0, Object var1, Object var2) {
      Set var3;
      (var3 = zzf(3, false)).add(var0);
      var3.add(var1);
      var3.add(var2);
      return Collections.unmodifiableSet(var3);
   }

   public static Set zzb(Object... var0) {
      Object var10000;
      Object var1;
      Object var2;
      switch(var0.length) {
      case 0:
         return Collections.emptySet();
      case 1:
         return Collections.singleton(var0[0]);
      case 2:
         var10000 = var0[0];
         var2 = var0[1];
         var1 = var10000;
         Set var6;
         (var6 = zzf(2, false)).add(var1);
         var6.add(var2);
         return Collections.unmodifiableSet(var6);
      case 3:
         return zza(var0[0], var0[1], var0[2]);
      case 4:
         var10000 = var0[0];
         Object var10001 = var0[1];
         Object var10002 = var0[2];
         Object var4 = var0[3];
         Object var3 = var10002;
         var2 = var10001;
         var1 = var10000;
         Set var5;
         (var5 = zzf(4, false)).add(var1);
         var5.add(var2);
         var5.add(var3);
         var5.add(var4);
         return Collections.unmodifiableSet(var5);
      default:
         Set var7;
         Collections.addAll(var7 = zzf(var0.length, false), var0);
         return Collections.unmodifiableSet(var7);
      }
   }

   private static Map zzg(int var0, boolean var1) {
      return (Map)(var0 <= 256 ? new ArrayMap(var0) : new HashMap(var0, 1.0F));
   }

   public static Map zza(Object var0, Object var1, Object var2, Object var3) {
      Map var4;
      (var4 = zzg(2, false)).put(var0, var1);
      var4.put(var2, var3);
      return Collections.unmodifiableMap(var4);
   }

   public static Map zza(Object var0, Object var1, Object var2, Object var3, Object var4, Object var5, Object var6, Object var7, Object var8, Object var9, Object var10, Object var11) {
      Map var12;
      (var12 = zzg(6, false)).put(var0, var1);
      var12.put(var2, var3);
      var12.put(var4, var5);
      var12.put(var6, var7);
      var12.put(var8, var9);
      var12.put(var10, var11);
      return Collections.unmodifiableMap(var12);
   }
}
