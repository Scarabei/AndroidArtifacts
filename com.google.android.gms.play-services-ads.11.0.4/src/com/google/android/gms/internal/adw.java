package com.google.android.gms.internal;

import java.io.IOException;

public final class adw extends adj {
   private adx zzcta = null;
   public adv[] zzctb = adv.zzLW();
   private byte[] body = null;
   private byte[] zzctc = null;
   private Integer zzctd = null;

   public adw() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzcta != null) {
         var1.zza(1, this.zzcta);
      }

      if (this.zzctb != null && this.zzctb.length > 0) {
         for(int var2 = 0; var2 < this.zzctb.length; ++var2) {
            adv var3;
            if ((var3 = this.zzctb[var2]) != null) {
               var1.zza(2, var3);
            }
         }
      }

      if (this.body != null) {
         var1.zzb(3, this.body);
      }

      if (this.zzctc != null) {
         var1.zzb(4, this.zzctc);
      }

      if (this.zzctd != null) {
         var1.zzr(5, this.zzctd.intValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzcta != null) {
         var1 += adh.zzb(1, this.zzcta);
      }

      if (this.zzctb != null && this.zzctb.length > 0) {
         for(int var2 = 0; var2 < this.zzctb.length; ++var2) {
            adv var3;
            if ((var3 = this.zzctb[var2]) != null) {
               var1 += adh.zzb(2, var3);
            }
         }
      }

      if (this.body != null) {
         var1 += adh.zzc(3, this.body);
      }

      if (this.zzctc != null) {
         var1 += adh.zzc(4, this.zzctc);
      }

      if (this.zzctd != null) {
         var1 += adh.zzs(5, this.zzctd.intValue());
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adw var2 = this;

      while(true) {
         int var4;
         int var6;
         adv[] var7;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            if (var2.zzcta == null) {
               var2.zzcta = new adx();
            }

            var3.zza(var2.zzcta);
            continue;
         case 18:
            int var5 = ads.zzb(var3, 18);
            var7 = new adv[(var6 = var2.zzctb == null ? 0 : var2.zzctb.length) + var5];
            if (var6 != 0) {
               System.arraycopy(var2.zzctb, 0, var7, 0, var6);
            }
            break;
         case 26:
            var2.body = var3.readBytes();
            continue;
         case 34:
            var2.zzctc = var3.readBytes();
            continue;
         case 40:
            var2.zzctd = var3.zzLC();
            continue;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
            continue;
         }

         while(var6 < var7.length - 1) {
            var7[var6] = new adv();
            var3.zza(var7[var6]);
            var3.zzLA();
            ++var6;
         }

         var7[var6] = new adv();
         var3.zza(var7[var6]);
         var2.zzctb = var7;
      }
   }
}
