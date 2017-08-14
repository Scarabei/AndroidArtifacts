package com.google.android.gms.internal;

import java.io.IOException;

public final class acj extends adj {
   public int zzcqq = 0;
   public long zzcqr = 0L;
   public ack[] zzcqz = ack.zzLo();

   public acj() {
      this.zzcsx = -1;
   }

   public final boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof acj)) {
         return false;
      } else {
         acj var2 = (acj)var1;
         if (this.zzcqq != var2.zzcqq) {
            return false;
         } else if (this.zzcqr != var2.zzcqr) {
            return false;
         } else if (!adn.equals(this.zzcqz, var2.zzcqz)) {
            return false;
         } else if (this.zzcso != null && !this.zzcso.isEmpty()) {
            return this.zzcso.equals(var2.zzcso);
         } else {
            return var2.zzcso == null || var2.zzcso.isEmpty();
         }
      }
   }

   public final int hashCode() {
      return ((((527 + this.getClass().getName().hashCode()) * 31 + this.zzcqq) * 31 + (int)(this.zzcqr ^ this.zzcqr >>> 32)) * 31 + adn.hashCode(this.zzcqz)) * 31 + (this.zzcso != null && !this.zzcso.isEmpty() ? this.zzcso.hashCode() : 0);
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcqq != 0) {
         var1.zzr(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1.zzb(3, this.zzcqr);
      }

      if (this.zzcqz != null && this.zzcqz.length > 0) {
         for(int var2 = 0; var2 < this.zzcqz.length; ++var2) {
            ack var3;
            if ((var3 = this.zzcqz[var2]) != null) {
               var1.zza(4, var3);
            }
         }
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcqq != 0) {
         var1 += adh.zzs(1, this.zzcqq);
      }

      if (this.zzcqr != 0L) {
         var1 += adh.zze(3, this.zzcqr);
      }

      if (this.zzcqz != null && this.zzcqz.length > 0) {
         for(int var2 = 0; var2 < this.zzcqz.length; ++var2) {
            ack var3;
            if ((var3 = this.zzcqz[var2]) != null) {
               var1 += adh.zzb(4, var3);
            }
         }
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      acj var2 = this;

      while(true) {
         int var4;
         int var5;
         int var6;
         ack[] var7;
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
         case 24:
            var2.zzcqr = var3.zzLG();
            continue;
         case 34:
            var5 = ads.zzb(var3, 34);
            var7 = new ack[(var6 = var2.zzcqz == null ? 0 : var2.zzcqz.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzcqz, 0, var7, 0, var6);
            }
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new ack();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new ack();
         var3.zza(var7[var6]);
         var2.zzcqz = var7;
      }
   }
}
