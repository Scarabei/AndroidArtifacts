package com.google.android.gms.internal;

import java.io.IOException;

public final class ada extends adj {
   private String zzaLx = "";

   public ada() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ada)) {
         return false;
      } else {
         ada var2 = (ada)var1;
         if (this.zzaLx == null) {
            if (var2.zzaLx != null) {
               return false;
            }
         } else if (!this.zzaLx.equals(var2.zzaLx)) {
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
      return ((527 + this.getClass().getName().hashCode()) * 31 + (this.zzaLx == null ? 0 : this.zzaLx.hashCode())) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzaLx != null && !this.zzaLx.equals("")) {
         var1.zzl(1, this.zzaLx);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzaLx != null && !this.zzaLx.equals("")) {
         var1 += adh.zzm(1, this.zzaLx);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      ada var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzaLx = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
