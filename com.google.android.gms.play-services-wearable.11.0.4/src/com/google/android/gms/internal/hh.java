package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class hh extends adj {
   public byte[] zzbTM;
   public String zzbTN;
   public double zzbTO;
   public float zzbTP;
   public long zzbTQ;
   public int zzbTR;
   public int zzbTS;
   public boolean zzbTT;
   public hf[] zzbTU;
   public hg[] zzbTV;
   public String[] zzbTW;
   public long[] zzbTX;
   public float[] zzbTY;
   public long zzbTZ;

   public hh() {
      this.zzbTM = ads.zzcsI;
      this.zzbTN = "";
      this.zzbTO = 0.0D;
      this.zzbTP = 0.0F;
      this.zzbTQ = 0L;
      this.zzbTR = 0;
      this.zzbTS = 0;
      this.zzbTT = false;
      this.zzbTU = hf.zzEa();
      this.zzbTV = hg.zzEb();
      this.zzbTW = ads.EMPTY_STRING_ARRAY;
      this.zzbTX = ads.zzcsD;
      this.zzbTY = ads.zzcsE;
      this.zzbTZ = 0L;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof hh)) {
         return false;
      } else {
         hh var2 = (hh)var1;
         if (!Arrays.equals(this.zzbTM, var2.zzbTM)) {
            return false;
         } else {
            if (this.zzbTN == null) {
               if (var2.zzbTN != null) {
                  return false;
               }
            } else if (!this.zzbTN.equals(var2.zzbTN)) {
               return false;
            }

            if (Double.doubleToLongBits(this.zzbTO) != Double.doubleToLongBits(var2.zzbTO)) {
               return false;
            } else if (Float.floatToIntBits(this.zzbTP) != Float.floatToIntBits(var2.zzbTP)) {
               return false;
            } else if (this.zzbTQ != var2.zzbTQ) {
               return false;
            } else if (this.zzbTR != var2.zzbTR) {
               return false;
            } else if (this.zzbTS != var2.zzbTS) {
               return false;
            } else if (this.zzbTT != var2.zzbTT) {
               return false;
            } else if (!adn.equals(this.zzbTU, var2.zzbTU)) {
               return false;
            } else if (!adn.equals(this.zzbTV, var2.zzbTV)) {
               return false;
            } else if (!adn.equals(this.zzbTW, var2.zzbTW)) {
               return false;
            } else if (!adn.equals(this.zzbTX, var2.zzbTX)) {
               return false;
            } else if (!adn.equals(this.zzbTY, var2.zzbTY)) {
               return false;
            } else if (this.zzbTZ != var2.zzbTZ) {
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
      int var1 = ((527 + this.getClass().getName().hashCode()) * 31 + Arrays.hashCode(this.zzbTM)) * 31 + (this.zzbTN == null ? 0 : this.zzbTN.hashCode());
      long var2 = Double.doubleToLongBits(this.zzbTO);
      return ((((((((((((var1 * 31 + (int)(var2 ^ var2 >>> 32)) * 31 + Float.floatToIntBits(this.zzbTP)) * 31 + (int)(this.zzbTQ ^ this.zzbTQ >>> 32)) * 31 + this.zzbTR) * 31 + this.zzbTS) * 31 + (this.zzbTT ? 1231 : 1237)) * 31 + adn.hashCode(this.zzbTU)) * 31 + adn.hashCode(this.zzbTV)) * 31 + adn.hashCode(this.zzbTW)) * 31 + adn.hashCode(this.zzbTX)) * 31 + adn.hashCode(this.zzbTY)) * 31 + (int)(this.zzbTZ ^ this.zzbTZ >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (!Arrays.equals(this.zzbTM, ads.zzcsI)) {
         var1.zzb(1, this.zzbTM);
      }

      if (this.zzbTN != null && !this.zzbTN.equals("")) {
         var1.zzl(2, this.zzbTN);
      }

      if (Double.doubleToLongBits(this.zzbTO) != Double.doubleToLongBits(0.0D)) {
         var1.zza(3, this.zzbTO);
      }

      if (Float.floatToIntBits(this.zzbTP) != Float.floatToIntBits(0.0F)) {
         var1.zzc(4, this.zzbTP);
      }

      if (this.zzbTQ != 0L) {
         var1.zzb(5, this.zzbTQ);
      }

      if (this.zzbTR != 0) {
         var1.zzr(6, this.zzbTR);
      }

      if (this.zzbTS != 0) {
         int var5 = this.zzbTS;
         var1.zzt(7, 0);
         var1.zzcu(adh.zzcw(var5));
      }

      if (this.zzbTT) {
         var1.zzk(8, this.zzbTT);
      }

      int var2;
      if (this.zzbTU != null && this.zzbTU.length > 0) {
         for(var2 = 0; var2 < this.zzbTU.length; ++var2) {
            hf var3;
            if ((var3 = this.zzbTU[var2]) != null) {
               var1.zza(9, var3);
            }
         }
      }

      if (this.zzbTV != null && this.zzbTV.length > 0) {
         for(var2 = 0; var2 < this.zzbTV.length; ++var2) {
            hg var7;
            if ((var7 = this.zzbTV[var2]) != null) {
               var1.zza(10, var7);
            }
         }
      }

      if (this.zzbTW != null && this.zzbTW.length > 0) {
         for(var2 = 0; var2 < this.zzbTW.length; ++var2) {
            String var8;
            if ((var8 = this.zzbTW[var2]) != null) {
               var1.zzl(11, var8);
            }
         }
      }

      if (this.zzbTX != null && this.zzbTX.length > 0) {
         for(var2 = 0; var2 < this.zzbTX.length; ++var2) {
            var1.zzb(12, this.zzbTX[var2]);
         }
      }

      if (this.zzbTZ != 0L) {
         var1.zzb(13, this.zzbTZ);
      }

      if (this.zzbTY != null && this.zzbTY.length > 0) {
         for(var2 = 0; var2 < this.zzbTY.length; ++var2) {
            var1.zzc(14, this.zzbTY[var2]);
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (!Arrays.equals(this.zzbTM, ads.zzcsI)) {
         var1 += adh.zzc(1, this.zzbTM);
      }

      if (this.zzbTN != null && !this.zzbTN.equals("")) {
         var1 += adh.zzm(2, this.zzbTN);
      }

      if (Double.doubleToLongBits(this.zzbTO) != Double.doubleToLongBits(0.0D)) {
         var1 += adh.zzct(3) + 8;
      }

      if (Float.floatToIntBits(this.zzbTP) != Float.floatToIntBits(0.0F)) {
         var1 += adh.zzct(4) + 4;
      }

      if (this.zzbTQ != 0L) {
         var1 += adh.zze(5, this.zzbTQ);
      }

      if (this.zzbTR != 0) {
         var1 += adh.zzs(6, this.zzbTR);
      }

      if (this.zzbTS != 0) {
         int var6 = this.zzbTS;
         var1 += adh.zzct(7) + adh.zzcv(adh.zzcw(var6));
      }

      if (this.zzbTT) {
         var1 += adh.zzct(8) + 1;
      }

      int var2;
      if (this.zzbTU != null && this.zzbTU.length > 0) {
         for(var2 = 0; var2 < this.zzbTU.length; ++var2) {
            hf var3;
            if ((var3 = this.zzbTU[var2]) != null) {
               var1 += adh.zzb(9, var3);
            }
         }
      }

      if (this.zzbTV != null && this.zzbTV.length > 0) {
         for(var2 = 0; var2 < this.zzbTV.length; ++var2) {
            hg var7;
            if ((var7 = this.zzbTV[var2]) != null) {
               var1 += adh.zzb(10, var7);
            }
         }
      }

      int var8;
      if (this.zzbTW != null && this.zzbTW.length > 0) {
         var2 = 0;
         var8 = 0;

         for(int var4 = 0; var4 < this.zzbTW.length; ++var4) {
            String var5;
            if ((var5 = this.zzbTW[var4]) != null) {
               ++var2;
               var8 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var8 + 1 * var2;
      }

      if (this.zzbTX != null && this.zzbTX.length > 0) {
         var2 = 0;

         for(var8 = 0; var8 < this.zzbTX.length; ++var8) {
            long var9 = this.zzbTX[var8];
            var2 += adh.zzaP(var9);
         }

         var1 = var1 + var2 + 1 * this.zzbTX.length;
      }

      if (this.zzbTZ != 0L) {
         var1 += adh.zze(13, this.zzbTZ);
      }

      if (this.zzbTY != null && this.zzbTY.length > 0) {
         var2 = 4 * this.zzbTY.length;
         var1 = var1 + var2 + 1 * this.zzbTY.length;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      hh var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int var8;
         int var12;
         hf[] var16;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzbTM = var3.readBytes();
            continue;
         case 18:
            var2.zzbTN = var3.readString();
            continue;
         case 25:
            var2.zzbTO = Double.longBitsToDouble(var3.zzLI());
            continue;
         case 37:
            var2.zzbTP = Float.intBitsToFloat(var3.zzLH());
            continue;
         case 40:
            var2.zzbTQ = var3.zzLG();
            continue;
         case 48:
            var2.zzbTR = var3.zzLF();
            continue;
         case 56:
            int var11;
            var2.zzbTS = (var11 = var3.zzLF()) >>> 1 ^ -(var11 & 1);
            continue;
         case 64:
            var2.zzbTT = var3.zzLD();
            continue;
         case 74:
            var5 = ads.zzb(var3, 74);
            var16 = new hf[(var6 = var2.zzbTU == null ? 0 : var2.zzbTU.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTU, 0, var16, 0, var6);
            }
            break;
         case 82:
            var5 = ads.zzb(var3, 82);
            hg[] var15 = new hg[(var6 = var2.zzbTV == null ? 0 : var2.zzbTV.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTV, 0, var15, 0, var6);
            }

            while(var6 < var15.length - 1) {
               var15[var6] = new hg();
               var3.zza(var15[var6]);
               var3.zzLA();
               ++var6;
            }

            var15[var6] = new hg();
            var3.zza(var15[var6]);
            var2.zzbTV = var15;
            continue;
         case 90:
            var5 = ads.zzb(var3, 90);
            String[] var14 = new String[(var6 = var2.zzbTW == null ? 0 : var2.zzbTW.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTW, 0, var14, 0, var6);
            }

            while(var6 < var14.length - 1) {
               var14[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var14[var6] = var3.readString();
            var2.zzbTW = var14;
            continue;
         case 96:
            var5 = ads.zzb(var3, 96);
            long[] var13 = new long[(var6 = var2.zzbTX == null ? 0 : var2.zzbTX.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTX, 0, var13, 0, var6);
            }

            while(var6 < var13.length - 1) {
               var13[var6] = var3.zzLG();
               var3.zzLA();
               ++var6;
            }

            var13[var6] = var3.zzLG();
            var2.zzbTX = var13;
            continue;
         case 98:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var12 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var12) {
               var3.zzLG();
            }

            var3.zzcp(var8);
            int var17;
            long[] var10 = new long[(var17 = var2.zzbTX == null ? 0 : var2.zzbTX.length) + var12];
            if (var17 != 0) {
               System.arraycopy(var2.zzbTX, 0, var10, 0, var17);
            }

            while(var17 < var10.length) {
               var10[var17] = var3.zzLG();
               ++var17;
            }

            var2.zzbTX = var10;
            var3.zzco(var6);
            continue;
         case 104:
            var2.zzbTZ = var3.zzLG();
            continue;
         case 114:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var12 = var5 / 4;
            float[] var9 = new float[(var8 = var2.zzbTY == null ? 0 : var2.zzbTY.length) + var12];
            if (var8 != 0) {
               System.arraycopy(var2.zzbTY, 0, var9, 0, var8);
            }

            while(var8 < var9.length) {
               var9[var8] = Float.intBitsToFloat(var3.zzLH());
               ++var8;
            }

            var2.zzbTY = var9;
            var3.zzco(var6);
            continue;
         case 117:
            var5 = ads.zzb(var3, 117);
            float[] var7 = new float[(var6 = var2.zzbTY == null ? 0 : var2.zzbTY.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTY, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = Float.intBitsToFloat(var3.zzLH());
               var3.zzLA();
               ++var6;
            }

            var7[var6] = Float.intBitsToFloat(var3.zzLH());
            var2.zzbTY = var7;
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var16.length - 1) {
            var16[var6] = new hf();
            var3.zza(var16[var6]);
            var3.zzLA();
            ++var6;
         }

         var16[var6] = new hf();
         var3.zza(var16[var6]);
         var2.zzbTU = var16;
      }
   }
}
