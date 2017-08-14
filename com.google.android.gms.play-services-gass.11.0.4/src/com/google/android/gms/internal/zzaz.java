package com.google.android.gms.internal;

import java.io.IOException;

public final class zzaz extends adj {
   private Long zzbM = null;
   private Long zzbN = null;
   public Long zzcu = null;
   public Long zzcv = null;
   public Long zzcw = null;

   public zzaz() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzbM != null) {
         var1.zzb(1, this.zzbM.longValue());
      }

      if (this.zzbN != null) {
         var1.zzb(2, this.zzbN.longValue());
      }

      if (this.zzcu != null) {
         var1.zzb(3, this.zzcu.longValue());
      }

      if (this.zzcv != null) {
         var1.zzb(4, this.zzcv.longValue());
      }

      if (this.zzcw != null) {
         var1.zzb(5, this.zzcw.longValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzbM != null) {
         var1 += adh.zze(1, this.zzbM.longValue());
      }

      if (this.zzbN != null) {
         var1 += adh.zze(2, this.zzbN.longValue());
      }

      if (this.zzcu != null) {
         var1 += adh.zze(3, this.zzcu.longValue());
      }

      if (this.zzcv != null) {
         var1 += adh.zze(4, this.zzcv.longValue());
      }

      if (this.zzcw != null) {
         var1 += adh.zze(5, this.zzcw.longValue());
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzaz var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var2.zzbM = var3.zzLG();
            break;
         case 16:
            var2.zzbN = var3.zzLG();
            break;
         case 24:
            var2.zzcu = var3.zzLG();
            break;
         case 32:
            var2.zzcv = var3.zzLG();
            break;
         case 40:
            var2.zzcw = var3.zzLG();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
