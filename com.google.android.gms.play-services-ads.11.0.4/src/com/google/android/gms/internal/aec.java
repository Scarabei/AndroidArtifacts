package com.google.android.gms.internal;

import java.io.IOException;

public final class aec extends adj {
   public String zzctu = null;
   public Long zzctv = null;

   public aec() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzctu != null) {
         var1.zzl(1, this.zzctu);
      }

      if (this.zzctv != null) {
         var1.zzb(2, this.zzctv.longValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzctu != null) {
         var1 += adh.zzm(1, this.zzctu);
      }

      if (this.zzctv != null) {
         var1 += adh.zze(2, this.zzctv.longValue());
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aec var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzctu = var3.readString();
            break;
         case 16:
            var2.zzctv = var3.zzLB();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
