package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbm extends adj {
   private static volatile zzbm[] zzkM;
   public int key = 0;
   public int value = 0;

   public static zzbm[] zzr() {
      if (zzkM == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzkM == null) {
               zzkM = new zzbm[0];
            }
         }
      }

      return zzkM;
   }

   public zzbm() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbm)) {
         return false;
      } else {
         zzbm var2 = (zzbm)var1;
         if (this.key != var2.key) {
            return false;
         } else if (this.value != var2.value) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((527 + this.getClass().getName().hashCode()) * 31 + this.key) * 31 + this.value) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzr(1, this.key);
      var1.zzr(2, this.value);
      super.zza(var1);
   }

   protected final int zzn() {
      return super.zzn() + adh.zzs(1, this.key) + adh.zzs(2, this.value);
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbm var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.key = var3.zzLF();
            break;
         case 16:
            var2.value = var3.zzLF();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
