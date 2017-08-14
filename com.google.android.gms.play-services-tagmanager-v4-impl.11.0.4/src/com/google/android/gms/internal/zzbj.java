package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbj extends adj {
   private static volatile zzbj[] zzkz;
   public int[] zzkA;
   private int zzkB;
   private int name;
   private boolean zzkC;
   private boolean zzkD;

   public static zzbj[] zzp() {
      if (zzkz == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzkz == null) {
               zzkz = new zzbj[0];
            }
         }
      }

      return zzkz;
   }

   public zzbj() {
      this.zzkA = ads.zzcsC;
      this.zzkB = 0;
      this.name = 0;
      this.zzkC = false;
      this.zzkD = false;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbj)) {
         return false;
      } else {
         zzbj var2 = (zzbj)var1;
         if (!adn.equals(this.zzkA, var2.zzkA)) {
            return false;
         } else if (this.zzkB != var2.zzkB) {
            return false;
         } else if (this.name != var2.name) {
            return false;
         } else if (this.zzkC != var2.zzkC) {
            return false;
         } else if (this.zzkD != var2.zzkD) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzkA)) * 31 + this.zzkB) * 31 + this.name) * 31 + (this.zzkC ? 1231 : 1237)) * 31 + (this.zzkD ? 1231 : 1237)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzkD) {
         var1.zzk(1, this.zzkD);
      }

      var1.zzr(2, this.zzkB);
      if (this.zzkA != null && this.zzkA.length > 0) {
         for(int var2 = 0; var2 < this.zzkA.length; ++var2) {
            var1.zzr(3, this.zzkA[var2]);
         }
      }

      if (this.name != 0) {
         var1.zzr(4, this.name);
      }

      if (this.zzkC) {
         var1.zzk(6, this.zzkC);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzkD) {
         var1 += adh.zzct(1) + 1;
      }

      var1 += adh.zzs(2, this.zzkB);
      if (this.zzkA != null && this.zzkA.length > 0) {
         int var2 = 0;

         for(int var3 = 0; var3 < this.zzkA.length; ++var3) {
            int var4 = this.zzkA[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzkA.length;
      }

      if (this.name != 0) {
         var1 += adh.zzs(4, this.name);
      }

      if (this.zzkC) {
         var1 += adh.zzct(6) + 1;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbj var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int[] var11;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzkD = var3.zzLD();
            continue;
         case 16:
            var2.zzkB = var3.zzLF();
            continue;
         case 24:
            var5 = ads.zzb(var3, 24);
            var11 = new int[(var6 = var2.zzkA == null ? 0 : var2.zzkA.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkA, 0, var11, 0, var6);
            }
            break;
         case 26:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var7 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzkA == null ? 0 : var2.zzkA.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzkA, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzkA = var10;
            var3.zzco(var6);
            continue;
         case 32:
            var2.name = var3.zzLF();
            continue;
         case 48:
            var2.zzkC = var3.zzLD();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var11.length - 1) {
            var11[var6] = var3.zzLF();
            var3.zzLA();
            ++var6;
         }

         var11[var6] = var3.zzLF();
         var2.zzkA = var11;
      }
   }
}
