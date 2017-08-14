package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.List;

final class zzap implements zzaq {
   // $FF: synthetic field
   private DataLayer zzbEl;

   zzap(DataLayer var1) {
      this.zzbEl = var1;
      super();
   }

   public final void zzJ(List var1) {
      Iterator var2 = var1.iterator();

      while(var2.hasNext()) {
         DataLayer.zza var3 = (DataLayer.zza)var2.next();
         DataLayer.zza(this.zzbEl, DataLayer.zzn(var3.zzBN, var3.mValue));
      }

      DataLayer.zza(this.zzbEl).countDown();
   }
}
