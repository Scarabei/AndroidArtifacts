package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbp extends adj {
   private static volatile zzbp[] zzlx;
   public String name = "";
   private zzbr zzly = null;
   public zzbl zzlz = null;

   public static zzbp[] zzt() {
      if (zzlx == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzlx == null) {
               zzlx = new zzbp[0];
            }
         }
      }

      return zzlx;
   }

   public zzbp() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof zzbp)) {
         return false;
      } else {
         zzbp var2 = (zzbp)var1;
         if (this.name == null) {
            if (var2.name != null) {
               return false;
            }
         } else if (!this.name.equals(var2.name)) {
            return false;
         }

         if (this.zzly == null) {
            if (var2.zzly != null) {
               return false;
            }
         } else if (!this.zzly.equals(var2.zzly)) {
            return false;
         }

         if (this.zzlz == null) {
            if (var2.zzlz != null) {
               return false;
            }
         } else if (!this.zzlz.equals(var2.zzlz)) {
            return false;
         }

         if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + (this.name == null ? 0 : this.name.hashCode())) * 31 + (this.zzly == null ? 0 : this.zzly.hashCode())) * 31 + (this.zzlz == null ? 0 : this.zzlz.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.name != null && !this.name.equals("")) {
         var1.zzl(1, this.name);
      }

      if (this.zzly != null) {
         var1.zza(2, this.zzly);
      }

      if (this.zzlz != null) {
         var1.zza(3, this.zzlz);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.name != null && !this.name.equals("")) {
         var1 += adh.zzm(1, this.name);
      }

      if (this.zzly != null) {
         var1 += adh.zzb(2, this.zzly);
      }

      if (this.zzlz != null) {
         var1 += adh.zzb(3, this.zzlz);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbp var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.name = var3.readString();
            break;
         case 18:
            if (var2.zzly == null) {
               var2.zzly = new zzbr();
            }

            var3.zza(var2.zzly);
            break;
         case 26:
            if (var2.zzlz == null) {
               var2.zzlz = new zzbl();
            }

            var3.zza(var2.zzlz);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
