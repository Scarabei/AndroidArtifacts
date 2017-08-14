package com.google.android.gms.internal;

import java.io.IOException;

public final class adv extends adj {
   private static volatile adv[] zzcsY;
   public byte[] zzcsZ = null;
   public byte[] zzcnR = null;

   public static adv[] zzLW() {
      if (zzcsY == null) {
         Object var0 = adn.zzcsw;
         synchronized(adn.zzcsw) {
            if (zzcsY == null) {
               zzcsY = new adv[0];
            }
         }
      }

      return zzcsY;
   }

   public adv() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      var1.zzb(1, this.zzcsZ);
      if (this.zzcnR != null) {
         var1.zzb(2, this.zzcnR);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn() + adh.zzc(1, this.zzcsZ);
      if (this.zzcnR != null) {
         var1 += adh.zzc(2, this.zzcnR);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adv var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzcsZ = var3.readBytes();
            break;
         case 18:
            var2.zzcnR = var3.readBytes();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
