package com.google.android.gms.vision;

import android.util.SparseArray;

public final class zzc {
   private static final Object zzuF = new Object();
   private static int zzbMY = 0;
   private SparseArray zzbMZ = new SparseArray();
   private SparseArray zzbNa = new SparseArray();

   public final int zzbL(int var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         Integer var3;
         if ((var3 = (Integer)this.zzbMZ.get(var1)) != null) {
            return var3.intValue();
         } else {
            int var4 = zzbMY++;
            this.zzbMZ.append(var1, var4);
            this.zzbNa.append(var4, var1);
            return var4;
         }
      }
   }

   public final int zzbM(int var1) {
      Object var2 = zzuF;
      synchronized(zzuF) {
         return ((Integer)this.zzbNa.get(var1)).intValue();
      }
   }
}
