package com.google.android.gms.awareness.fence;

import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.internal.zzbiy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class AwarenessFence extends com.google.android.gms.common.internal.safeparcel.zza {
   public static AwarenessFence and(AwarenessFence... var0) {
      zzbo.zzaf(var0 != null && var0.length > 0);
      return zzbiy.zze(zza(var0));
   }

   public static AwarenessFence and(Collection var0) {
      zzbo.zzaf(var0 != null && !var0.isEmpty());
      return zzbiy.zze(zzd(var0));
   }

   public static AwarenessFence or(AwarenessFence... var0) {
      zzbo.zzaf(var0 != null && var0.length > 0);
      return zzbiy.zzf(zza(var0));
   }

   public static AwarenessFence or(Collection var0) {
      zzbo.zzaf(var0 != null && !var0.isEmpty());
      return zzbiy.zzf(zzd(var0));
   }

   public static AwarenessFence not(AwarenessFence var0) {
      zzbo.zzu(var0);
      return zzbiy.zza((zzbiy)var0);
   }

   private static ArrayList zza(AwarenessFence[] var0) {
      ArrayList var1 = new ArrayList(var0.length);
      AwarenessFence[] var2 = var0;
      int var3 = var0.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         AwarenessFence var5 = var2[var4];
         var1.add((zzbiy)var5);
      }

      return var1;
   }

   private static ArrayList zzd(Collection var0) {
      ArrayList var1 = new ArrayList(var0.size());
      Iterator var2 = var0.iterator();

      while(var2.hasNext()) {
         AwarenessFence var3 = (AwarenessFence)var2.next();
         var1.add((zzbiy)var3);
      }

      return var1;
   }
}
