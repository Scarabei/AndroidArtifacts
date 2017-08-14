package com.google.android.gms.internal;

import java.io.IOException;

public final class acr extends adj {
   private String zzcrj = "";
   private String zzcrk = "";
   private String zzcrl = "";

   public acr() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acr)) {
         return false;
      } else {
         acr var2 = (acr)var1;
         if (this.zzcrj == null) {
            if (var2.zzcrj != null) {
               return false;
            }
         } else if (!this.zzcrj.equals(var2.zzcrj)) {
            return false;
         }

         if (this.zzcrk == null) {
            if (var2.zzcrk != null) {
               return false;
            }
         } else if (!this.zzcrk.equals(var2.zzcrk)) {
            return false;
         }

         if (this.zzcrl == null) {
            if (var2.zzcrl != null) {
               return false;
            }
         } else if (!this.zzcrl.equals(var2.zzcrl)) {
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
      return ((((527 + this.getClass().getName().hashCode()) * 31 + (this.zzcrj == null ? 0 : this.zzcrj.hashCode())) * 31 + (this.zzcrk == null ? 0 : this.zzcrk.hashCode())) * 31 + (this.zzcrl == null ? 0 : this.zzcrl.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcrj != null && !this.zzcrj.equals("")) {
         var1.zzl(1, this.zzcrj);
      }

      if (this.zzcrk != null && !this.zzcrk.equals("")) {
         var1.zzl(2, this.zzcrk);
      }

      if (this.zzcrl != null && !this.zzcrl.equals("")) {
         var1.zzl(3, this.zzcrl);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcrj != null && !this.zzcrj.equals("")) {
         var1 += adh.zzm(1, this.zzcrj);
      }

      if (this.zzcrk != null && !this.zzcrk.equals("")) {
         var1 += adh.zzm(2, this.zzcrk);
      }

      if (this.zzcrl != null && !this.zzcrl.equals("")) {
         var1 += adh.zzm(3, this.zzcrl);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acr var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzcrj = var3.readString();
            break;
         case 18:
            var2.zzcrk = var3.readString();
            break;
         case 26:
            var2.zzcrl = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
