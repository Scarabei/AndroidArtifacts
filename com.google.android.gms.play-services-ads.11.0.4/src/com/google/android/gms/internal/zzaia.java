package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

@zzzn
public final class zzaia {
   private final String[] zzZS;
   private final double[] zzZT;
   private final double[] zzZU;
   private final int[] zzZV;
   private int zzZW;

   private zzaia(zzaid var1) {
      int var2 = zzaid.zza(var1).size();
      this.zzZS = (String[])zzaid.zzb(var1).toArray(new String[var2]);
      this.zzZT = zzo(zzaid.zza(var1));
      this.zzZU = zzo(zzaid.zzc(var1));
      this.zzZV = new int[var2];
      this.zzZW = 0;
   }

   private static double[] zzo(List var0) {
      double[] var1 = new double[var0.size()];

      for(int var2 = 0; var2 < var1.length; ++var2) {
         var1[var2] = ((Double)var0.get(var2)).doubleValue();
      }

      return var1;
   }

   public final void zza(double var1) {
      ++this.zzZW;

      for(int var3 = 0; var3 < this.zzZU.length; ++var3) {
         if (this.zzZU[var3] <= var1 && var1 < this.zzZT[var3]) {
            ++this.zzZV[var3];
         }

         if (var1 < this.zzZU[var3]) {
            break;
         }
      }

   }

   public final List getBuckets() {
      ArrayList var1 = new ArrayList(this.zzZS.length);

      for(int var2 = 0; var2 < this.zzZS.length; ++var2) {
         var1.add(new zzaic(this.zzZS[var2], this.zzZU[var2], this.zzZT[var2], (double)this.zzZV[var2] / (double)this.zzZW, this.zzZV[var2]));
      }

      return var1;
   }

   // $FF: synthetic method
   zzaia(zzaid var1, zzaib var2) {
      this(var1);
   }
}
