package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbl extends adj {
   public zzbr[] zzkJ = zzbr.zzu();
   public zzbr[] zzkK = zzbr.zzu();
   public zzbk[] zzkL = zzbk.zzq();

   public zzbl() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbl)) {
         return false;
      } else {
         zzbl var2 = (zzbl)var1;
         if (!adn.equals(this.zzkJ, var2.zzkJ)) {
            return false;
         } else if (!adn.equals(this.zzkK, var2.zzkK)) {
            return false;
         } else if (!adn.equals(this.zzkL, var2.zzkL)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzkJ)) * 31 + adn.hashCode(this.zzkK)) * 31 + adn.hashCode(this.zzkL)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      int var2;
      zzbr var3;
      if (this.zzkJ != null && this.zzkJ.length > 0) {
         for(var2 = 0; var2 < this.zzkJ.length; ++var2) {
            if ((var3 = this.zzkJ[var2]) != null) {
               var1.zza(1, var3);
            }
         }
      }

      if (this.zzkK != null && this.zzkK.length > 0) {
         for(var2 = 0; var2 < this.zzkK.length; ++var2) {
            if ((var3 = this.zzkK[var2]) != null) {
               var1.zza(2, var3);
            }
         }
      }

      if (this.zzkL != null && this.zzkL.length > 0) {
         for(var2 = 0; var2 < this.zzkL.length; ++var2) {
            zzbk var4;
            if ((var4 = this.zzkL[var2]) != null) {
               var1.zza(3, var4);
            }
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      int var2;
      zzbr var3;
      if (this.zzkJ != null && this.zzkJ.length > 0) {
         for(var2 = 0; var2 < this.zzkJ.length; ++var2) {
            if ((var3 = this.zzkJ[var2]) != null) {
               var1 += adh.zzb(1, var3);
            }
         }
      }

      if (this.zzkK != null && this.zzkK.length > 0) {
         for(var2 = 0; var2 < this.zzkK.length; ++var2) {
            if ((var3 = this.zzkK[var2]) != null) {
               var1 += adh.zzb(2, var3);
            }
         }
      }

      if (this.zzkL != null && this.zzkL.length > 0) {
         for(var2 = 0; var2 < this.zzkL.length; ++var2) {
            zzbk var4;
            if ((var4 = this.zzkL[var2]) != null) {
               var1 += adh.zzb(3, var4);
            }
         }
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbl var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         zzbr[] var8;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var5 = ads.zzb(var3, 10);
            var8 = new zzbr[(var6 = var2.zzkJ == null ? 0 : var2.zzkJ.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkJ, 0, var8, 0, var6);
            }
            break;
         case 18:
            var5 = ads.zzb(var3, 18);
            var8 = new zzbr[(var6 = var2.zzkK == null ? 0 : var2.zzkK.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkK, 0, var8, 0, var6);
            }

            while(var6 < var8.length - 1) {
               var8[var6] = new zzbr();
               var3.zza(var8[var6]);
               var3.zzLA();
               ++var6;
            }

            var8[var6] = new zzbr();
            var3.zza(var8[var6]);
            var2.zzkK = var8;
            continue;
         case 26:
            var5 = ads.zzb(var3, 26);
            zzbk[] var7 = new zzbk[(var6 = var2.zzkL == null ? 0 : var2.zzkL.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzkL, 0, var7, 0, var6);
            }

            while(var6 < var7.length - 1) {
               var7[var6] = new zzbk();
               var3.zza(var7[var6]);
               var3.zzLA();
               ++var6;
            }

            var7[var6] = new zzbk();
            var3.zza(var7[var6]);
            var2.zzkL = var7;
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var8.length - 1) {
            var8[var6] = new zzbr();
            var3.zza(var8[var6]);
            var3.zzLA();
            ++var6;
         }

         var8[var6] = new zzbr();
         var3.zza(var8[var6]);
         var2.zzkJ = var8;
      }
   }
}
