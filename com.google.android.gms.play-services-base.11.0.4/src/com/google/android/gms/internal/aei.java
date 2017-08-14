package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class aei extends adj implements Cloneable {
   private byte[] zzctM;
   private String zzctN;
   private byte[][] zzctO;
   private boolean zzctP;

   public aei() {
      this.zzctM = ads.zzcsI;
      this.zzctN = "";
      this.zzctO = ads.zzcsH;
      this.zzctP = false;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   private aei zzMc() {
      aei var1;
      try {
         var1 = (aei)super.zzLN();
      } catch (CloneNotSupportedException var3) {
         throw new AssertionError(var3);
      }

      if (this.zzctO != null && this.zzctO.length > 0) {
         var1.zzctO = (byte[][])this.zzctO.clone();
      }

      return var1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aei)) {
         return false;
      } else {
         aei var2 = (aei)var1;
         if (!Arrays.equals(this.zzctM, var2.zzctM)) {
            return false;
         } else {
            if (this.zzctN == null) {
               if (var2.zzctN != null) {
                  return false;
               }
            } else if (!this.zzctN.equals(var2.zzctN)) {
               return false;
            }

            if (!adn.zza(this.zzctO, var2.zzctO)) {
               return false;
            } else if (this.zzctP != var2.zzctP) {
               return false;
            } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
               return this.zzcso.equals(var2.zzcso);
            } else {
               return var2.zzcso == null || var2.zzcso.isEmpty();
            }
         }
      }
   }

   public final int hashCode() {
      return (((((527 + this.getClass().getName().hashCode()) * 31 + Arrays.hashCode(this.zzctM)) * 31 + (this.zzctN == null ? 0 : this.zzctN.hashCode())) * 31 + adn.zzc(this.zzctO)) * 31 + (this.zzctP ? 1231 : 1237)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (!Arrays.equals(this.zzctM, ads.zzcsI)) {
         var1.zzb(1, this.zzctM);
      }

      if (this.zzctO != null && this.zzctO.length > 0) {
         for(int var2 = 0; var2 < this.zzctO.length; ++var2) {
            byte[] var3;
            if ((var3 = this.zzctO[var2]) != null) {
               var1.zzb(2, var3);
            }
         }
      }

      if (this.zzctP) {
         var1.zzk(3, this.zzctP);
      }

      if (this.zzctN != null && !this.zzctN.equals("")) {
         var1.zzl(4, this.zzctN);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (!Arrays.equals(this.zzctM, ads.zzcsI)) {
         var1 += adh.zzc(1, this.zzctM);
      }

      if (this.zzctO != null && this.zzctO.length > 0) {
         int var2 = 0;
         int var3 = 0;

         for(int var4 = 0; var4 < this.zzctO.length; ++var4) {
            byte[] var5;
            if ((var5 = this.zzctO[var4]) != null) {
               ++var2;
               var3 += adh.zzJ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzctP) {
         var1 += adh.zzct(3) + 1;
      }

      if (this.zzctN != null && !this.zzctN.equals("")) {
         var1 += adh.zzm(4, this.zzctN);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adj zzLN() throws CloneNotSupportedException {
      return (aei)this.clone();
   }

   // $FF: synthetic method
   public final adp zzLO() throws CloneNotSupportedException {
      return (aei)this.clone();
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aei var2 = this;

      while(true) {
         int var4;
         int var6;
         byte[][] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzctM = var3.readBytes();
            continue;
         case 18:
            int var5 = ads.zzb(var3, 18);
            var7 = new byte[(var6 = var2.zzctO == null ? 0 : var2.zzctO.length) + var5][];
            if (var6 != 0) {
               System.arraycopy(var2.zzctO, 0, var7, 0, var6);
            }
            break;
         case 24:
            var2.zzctP = var3.zzLD();
            continue;
         case 34:
            var2.zzctN = var3.readString();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = var3.readBytes();
            var3.zzLA();
            ++var6;
         }

         var7[var6] = var3.readBytes();
         var2.zzctO = var7;
      }
   }

   // $FF: synthetic method
   public final Object clone() throws CloneNotSupportedException {
      return this.zzMc();
   }
}
