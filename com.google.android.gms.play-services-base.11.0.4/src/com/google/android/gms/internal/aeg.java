package com.google.android.gms.internal;

import java.io.IOException;

public final class aeg extends adj implements Cloneable {
   private String[] zzctG;
   private String[] zzctH;
   private int[] zzctI;
   private long[] zzctJ;
   private long[] zzctK;

   public aeg() {
      this.zzctG = ads.EMPTY_STRING_ARRAY;
      this.zzctH = ads.EMPTY_STRING_ARRAY;
      this.zzctI = ads.zzcsC;
      this.zzctJ = ads.zzcsD;
      this.zzctK = ads.zzcsD;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private aeg zzMa() {
      aeg var1;
      try {
         var1 = (aeg)super.zzLN();
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }

      if (this.zzctG != null && this.zzctG.length > 0) {
         var1.zzctG = (String[])this.zzctG.clone();
      }

      if (this.zzctH != null && this.zzctH.length > 0) {
         var1.zzctH = (String[])this.zzctH.clone();
      }

      if (this.zzctI != null && this.zzctI.length > 0) {
         var1.zzctI = (int[])this.zzctI.clone();
      }

      if (this.zzctJ != null && this.zzctJ.length > 0) {
         var1.zzctJ = (long[])this.zzctJ.clone();
      }

      if (this.zzctK != null && this.zzctK.length > 0) {
         var1.zzctK = (long[])this.zzctK.clone();
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aeg)) {
         return false;
      } else {
         aeg var2 = (aeg)var1;
         if (!adn.equals(this.zzctG, var2.zzctG)) {
            return false;
         } else if (!adn.equals(this.zzctH, var2.zzctH)) {
            return false;
         } else if (!adn.equals(this.zzctI, var2.zzctI)) {
            return false;
         } else if (!adn.equals(this.zzctJ, var2.zzctJ)) {
            return false;
         } else if (!adn.equals(this.zzctK, var2.zzctK)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzctG)) * 31 + adn.hashCode(this.zzctH)) * 31 + adn.hashCode(this.zzctI)) * 31 + adn.hashCode(this.zzctJ)) * 31 + adn.hashCode(this.zzctK)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      int var2;
      String var3;
      if (this.zzctG != null && this.zzctG.length > 0) {
         for(var2 = 0; var2 < this.zzctG.length; ++var2) {
            if ((var3 = this.zzctG[var2]) != null) {
               var1.zzl(1, var3);
            }
         }
      }

      if (this.zzctH != null && this.zzctH.length > 0) {
         for(var2 = 0; var2 < this.zzctH.length; ++var2) {
            if ((var3 = this.zzctH[var2]) != null) {
               var1.zzl(2, var3);
            }
         }
      }

      if (this.zzctI != null && this.zzctI.length > 0) {
         for(var2 = 0; var2 < this.zzctI.length; ++var2) {
            var1.zzr(3, this.zzctI[var2]);
         }
      }

      if (this.zzctJ != null && this.zzctJ.length > 0) {
         for(var2 = 0; var2 < this.zzctJ.length; ++var2) {
            var1.zzb(4, this.zzctJ[var2]);
         }
      }

      if (this.zzctK != null && this.zzctK.length > 0) {
         for(var2 = 0; var2 < this.zzctK.length; ++var2) {
            var1.zzb(5, this.zzctK[var2]);
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      int var2;
      int var3;
      int var4;
      String var5;
      if (this.zzctG != null && this.zzctG.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzctG.length; ++var4) {
            if ((var5 = this.zzctG[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzctH != null && this.zzctH.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzctH.length; ++var4) {
            if ((var5 = this.zzctH[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzctI != null && this.zzctI.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzctI.length; ++var3) {
            var4 = this.zzctI[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzctI.length;
      }

      long var6;
      if (this.zzctJ != null && this.zzctJ.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzctJ.length; ++var3) {
            var6 = this.zzctJ[var3];
            var2 += adh.zzaP(var6);
         }

         var1 = var1 + var2 + 1 * this.zzctJ.length;
      }

      if (this.zzctK != null && this.zzctK.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzctK.length; ++var3) {
            var6 = this.zzctK[var3];
            var2 += adh.zzaP(var6);
         }

         var1 = var1 + var2 + 1 * this.zzctK.length;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (aeg)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (aeg)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aeg var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int var7;
         int var8;
         int var9;
         long[] var10;
         long[] var11;
         String[] var13;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var5 = ads.zzb(var3, 10);
            var13 = new String[(var6 = var2.zzctG == null ? 0 : var2.zzctG.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctG, 0, var13, 0, var6);
            }
            break;
         case 18:
            var5 = ads.zzb(var3, 18);
            var13 = new String[(var6 = var2.zzctH == null ? 0 : var2.zzctH.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctH, 0, var13, 0, var6);
            }

            while(var6 < var13.length - 1) {
               var13[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var13[var6] = var3.readString();
            var2.zzctH = var13;
            continue;
         case 24:
            var5 = ads.zzb(var3, 24);
            int[] var12 = new int[(var6 = var2.zzctI == null ? 0 : var2.zzctI.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctI, 0, var12, 0, var6);
            }

            while(var6 < var12.length - 1) {
               var12[var6] = var3.zzLC();
               var3.zzLA();
               ++var6;
            }

            var12[var6] = var3.zzLC();
            var2.zzctI = var12;
            continue;
         case 26:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLC();
            }

            var3.zzcp(var8);
            int[] var14 = new int[(var9 = var2.zzctI == null ? 0 : var2.zzctI.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzctI, 0, var14, 0, var9);
            }

            while(var9 < var14.length) {
               var14[var9] = var3.zzLC();
               ++var9;
            }

            var2.zzctI = var14;
            var3.zzco(var6);
            continue;
         case 32:
            var5 = ads.zzb(var3, 32);
            var11 = new long[(var6 = var2.zzctJ == null ? 0 : var2.zzctJ.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctJ, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLB();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLB();
            var2.zzctJ = var11;
            continue;
         case 34:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLB();
            }

            var3.zzcp(var8);
            var10 = new long[(var9 = var2.zzctJ == null ? 0 : var2.zzctJ.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzctJ, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLB();
               ++var9;
            }

            var2.zzctJ = var10;
            var3.zzco(var6);
            continue;
         case 40:
            var5 = ads.zzb(var3, 40);
            var11 = new long[(var6 = var2.zzctK == null ? 0 : var2.zzctK.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctK, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLB();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLB();
            var2.zzctK = var11;
            continue;
         case 42:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLB();
            }

            var3.zzcp(var8);
            var10 = new long[(var9 = var2.zzctK == null ? 0 : var2.zzctK.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzctK, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLB();
               ++var9;
            }

            var2.zzctK = var10;
            var3.zzco(var6);
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var13.length - 1) {
            var13[var6] = var3.readString();
            var3.zzLA();
            ++var6;
         }

         var13[var6] = var3.readString();
         var2.zzctG = var13;
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMa();
   }
}
