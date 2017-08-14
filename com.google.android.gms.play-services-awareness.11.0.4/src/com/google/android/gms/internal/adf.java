package com.google.android.gms.internal;

import java.io.IOException;

public final class adf extends adj {
   private int zzcqq = 0;
   private long zzcqr = 0L;
   private float zzcrK = 0.0F;
   private int zzcrL = 0;

   public adf() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof adf)) {
         return false;
      } else {
         adf var2 = (adf)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (Float.floatToIntBits(this.zzcrK) != Float.floatToIntBits(var2.zzcrK)) {
            return false;
         } else if (this.zzcrL != var2.zzcrL) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + Float.floatToIntBits(this.zzcrK)) * 31 + this.zzcrL) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(2, this.zzcqr);
      }

      if (Float.floatToIntBits(this.zzcrK) != Float.floatToIntBits(0.0F)) {
         var1.zzc(3, this.zzcrK);
      }

      if (this.zzcrL != 0) {
         var1.zzr(4, this.zzcrL);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1 += adh.zze(2, this.zzcqr);
      }

      if (Float.floatToIntBits(this.zzcrK) != Float.floatToIntBits(0.0F)) {
         var1 += adh.zzct(3) + 4;
      }

      if (this.zzcrL != 0) {
         var1 += adh.zzs(4, this.zzcrL);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adf var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
            case 3:
               var2.zzcqq = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 16:
            var2.zzcqr = var3.zzLG();
            break;
         case 29:
            var2.zzcrK = Float.intBitsToFloat(var3.zzLH());
            break;
         case 32:
            var2.zzcrL = var3.zzLF();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
