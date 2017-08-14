package com.google.android.gms.internal;

import java.io.IOException;

public final class zzav extends adj {
   public Integer zzaS;

   public zzav() {
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzaS != null) {
         var1.zzr(27, this.zzaS.intValue());
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzaS != null) {
         var1 += adh.zzs(27, this.zzaS.intValue());
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      zzav var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 216:
            int var5 = var3.getPosition();
            int var6;
            switch(var6 = var3.zzLF()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
               var2.zzaS = var6;
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
