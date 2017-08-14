package com.google.android.gms.internal;

import java.util.concurrent.Callable;

public final class zzdq implements Callable {
   private final zzdb zzpJ;
   private final zzax zzro;

   public zzdq(zzdb var1, zzax var2) {
      this.zzpJ = var1;
      this.zzro = var2;
   }

   private final Void zzV() throws Exception {
      if (this.zzpJ.zzL() != null) {
         this.zzpJ.zzL().get();
      }

      zzax var1;
      if ((var1 = this.zzpJ.zzK()) != null) {
         try {
            zzax var2 = this.zzro;
            synchronized(this.zzro) {
               adp.zza(this.zzro, adp.zzc(var1));
            }
         } catch (ado var5) {
            ;
         }
      }

      return null;
   }

   // $FF: synthetic method
   public final Object call() throws Exception {
      return this.zzV();
   }
}
