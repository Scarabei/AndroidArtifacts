package com.google.android.gms.wearable.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzbaz;
import java.lang.ref.WeakReference;
import java.util.Map;

final class zzdq extends zzfc {
   private WeakReference zzbSR;
   private WeakReference zzbSS;

   zzdq(Map var1, Object var2, zzbaz var3) {
      super(var3);
      this.zzbSR = new WeakReference(var1);
      this.zzbSS = new WeakReference(var2);
   }

   public final void zza(Status var1) {
      Map var2 = (Map)this.zzbSR.get();
      Object var3 = this.zzbSS.get();
      if (!var1.getStatus().isSuccess() && var2 != null && var3 != null) {
         synchronized(var2) {
            zzga var5;
            if ((var5 = (zzga)var2.remove(var3)) != null) {
               var5.clear();
            }
         }
      }

      this.zzR(var1);
   }
}
