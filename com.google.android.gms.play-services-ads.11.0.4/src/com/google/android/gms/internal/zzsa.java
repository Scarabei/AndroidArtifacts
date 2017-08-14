package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzzn
public final class zzsa implements Iterable {
   private final List zzJO = new LinkedList();

   public static boolean zze(zzaka var0) {
      zzry var1;
      if ((var1 = zzg(var0)) != null) {
         var1.zzJL.abort();
         return true;
      } else {
         return false;
      }
   }

   public static boolean zzf(zzaka var0) {
      return zzg(var0) != null;
   }

   private static zzry zzg(zzaka var0) {
      Iterator var1 = com.google.android.gms.ads.internal.zzbs.zzbW().iterator();

      zzry var2;
      do {
         if (!var1.hasNext()) {
            return null;
         }
      } while((var2 = (zzry)var1.next()).zzJH != var0);

      return var2;
   }

   public final void zza(zzry var1) {
      this.zzJO.add(var1);
   }

   public final void zzb(zzry var1) {
      this.zzJO.remove(var1);
   }

   public final Iterator iterator() {
      return this.zzJO.iterator();
   }

   public final int zzeE() {
      return this.zzJO.size();
   }
}
