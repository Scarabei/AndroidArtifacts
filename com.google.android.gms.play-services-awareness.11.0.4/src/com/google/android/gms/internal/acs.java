package com.google.android.gms.internal;

import java.io.IOException;

public final class acs extends adj {
   public int zzcqq = 0;
   public int zzcrm = 0;
   public long zzcqr = 0L;
   public int zzcrn = 0;
   public int zzcro = 0;
   public double zzcrp = 0.0D;
   public double zzcrq = 0.0D;
   public long zzcrr = 0L;

   public acs() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acs)) {
         return false;
      } else {
         acs var2 = (acs)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcrm != var2.zzcrm) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (this.zzcrn != var2.zzcrn) {
            return false;
         } else if (this.zzcro != var2.zzcro) {
            return false;
         } else if (Double.doubleToLongBits(this.zzcrp) != Double.doubleToLongBits(var2.zzcrp)) {
            return false;
         } else if (Double.doubleToLongBits(this.zzcrq) != Double.doubleToLongBits(var2.zzcrq)) {
            return false;
         } else if (this.zzcrr != var2.zzcrr) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      int var1 = (((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + this.zzcrm) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + this.zzcrn) * 31 + this.zzcro;
      long var2 = Double.doubleToLongBits(this.zzcrp);
      var1 = var1 * 31 + (int)(var2 ^ var2 >>> 32);
      var2 = Double.doubleToLongBits(this.zzcrq);
      return ((var1 * 31 + (int)(var2 ^ var2 >>> 32)) * 31 + (int)(this.zzcrr ^ this.zzcrr >>> 32)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcrm != 0) {
         var1.zzr(2, this.zzcrm);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(3, this.zzcqr);
      }

      if (this.zzcrn != 0) {
         var1.zzr(4, this.zzcrn);
      }

      if (this.zzcro != 0) {
         var1.zzr(5, this.zzcro);
      }

      if (Double.doubleToLongBits(this.zzcrp) != Double.doubleToLongBits(0.0D)) {
         var1.zza(6, this.zzcrp);
      }

      if (Double.doubleToLongBits(this.zzcrq) != Double.doubleToLongBits(0.0D)) {
         var1.zza(7, this.zzcrq);
      }

      if (this.zzcrr != 0L) {
         var1.zzb(8, this.zzcrr);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcrm != 0) {
         var1 += adh.zzs(2, this.zzcrm);
      }

      if (this.zzcqr != 0L) {
         var1 += adh.zze(3, this.zzcqr);
      }

      if (this.zzcrn != 0) {
         var1 += adh.zzs(4, this.zzcrn);
      }

      if (this.zzcro != 0) {
         var1 += adh.zzs(5, this.zzcro);
      }

      if (Double.doubleToLongBits(this.zzcrp) != Double.doubleToLongBits(0.0D)) {
         var1 += adh.zzct(6) + 8;
      }

      if (Double.doubleToLongBits(this.zzcrq) != Double.doubleToLongBits(0.0D)) {
         var1 += adh.zzct(7) + 8;
      }

      if (this.zzcrr != 0L) {
         var1 += adh.zze(8, this.zzcrr);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acs var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 8:
            var5 = var3.getPosition();
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
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
               var2.zzcrm = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 24:
            var2.zzcqr = var3.zzLG();
            break;
         case 32:
            var2.zzcrn = var3.zzLF();
            break;
         case 40:
            var2.zzcro = var3.zzLF();
            break;
         case 49:
            var2.zzcrp = Double.longBitsToDouble(var3.zzLI());
            break;
         case 57:
            var2.zzcrq = Double.longBitsToDouble(var3.zzLI());
            break;
         case 64:
            var2.zzcrr = var3.zzLG();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
