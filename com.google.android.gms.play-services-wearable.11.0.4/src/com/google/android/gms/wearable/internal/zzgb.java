package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbdz;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataEventBuffer;

final class zzgb implements zzbdz {
   // $FF: synthetic field
   private DataHolder zzbRz;

   zzgb(DataHolder var1) {
      this.zzbRz = var1;
      super();
   }

   public final void zzpT() {
      this.zzbRz.close();
   }

   // $FF: synthetic method
   public final void zzq(Object var1) {
      DataApi.DataListener var3 = (DataApi.DataListener)var1;
      zzgb var2 = this;

      try {
         var3.onDataChanged(new DataEventBuffer(var2.zzbRz));
      } finally {
         this.zzbRz.close();
      }

   }
}
