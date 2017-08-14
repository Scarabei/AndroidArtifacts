package com.google.android.gms.internal;

import java.io.IOException;

public final class hg extends adj {
   private static volatile hg[] zzbTK;
   public int type = 1;
   public hh zzbTL = null;

   public static hg[] zzEb() {
      if (zzbTK == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzbTK == null) {
               zzbTK = new hg[0];
            }
         }
      }

      return zzbTK;
   }

   public hg() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof hg)) {
         return false;
      } else {
         hg var2 = (hg)var1;
         if (this.type != var2.type) {
            return false;
         } else {
            if (this.zzbTL == null) {
               if (var2.zzbTL != null) {
                  return false;
               }
            } else if (!this.zzbTL.equals(var2.zzbTL)) {
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
      return (((527 + this.getClass().getName().hashCode()) * 31 + this.type) * 31 + (this.zzbTL == null ? 0 : this.zzbTL.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.type);
      if (this.zzbTL != null) {
         var1.zza(2, this.zzbTL);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzs(1, this.type);
      if (this.zzbTL != null) {
         var1 += adh.zzb(2, this.zzbTL);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      hg var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLF()) {
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
               var2.type = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 18:
            if (var2.zzbTL == null) {
               var2.zzbTL = new hh();
            }

            var3.zza(var2.zzbTL);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
