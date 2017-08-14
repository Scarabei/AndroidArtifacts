package com.google.android.gms.internal;

import java.io.IOException;

public final class he extends adj {
   public hf[] zzbTH = hf.zzEa();

   public he() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof he)) {
         return false;
      } else {
         he var2 = (he)var1;
         if (!adn.equals(this.zzbTH, var2.zzbTH)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((527 + this.getClass().getName().hashCode()) * 31 + adn.hashCode(this.zzbTH)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzbTH != null && this.zzbTH.length > 0) {
         for(int var2 = 0; var2 < this.zzbTH.length; ++var2) {
            hf var3;
            if ((var3 = this.zzbTH[var2]) != null) {
               var1.zza(1, var3);
            }
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzbTH != null && this.zzbTH.length > 0) {
         for(int var2 = 0; var2 < this.zzbTH.length; ++var2) {
            hf var3;
            if ((var3 = this.zzbTH[var2]) != null) {
               var1 += adh.zzb(1, var3);
            }
         }
      }

      return var1;
   }

   public static he zzy(byte[] var0) throws ado {
      return (he)adp.zza(new he(), var0);
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      he var2 = this;

      while(true) {
         int var4;
         int var6;
         hf[] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            int var5 = ads.zzb(var3, 10);
            var7 = new hf[(var6 = var2.zzbTH == null ? 0 : var2.zzbTH.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzbTH, 0, var7, 0, var6);
            }
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new hf();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new hf();
         var3.zza(var7[var6]);
         var2.zzbTH = var7;
      }
   }
}
