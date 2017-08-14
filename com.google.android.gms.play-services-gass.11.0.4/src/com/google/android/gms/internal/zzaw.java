package com.google.android.gms.internal;

import java.io.IOException;

public final class zzaw extends adj {
   public String zzaT = null;
   private String zzaU = null;
   private String zzaV = null;
   private String zzaW = null;
   private String zzaX = null;

   public zzaw() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzaT != null) {
         var1.zzl(1, this.zzaT);
      }

      if (this.zzaU != null) {
         var1.zzl(2, this.zzaU);
      }

      if (this.zzaV != null) {
         var1.zzl(3, this.zzaV);
      }

      if (this.zzaW != null) {
         var1.zzl(4, this.zzaW);
      }

      if (this.zzaX != null) {
         var1.zzl(5, this.zzaX);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzaT != null) {
         var1 += adh.zzm(1, this.zzaT);
      }

      if (this.zzaU != null) {
         var1 += adh.zzm(2, this.zzaU);
      }

      if (this.zzaV != null) {
         var1 += adh.zzm(3, this.zzaV);
      }

      if (this.zzaW != null) {
         var1 += adh.zzm(4, this.zzaW);
      }

      if (this.zzaX != null) {
         var1 += adh.zzm(5, this.zzaX);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzaw var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzaT = var3.readString();
            break;
         case 18:
            var2.zzaU = var3.readString();
            break;
         case 26:
            var2.zzaV = var3.readString();
            break;
         case 34:
            var2.zzaW = var3.readString();
            break;
         case 42:
            var2.zzaX = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
