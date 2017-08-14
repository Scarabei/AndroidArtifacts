package com.google.android.gms.internal;

import java.io.IOException;

public final class zzba extends adj {
   private Long zzcx = null;
   private Integer zzcy = null;
   private Boolean zzcz = null;
   private int[] zzcA;
   private Long zzcB;

   public zzba() {
      this.zzcA = ads.zzcsC;
      this.zzcB = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcx != null) {
         var1.zzb(1, this.zzcx.longValue());
      }

      if (this.zzcy != null) {
         var1.zzr(2, this.zzcy.intValue());
      }

      if (this.zzcz != null) {
         var1.zzk(3, this.zzcz.booleanValue());
      }

      if (this.zzcA != null && this.zzcA.length > 0) {
         for(int var2 = 0; var2 < this.zzcA.length; ++var2) {
            var1.zzr(4, this.zzcA[var2]);
         }
      }

      if (this.zzcB != null) {
         var1.zza(5, this.zzcB.longValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcx != null) {
         var1 += adh.zze(1, this.zzcx.longValue());
      }

      if (this.zzcy != null) {
         var1 += adh.zzs(2, this.zzcy.intValue());
      }

      if (this.zzcz != null) {
         this.zzcz.booleanValue();
         var1 += adh.zzct(3) + 1;
      }

      if (this.zzcA != null && this.zzcA.length > 0) {
         int var2 = 0;

         for(int var3 = 0; var3 < this.zzcA.length; ++var3) {
            int var4 = this.zzcA[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzcA.length;
      }

      if (this.zzcB != null) {
         long var5 = this.zzcB.longValue();
         var1 += adh.zzct(5) + adh.zzaP(var5);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzba var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int[] var11;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzcx = var3.zzLG();
            continue;
         case 16:
            var2.zzcy = var3.zzLF();
            continue;
         case 24:
            var2.zzcz = var3.zzLD();
            continue;
         case 32:
            var5 = ads.zzb(var3, 32);
            var11 = new int[(var6 = var2.zzcA == null ? 0 : var2.zzcA.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcA, 0, var11, 0, var6);
            }
            break;
         case 34:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var7 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzcA == null ? 0 : var2.zzcA.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzcA, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzcA = var10;
            var3.zzco(var6);
            continue;
         case 40:
            var2.zzcB = var3.zzLG();
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
         var2.zzcA = var11;
      }
   }
}
