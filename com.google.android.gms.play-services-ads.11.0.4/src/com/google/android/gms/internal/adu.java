package com.google.android.gms.internal;

import java.io.IOException;

public final class adu extends adj {
   public String zzXr = null;

   public adu() {
      this.zzcso = null;
      this.zzcsx = -1;
   }

   public final void zza(adh var1) throws IOException {
      if (this.zzXr != null) {
         var1.zzl(1, this.zzXr);
      }

      super.zza(var1);
   }

   protected final int zzn() {
      int var1 = super.zzn();
      if (this.zzXr != null) {
         var1 += adh.zzm(1, this.zzXr);
      }

      return var1;
   }

   // $FF: synthetic method
   public final adp zza(adg var1) throws IOException {
      adg var3 = var1;
      adu var2 = this;

      while(true) {
         int var4;
         switch(var4 = var3.zzLA()) {
         case 0:
            return var2;
         case 10:
            var2.zzXr = var3.readString();
            break;
         default:
            if (!var2.zza(var3, var4)) {
               return var2;
            }
         }
      }
   }
}
