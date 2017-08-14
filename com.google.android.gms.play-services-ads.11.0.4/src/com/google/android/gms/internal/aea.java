package com.google.android.gms.internal;

import java.io.IOException;

public final class aea extends adj {
   public Integer zzcsJ = null;
   public String mimeType = null;
   public byte[] zzctl = null;

   public aea() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcsJ != null) {
         var1.zzr(1, this.zzcsJ.intValue());
      }

      if (this.mimeType != null) {
         var1.zzl(2, this.mimeType);
      }

      if (this.zzctl != null) {
         var1.zzb(3, this.zzctl);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcsJ != null) {
         var1 += adh.zzs(1, this.zzcsJ.intValue());
      }

      if (this.mimeType != null) {
         var1 += adh.zzm(2, this.mimeType);
      }

      if (this.zzctl != null) {
         var1 += adh.zzc(3, this.zzctl);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aea var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLC()) {
            case 0:
            case 1:
               var2.zzcsJ = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 18:
            var2.mimeType = var3.readString();
            break;
         case 26:
            var2.zzctl = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
