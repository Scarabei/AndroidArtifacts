package com.google.android.gms.internal;

import java.io.IOException;

public final class acz extends adj {
   private int zzcqq = 0;
   private long zzcqr = 0L;
   private int zzcrC = 0;

   public acz() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acz)) {
         return false;
      } else {
         acz var2 = (acz)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (this.zzcrC != var2.zzcrC) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + this.zzcrC) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(2, this.zzcqr);
      }

      if (this.zzcrC != 0) {
         var1.zzr(3, this.zzcrC);
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

      if (this.zzcrC != 0) {
         var1 += adh.zzs(3, this.zzcrC);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acz var2 = this;

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
            var2.zzcqr = var3.zzLG();
            break;
         case 24:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcrC = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
