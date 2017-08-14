package com.google.android.gms.internal;

import java.io.IOException;

public final class acl extends adj {
   private static adk zzcqB = adk.zza(11, acl.class, 855033290L);
   private static final acl[] zzcqC = new acl[0];
   public String zzcqD = "";
   public aco zzcqE = null;
   private int zzcqF = 0;
   private int zzcqG = 0;
   private int zzcqH = 0;
   private adc zzcqI = null;
   private acm zzcqJ = null;
   private String[] zzcqK;
   private acr zzcqL;

   public acl() {
      this.zzcqK = ads.EMPTY_STRING_ARRAY;
      this.zzcqL = null;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acl)) {
         return false;
      } else {
         acl var2 = (acl)var1;
         if (this.zzcqD == null) {
            if (var2.zzcqD != null) {
               return false;
            }
         } else if (!this.zzcqD.equals(var2.zzcqD)) {
            return false;
         }

         if (this.zzcqE == null) {
            if (var2.zzcqE != null) {
               return false;
            }
         } else if (!this.zzcqE.equals(var2.zzcqE)) {
            return false;
         }

         if (this.zzcqF != var2.zzcqF) {
            return false;
         } else if (this.zzcqG != var2.zzcqG) {
            return false;
         } else if (this.zzcqH != var2.zzcqH) {
            return false;
         } else {
            if (this.zzcqI == null) {
               if (var2.zzcqI != null) {
                  return false;
               }
            } else if (!this.zzcqI.equals(var2.zzcqI)) {
               return false;
            }

            if (this.zzcqJ == null) {
               if (var2.zzcqJ != null) {
                  return false;
               }
            } else if (!this.zzcqJ.equals(var2.zzcqJ)) {
               return false;
            }

            if (!adn.equals(this.zzcqK, var2.zzcqK)) {
               return false;
            } else {
               if (this.zzcqL == null) {
                  if (var2.zzcqL != null) {
                     return false;
                  }
               } else if (!this.zzcqL.equals(var2.zzcqL)) {
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
   }

   public final int hashCode() {
      return ((((((((((527 + this.getClass().getName().hashCode()) * 31 + (this.zzcqD == null ? 0 : this.zzcqD.hashCode())) * 31 + (this.zzcqE == null ? 0 : this.zzcqE.hashCode())) * 31 + this.zzcqF) * 31 + this.zzcqG) * 31 + this.zzcqH) * 31 + (this.zzcqI == null ? 0 : this.zzcqI.hashCode())) * 31 + (this.zzcqJ == null ? 0 : this.zzcqJ.hashCode())) * 31 + adn.hashCode(this.zzcqK)) * 31 + (this.zzcqL == null ? 0 : this.zzcqL.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqD != null && !this.zzcqD.equals("")) {
         var1.zzl(1, this.zzcqD);
      }

      if (this.zzcqE != null) {
         var1.zza(2, this.zzcqE);
      }

      if (this.zzcqF != 0) {
         var1.zzr(4, this.zzcqF);
      }

      if (this.zzcqG != 0) {
         var1.zzr(5, this.zzcqG);
      }

      if (this.zzcqH != 0) {
         var1.zzr(6, this.zzcqH);
      }

      if (this.zzcqI != null) {
         var1.zza(7, this.zzcqI);
      }

      if (this.zzcqJ != null) {
         var1.zza(8, this.zzcqJ);
      }

      if (this.zzcqK != null && this.zzcqK.length > 0) {
         for(int var2 = 0; var2 < this.zzcqK.length; ++var2) {
            String var3;
            if ((var3 = this.zzcqK[var2]) != null) {
               var1.zzl(9, var3);
            }
         }
      }

      if (this.zzcqL != null) {
         var1.zza(10, this.zzcqL);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqD != null && !this.zzcqD.equals("")) {
         var1 += adh.zzm(1, this.zzcqD);
      }

      if (this.zzcqE != null) {
         var1 += adh.zzb(2, this.zzcqE);
      }

      if (this.zzcqF != 0) {
         var1 += adh.zzs(4, this.zzcqF);
      }

      if (this.zzcqG != 0) {
         var1 += adh.zzs(5, this.zzcqG);
      }

      if (this.zzcqH != 0) {
         var1 += adh.zzs(6, this.zzcqH);
      }

      if (this.zzcqI != null) {
         var1 += adh.zzb(7, this.zzcqI);
      }

      if (this.zzcqJ != null) {
         var1 += adh.zzb(8, this.zzcqJ);
      }

      if (this.zzcqK != null && this.zzcqK.length > 0) {
         int var2 = 0;
         int var3 = 0;

         for(int var4 = 0; var4 < this.zzcqK.length; ++var4) {
            String var5;
            if ((var5 = this.zzcqK[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      if (this.zzcqL != null) {
         var1 += adh.zzb(10, this.zzcqL);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acl var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         String[] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzcqD = var3.readString();
            continue;
         case 18:
            if (var2.zzcqE == null) {
               var2.zzcqE = new aco();
            }

            var3.zza(var2.zzcqE);
            continue;
         case 32:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
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
               var2.zzcqF = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 40:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
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
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 10000:
            case 10001:
            case 10002:
            case 10003:
            case 10004:
            case 10005:
            case 10006:
            case 10007:
            case 10008:
            case 10009:
            case 99999:
               var2.zzcqG = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 48:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
            case 3:
               var2.zzcqH = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 58:
            if (var2.zzcqI == null) {
               var2.zzcqI = new adc();
            }

            var3.zza(var2.zzcqI);
            continue;
         case 66:
            if (var2.zzcqJ == null) {
               var2.zzcqJ = new acm();
            }

            var3.zza(var2.zzcqJ);
            continue;
         case 74:
            var5 = ads.zzb(var3, 74);
            var7 = new String[(var6 = var2.zzcqK == null ? 0 : var2.zzcqK.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcqK, 0, var7, 0, var6);
            }
            break;
         case 82:
            if (var2.zzcqL == null) {
               var2.zzcqL = new acr();
            }

            var3.zza(var2.zzcqL);
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
         var2.zzcqK = var7;
      }
   }
}
