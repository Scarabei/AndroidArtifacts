package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzaid {
   private final List zzaaa = new ArrayList();
   private final List zzaab = new ArrayList();
   private final List zzaac = new ArrayList();

   public final zzaid zza(String var1, double var2, double var4) {
      int var6;
      for(var6 = 0; var6 < this.zzaaa.size(); ++var6) {
         double var7 = ((Double)this.zzaac.get(var6)).doubleValue();
         double var9 = ((Double)this.zzaab.get(var6)).doubleValue();
         if (var2 < var7 || var7 == var2 && var4 < var9) {
            break;
         }
      }

      this.zzaaa.add(var6, var1);
      this.zzaac.add(var6, var2);
      this.zzaab.add(var6, var4);
      return this;
   }

   public final zzaia zzid() {
      return new zzaia(this, (zzaib)null);
   }

   // $FF: synthetic method
   static List zza(zzaid var0) {
      return var0.zzaab;
   }

   // $FF: synthetic method
   static List zzb(zzaid var0) {
      return var0.zzaaa;
   }

   // $FF: synthetic method
   static List zzc(zzaid var0) {
      return var0.zzaac;
   }
}
