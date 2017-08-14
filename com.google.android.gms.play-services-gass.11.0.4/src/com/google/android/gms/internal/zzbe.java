package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbe extends adj {
   public byte[][] zzcJ;
   public byte[] zzcE;
   private Integer zzcK;
   private Integer zzcL;

   public zzbe() {
      this.zzcJ = ads.zzcsH;
      this.zzcE = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcJ != null && this.zzcJ.length > 0) {
         for(int var2 = 0; var2 < this.zzcJ.length; ++var2) {
            byte[] var3;
            if ((var3 = this.zzcJ[var2]) != null) {
               var1.zzb(1, var3);
            }
         }
      }

      if (this.zzcE != null) {
         var1.zzb(2, this.zzcE);
      }

      if (this.zzcK != null) {
         var1.zzr(3, this.zzcK.intValue());
      }

      if (this.zzcL != null) {
         var1.zzr(4, this.zzcL.intValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcJ != null && this.zzcJ.length > 0) {
         int var2 = 0;
         int var3 = 0;

         for(int var4 = 0; var4 < this.zzcJ.length; ++var4) {
            byte[] var5;
            if ((var5 = this.zzcJ[var4]) != null) {
               ++var2;
               var3 += adh.zzJ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzcE != null) {
         var1 += adh.zzc(2, this.zzcE);
      }

      if (this.zzcK != null) {
         var1 += adh.zzs(3, this.zzcK.intValue());
      }

      if (this.zzcL != null) {
         var1 += adh.zzs(4, this.zzcL.intValue());
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbe var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         byte[][] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var5 = ads.zzb(var3, 10);
            var7 = new byte[(var6 = var2.zzcJ == null ? 0 : var2.zzcJ.length) + var5][];
            if (var6 != 0) {
               System.arraycopy(var2.zzcJ, 0, var7, 0, var6);
            }
            break;
         case 18:
            var2.zzcE = var3.readBytes();
            continue;
         case 24:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
               var2.zzcK = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 32:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcL = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
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
         var2.zzcJ = var7;
      }
   }
}
