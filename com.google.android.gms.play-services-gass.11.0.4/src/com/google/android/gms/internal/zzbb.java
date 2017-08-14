package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbb extends adj {
   public byte[] zzcC = null;
   public byte[] zzcD = null;

   public zzbb() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcC != null) {
         var1.zzb(1, this.zzcC);
      }

      if (this.zzcD != null) {
         var1.zzb(2, this.zzcD);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcC != null) {
         var1 += adh.zzc(1, this.zzcC);
      }

      if (this.zzcD != null) {
         var1 += adh.zzc(2, this.zzcD);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbb var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzcC = var3.readBytes();
            break;
         case 18:
            var2.zzcD = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
