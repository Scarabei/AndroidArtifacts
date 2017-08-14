package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbr extends adj {
   private static volatile zzbr[] zzlD;
   public int type = 1;
   public String string = "";
   public zzbr[] zzlE = zzu();
   public zzbr[] zzlF = zzu();
   public zzbr[] zzlG = zzu();
   public String zzlH = "";
   public String zzlI = "";
   public long zzlJ = 0L;
   public boolean zzlK = false;
   public zzbr[] zzlL = zzu();
   public int[] zzlM;
   public boolean zzlN;

   public static zzbr[] zzu() {
      if (zzlD == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzlD == null) {
               zzlD = new zzbr[0];
            }
         }
      }

      return zzlD;
   }

   public zzbr() {
      this.zzlM = ads.zzcsC;
      this.zzlN = false;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbr)) {
         return false;
      } else {
         zzbr var2 = (zzbr)var1;
         if (this.type != var2.type) {
            return false;
         } else {
            if (this.string == null) {
               if (var2.string != null) {
                  return false;
               }
            } else if (!this.string.equals(var2.string)) {
               return false;
            }

            if (!adn.equals(this.zzlE, var2.zzlE)) {
               return false;
            } else if (!adn.equals(this.zzlF, var2.zzlF)) {
               return false;
            } else if (!adn.equals(this.zzlG, var2.zzlG)) {
               return false;
            } else {
               if (this.zzlH == null) {
                  if (var2.zzlH != null) {
                     return false;
                  }
               } else if (!this.zzlH.equals(var2.zzlH)) {
                  return false;
               }

               if (this.zzlI == null) {
                  if (var2.zzlI != null) {
                     return false;
                  }
               } else if (!this.zzlI.equals(var2.zzlI)) {
                  return false;
               }

               if (this.zzlJ != var2.zzlJ) {
                  return false;
               } else if (this.zzlK != var2.zzlK) {
                  return false;
               } else if (!adn.equals(this.zzlL, var2.zzlL)) {
                  return false;
               } else if (!adn.equals(this.zzlM, var2.zzlM)) {
                  return false;
               } else if (this.zzlN != var2.zzlN) {
                  return false;
               } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
                  return this.zzcso.equals(var2.zzcso);
               } else {
                  return var2.zzcso == null || var2.zzcso.isEmpty();
               }
            }
         }
      }
   }

   public final int hashCode() {
      return (((((((((((((527 + this.getClass().getName().hashCode()) * 31 + this.type) * 31 + (this.string == null ? 0 : this.string.hashCode())) * 31 + adn.hashCode(this.zzlE)) * 31 + adn.hashCode(this.zzlF)) * 31 + adn.hashCode(this.zzlG)) * 31 + (this.zzlH == null ? 0 : this.zzlH.hashCode())) * 31 + (this.zzlI == null ? 0 : this.zzlI.hashCode())) * 31 + (int)(this.zzlJ ^ this.zzlJ >>> 32)) * 31 + (this.zzlK ? 1231 : 1237)) * 31 + adn.hashCode(this.zzlL)) * 31 + adn.hashCode(this.zzlM)) * 31 + (this.zzlN ? 1231 : 1237)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.type);
      if (this.string != null && !this.string.equals("")) {
         var1.zzl(2, this.string);
      }

      int var2;
      zzbr var3;
      if (this.zzlE != null && this.zzlE.length > 0) {
         for(var2 = 0; var2 < this.zzlE.length; ++var2) {
            if ((var3 = this.zzlE[var2]) != null) {
               var1.zza(3, var3);
            }
         }
      }

      if (this.zzlF != null && this.zzlF.length > 0) {
         for(var2 = 0; var2 < this.zzlF.length; ++var2) {
            if ((var3 = this.zzlF[var2]) != null) {
               var1.zza(4, var3);
            }
         }
      }

      if (this.zzlG != null && this.zzlG.length > 0) {
         for(var2 = 0; var2 < this.zzlG.length; ++var2) {
            if ((var3 = this.zzlG[var2]) != null) {
               var1.zza(5, var3);
            }
         }
      }

      if (this.zzlH != null && !this.zzlH.equals("")) {
         var1.zzl(6, this.zzlH);
      }

      if (this.zzlI != null && !this.zzlI.equals("")) {
         var1.zzl(7, this.zzlI);
      }

      if (this.zzlJ != 0L) {
         var1.zzb(8, this.zzlJ);
      }

      if (this.zzlN) {
         var1.zzk(9, this.zzlN);
      }

      if (this.zzlM != null && this.zzlM.length > 0) {
         for(var2 = 0; var2 < this.zzlM.length; ++var2) {
            var1.zzr(10, this.zzlM[var2]);
         }
      }

      if (this.zzlL != null && this.zzlL.length > 0) {
         for(var2 = 0; var2 < this.zzlL.length; ++var2) {
            if ((var3 = this.zzlL[var2]) != null) {
               var1.zza(11, var3);
            }
         }
      }

      if (this.zzlK) {
         var1.zzk(12, this.zzlK);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzs(1, this.type);
      if (this.string != null && !this.string.equals("")) {
         var1 += adh.zzm(2, this.string);
      }

      int var2;
      zzbr var3;
      if (this.zzlE != null && this.zzlE.length > 0) {
         for(var2 = 0; var2 < this.zzlE.length; ++var2) {
            if ((var3 = this.zzlE[var2]) != null) {
               var1 += adh.zzb(3, var3);
            }
         }
      }

      if (this.zzlF != null && this.zzlF.length > 0) {
         for(var2 = 0; var2 < this.zzlF.length; ++var2) {
            if ((var3 = this.zzlF[var2]) != null) {
               var1 += adh.zzb(4, var3);
            }
         }
      }

      if (this.zzlG != null && this.zzlG.length > 0) {
         for(var2 = 0; var2 < this.zzlG.length; ++var2) {
            if ((var3 = this.zzlG[var2]) != null) {
               var1 += adh.zzb(5, var3);
            }
         }
      }

      if (this.zzlH != null && !this.zzlH.equals("")) {
         var1 += adh.zzm(6, this.zzlH);
      }

      if (this.zzlI != null && !this.zzlI.equals("")) {
         var1 += adh.zzm(7, this.zzlI);
      }

      if (this.zzlJ != 0L) {
         var1 += adh.zze(8, this.zzlJ);
      }

      if (this.zzlN) {
         var1 += adh.zzct(9) + 1;
      }

      if (this.zzlM != null && this.zzlM.length > 0) {
         var2 = 0;

         for(int var5 = 0; var5 < this.zzlM.length; ++var5) {
            int var4 = this.zzlM[var5];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlM.length;
      }

      if (this.zzlL != null && this.zzlL.length > 0) {
         for(var2 = 0; var2 < this.zzlL.length; ++var2) {
            if ((var3 = this.zzlL[var2]) != null) {
               var1 += adh.zzb(11, var3);
            }
         }
      }

      if (this.zzlK) {
         var1 += adh.zzct(12) + 1;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbr var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         zzbr[] var7;
         int var8;
         int var9;
         int var14;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
               var2.type = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 18:
            var2.string = var3.readString();
            continue;
         case 26:
            var5 = ads.zzb(var3, 26);
            var7 = new zzbr[(var6 = var2.zzlE == null ? 0 : var2.zzlE.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlE, 0, var7, 0, var6);
            }
            break;
         case 34:
            var5 = ads.zzb(var3, 34);
            var7 = new zzbr[(var6 = var2.zzlF == null ? 0 : var2.zzlF.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlF, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = new zzbr();
               var3.zza(var7[var6]);
               var3.zzLA();
               ++var6;
            }

            var7[var6] = new zzbr();
            var3.zza(var7[var6]);
            var2.zzlF = var7;
            continue;
         case 42:
            var5 = ads.zzb(var3, 42);
            var7 = new zzbr[(var6 = var2.zzlG == null ? 0 : var2.zzlG.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlG, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = new zzbr();
               var3.zza(var7[var6]);
               var3.zzLA();
               ++var6;
            }

            var7[var6] = new zzbr();
            var3.zza(var7[var6]);
            var2.zzlG = var7;
            continue;
         case 50:
            var2.zzlH = var3.readString();
            continue;
         case 58:
            var2.zzlI = var3.readString();
            continue;
         case 64:
            var2.zzlJ = var3.zzLG();
            continue;
         case 72:
            var2.zzlN = var3.zzLD();
            continue;
         case 80:
            int[] var13 = new int[var5 = ads.zzb(var3, 80)];
            var14 = 0;

            for(var8 = 0; var8 < var5; ++var8) {
               if (var8 != 0) {
                  var3.zzLA();
               }

               var9 = var3.getPosition();
               int var16;
               switch(var16 = var3.zzLF()) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
                  var13[var14++] = var16;
                  break;
               default:
                  var3.zzcp(var9);
                  var2.zza(var3, var4);
               }
            }

            if (var14 == 0) {
               continue;
            }

            if ((var8 = var2.zzlM == null ? 0 : var2.zzlM.length) == 0 && var14 == var13.length) {
               var2.zzlM = var13;
               continue;
            }

            int[] var15 = new int[var8 + var14];
            if (var8 != 0) {
               System.arraycopy(var2.zzlM, 0, var15, 0, var8);
            }

            System.arraycopy(var13, 0, var15, var8, var14);
            var2.zzlM = var15;
            continue;
         case 82:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var14 = 0;
            var8 = var3.getPosition();

            while(var3.zzLK() > 0) {
               switch(var3.zzLF()) {
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 11:
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
                  ++var14;
               }
            }

            if (var14 != 0) {
               var3.zzcp(var8);
               int[] var10 = new int[(var9 = var2.zzlM == null ? 0 : var2.zzlM.length) + var14];
               if (var9 != 0) {
                  System.arraycopy(var2.zzlM, 0, var10, 0, var9);
               }

               while(var3.zzLK() > 0) {
                  int var11 = var3.getPosition();
                  int var12;
                  switch(var12 = var3.zzLF()) {
                  case 1:
                  case 2:
                  case 3:
                  case 4:
                  case 5:
                  case 6:
                  case 7:
                  case 8:
                  case 9:
                  case 10:
                  case 11:
                  case 12:
                  case 13:
                  case 14:
                  case 15:
                  case 16:
                  case 17:
                     var10[var9++] = var12;
                     break;
                  default:
                     var3.zzcp(var11);
                     var2.zza(var3, 80);
                  }
               }

               var2.zzlM = var10;
            }

            var3.zzco(var6);
            continue;
         case 90:
            var5 = ads.zzb(var3, 90);
            var7 = new zzbr[(var6 = var2.zzlL == null ? 0 : var2.zzlL.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlL, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = new zzbr();
               var3.zza(var7[var6]);
               var3.zzLA();
               ++var6;
            }

            var7[var6] = new zzbr();
            var3.zza(var7[var6]);
            var2.zzlL = var7;
            continue;
         case 96:
            var2.zzlK = var3.zzLD();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new zzbr();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new zzbr();
         var3.zza(var7[var6]);
         var2.zzlE = var7;
      }
   }
}
