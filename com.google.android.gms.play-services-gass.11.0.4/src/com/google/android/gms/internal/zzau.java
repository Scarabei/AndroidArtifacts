package com.google.android.gms.internal;

import java.io.IOException;

public final class zzau extends adj {
   public zzav zzaQ = null;
   public zzaw zzaR = null;

   public zzau() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzaQ != null) {
         var1.zza(1, this.zzaQ);
      }

      if (this.zzaR != null) {
         var1.zza(2, this.zzaR);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzaQ != null) {
         var1 += adh.zzb(1, this.zzaQ);
      }

      if (this.zzaR != null) {
         var1 += adh.zzb(2, this.zzaR);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzau var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            if (var2.zzaQ == null) {
               var2.zzaQ = new zzav();
            }

            var3.zza(var2.zzaQ);
            break;
         case 18:
            if (var2.zzaR == null) {
               var2.zzaR = new zzaw();
            }

            var3.zza(var2.zzaR);
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
