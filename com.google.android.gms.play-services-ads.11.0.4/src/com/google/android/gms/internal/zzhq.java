package com.google.android.gms.internal;

import java.util.Comparator;

public final class zzhq implements Comparator {
   public zzhq(zzhp var1) {
   }

   // $FF: synthetic method
   public final int compare(Object var1, Object var2) {
      zzhe var10000 = (zzhe)var1;
      zzhe var4 = (zzhe)var2;
      zzhe var3 = var10000;
      if (var10000.zzcS() < var4.zzcS()) {
         return -1;
      } else if (var3.zzcS() > var4.zzcS()) {
         return 1;
      } else if (var3.zzcR() < var4.zzcR()) {
         return -1;
      } else if (var3.zzcR() > var4.zzcR()) {
         return 1;
      } else {
         float var5 = (var3.zzcU() - var3.zzcS()) * (var3.zzcT() - var3.zzcR());
         float var6 = (var4.zzcU() - var4.zzcS()) * (var4.zzcT() - var4.zzcR());
         if (var5 > var6) {
            return -1;
         } else {
            return var5 < var6 ? 1 : 0;
         }
      }
   }
}
