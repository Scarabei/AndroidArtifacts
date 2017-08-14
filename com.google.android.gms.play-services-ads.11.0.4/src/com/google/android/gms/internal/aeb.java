package com.google.android.gms.internal;

import java.io.IOException;

public final class aeb extends adj {
   private static volatile aeb[] zzctm;
   public Integer zzbuM = null;
   public String url = null;
   public adw zzctn = null;
   private ady zzcto = null;
   private Integer zzctp = null;
   private int[] zzctq;
   private String zzctr;
   public Integer zzcts;
   public String[] zzctt;

   public static aeb[] zzLX() {
      if (zzctm == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzctm == null) {
               zzctm = new aeb[0];
            }
         }
      }

      return zzctm;
   }

   public aeb() {
      this.zzctq = ads.zzcsC;
      this.zzctr = null;
      this.zzcts = null;
      this.zzctt = ads.EMPTY_STRING_ARRAY;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.zzbuM.intValue());
      if (this.url != null) {
         var1.zzl(2, this.url);
      }

      if (this.zzctn != null) {
         var1.zza(3, this.zzctn);
      }

      if (this.zzcto != null) {
         var1.zza(4, this.zzcto);
      }

      if (this.zzctp != null) {
         var1.zzr(5, this.zzctp.intValue());
      }

      int var2;
      if (this.zzctq != null && this.zzctq.length > 0) {
         for(var2 = 0; var2 < this.zzctq.length; ++var2) {
            var1.zzr(6, this.zzctq[var2]);
         }
      }

      if (this.zzctr != null) {
         var1.zzl(7, this.zzctr);
      }

      if (this.zzcts != null) {
         var1.zzr(8, this.zzcts.intValue());
      }

      if (this.zzctt != null && this.zzctt.length > 0) {
         for(var2 = 0; var2 < this.zzctt.length; ++var2) {
            String var3;
            if ((var3 = this.zzctt[var2]) != null) {
               var1.zzl(9, var3);
            }
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzs(1, this.zzbuM.intValue());
      if (this.url != null) {
         var1 += adh.zzm(2, this.url);
      }

      if (this.zzctn != null) {
         var1 += adh.zzb(3, this.zzctn);
      }

      if (this.zzcto != null) {
         var1 += adh.zzb(4, this.zzcto);
      }

      if (this.zzctp != null) {
         var1 += adh.zzs(5, this.zzctp.intValue());
      }

      int var2;
      int var3;
      int var4;
      if (this.zzctq != null && this.zzctq.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzctq.length; ++var3) {
            var4 = this.zzctq[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzctq.length;
      }

      if (this.zzctr != null) {
         var1 += adh.zzm(7, this.zzctr);
      }

      if (this.zzcts != null) {
         var1 += adh.zzs(8, this.zzcts.intValue());
      }

      if (this.zzctt != null && this.zzctt.length > 0) {
         var2 = 0;
         var3 = 0;

         for(var4 = 0; var4 < this.zzctt.length; ++var4) {
            String var5;
            if ((var5 = this.zzctt[var4]) != null) {
               ++var2;
               var3 += adh.zzhQ(var5);
            }
         }

         var1 = var1 + var3 + 1 * var2;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aeb var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int[] var12;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzbuM = var3.zzLC();
            continue;
         case 18:
            var2.url = var3.readString();
            continue;
         case 26:
            if (var2.zzctn == null) {
               var2.zzctn = new adw();
            }

            var3.zza(var2.zzctn);
            continue;
         case 34:
            if (var2.zzcto == null) {
               var2.zzcto = new ady();
            }

            var3.zza(var2.zzcto);
            continue;
         case 40:
            var2.zzctp = var3.zzLC();
            continue;
         case 48:
            var5 = ads.zzb(var3, 48);
            var12 = new int[(var6 = var2.zzctq == null ? 0 : var2.zzctq.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctq, 0, var12, 0, var6);
            }
            break;
         case 50:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            int var11 = 0;

            int var8;
            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var11) {
               var3.zzLC();
            }

            var3.zzcp(var8);
            int var9;
            int[] var10 = new int[(var9 = var2.zzctq == null ? 0 : var2.zzctq.length) + var11];
            if (var9 != 0) {
               System.arraycopy(var2.zzctq, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLC();
               ++var9;
            }

            var2.zzctq = var10;
            var3.zzco(var6);
            continue;
         case 58:
            var2.zzctr = var3.readString();
            continue;
         case 64:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLC()) {
            case 0:
            case 1:
            case 2:
            case 3:
               var2.zzcts = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 74:
            var5 = ads.zzb(var3, 74);
            String[] var7 = new String[(var6 = var2.zzctt == null ? 0 : var2.zzctt.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctt, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = var3.readString();
               var3.zzLA();
               ++var6;
            }

            var7[var6] = var3.readString();
            var2.zzctt = var7;
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var12.length - 1) {
            var12[var6] = var3.zzLC();
            var3.zzLA();
            ++var6;
         }

         var12[var6] = var3.zzLC();
         var2.zzctq = var12;
      }
   }
}
