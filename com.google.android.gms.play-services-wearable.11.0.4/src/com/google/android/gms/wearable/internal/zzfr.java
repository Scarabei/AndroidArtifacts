package com.google.android.gms.wearable.internal;

import com.google.android.gms.internal.zzbaz;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.FutureTask;

final class zzfr extends zzfc {
   private final List zzJO;

   zzfr(zzbaz var1, List var2) {
      super(var1);
      this.zzJO = var2;
   }

   public final void zza(zzem var1) {
      this.zzR(new zzbs(zzev.zzaY(var1.statusCode), var1.zzbSP));
      if (var1.statusCode != 0) {
         Iterator var2 = this.zzJO.iterator();

         while(var2.hasNext()) {
            ((FutureTask)var2.next()).cancel(true);
         }
      }

   }
}
