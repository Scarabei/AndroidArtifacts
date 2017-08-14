package com.google.android.gms.internal;

import java.io.IOException;

public final class hf extends adj {
   private static volatile hf[] zzbTI;
   public String name = "";
   public hg zzbTJ = null;

   public static hf[] zzEa() {
      if (zzbTI == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzbTI == null) {
               zzbTI = new hf[0];
            }
         }
      }

      return zzbTI;
   }

   public hf() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof hf)) {
         return false;
      } else {
         hf var2 = (hf)var1;
         if (this.name == null) {
            if (var2.name != null) {
               return false;
            }
         } else if (!this.name.equals(var2.name)) {
            return false;
         }

         if (this.zzbTJ == null) {
            if (var2.zzbTJ != null) {
               return false;
            }
         } else if (!this.zzbTJ.equals(var2.zzbTJ)) {
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
      return (((527 + this.getClass().getName().hashCode()) * 31 + (this.name == null ? 0 : this.name.hashCode())) * 31 + (this.zzbTJ == null ? 0 : this.zzbTJ.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      var1.zzl(1, this.name);
      if (this.zzbTJ != null) {
         var1.zza(2, this.zzbTJ);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzm(1, this.name);
      if (this.zzbTJ != null) {
         var1 += adh.zzb(2, this.zzbTJ);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      hf var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.name = var3.readString();
            break;
         case 18:
            if (var2.zzbTJ == null) {
               var2.zzbTJ = new hg();
            }

            var3.zza(var2.zzbTJ);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
