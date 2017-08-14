package com.google.android.gms.internal;

import java.io.IOException;

public final class zzbd extends adj {
   public Long zzcx = null;
   private String zzcH = null;
   private byte[] zzcI = null;

   public zzbd() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcx != null) {
         var1.zzb(1, this.zzcx.longValue());
      }

      if (this.zzcH != null) {
         var1.zzl(3, this.zzcH);
      }

      if (this.zzcI != null) {
         var1.zzb(4, this.zzcI);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcx != null) {
         var1 += adh.zze(1, this.zzcx.longValue());
      }

      if (this.zzcH != null) {
         var1 += adh.zzm(3, this.zzcH);
      }

      if (this.zzcI != null) {
         var1 += adh.zzc(4, this.zzcI);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzbd var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzcx = var3.zzLG();
            break;
         case 26:
            var2.zzcH = var3.readString();
            break;
         case 34:
            var2.zzcI = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
