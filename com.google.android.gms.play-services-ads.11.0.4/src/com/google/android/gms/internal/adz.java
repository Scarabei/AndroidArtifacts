package com.google.android.gms.internal;

import java.io.IOException;

public final class adz extends adj {
   private Integer zzctj = null;
   private byte[] zzctk = null;
   private byte[] zzctg = null;

   public adz() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzctj != null) {
         var1.zzr(1, this.zzctj.intValue());
      }

      if (this.zzctk != null) {
         var1.zzb(2, this.zzctk);
      }

      if (this.zzctg != null) {
         var1.zzb(3, this.zzctg);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzctj != null) {
         var1 += adh.zzs(1, this.zzctj.intValue());
      }

      if (this.zzctk != null) {
         var1 += adh.zzc(2, this.zzctk);
      }

      if (this.zzctg != null) {
         var1 += adh.zzc(3, this.zzctg);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adz var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzctj = var3.zzLC();
            break;
         case 18:
            var2.zzctk = var3.readBytes();
            break;
         case 26:
            var2.zzctg = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
