package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbc extends adj {
   public byte[] data = null;
   public byte[] zzcE = null;
   public byte[] zzcF = null;
   public byte[] zzcG = null;

   public zzbc() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.data != null) {
         var1.zzb(1, this.data);
      }

      if (this.zzcE != null) {
         var1.zzb(2, this.zzcE);
      }

      if (this.zzcF != null) {
         var1.zzb(3, this.zzcF);
      }

      if (this.zzcG != null) {
         var1.zzb(4, this.zzcG);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.data != null) {
         var1 += adh.zzc(1, this.data);
      }

      if (this.zzcE != null) {
         var1 += adh.zzc(2, this.zzcE);
      }

      if (this.zzcF != null) {
         var1 += adh.zzc(3, this.zzcF);
      }

      if (this.zzcG != null) {
         var1 += adh.zzc(4, this.zzcG);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbc var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.data = var3.readBytes();
            break;
         case 18:
            var2.zzcE = var3.readBytes();
            break;
         case 26:
            var2.zzcF = var3.readBytes();
            break;
         case 34:
            var2.zzcG = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
