package com.google.android.gms.internal;

import java.io.IOException;

public final class acw extends adj {
   private int zzcqq = 0;
   private long zzcqr = 0L;
   private int[] zzcrv;
   private String[] zzcrw;
   private long zzcrr;
   private String[] zzcrx;
   private String zzcry;

   public acw() {
      this.zzcrv = ads.zzcsC;
      this.zzcrw = ads.EMPTY_STRING_ARRAY;
      this.zzcrr = 0L;
      this.zzcrx = ads.EMPTY_STRING_ARRAY;
      this.zzcry = "";
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acw)) {
         return false;
      } else {
         acw var2 = (acw)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (!adn.equals(this.zzcrv, var2.zzcrv)) {
            return false;
         } else if (!adn.equals(this.zzcrw, var2.zzcrw)) {
            return false;
         } else if (this.zzcrr != var2.zzcrr) {
            return false;
         } else if (!adn.equals(this.zzcrx, var2.zzcrx)) {
            return false;
         } else {
            if (this.zzcry == null) {
               if (var2.zzcry != null) {
                  return false;
               }
            } else if (!this.zzcry.equals(var2.zzcry)) {
               return false;
            }

            if (this.zzcso != null && !this.zzcso.isEmpty()) {
               return this.zzcso.equals(var2.zzcso);
            } else {
               return var2.zzcso == null || var2.zzcso.isEmpty();
            }
         }
      }
   }

   public final int hashCode() {
      return ((((((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + adn.hashCode(this.zzcrv)) * 31 + adn.hashCode(this.zzcrw)) * 31 + (int)(this.zzcrr ^ this.zzcrr >>> 32)) * 31 + adn.hashCode(this.zzcrx)) * 31 + (this.zzcry == null ? 0 : this.zzcry.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(2, this.zzcqr);
      }

      int var2;
      if (this.zzcrv != null && this.zzcrv.length > 0) {
         for(var2 = 0; var2 < this.zzcrv.length; ++var2) {
            var1.zzr(3, this.zzcrv[var2]);
         }
      }

      String var3;
      if (this.zzcrw != null && this.zzcrw.length > 0) {
         for(var2 = 0; var2 < this.zzcrw.length; ++var2) {
            if ((var3 = this.zzcrw[var2]) != null) {
               var1.zzl(4, var3);
            }
         }
      }

      if (this.zzcrr != 0L) {
         var1.zzb(5, this.zzcrr);
      }

      if (this.zzcrx != null && this.zzcrx.length > 0) {
         for(var2 = 0; var2 < this.zzcrx.length; ++var2) {
            if ((var3 = this.zzcrx[var2]) != null) {
               var1.zzl(6, var3);
            }
         }
      }

      if (this.zzcry != null && !this.zzcry.equals("")) {
         var1.zzl(7, this.zzcry);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1 += adh.zze(2, this.zzcqr);
      }

      int var2;
      int var3;
      int var4;
      if (this.zzcrv != null && this.zzcrv.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzcrv.length; ++var3) {
            var4 = this.zzcrv[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzcrv.length;
      }

      String var5;
      if (this.zzcrw != null && this.zzcrw.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzcrw.length; ++var4) {
            if ((var5 = this.zzcrw[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzcrr != 0L) {
         var1 += adh.zze(5, this.zzcrr);
      }

      if (this.zzcrx != null && this.zzcrx.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzcrx.length; ++var4) {
            if ((var5 = this.zzcrx[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzcry != null && !this.zzcry.equals("")) {
         var1 += adh.zzm(7, this.zzcry);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acw var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         String[] var7;
         int[] var12;
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
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var2.zzcqr = var3.zzLG();
            continue;
         case 24:
            var5 = ads.zzb(var3, 24);
            var12 = new int[(var6 = var2.zzcrv == null ? 0 : var2.zzcrv.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcrv, 0, var12, 0, var6);
            }
            break;
         case 26:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var11 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var11) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzcrv == null ? 0 : var2.zzcrv.length) + var11];
            if (var9 != 0) {
               System.arraycopy(var2.zzcrv, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzcrv = var10;
            var3.zzco(var6);
            continue;
         case 34:
            var5 = ads.zzb(var3, 34);
            var7 = new String[(var6 = var2.zzcrw == null ? 0 : var2.zzcrw.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcrw, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzcrw = var7;
            continue;
         case 40:
            var2.zzcrr = var3.zzLG();
            continue;
         case 50:
            var5 = ads.zzb(var3, 50);
            var7 = new String[(var6 = var2.zzcrx == null ? 0 : var2.zzcrx.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcrx, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzcrx = var7;
            continue;
         case 58:
            var2.zzcry = var3.readString();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var12.length - 1) {
            var12[var6] = var3.zzLF();
            var3.zzLA();
            ++var6;
         }

         var12[var6] = var3.zzLF();
         var2.zzcrv = var12;
      }
   }
}
