package com.google.android.gms.internal;

import java.io.IOException;

public final class aci extends adj {
   public int zzcqq = 0;
   public long zzcqr = 0L;
   public int zzcqt = 0;
   public int zzcqu = 0;
   public int zzcqv = 0;
   public int zzcqw = 0;
   public int zzcqx = 0;
   public int zzcqy = 0;

   public aci() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof aci)) {
         return false;
      } else {
         aci var2 = (aci)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (this.zzcqt != var2.zzcqt) {
            return false;
         } else if (this.zzcqu != var2.zzcqu) {
            return false;
         } else if (this.zzcqv != var2.zzcqv) {
            return false;
         } else if (this.zzcqw != var2.zzcqw) {
            return false;
         } else if (this.zzcqx != var2.zzcqx) {
            return false;
         } else if (this.zzcqy != var2.zzcqy) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return (((((((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + this.zzcqt) * 31 + this.zzcqu) * 31 + this.zzcqv) * 31 + this.zzcqw) * 31 + this.zzcqx) * 31 + this.zzcqy) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(2, this.zzcqr);
      }

      if (this.zzcqt != 0) {
         var1.zzr(3, this.zzcqt);
      }

      if (this.zzcqu != 0) {
         var1.zzr(4, this.zzcqu);
      }

      if (this.zzcqv != 0) {
         var1.zzr(5, this.zzcqv);
      }

      if (this.zzcqw != 0) {
         var1.zzr(6, this.zzcqw);
      }

      if (this.zzcqx != 0) {
         var1.zzr(7, this.zzcqx);
      }

      if (this.zzcqy != 0) {
         var1.zzr(8, this.zzcqy);
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

      if (this.zzcqt != 0) {
         var1 += adh.zzs(3, this.zzcqt);
      }

      if (this.zzcqu != 0) {
         var1 += adh.zzs(4, this.zzcqu);
      }

      if (this.zzcqv != 0) {
         var1 += adh.zzs(5, this.zzcqv);
      }

      if (this.zzcqw != 0) {
         var1 += adh.zzs(6, this.zzcqw);
      }

      if (this.zzcqx != 0) {
         var1 += adh.zzs(7, this.zzcqx);
      }

      if (this.zzcqy != 0) {
         var1 += adh.zzs(8, this.zzcqy);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      aci var2 = this;

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
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
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
               var2.zzcqt = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 32:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqu = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 40:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqv = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 48:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqw = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 56:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqx = var6;
               continue;
            default:
               var3.zzcp(var5);
               var2.zza(var3, var4);
               continue;
            }
         case 64:
            var5 = var3.getPosition();
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
               var2.zzcqy = var6;
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
