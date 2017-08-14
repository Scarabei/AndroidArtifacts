package com.google.android.gms.internal;

import java.io.IOException;

public final class acx extends adj {
   private int zzcqq = 0;
   private double zzcrz = 0.0D;
   private double zzcrA = 0.0D;
   private long zzcqr = 0L;
   private int[] zzcrB;

   public acx() {
      this.zzcrB = ads.zzcsC;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acx)) {
         return false;
      } else {
         acx var2 = (acx)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (Double.doubleToLongBits(this.zzcrz) != Double.doubleToLongBits(var2.zzcrz)) {
            return false;
         } else if (Double.doubleToLongBits(this.zzcrA) != Double.doubleToLongBits(var2.zzcrA)) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (!adn.equals(this.zzcrB, var2.zzcrB)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      int var1 = (527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq;
      long var2 = Double.doubleToLongBits(this.zzcrz);
      var1 = var1 * 31 + (int)(var2 ^ var2 >>> 32);
      var2 = Double.doubleToLongBits(this.zzcrA);
      return (((var1 * 31 + (int)(var2 ^ var2 >>> 32)) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + adn.hashCode(this.zzcrB)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (Double.doubleToLongBits(this.zzcrz) != Double.doubleToLongBits(0.0D)) {
         var1.zza(2, this.zzcrz);
      }

      if (Double.doubleToLongBits(this.zzcrA) != Double.doubleToLongBits(0.0D)) {
         var1.zza(3, this.zzcrA);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(4, this.zzcqr);
      }

      if (this.zzcrB != null && this.zzcrB.length > 0) {
         for(int var2 = 0; var2 < this.zzcrB.length; ++var2) {
            var1.zzr(5, this.zzcrB[var2]);
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (Double.doubleToLongBits(this.zzcrz) != Double.doubleToLongBits(0.0D)) {
         var1 += adh.zzct(2) + 8;
      }

      if (Double.doubleToLongBits(this.zzcrA) != Double.doubleToLongBits(0.0D)) {
         var1 += adh.zzct(3) + 8;
      }

      if (this.zzcqr != 0L) {
         var1 += adh.zze(4, this.zzcqr);
      }

      if (this.zzcrB != null && this.zzcrB.length > 0) {
         int var2 = 0;

         for(int var3 = 0; var3 < this.zzcrB.length; ++var3) {
            int var4 = this.zzcrB[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzcrB.length;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acx var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int var7;
         int var8;
         int var9;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 17:
            var2.zzcrz = Double.longBitsToDouble(var3.zzLI());
            break;
         case 25:
            var2.zzcrA = Double.longBitsToDouble(var3.zzLI());
            break;
         case 32:
            var2.zzcqr = var3.zzLG();
            break;
         case 40:
            int[] var13 = new int[var5 = ads.zzb(var3, 40)];
            var7 = 0;
            var8 = 0;

            for(; var8 < var5; ++var8) {
               if (var8 != 0) {
                  var3.zzLA();
               }

               var9 = var3.getPosition();
               int var15;
               switch(var15 = var3.zzLF()) {
               case 0:
               case 1:
               case 2:
               case 3:
               case 4:
                  var13[var7++] = var15;
                  break;
               default:
                  var3.zzcp(var9);
                  var2.zza(var3, var4);
               }
            }

            if (var7 != 0) {
               if ((var8 = var2.zzcrB == null ? 0 : var2.zzcrB.length) == 0 && var7 == var13.length) {
                  var2.zzcrB = var13;
               } else {
                  int[] var14 = new int[var8 + var7];
                  if (var8 != 0) {
                     System.arraycopy(var2.zzcrB, 0, var14, 0, var8);
                  }

                  System.arraycopy(var13, 0, var14, var8, var7);
                  var2.zzcrB = var14;
               }
            }
            break;
         case 42:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;
            var8 = var3.getPosition();

            while(var3.zzLK() > 0) {
               switch(var3.zzLF()) {
               case 0:
               case 1:
               case 2:
               case 3:
               case 4:
                  ++var7;
               }
            }

            if (var7 != 0) {
               var3.zzcp(var8);
               int[] var10 = new int[(var9 = var2.zzcrB == null ? 0 : var2.zzcrB.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzcrB, 0, var10, 0, var9);
               }

               while(var3.zzLK() > 0) {
                  int var11 = var3.getPosition();
                  int var12;
                  switch(var12 = var3.zzLF()) {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                     var10[var9++] = var12;
                     break;
                  default:
                     var3.zzcp(var11);
                     var2.zza(var3, 40);
                  }
               }

               var2.zzcrB = var10;
            }

            var3.zzco(var6);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
