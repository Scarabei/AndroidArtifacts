package com.google.android.gms.internal;

import java.io.IOException;

public final class act extends adj {
   private int zzcqq = 0;
   private long zzcqr = 0L;
   private int[] zzcrs;
   private int[] zzcrt;

   public act() {
      this.zzcrs = ads.zzcsC;
      this.zzcrt = ads.zzcsC;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof act)) {
         return false;
      } else {
         act var2 = (act)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (!adn.equals(this.zzcrs, var2.zzcrs)) {
            return false;
         } else if (!adn.equals(this.zzcrt, var2.zzcrt)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + adn.hashCode(this.zzcrs)) * 31 + adn.hashCode(this.zzcrt)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(2, this.zzcqr);
      }

      int var2;
      if (this.zzcrs != null && this.zzcrs.length > 0) {
         for(var2 = 0; var2 < this.zzcrs.length; ++var2) {
            var1.zzr(3, this.zzcrs[var2]);
         }
      }

      if (this.zzcrt != null && this.zzcrt.length > 0) {
         for(var2 = 0; var2 < this.zzcrt.length; ++var2) {
            var1.zzr(4, this.zzcrt[var2]);
         }
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
      if (this.zzcrs != null && this.zzcrs.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzcrs.length; ++var3) {
            var4 = this.zzcrs[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzcrs.length;
      }

      if (this.zzcrt != null && this.zzcrt.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzcrt.length; ++var3) {
            var4 = this.zzcrt[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzcrt.length;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      act var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int var7;
         int var8;
         int var9;
         int[] var10;
         int var11;
         int var12;
         int[] var13;
         int[] var14;
         int var15;
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
            case 5:
            case 6:
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var2.zzcqr = var3.zzLG();
            break;
         case 24:
            var13 = new int[var5 = ads.zzb(var3, 24)];
            var7 = 0;
            var8 = 0;

            for(; var8 < var5; ++var8) {
               if (var8 != 0) {
                  var3.zzLA();
               }

               var9 = var3.getPosition();
               switch(var15 = var3.zzLF()) {
               case 0:
               case 1:
               case 2:
               case 3:
                  var13[var7++] = var15;
                  break;
               default:
                  var3.zzcp(var9);
                  var2.zza(var3, var4);
               }
            }

            if (var7 != 0) {
               if ((var8 = var2.zzcrs == null ? 0 : var2.zzcrs.length) == 0 && var7 == var13.length) {
                  var2.zzcrs = var13;
               } else {
                  var14 = new int[var8 + var7];
                  if (var8 != 0) {
                     System.arraycopy(var2.zzcrs, 0, var14, 0, var8);
                  }

                  System.arraycopy(var13, 0, var14, var8, var7);
                  var2.zzcrs = var14;
               }
            }
            break;
         case 26:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;
            var8 = var3.getPosition();

            while(var3.zzLK() > 0) {
               switch(var3.zzLF()) {
               case 0:
               case 1:
               case 2:
               case 3:
                  ++var7;
               }
            }

            if (var7 != 0) {
               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzcrs == null ? 0 : var2.zzcrs.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzcrs, 0, var10, 0, var9);
               }

               while(var3.zzLK() > 0) {
                  var11 = var3.getPosition();
                  switch(var12 = var3.zzLF()) {
                  case 0:
                  case 1:
                  case 2:
                  case 3:
                     var10[var9++] = var12;
                     break;
                  default:
                     var3.zzcp(var11);
                     var2.zza(var3, 24);
                  }
               }

               var2.zzcrs = var10;
            }

            var3.zzco(var6);
            break;
         case 32:
            var13 = new int[var5 = ads.zzb(var3, 32)];
            var7 = 0;

            for(var8 = 0; var8 < var5; ++var8) {
               if (var8 != 0) {
                  var3.zzLA();
               }

               var9 = var3.getPosition();
               switch(var15 = var3.zzLF()) {
               case 0:
               case 1:
               case 2:
                  var13[var7++] = var15;
                  break;
               default:
                  var3.zzcp(var9);
                  var2.zza(var3, var4);
               }
            }

            if (var7 == 0) {
               break;
            }

            if ((var8 = var2.zzcrt == null ? 0 : var2.zzcrt.length) == 0 && var7 == var13.length) {
               var2.zzcrt = var13;
               break;
            }

            var14 = new int[var8 + var7];
            if (var8 != 0) {
               System.arraycopy(var2.zzcrt, 0, var14, 0, var8);
            }

            System.arraycopy(var13, 0, var14, var8, var7);
            var2.zzcrt = var14;
            break;
         case 34:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;
            var8 = var3.getPosition();

            while(var3.zzLK() > 0) {
               switch(var3.zzLF()) {
               case 0:
               case 1:
               case 2:
                  ++var7;
               }
            }

            if (var7 != 0) {
               var3.zzcp(var8);
               var10 = new int[(var9 = var2.zzcrt == null ? 0 : var2.zzcrt.length) + var7];
               if (var9 != 0) {
                  System.arraycopy(var2.zzcrt, 0, var10, 0, var9);
               }

               while(var3.zzLK() > 0) {
                  var11 = var3.getPosition();
                  switch(var12 = var3.zzLF()) {
                  case 0:
                  case 1:
                  case 2:
                     var10[var9++] = var12;
                     break;
                  default:
                     var3.zzcp(var11);
                     var2.zza(var3, 32);
                  }
               }

               var2.zzcrt = var10;
            }

            var3.zzco(var6);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
