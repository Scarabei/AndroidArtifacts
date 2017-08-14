package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbo extends adj {
   private static volatile zzbo[] zzld;
   public int[] zzle;
   public int[] zzlf;
   public int[] zzlg;
   public int[] zzlh;
   public int[] zzli;
   public int[] zzlj;
   public int[] zzlk;
   public int[] zzll;
   public int[] zzlm;
   public int[] zzln;

   public static zzbo[] zzs() {
      if (zzld == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzld == null) {
               zzld = new zzbo[0];
            }
         }
      }

      return zzld;
   }

   public zzbo() {
      this.zzle = ads.zzcsC;
      this.zzlf = ads.zzcsC;
      this.zzlg = ads.zzcsC;
      this.zzlh = ads.zzcsC;
      this.zzli = ads.zzcsC;
      this.zzlj = ads.zzcsC;
      this.zzlk = ads.zzcsC;
      this.zzll = ads.zzcsC;
      this.zzlm = ads.zzcsC;
      this.zzln = ads.zzcsC;
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbo)) {
         return false;
      } else {
         zzbo var2 = (zzbo)var1;
         if (!adn.equals(this.zzle, var2.zzle)) {
            return false;
         } else if (!adn.equals(this.zzlf, var2.zzlf)) {
            return false;
         } else if (!adn.equals(this.zzlg, var2.zzlg)) {
            return false;
         } else if (!adn.equals(this.zzlh, var2.zzlh)) {
            return false;
         } else if (!adn.equals(this.zzli, var2.zzli)) {
            return false;
         } else if (!adn.equals(this.zzlj, var2.zzlj)) {
            return false;
         } else if (!adn.equals(this.zzlk, var2.zzlk)) {
            return false;
         } else if (!adn.equals(this.zzll, var2.zzll)) {
            return false;
         } else if (!adn.equals(this.zzlm, var2.zzlm)) {
            return false;
         } else if (!adn.equals(this.zzln, var2.zzln)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((((((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzle)) * 31 + adn.hashCode(this.zzlf)) * 31 + adn.hashCode(this.zzlg)) * 31 + adn.hashCode(this.zzlh)) * 31 + adn.hashCode(this.zzli)) * 31 + adn.hashCode(this.zzlj)) * 31 + adn.hashCode(this.zzlk)) * 31 + adn.hashCode(this.zzll)) * 31 + adn.hashCode(this.zzlm)) * 31 + adn.hashCode(this.zzln)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      int var2;
      if (this.zzle != null && this.zzle.length > 0) {
         for(var2 = 0; var2 < this.zzle.length; ++var2) {
            var1.zzr(1, this.zzle[var2]);
         }
      }

      if (this.zzlf != null && this.zzlf.length > 0) {
         for(var2 = 0; var2 < this.zzlf.length; ++var2) {
            var1.zzr(2, this.zzlf[var2]);
         }
      }

      if (this.zzlg != null && this.zzlg.length > 0) {
         for(var2 = 0; var2 < this.zzlg.length; ++var2) {
            var1.zzr(3, this.zzlg[var2]);
         }
      }

      if (this.zzlh != null && this.zzlh.length > 0) {
         for(var2 = 0; var2 < this.zzlh.length; ++var2) {
            var1.zzr(4, this.zzlh[var2]);
         }
      }

      if (this.zzli != null && this.zzli.length > 0) {
         for(var2 = 0; var2 < this.zzli.length; ++var2) {
            var1.zzr(5, this.zzli[var2]);
         }
      }

      if (this.zzlj != null && this.zzlj.length > 0) {
         for(var2 = 0; var2 < this.zzlj.length; ++var2) {
            var1.zzr(6, this.zzlj[var2]);
         }
      }

      if (this.zzlk != null && this.zzlk.length > 0) {
         for(var2 = 0; var2 < this.zzlk.length; ++var2) {
            var1.zzr(7, this.zzlk[var2]);
         }
      }

      if (this.zzll != null && this.zzll.length > 0) {
         for(var2 = 0; var2 < this.zzll.length; ++var2) {
            var1.zzr(8, this.zzll[var2]);
         }
      }

      if (this.zzlm != null && this.zzlm.length > 0) {
         for(var2 = 0; var2 < this.zzlm.length; ++var2) {
            var1.zzr(9, this.zzlm[var2]);
         }
      }

      if (this.zzln != null && this.zzln.length > 0) {
         for(var2 = 0; var2 < this.zzln.length; ++var2) {
            var1.zzr(10, this.zzln[var2]);
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      int var2;
      int var3;
      int var4;
      if (this.zzle != null && this.zzle.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzle.length; ++var3) {
            var4 = this.zzle[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzle.length;
      }

      if (this.zzlf != null && this.zzlf.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlf.length; ++var3) {
            var4 = this.zzlf[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlf.length;
      }

      if (this.zzlg != null && this.zzlg.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlg.length; ++var3) {
            var4 = this.zzlg[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlg.length;
      }

      if (this.zzlh != null && this.zzlh.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlh.length; ++var3) {
            var4 = this.zzlh[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlh.length;
      }

      if (this.zzli != null && this.zzli.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzli.length; ++var3) {
            var4 = this.zzli[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzli.length;
      }

      if (this.zzlj != null && this.zzlj.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlj.length; ++var3) {
            var4 = this.zzlj[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlj.length;
      }

      if (this.zzlk != null && this.zzlk.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlk.length; ++var3) {
            var4 = this.zzlk[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlk.length;
      }

      if (this.zzll != null && this.zzll.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzll.length; ++var3) {
            var4 = this.zzll[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzll.length;
      }

      if (this.zzlm != null && this.zzlm.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzlm.length; ++var3) {
            var4 = this.zzlm[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzlm.length;
      }

      if (this.zzln != null && this.zzln.length > 0) {
         var2 = 0;

         for(var3 = 0; var3 < this.zzln.length; ++var3) {
            var4 = this.zzln[var3];
            var2 += adh.zzcr(var4);
         }

         var1 = var1 + var2 + 1 * this.zzln.length;
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbo var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         int var7;
         int var8;
         int var9;
         int[] var10;
         int[] var11;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 9:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 17:
         case 19:
         case 20:
         case 21:
         case 22:
         case 23:
         case 25:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 33:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 41:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 49:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 57:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 65:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 73:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 81:
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         case 8:
            var5 = ads.zzb(var3, 8);
            var11 = new int[(var6 = var2.zzle == null ? 0 : var2.zzle.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzle, 0, var11, 0, var6);
            }
            break;
         case 10:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzle == null ? 0 : var2.zzle.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzle, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzle = var10;
            var3.zzco(var6);
            continue;
         case 16:
            var5 = ads.zzb(var3, 16);
            var11 = new int[(var6 = var2.zzlf == null ? 0 : var2.zzlf.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlf, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlf = var11;
            continue;
         case 18:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlf == null ? 0 : var2.zzlf.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlf, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlf = var10;
            var3.zzco(var6);
            continue;
         case 24:
            var5 = ads.zzb(var3, 24);
            var11 = new int[(var6 = var2.zzlg == null ? 0 : var2.zzlg.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlg, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlg = var11;
            continue;
         case 26:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlg == null ? 0 : var2.zzlg.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlg, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlg = var10;
            var3.zzco(var6);
            continue;
         case 32:
            var5 = ads.zzb(var3, 32);
            var11 = new int[(var6 = var2.zzlh == null ? 0 : var2.zzlh.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlh, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlh = var11;
            continue;
         case 34:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlh == null ? 0 : var2.zzlh.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlh, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlh = var10;
            var3.zzco(var6);
            continue;
         case 40:
            var5 = ads.zzb(var3, 40);
            var11 = new int[(var6 = var2.zzli == null ? 0 : var2.zzli.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzli, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzli = var11;
            continue;
         case 42:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzli == null ? 0 : var2.zzli.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzli, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzli = var10;
            var3.zzco(var6);
            continue;
         case 48:
            var5 = ads.zzb(var3, 48);
            var11 = new int[(var6 = var2.zzlj == null ? 0 : var2.zzlj.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlj, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlj = var11;
            continue;
         case 50:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlj == null ? 0 : var2.zzlj.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlj, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlj = var10;
            var3.zzco(var6);
            continue;
         case 56:
            var5 = ads.zzb(var3, 56);
            var11 = new int[(var6 = var2.zzlk == null ? 0 : var2.zzlk.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlk, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlk = var11;
            continue;
         case 58:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlk == null ? 0 : var2.zzlk.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlk, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlk = var10;
            var3.zzco(var6);
            continue;
         case 64:
            var5 = ads.zzb(var3, 64);
            var11 = new int[(var6 = var2.zzll == null ? 0 : var2.zzll.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzll, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzll = var11;
            continue;
         case 66:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzll == null ? 0 : var2.zzll.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzll, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzll = var10;
            var3.zzco(var6);
            continue;
         case 72:
            var5 = ads.zzb(var3, 72);
            var11 = new int[(var6 = var2.zzlm == null ? 0 : var2.zzlm.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzlm, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzlm = var11;
            continue;
         case 74:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzlm == null ? 0 : var2.zzlm.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzlm, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzlm = var10;
            var3.zzco(var6);
            continue;
         case 80:
            var5 = ads.zzb(var3, 80);
            var11 = new int[(var6 = var2.zzln == null ? 0 : var2.zzln.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzln, 0, var11, 0, var6);
            }

            while(var6 < var11.length - 1) {
               var11[var6] = var3.zzLF();
               var3.zzLA();
               ++var6;
            }

            var11[var6] = var3.zzLF();
            var2.zzln = var11;
            continue;
         case 82:
            var5 = var3.zzLF();
            var6 = var3.zzcn(var5);
            var7 = 0;

            for(var8 = var3.getPosition(); var3.zzLK() > 0; ++var7) {
               var3.zzLF();
            }

            var3.zzcp(var8);
            var10 = new int[(var9 = var2.zzln == null ? 0 : var2.zzln.length) + var7];
            if (var9 != 0) {
               System.arraycopy(var2.zzln, 0, var10, 0, var9);
            }

            while(var9 < var10.length) {
               var10[var9] = var3.zzLF();
               ++var9;
            }

            var2.zzln = var10;
            var3.zzco(var6);
            continue;
         }

         while(var6 < var11.length - 1) {
            var11[var6] = var3.zzLF();
            var3.zzLA();
            ++var6;
         }

         var11[var6] = var3.zzLF();
         var2.zzle = var11;
      }
   }
}
