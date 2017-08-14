package com.google.android.gms.plus.internal;

import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbaz;

final class zzj extends zza {
   private final zzbaz zzbiT;

   public zzj(zzbaz var1) {
      this.zzbiT = var1;
   }

   public final void zza(DataHolder var1, String var2) {
      PendingIntent var3 = null;
      if (var1.zzqN() != null) {
         var3 = (PendingIntent)var1.zzqN().getParcelable("pendingIntent");
      }

      Status var4;
      if (!(var4 = new Status(var1.getStatusCode(), (String)null, var3)).isSuccess() && var1 != null) {
         if (!var1.isClosed()) {
            var1.close();
         }

         var1 = null;
      }

      this.zzbiT.setResult(new zzi(var4, var1, var2));
   }
}
