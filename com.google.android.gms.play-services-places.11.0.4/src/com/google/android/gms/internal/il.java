package com.google.android.gms.internal;

import java.io.IOException;

public final class il extends adj {
   public String[] zzbUS;
   public int[] zzbUT;
   public byte[][] zzbUU;

   public il() {
      this.zzbUS = ads.EMPTY_STRING_ARRAY;
      this.zzbUT = ads.zzcsC;
      this.zzbUU = ads.zzcsH;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof il)) {
         return false;
      } else {
         il var2 = (il)var1;
         if (!adn.equals(this.zzbUS, var2.zzbUS)) {
            return false;
         } else if (!adn.equals(this.zzbUT, var2.zzbUT)) {
            return false;
         } else if (!adn.zza(this.zzbUU, var2.zzbUU)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzbUS)) * 31 + adn.hashCode(this.zzbUT)) * 31 + adn.zzc(this.zzbUU)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      int var2;
      if (this.zzbUS != null && this.zzbUS.length > 0) {
         for(var2 = 0; var2 < this.zzbUS.length; ++var2) {
            String var3;
            if ((var3 = this.zzbUS[var2]) != null) {
               var1.zzl(1, var3);
            }
         }
      }

      if (this.zzbUT != null && this.zzbUT.length > 0) {
         for(var2 = 0; var2 < this.zzbUT.length; ++var2) {
            var1.zzr(2, this.zzbUT[var2]);
         }
      }

      if (this.zzbUU != null && this.zzbUU.length > 0) {
         for(var2 = 0; var2 < this.zzbUU.length; ++var2) {
            byte[] var4;
            if ((var4 = this.zzbUU[var2]) != null) {
               var1.zzb(3, var4);
            }
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      int var2;
      int var3;
      int var4;
      if (this.zzbUS != null && this.zzbUS.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzbUS.length; ++var4) {
            String var5;
            if ((var5 = this.zzbUS[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzbUT != null && this.zzbUT.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzbUT.length; ++var3) {
            var4 = this.zzbUT[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzbUT.length;
      }

      if (this.zzbUU != null && this.zzbUU.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzbUU.length; ++var4) {
            byte[] var6;
            if ((var6 = this.zzbUU[var4]) != null) {
               ++var2;
               var3 += adh.zzJ(var6);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      return var1;
   }

   public static il zzz(byte[] var0) throws ado {
      return (il)adp.zza(new il(), var0);
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      il var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         String[] var13;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var5 = ads.zzb(var3, 10);
            var13 = new String[(var6 = var2.zzbUS == null ? 0 : var2.zzbUS.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbUS, 0, var13, 0, var6);
            }
            break;
         case 16:
            var5 = ads.zzb(var3, 16);
            int[] var12 = new int[(var6 = var2.zzbUT == null ? 0 : var2.zzbUT.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbUT, 0, var12, 0, var6);
            }

            while(var6 < var12.length - 1) {
               var12[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var12[var6] = var3.zzLF();
            var2.zzbUT = var12;
            continue;
         case 18:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var11 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var11) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzbUT == null ? 0 : var2.zzbUT.length) + var11];
            if (var9 != 0) {
               System.arraycopy(var2.zzbUT, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzbUT = var10;
            var3.zzco(var6);
            continue;
         case 26:
            var5 = ads.zzb(var3, 26);
            byte[][] var7 = new byte[(var6 = var2.zzbUU == null ? 0 : var2.zzbUU.length) + var5][];
            if (var6 != 0) {
               System.arraycopy(var2.zzbUU, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readBytes();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readBytes();
            var2.zzbUU = var7;
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
         var2.zzbUS = var13;
      }
   }
}
