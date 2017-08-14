package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class zzae {
   private List zzaq = new LinkedList();
   private List zzar = new ArrayList(64);
   private int zzas = 0;
   private final int zzat;
   private static Comparator zzau = new zzaf();

   public zzae(int var1) {
      this.zzat = var1;
   }

   public final synchronized byte[] zzb(int var1) {
      for(int var2 = 0; var2 < this.zzar.size(); ++var2) {
         byte[] var3;
         if ((var3 = (byte[])this.zzar.get(var2)).length >= var1) {
            this.zzas -= var3.length;
            this.zzar.remove(var2);
            this.zzaq.remove(var3);
            return var3;
         }
      }

      return new byte[var1];
   }

   public final synchronized void zza(byte[] var1) {
      if (var1 != null && var1.length <= this.zzat) {
         this.zzaq.add(var1);
         int var2;
         if ((var2 = Collections.binarySearch(this.zzar, var1, zzau)) < 0) {
            var2 = -var2 - 1;
         }

         this.zzar.add(var2, var1);
         this.zzas += var1.length;
         this.zzm();
      }
   }

   private final synchronized void zzm() {
      while(this.zzas > this.zzat) {
         byte[] var1 = (byte[])this.zzaq.remove(0);
         this.zzar.remove(var1);
         this.zzas -= var1.length;
      }

   }
}
