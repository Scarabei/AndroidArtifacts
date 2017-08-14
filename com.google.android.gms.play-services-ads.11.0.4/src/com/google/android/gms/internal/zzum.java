package com.google.android.gms.internal;

import java.util.Iterator;

final class zzum implements Runnable {
   // $FF: synthetic field
   private zzajm zzMV;
   // $FF: synthetic field
   private zzuk zzMU;

   zzum(zzuk var1, zzajm var2) {
      this.zzMU = var1;
      this.zzMV = var2;
      super();
   }

   public final void run() {
      Iterator var1 = zzuk.zze(this.zzMU).keySet().iterator();

      while(var1.hasNext()) {
         zzajm var2;
         if ((var2 = (zzajm)var1.next()) != this.zzMV) {
            ((zzue)zzuk.zze(this.zzMU).get(var2)).cancel();
         }
      }

   }
}
