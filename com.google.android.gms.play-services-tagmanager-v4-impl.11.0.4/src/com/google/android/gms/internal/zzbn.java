package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbn extends adj {
   private String[] zzkN;
   public String[] zzkO;
   public zzbr[] zzkP;
   public zzbm[] zzkQ;
   public zzbj[] zzkR;
   public zzbj[] zzkS;
   public zzbj[] zzkT;
   public zzbo[] zzkU;
   private String zzkV;
   private String zzkW;
   private String zzkX;
   public String version;
   private zzbi zzkY;
   private float zzkZ;
   private boolean zzla;
   private String[] zzlb;
   public int zzlc;

   public zzbn() {
      this.zzkN = ads.EMPTY_STRING_ARRAY;
      this.zzkO = ads.EMPTY_STRING_ARRAY;
      this.zzkP = zzbr.zzu();
      this.zzkQ = zzbm.zzr();
      this.zzkR = zzbj.zzp();
      this.zzkS = zzbj.zzp();
      this.zzkT = zzbj.zzp();
      this.zzkU = zzbo.zzs();
      this.zzkV = "";
      this.zzkW = "";
      this.zzkX = "0";
      this.version = "";
      this.zzkY = null;
      this.zzkZ = 0.0F;
      this.zzla = false;
      this.zzlb = ads.EMPTY_STRING_ARRAY;
      this.zzlc = 0;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbn)) {
         return false;
      } else {
         zzbn var2 = (zzbn)var1;
         if (!adn.equals(this.zzkN, var2.zzkN)) {
            return false;
         } else if (!adn.equals(this.zzkO, var2.zzkO)) {
            return false;
         } else if (!adn.equals(this.zzkP, var2.zzkP)) {
            return false;
         } else if (!adn.equals(this.zzkQ, var2.zzkQ)) {
            return false;
         } else if (!adn.equals(this.zzkR, var2.zzkR)) {
            return false;
         } else if (!adn.equals(this.zzkS, var2.zzkS)) {
            return false;
         } else if (!adn.equals(this.zzkT, var2.zzkT)) {
            return false;
         } else if (!adn.equals(this.zzkU, var2.zzkU)) {
            return false;
         } else {
            if (this.zzkV == null) {
               if (var2.zzkV != null) {
                  return false;
               }
            } else if (!this.zzkV.equals(var2.zzkV)) {
               return false;
            }

            if (this.zzkW == null) {
               if (var2.zzkW != null) {
                  return false;
               }
            } else if (!this.zzkW.equals(var2.zzkW)) {
               return false;
            }

            if (this.zzkX == null) {
               if (var2.zzkX != null) {
                  return false;
               }
            } else if (!this.zzkX.equals(var2.zzkX)) {
               return false;
            }

            if (this.version == null) {
               if (var2.version != null) {
                  return false;
               }
            } else if (!this.version.equals(var2.version)) {
               return false;
            }

            if (this.zzkY == null) {
               if (var2.zzkY != null) {
                  return false;
               }
            } else if (!this.zzkY.equals(var2.zzkY)) {
               return false;
            }

            if (Float.floatToIntBits(this.zzkZ) != Float.floatToIntBits(var2.zzkZ)) {
               return false;
            } else if (this.zzla != var2.zzla) {
               return false;
            } else if (!adn.equals(this.zzlb, var2.zzlb)) {
               return false;
            } else if (this.zzlc != var2.zzlc) {
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
      return ((((((((((((((((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzkN)) * 31 + adn.hashCode(this.zzkO)) * 31 + adn.hashCode(this.zzkP)) * 31 + adn.hashCode(this.zzkQ)) * 31 + adn.hashCode(this.zzkR)) * 31 + adn.hashCode(this.zzkS)) * 31 + adn.hashCode(this.zzkT)) * 31 + adn.hashCode(this.zzkU)) * 31 + (this.zzkV == null ? 0 : this.zzkV.hashCode())) * 31 + (this.zzkW == null ? 0 : this.zzkW.hashCode())) * 31 + (this.zzkX == null ? 0 : this.zzkX.hashCode())) * 31 + (this.version == null ? 0 : this.version.hashCode())) * 31 + (this.zzkY == null ? 0 : this.zzkY.hashCode())) * 31 + Float.floatToIntBits(this.zzkZ)) * 31 + (this.zzla ? 1231 : 1237)) * 31 + adn.hashCode(this.zzlb)) * 31 + this.zzlc) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      int var2;
      String var3;
      if (this.zzkO != null && this.zzkO.length > 0) {
         for(var2 = 0; var2 < this.zzkO.length; ++var2) {
            if ((var3 = this.zzkO[var2]) != null) {
               var1.zzl(1, var3);
            }
         }
      }

      if (this.zzkP != null && this.zzkP.length > 0) {
         for(var2 = 0; var2 < this.zzkP.length; ++var2) {
            zzbr var4;
            if ((var4 = this.zzkP[var2]) != null) {
               var1.zza(2, var4);
            }
         }
      }

      if (this.zzkQ != null && this.zzkQ.length > 0) {
         for(var2 = 0; var2 < this.zzkQ.length; ++var2) {
            zzbm var5;
            if ((var5 = this.zzkQ[var2]) != null) {
               var1.zza(3, var5);
            }
         }
      }

      zzbj var6;
      if (this.zzkR != null && this.zzkR.length > 0) {
         for(var2 = 0; var2 < this.zzkR.length; ++var2) {
            if ((var6 = this.zzkR[var2]) != null) {
               var1.zza(4, var6);
            }
         }
      }

      if (this.zzkS != null && this.zzkS.length > 0) {
         for(var2 = 0; var2 < this.zzkS.length; ++var2) {
            if ((var6 = this.zzkS[var2]) != null) {
               var1.zza(5, var6);
            }
         }
      }

      if (this.zzkT != null && this.zzkT.length > 0) {
         for(var2 = 0; var2 < this.zzkT.length; ++var2) {
            if ((var6 = this.zzkT[var2]) != null) {
               var1.zza(6, var6);
            }
         }
      }

      if (this.zzkU != null && this.zzkU.length > 0) {
         for(var2 = 0; var2 < this.zzkU.length; ++var2) {
            zzbo var7;
            if ((var7 = this.zzkU[var2]) != null) {
               var1.zza(7, var7);
            }
         }
      }

      if (this.zzkV != null && !this.zzkV.equals("")) {
         var1.zzl(9, this.zzkV);
      }

      if (this.zzkW != null && !this.zzkW.equals("")) {
         var1.zzl(10, this.zzkW);
      }

      if (this.zzkX != null && !this.zzkX.equals("0")) {
         var1.zzl(12, this.zzkX);
      }

      if (this.version != null && !this.version.equals("")) {
         var1.zzl(13, this.version);
      }

      if (this.zzkY != null) {
         var1.zza(14, this.zzkY);
      }

      if (Float.floatToIntBits(this.zzkZ) != Float.floatToIntBits(0.0F)) {
         var1.zzc(15, this.zzkZ);
      }

      if (this.zzlb != null && this.zzlb.length > 0) {
         for(var2 = 0; var2 < this.zzlb.length; ++var2) {
            if ((var3 = this.zzlb[var2]) != null) {
               var1.zzl(16, var3);
            }
         }
      }

      if (this.zzlc != 0) {
         var1.zzr(17, this.zzlc);
      }

      if (this.zzla) {
         var1.zzk(18, this.zzla);
      }

      if (this.zzkN != null && this.zzkN.length > 0) {
         for(var2 = 0; var2 < this.zzkN.length; ++var2) {
            if ((var3 = this.zzkN[var2]) != null) {
               var1.zzl(19, var3);
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
      String var5;
      if (this.zzkO != null && this.zzkO.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzkO.length; ++var4) {
            if ((var5 = this.zzkO[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzkP != null && this.zzkP.length > 0) {
         for(var2 = 0; var2 < this.zzkP.length; ++var2) {
            zzbr var6;
            if ((var6 = this.zzkP[var2]) != null) {
               var1 += adh.zzb(2, var6);
            }
         }
      }

      if (this.zzkQ != null && this.zzkQ.length > 0) {
         for(var2 = 0; var2 < this.zzkQ.length; ++var2) {
            zzbm var7;
            if ((var7 = this.zzkQ[var2]) != null) {
               var1 += adh.zzb(3, var7);
            }
         }
      }

      zzbj var8;
      if (this.zzkR != null && this.zzkR.length > 0) {
         for(var2 = 0; var2 < this.zzkR.length; ++var2) {
            if ((var8 = this.zzkR[var2]) != null) {
               var1 += adh.zzb(4, var8);
            }
         }
      }

      if (this.zzkS != null && this.zzkS.length > 0) {
         for(var2 = 0; var2 < this.zzkS.length; ++var2) {
            if ((var8 = this.zzkS[var2]) != null) {
               var1 += adh.zzb(5, var8);
            }
         }
      }

      if (this.zzkT != null && this.zzkT.length > 0) {
         for(var2 = 0; var2 < this.zzkT.length; ++var2) {
            if ((var8 = this.zzkT[var2]) != null) {
               var1 += adh.zzb(6, var8);
            }
         }
      }

      if (this.zzkU != null && this.zzkU.length > 0) {
         for(var2 = 0; var2 < this.zzkU.length; ++var2) {
            zzbo var9;
            if ((var9 = this.zzkU[var2]) != null) {
               var1 += adh.zzb(7, var9);
            }
         }
      }

      if (this.zzkV != null && !this.zzkV.equals("")) {
         var1 += adh.zzm(9, this.zzkV);
      }

      if (this.zzkW != null && !this.zzkW.equals("")) {
         var1 += adh.zzm(10, this.zzkW);
      }

      if (this.zzkX != null && !this.zzkX.equals("0")) {
         var1 += adh.zzm(12, this.zzkX);
      }

      if (this.version != null && !this.version.equals("")) {
         var1 += adh.zzm(13, this.version);
      }

      if (this.zzkY != null) {
         var1 += adh.zzb(14, this.zzkY);
      }

      if (Float.floatToIntBits(this.zzkZ) != Float.floatToIntBits(0.0F)) {
         var1 += adh.zzct(15) + 4;
      }

      if (this.zzlb != null && this.zzlb.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzlb.length; ++var4) {
            if ((var5 = this.zzlb[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 2 * var2;
      }

      if (this.zzlc != 0) {
         var1 += adh.zzs(17, this.zzlc);
      }

      if (this.zzla) {
         var1 += adh.zzct(18) + 1;
      }

      if (this.zzkN != null && this.zzkN.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzkN.length; ++var4) {
            if ((var5 = this.zzkN[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 2 * var2;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbn var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         String[] var7;
         zzbj[] var9;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var5 = ads.zzb(var3, 10);
            var7 = new String[(var6 = var2.zzkO == null ? 0 : var2.zzkO.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkO, 0, var7, 0, var6);
            }
            break;
         case 18:
            var5 = ads.zzb(var3, 18);
            zzbr[] var11 = new zzbr[(var6 = var2.zzkP == null ? 0 : var2.zzkP.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkP, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = new zzbr();
               var3.zza(var11[var6]);
               var3.zzLA();
               ++var6;
            }

            var11[var6] = new zzbr();
            var3.zza(var11[var6]);
            var2.zzkP = var11;
            continue;
         case 26:
            var5 = ads.zzb(var3, 26);
            zzbm[] var10 = new zzbm[(var6 = var2.zzkQ == null ? 0 : var2.zzkQ.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkQ, 0, var10, 0, var6);
            }

            while(var6 < var10.length - 1) {
               var10[var6] = new zzbm();
               var3.zza(var10[var6]);
               var3.zzLA();
               ++var6;
            }

            var10[var6] = new zzbm();
            var3.zza(var10[var6]);
            var2.zzkQ = var10;
            continue;
         case 34:
            var5 = ads.zzb(var3, 34);
            var9 = new zzbj[(var6 = var2.zzkR == null ? 0 : var2.zzkR.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkR, 0, var9, 0, var6);
            }

            while(var6 < var9.length - 1) {
               var9[var6] = new zzbj();
               var3.zza(var9[var6]);
               var3.zzLA();
               ++var6;
            }

            var9[var6] = new zzbj();
            var3.zza(var9[var6]);
            var2.zzkR = var9;
            continue;
         case 42:
            var5 = ads.zzb(var3, 42);
            var9 = new zzbj[(var6 = var2.zzkS == null ? 0 : var2.zzkS.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkS, 0, var9, 0, var6);
            }

            while(var6 < var9.length - 1) {
               var9[var6] = new zzbj();
               var3.zza(var9[var6]);
               var3.zzLA();
               ++var6;
            }

            var9[var6] = new zzbj();
            var3.zza(var9[var6]);
            var2.zzkS = var9;
            continue;
         case 50:
            var5 = ads.zzb(var3, 50);
            var9 = new zzbj[(var6 = var2.zzkT == null ? 0 : var2.zzkT.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkT, 0, var9, 0, var6);
            }

            while(var6 < var9.length - 1) {
               var9[var6] = new zzbj();
               var3.zza(var9[var6]);
               var3.zzLA();
               ++var6;
            }

            var9[var6] = new zzbj();
            var3.zza(var9[var6]);
            var2.zzkT = var9;
            continue;
         case 58:
            var5 = ads.zzb(var3, 58);
            zzbo[] var8 = new zzbo[(var6 = var2.zzkU == null ? 0 : var2.zzkU.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkU, 0, var8, 0, var6);
            }

            while(var6 < var8.length - 1) {
               var8[var6] = new zzbo();
               var3.zza(var8[var6]);
               var3.zzLA();
               ++var6;
            }

            var8[var6] = new zzbo();
            var3.zza(var8[var6]);
            var2.zzkU = var8;
            continue;
         case 74:
            var2.zzkV = var3.readString();
            continue;
         case 82:
            var2.zzkW = var3.readString();
            continue;
         case 98:
            var2.zzkX = var3.readString();
            continue;
         case 106:
            var2.version = var3.readString();
            continue;
         case 114:
            if (var2.zzkY == null) {
               var2.zzkY = new zzbi();
            }

            var3.zza(var2.zzkY);
            continue;
         case 125:
            var2.zzkZ = Float.intBitsToFloat(var3.zzLH());
            continue;
         case 130:
            var5 = ads.zzb(var3, 130);
            var7 = new String[(var6 = var2.zzlb == null ? 0 : var2.zzlb.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlb, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzlb = var7;
            continue;
         case 136:
            var2.zzlc = var3.zzLF();
            continue;
         case 144:
            var2.zzla = var3.zzLD();
            continue;
         case 154:
            var5 = ads.zzb(var3, 154);
            var7 = new String[(var6 = var2.zzkN == null ? 0 : var2.zzkN.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkN, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzkN = var7;
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = var3.readString();
            var3.zzLA();
            ++var6;
         }

         var7[var6] = var3.readString();
         var2.zzkO = var7;
      }
   }
}
